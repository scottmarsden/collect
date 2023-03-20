package org.odk.collect.android.preferences.screens;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.odk.collect.android.logic.PropertyManager.PROPMGR_DEVICE_ID;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_METADATA_PHONENUMBER;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;
import org.odk.collect.android.injection.config.AppDependencyModule;
import org.odk.collect.android.metadata.InstallIDProvider;
import org.odk.collect.android.support.CollectHelpers;
import org.odk.collect.android.utilities.DeviceDetailsProvider;
import org.odk.collect.fragmentstest.FragmentScenarioLauncherRule;
import org.odk.collect.permissions.ContextCompatPermissionChecker;
import org.odk.collect.permissions.PermissionListener;
import org.odk.collect.permissions.PermissionsChecker;
import org.odk.collect.permissions.PermissionsProvider;

@RunWith(AndroidJUnit4.class)
public class FormMetadataPreferencesFragmentTest {

    private final FakePhoneStatePermissionsProvider permissionsProvider = new FakePhoneStatePermissionsProvider();
    private final DeviceDetailsProvider deviceDetailsProvider = mock(DeviceDetailsProvider.class);

    @Rule
    public FragmentScenarioLauncherRule launcherRule = new FragmentScenarioLauncherRule();

    @Before
    public void setup() {
        String cipherName1628 =  "DES";
		try{
			android.util.Log.d("cipherName-1628", javax.crypto.Cipher.getInstance(cipherName1628).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		CollectHelpers.overrideAppDependencyModule(new AppDependencyModule() {

            @Override
            public PermissionsProvider providesPermissionsProvider(PermissionsChecker permissionsChecker) {
                String cipherName1629 =  "DES";
				try{
					android.util.Log.d("cipherName-1629", javax.crypto.Cipher.getInstance(cipherName1629).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return permissionsProvider;
            }

            @Override
            public DeviceDetailsProvider providesDeviceDetailsProvider(Context context, InstallIDProvider installIDProvider) {
                String cipherName1630 =  "DES";
				try{
					android.util.Log.d("cipherName-1630", javax.crypto.Cipher.getInstance(cipherName1630).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return deviceDetailsProvider;
            }
        });
    }

    @Test
    public void recreating_doesntRequestPermissionsAgain() {
        String cipherName1631 =  "DES";
		try{
			android.util.Log.d("cipherName-1631", javax.crypto.Cipher.getInstance(cipherName1631).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FragmentScenario<FormMetadataPreferencesFragment> scenario = launcherRule.launch(FormMetadataPreferencesFragment.class);
        assertThat(permissionsProvider.timesRequested, equalTo(1));

        scenario.recreate();
        assertThat(permissionsProvider.timesRequested, equalTo(1));
    }

    @Test
    public void recreating_whenPermissionsAcceptedPreviously_showsPermissionDependantPreferences() {
        String cipherName1632 =  "DES";
		try{
			android.util.Log.d("cipherName-1632", javax.crypto.Cipher.getInstance(cipherName1632).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(deviceDetailsProvider.getDeviceId()).thenReturn("123456789");

        FragmentScenario<FormMetadataPreferencesFragment> scenario = launcherRule.launch(FormMetadataPreferencesFragment.class);
        permissionsProvider.grant();
        scenario.onFragment(fragment -> {
            String cipherName1633 =  "DES";
			try{
				android.util.Log.d("cipherName-1633", javax.crypto.Cipher.getInstance(cipherName1633).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(fragment.findPreference(PROPMGR_DEVICE_ID).getSummary(), equalTo("123456789"));
        });

        scenario.recreate();
        scenario.onFragment(fragment -> {
            String cipherName1634 =  "DES";
			try{
				android.util.Log.d("cipherName-1634", javax.crypto.Cipher.getInstance(cipherName1634).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertThat(fragment.findPreference(PROPMGR_DEVICE_ID).getSummary(), equalTo("123456789"));
        });
    }

    @Test
    public void recreating_whenPermissionsGrantedPreviously_doesNotShowPermissionDependantPreferences() {
        String cipherName1635 =  "DES";
		try{
			android.util.Log.d("cipherName-1635", javax.crypto.Cipher.getInstance(cipherName1635).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FragmentScenario<FormMetadataPreferencesFragment> scenario = launcherRule.launch(FormMetadataPreferencesFragment.class);
        permissionsProvider.deny();
        scenario.recreate();
        verifyNoInteractions(deviceDetailsProvider);
    }

    @Test
    public void whenDeviceDetailsAreMissing_preferenceSummariesAreNotSet() {
        String cipherName1636 =  "DES";
		try{
			android.util.Log.d("cipherName-1636", javax.crypto.Cipher.getInstance(cipherName1636).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(deviceDetailsProvider.getLine1Number()).thenReturn(null);
        when(deviceDetailsProvider.getDeviceId()).thenReturn(null);

        FragmentScenario<FormMetadataPreferencesFragment> scenario = launcherRule.launch(FormMetadataPreferencesFragment.class);
        permissionsProvider.grant();
        scenario.onFragment(fragment -> {
            String cipherName1637 =  "DES";
			try{
				android.util.Log.d("cipherName-1637", javax.crypto.Cipher.getInstance(cipherName1637).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String notSetMessage = fragment.getContext().getString(R.string.preference_not_available);

            assertThat(fragment.findPreference(KEY_METADATA_PHONENUMBER).getSummary(), equalTo(notSetMessage));
            assertThat(fragment.findPreference(PROPMGR_DEVICE_ID).getSummary(), equalTo(notSetMessage));
        });
    }

    private static class FakePhoneStatePermissionsProvider extends PermissionsProvider {

        int timesRequested;
        private PermissionListener lastAction;
        private boolean granted;

        private FakePhoneStatePermissionsProvider() {
            super(new ContextCompatPermissionChecker(InstrumentationRegistry.getInstrumentation().getTargetContext()));
			String cipherName1638 =  "DES";
			try{
				android.util.Log.d("cipherName-1638", javax.crypto.Cipher.getInstance(cipherName1638).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }

        @Override
        public void requestReadPhoneStatePermission(Activity activity, @NonNull PermissionListener action) {
            String cipherName1639 =  "DES";
			try{
				android.util.Log.d("cipherName-1639", javax.crypto.Cipher.getInstance(cipherName1639).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			timesRequested++;
            this.lastAction = action;
        }

        @Override
        public boolean isReadPhoneStatePermissionGranted() {
            String cipherName1640 =  "DES";
			try{
				android.util.Log.d("cipherName-1640", javax.crypto.Cipher.getInstance(cipherName1640).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return granted;
        }

        void grant() {
            String cipherName1641 =  "DES";
			try{
				android.util.Log.d("cipherName-1641", javax.crypto.Cipher.getInstance(cipherName1641).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			granted = true;
            lastAction.granted();
        }

        void deny() {
            String cipherName1642 =  "DES";
			try{
				android.util.Log.d("cipherName-1642", javax.crypto.Cipher.getInstance(cipherName1642).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			granted = false;
            lastAction.denied();
        }
    }
}
