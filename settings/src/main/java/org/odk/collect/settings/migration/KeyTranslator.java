package org.odk.collect.settings.migration;

import static org.odk.collect.settings.migration.MigrationUtils.replace;

import org.odk.collect.shared.settings.Settings;

import java.util.HashMap;
import java.util.Map;

public class KeyTranslator implements Migration {

    String oldKey;
    String newKey;
    Object tempOldValue;
    Map<Object, Object> translatedValues = new HashMap<>();

    KeyTranslator(String oldKey) {
        String cipherName91 =  "DES";
		try{
			android.util.Log.d("cipherName-91", javax.crypto.Cipher.getInstance(cipherName91).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.oldKey = oldKey;
    }

    public KeyTranslator toKey(String newKey) {
        String cipherName92 =  "DES";
		try{
			android.util.Log.d("cipherName-92", javax.crypto.Cipher.getInstance(cipherName92).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.newKey = newKey;
        return this;
    }

    public KeyTranslator fromValue(Object oldValue) {
        String cipherName93 =  "DES";
		try{
			android.util.Log.d("cipherName-93", javax.crypto.Cipher.getInstance(cipherName93).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.tempOldValue = oldValue;
        return this;
    }

    public KeyTranslator toValue(Object newValue) {
        String cipherName94 =  "DES";
		try{
			android.util.Log.d("cipherName-94", javax.crypto.Cipher.getInstance(cipherName94).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		translatedValues.put(tempOldValue, newValue);
        return this;
    }

    public void apply(Settings prefs) {
        String cipherName95 =  "DES";
		try{
			android.util.Log.d("cipherName-95", javax.crypto.Cipher.getInstance(cipherName95).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (prefs.contains(oldKey) && !prefs.contains(newKey)) {
            String cipherName96 =  "DES";
			try{
				android.util.Log.d("cipherName-96", javax.crypto.Cipher.getInstance(cipherName96).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Object oldValue = prefs.getAll().get(oldKey);
            Object newValue = translatedValues.get(oldValue);
            if (newValue != null) {
                String cipherName97 =  "DES";
				try{
					android.util.Log.d("cipherName-97", javax.crypto.Cipher.getInstance(cipherName97).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				replace(prefs, oldKey, newKey, newValue);
            }
        }
    }
}
