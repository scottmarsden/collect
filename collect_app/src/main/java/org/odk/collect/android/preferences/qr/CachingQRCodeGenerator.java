package org.odk.collect.android.preferences.qr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.zxing.WriterException;

import org.json.JSONException;
import org.odk.collect.android.storage.StoragePathProvider;
import org.odk.collect.android.storage.StorageSubdirectory;
import org.odk.collect.android.utilities.FileUtils;
import org.odk.collect.android.utilities.QRCodeUtils;
import org.odk.collect.android.utilities.SharedPreferencesUtils;
import org.odk.collect.utilities.Consumer;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static org.odk.collect.android.preferences.AdminKeys.KEY_ADMIN_PW;
import static org.odk.collect.android.preferences.GeneralKeys.KEY_PASSWORD;

public class CachingQRCodeGenerator implements QRCodeGenerator {

    private static final String SETTINGS_MD5_FILE = ".collect-settings-hash";

    @Override
    public void generateQRCode(Consumer<String> callback) {
        String filePath = getQRCodeFilepath();

        if (new File(filePath).exists()) {
            callback.accept(filePath);
        } else {
            Collection<String> keys = new ArrayList<>();
            keys.add(KEY_ADMIN_PW);
            keys.add(KEY_PASSWORD);
            generateQRCode(keys)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(bitmap -> {
                        callback.accept(filePath);
                    }, Timber::e);
        }
    }

    @Override
    public Observable<Bitmap> generateQRCode(Collection<String> selectedPasswordKeys) {
        return Observable.create(emitter -> {
            Bitmap bitmap = getQRCode(selectedPasswordKeys);

            if (bitmap != null) {
                // Send the QRCode to the observer
                emitter.onNext(bitmap);
                // Send the task completion event
                emitter.onComplete();
            }
        });
    }

    private Bitmap getQRCode(Collection<String> selectedPasswordKeys) throws JSONException, NoSuchAlgorithmException, IOException, WriterException {
        String preferencesString = SharedPreferencesUtils.getJSONFromPreferences(selectedPasswordKeys);

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(preferencesString.getBytes());
        byte[] messageDigest = md.digest();

        boolean shouldWriteToDisk = true;
        Bitmap bitmap = null;

        // check if settings directory exists, if not then create one
        File writeDir = new File(new StoragePathProvider().getDirPath(StorageSubdirectory.SETTINGS));
        if (!writeDir.exists()) {
            if (!writeDir.mkdirs()) {
                Timber.e("Error creating directory " + writeDir.getAbsolutePath());
            }
        }

        File mdCacheFile = new File(getMd5CachePath());
        if (mdCacheFile.exists()) {
            byte[] cachedMessageDigest = FileUtils.read(mdCacheFile);

            /*
             * If the messageDigest generated from the preferences is equal to cachedMessageDigest
             * then don't generate QRCode and read the one saved in disk
             */
            if (Arrays.equals(messageDigest, cachedMessageDigest)) {
                Timber.i("Loading QRCode from the disk...");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                bitmap = FileUtils.getBitmap(getQRCodeFilepath(), options);
                shouldWriteToDisk = false;
            }
        }

        // If the file is not found in the disk or md5Hash not matched
        if (bitmap == null) {
            Timber.i("Generating QRCode...");
            final long time = System.currentTimeMillis();
            Bitmap bmp = QRCodeUtils.encode(preferencesString);
            Timber.i("QR Code generation took : %d ms", System.currentTimeMillis() - time);
            bitmap = bmp;
            shouldWriteToDisk = true;
        }

        // Save the QRCode to disk
        if (shouldWriteToDisk) {
            Timber.i("Saving QR Code to disk... : " + getQRCodeFilepath());
            FileUtils.saveBitmapToFile(bitmap, getQRCodeFilepath());

            FileUtils.write(mdCacheFile, messageDigest);
            Timber.i("Updated %s file contents", SETTINGS_MD5_FILE);
        }

        return bitmap;
    }

    private String getQRCodeFilepath() {
        return new StoragePathProvider().getDirPath(StorageSubdirectory.SETTINGS) + File.separator + "collect-settings.png";
    }

    private String getMd5CachePath() {
        return new StoragePathProvider().getDirPath(StorageSubdirectory.SETTINGS) + File.separator + SETTINGS_MD5_FILE;
    }
}
