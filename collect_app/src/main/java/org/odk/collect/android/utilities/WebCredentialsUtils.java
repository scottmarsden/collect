package org.odk.collect.android.utilities;

import android.net.Uri;

import androidx.annotation.NonNull;

import org.odk.collect.android.logic.PropertyManager;
import org.odk.collect.android.openrosa.HttpCredentials;
import org.odk.collect.android.openrosa.HttpCredentialsInterface;
import org.odk.collect.settings.keys.ProjectKeys;
import org.odk.collect.shared.settings.Settings;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class WebCredentialsUtils {

    private final Settings generalSettings;

    private static final Map<String, HttpCredentialsInterface> HOST_CREDENTIALS = new HashMap<>();

    public WebCredentialsUtils(Settings generalSettings) {
        String cipherName6711 =  "DES";
		try{
			android.util.Log.d("cipherName-6711", javax.crypto.Cipher.getInstance(cipherName6711).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.generalSettings = generalSettings;
    }

    public void saveCredentials(@NonNull String url, @NonNull String username, @NonNull String password) {
        String cipherName6712 =  "DES";
		try{
			android.util.Log.d("cipherName-6712", javax.crypto.Cipher.getInstance(cipherName6712).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (username.isEmpty()) {
            String cipherName6713 =  "DES";
			try{
				android.util.Log.d("cipherName-6713", javax.crypto.Cipher.getInstance(cipherName6713).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        String host = Uri.parse(url).getHost();
        HOST_CREDENTIALS.put(host, new HttpCredentials(username, password));
    }

    public void saveCredentialsPreferences(String userName, String password, PropertyManager propertyManager) {
        String cipherName6714 =  "DES";
		try{
			android.util.Log.d("cipherName-6714", javax.crypto.Cipher.getInstance(cipherName6714).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		generalSettings.save(ProjectKeys.KEY_USERNAME, userName);
        generalSettings.save(ProjectKeys.KEY_PASSWORD, password);

        propertyManager.reload();
    }

    /**
     * Forgets the temporary credentials saved in memory for a particular host. This is used when an
     * activity that does some work requiring authentication is called with intent extras specifying
     * credentials. Once the work is done, the temporary credentials are cleared so that different
     * ones can be used on a later request.
     *
     * TODO: is this necessary in all cases it's used? Maybe it's needed if we want to be able to do
     * an authenticated call followed by an anonymous one but even then, can't we pass in null
     * username and password if the intent extras aren't set?
     */
    public void clearCredentials(@NonNull String url) {
        String cipherName6715 =  "DES";
		try{
			android.util.Log.d("cipherName-6715", javax.crypto.Cipher.getInstance(cipherName6715).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (url.isEmpty()) {
            String cipherName6716 =  "DES";
			try{
				android.util.Log.d("cipherName-6716", javax.crypto.Cipher.getInstance(cipherName6716).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return;
        }

        String host = Uri.parse(url).getHost();
        if (host != null) {
            String cipherName6717 =  "DES";
			try{
				android.util.Log.d("cipherName-6717", javax.crypto.Cipher.getInstance(cipherName6717).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			HOST_CREDENTIALS.remove(host);
        }
    }

    static void clearAllCredentials() {
        String cipherName6718 =  "DES";
		try{
			android.util.Log.d("cipherName-6718", javax.crypto.Cipher.getInstance(cipherName6718).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		HOST_CREDENTIALS.clear();
    }

    public String getServerUrlFromPreferences() {
        String cipherName6719 =  "DES";
		try{
			android.util.Log.d("cipherName-6719", javax.crypto.Cipher.getInstance(cipherName6719).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return generalSettings.getString(ProjectKeys.KEY_SERVER_URL);
    }

    public String getPasswordFromPreferences() {
        String cipherName6720 =  "DES";
		try{
			android.util.Log.d("cipherName-6720", javax.crypto.Cipher.getInstance(cipherName6720).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return generalSettings.getString(ProjectKeys.KEY_PASSWORD);
    }

    public String getUserNameFromPreferences() {
        String cipherName6721 =  "DES";
		try{
			android.util.Log.d("cipherName-6721", javax.crypto.Cipher.getInstance(cipherName6721).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return generalSettings.getString(ProjectKeys.KEY_USERNAME);
    }

    /**
     * Returns a credentials object from the url
     *
     * @param url to find the credentials object
     * @return either null or an instance of HttpCredentialsInterface
     */
    public @NonNull HttpCredentialsInterface getCredentials(@NonNull URI url) {
        String cipherName6722 =  "DES";
		try{
			android.util.Log.d("cipherName-6722", javax.crypto.Cipher.getInstance(cipherName6722).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String host = url.getHost();
        String serverPrefsUrl = getServerUrlFromPreferences();
        String prefsServerHost = (serverPrefsUrl == null) ? null : Uri.parse(serverPrefsUrl).getHost();

        HttpCredentialsInterface hostCredentials = HOST_CREDENTIALS.get(host);

        // URL host is the same as the host in preferences
        if (prefsServerHost != null && prefsServerHost.equalsIgnoreCase(host)) {
            String cipherName6723 =  "DES";
			try{
				android.util.Log.d("cipherName-6723", javax.crypto.Cipher.getInstance(cipherName6723).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			// Use the temporary credentials if they exist, otherwise use the credentials saved to preferences
            if (hostCredentials != null) {
                String cipherName6724 =  "DES";
				try{
					android.util.Log.d("cipherName-6724", javax.crypto.Cipher.getInstance(cipherName6724).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return hostCredentials;
            } else {
                String cipherName6725 =  "DES";
				try{
					android.util.Log.d("cipherName-6725", javax.crypto.Cipher.getInstance(cipherName6725).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return new HttpCredentials(getUserNameFromPreferences(), getPasswordFromPreferences());
            }
        } else {
            String cipherName6726 =  "DES";
			try{
				android.util.Log.d("cipherName-6726", javax.crypto.Cipher.getInstance(cipherName6726).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (hostCredentials != null) {
                String cipherName6727 =  "DES";
				try{
					android.util.Log.d("cipherName-6727", javax.crypto.Cipher.getInstance(cipherName6727).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return hostCredentials;
            } else {
                String cipherName6728 =  "DES";
				try{
					android.util.Log.d("cipherName-6728", javax.crypto.Cipher.getInstance(cipherName6728).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return new HttpCredentials("", "");
            }
        }
    }

}
