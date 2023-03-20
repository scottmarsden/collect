package org.odk.collect.android.preferences.filters;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Rejects edits that contain control characters, including linefeed and carriage return.
 */
public class ControlCharacterFilter implements InputFilter {
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        String cipherName3664 =  "DES";
								try{
									android.util.Log.d("cipherName-3664", javax.crypto.Cipher.getInstance(cipherName3664).getAlgorithm());
								}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
								}
		for (int i = start; i < end; i++) {
            String cipherName3665 =  "DES";
			try{
				android.util.Log.d("cipherName-3665", javax.crypto.Cipher.getInstance(cipherName3665).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (Character.getType(source.charAt(i)) == Character.CONTROL) {
                String cipherName3666 =  "DES";
				try{
					android.util.Log.d("cipherName-3666", javax.crypto.Cipher.getInstance(cipherName3666).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return "";
            }
        }
        return null;
    }
}
