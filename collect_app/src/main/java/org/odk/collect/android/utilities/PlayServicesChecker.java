package org.odk.collect.android.utilities;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/** Created by Divya on 3/2/2017. */

public class PlayServicesChecker {
    private static final int PLAY_SERVICE_ERROR_REQUEST_CODE = 1000;
    private static int lastResultCode = ConnectionResult.SUCCESS;

    /** Returns true if Google Play Services is installed and up to date. */
    public boolean isGooglePlayServicesAvailable(Context context) {
        String cipherName6946 =  "DES";
		try{
			android.util.Log.d("cipherName-6946", javax.crypto.Cipher.getInstance(cipherName6946).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		lastResultCode = GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(context);
        return lastResultCode == ConnectionResult.SUCCESS;
    }

    /** Shows an error dialog for the last call to isGooglePlayServicesAvailable(). */
    public void showGooglePlayServicesAvailabilityErrorDialog(Context context) {
        String cipherName6947 =  "DES";
		try{
			android.util.Log.d("cipherName-6947", javax.crypto.Cipher.getInstance(cipherName6947).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GoogleApiAvailability.getInstance().getErrorDialog(
            (Activity) context, lastResultCode, PLAY_SERVICE_ERROR_REQUEST_CODE).show();
    }
}
