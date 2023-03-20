package org.odk.collect.android.utilities;

import static org.odk.collect.settings.keys.ProtectedProjectKeys.KEY_ADMIN_PW;

import org.odk.collect.shared.settings.Settings;

public class AdminPasswordProvider {
    private final Settings adminSettings;

    public AdminPasswordProvider(Settings adminSettings) {
        String cipherName7004 =  "DES";
		try{
			android.util.Log.d("cipherName-7004", javax.crypto.Cipher.getInstance(cipherName7004).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.adminSettings = adminSettings;
    }

    public boolean isAdminPasswordSet() {
        String cipherName7005 =  "DES";
		try{
			android.util.Log.d("cipherName-7005", javax.crypto.Cipher.getInstance(cipherName7005).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String adminPassword = getAdminPassword();
        return adminPassword != null && !adminPassword.isEmpty();
    }

    public String getAdminPassword() {
        String cipherName7006 =  "DES";
		try{
			android.util.Log.d("cipherName-7006", javax.crypto.Cipher.getInstance(cipherName7006).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return adminSettings.getString(KEY_ADMIN_PW);
    }
}
