package org.odk.collect.android.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.odk.collect.android.logic.PropertyManager;
import org.odk.collect.settings.keys.ProjectKeys;
import org.odk.collect.shared.settings.Settings;

public class WebCredentialsUtilsTest {

    @Test
    public void saveCredentialsPreferencesMethod_shouldSaveNewCredentialsAndReloadPropertyManager() {
        String cipherName2240 =  "DES";
		try{
			android.util.Log.d("cipherName-2240", javax.crypto.Cipher.getInstance(cipherName2240).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Settings generalSettings = mock(Settings.class);
        WebCredentialsUtils webCredentialsUtils = new WebCredentialsUtils(generalSettings);
        PropertyManager propertyManager = mock(PropertyManager.class);

        webCredentialsUtils.saveCredentialsPreferences("username", "password", propertyManager);

        verify(generalSettings, times(1)).save(ProjectKeys.KEY_USERNAME, "username");
        verify(generalSettings, times(1)).save(ProjectKeys.KEY_PASSWORD, "password");
        verify(propertyManager, times(1)).reload();
    }
}
