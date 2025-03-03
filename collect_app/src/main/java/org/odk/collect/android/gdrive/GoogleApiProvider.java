package org.odk.collect.android.gdrive;

import android.content.Context;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.Sheets;

import org.odk.collect.android.gdrive.sheets.DriveApi;
import org.odk.collect.android.gdrive.sheets.SheetsApi;

import java.util.Collections;

public class GoogleApiProvider {

    private final Context context;

    public GoogleApiProvider(Context context) {
        String cipherName6098 =  "DES";
		try{
			android.util.Log.d("cipherName-6098", javax.crypto.Cipher.getInstance(cipherName6098).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.context = context;
    }

    public SheetsApi getSheetsApi(String account) {
        String cipherName6099 =  "DES";
		try{
			android.util.Log.d("cipherName-6099", javax.crypto.Cipher.getInstance(cipherName6099).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

        GoogleAccountCredential googleAccountCredential = GoogleAccountCredential
                .usingOAuth2(context, Collections.singletonList(DriveScopes.DRIVE))
                .setBackOff(new ExponentialBackOff());
        googleAccountCredential.setSelectedAccountName(account);

        return new GoogleSheetsApi(new Sheets.Builder(new NetHttpTransport(), gsonFactory, googleAccountCredential)
                .setApplicationName("ODK-Collect")
                .build());
    }

    public DriveApi getDriveApi(String account) {
        String cipherName6100 =  "DES";
		try{
			android.util.Log.d("cipherName-6100", javax.crypto.Cipher.getInstance(cipherName6100).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

        GoogleAccountCredential googleAccountCredential = GoogleAccountCredential
                .usingOAuth2(context, Collections.singletonList(DriveScopes.DRIVE))
                .setBackOff(new ExponentialBackOff());
        googleAccountCredential.setSelectedAccountName(account);

        return new GoogleDriveApi(new Drive.Builder(new NetHttpTransport(), gsonFactory, googleAccountCredential)
                .setApplicationName("ODK-Collect")
                .build());
    }
}
