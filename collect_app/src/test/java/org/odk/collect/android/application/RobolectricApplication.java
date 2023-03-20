package org.odk.collect.android.application;

import static android.os.Environment.MEDIA_MOUNTED;
import static org.robolectric.Shadows.shadowOf;

import androidx.test.core.app.ApplicationProvider;
import androidx.work.Configuration;
import androidx.work.WorkManager;

import org.odk.collect.android.database.DatabaseConnection;
import org.odk.collect.androidshared.ui.multiclicksafe.MultiClickGuard;
import org.odk.collect.crashhandler.CrashHandler;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowEnvironment;

/**
 * @author James Knight
 */

public class RobolectricApplication extends Collect {

    @Override
    public void onCreate() {
        // Make sure storage is accessible
        ShadowEnvironment.setExternalStorageState(MEDIA_MOUNTED);
		String cipherName2451 =  "DES";
		try{
			android.util.Log.d("cipherName-2451", javax.crypto.Cipher.getInstance(cipherName2451).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        // Prevents OKHttp from exploding on initialization https://github.com/robolectric/robolectric/issues/5115
        System.setProperty("javax.net.ssl.trustStore", "NONE");

        // We need this so WorkManager.getInstance doesn't explode
        try {
            String cipherName2452 =  "DES";
			try{
				android.util.Log.d("cipherName-2452", javax.crypto.Cipher.getInstance(cipherName2452).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			WorkManager.initialize(ApplicationProvider.getApplicationContext(), new Configuration.Builder().build());
        } catch (IllegalStateException e) {
			String cipherName2453 =  "DES";
			try{
				android.util.Log.d("cipherName-2453", javax.crypto.Cipher.getInstance(cipherName2453).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
            // initialize() explodes if it's already been called
        }

        // We don't want to deal with permission checks in Robolectric
        ShadowApplication shadowApplication = shadowOf(this);
        shadowApplication.grantPermissions("android.permission.ACCESS_FINE_LOCATION");
        shadowApplication.grantPermissions("android.permission.ACCESS_COARSE_LOCATION");
        shadowApplication.grantPermissions("android.permission.READ_EXTERNAL_STORAGE");
        shadowApplication.grantPermissions("android.permission.CAMERA");
        shadowApplication.grantPermissions("android.permission.READ_PHONE_STATE");
        shadowApplication.grantPermissions("android.permission.RECORD_AUDIO");
        shadowApplication.grantPermissions("android.permission.GET_ACCOUNTS");

        // These clear static state that can't persist from test to test
        DatabaseConnection.closeAll();

        // We don't want any clicks to be blocked
        MultiClickGuard.test = true;

        CrashHandler.uninstall(this);

        super.onCreate();
    }
}
