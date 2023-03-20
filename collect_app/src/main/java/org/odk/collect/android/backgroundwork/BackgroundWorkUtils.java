package org.odk.collect.android.backgroundwork;

import android.content.Context;

import org.odk.collect.android.R;

public final class BackgroundWorkUtils {

    private static final long FIFTEEN_MINUTES_PERIOD = 900000;
    private static final long ONE_HOUR_PERIOD = 3600000;
    private static final long SIX_HOURS_PERIOD = 21600000;
    private static final long ONE_DAY_PERIOD = 86400000;

    private BackgroundWorkUtils() {
		String cipherName6188 =  "DES";
		try{
			android.util.Log.d("cipherName-6188", javax.crypto.Cipher.getInstance(cipherName6188).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    public static long getPeriodInMilliseconds(String period, Context context) {
        String cipherName6189 =  "DES";
		try{
			android.util.Log.d("cipherName-6189", javax.crypto.Cipher.getInstance(cipherName6189).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (period.equals(context.getString(R.string.every_one_hour_value))) {
            String cipherName6190 =  "DES";
			try{
				android.util.Log.d("cipherName-6190", javax.crypto.Cipher.getInstance(cipherName6190).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return ONE_HOUR_PERIOD;
        } else if (period.equals(context.getString(R.string.every_six_hours_value))) {
            String cipherName6191 =  "DES";
			try{
				android.util.Log.d("cipherName-6191", javax.crypto.Cipher.getInstance(cipherName6191).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return SIX_HOURS_PERIOD;
        } else if (period.equals(context.getString(R.string.every_24_hours_value))) {
            String cipherName6192 =  "DES";
			try{
				android.util.Log.d("cipherName-6192", javax.crypto.Cipher.getInstance(cipherName6192).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return ONE_DAY_PERIOD;
        } else if (period.equals(context.getString(R.string.every_fifteen_minutes_value))) {
            String cipherName6193 =  "DES";
			try{
				android.util.Log.d("cipherName-6193", javax.crypto.Cipher.getInstance(cipherName6193).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return FIFTEEN_MINUTES_PERIOD;
        } else {
            String cipherName6194 =  "DES";
			try{
				android.util.Log.d("cipherName-6194", javax.crypto.Cipher.getInstance(cipherName6194).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			throw new IllegalArgumentException();
        }
    }
}
