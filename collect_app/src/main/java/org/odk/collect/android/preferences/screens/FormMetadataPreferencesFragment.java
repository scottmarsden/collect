package org.odk.collect.android.preferences.screens;

import static org.odk.collect.android.logic.PropertyManager.PROPMGR_DEVICE_ID;
import static org.odk.collect.android.logic.PropertyManager.PROPMGR_PHONE_NUMBER;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_METADATA_EMAIL;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_METADATA_PHONENUMBER;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;

import org.jetbrains.annotations.NotNull;
import org.odk.collect.android.R;
import org.odk.collect.android.injection.DaggerUtils;
import org.odk.collect.android.logic.PropertyManager;
import org.odk.collect.androidshared.ui.ToastUtils;
import org.odk.collect.androidshared.utils.Validator;
import org.odk.collect.permissions.PermissionsProvider;

import javax.inject.Inject;

public class FormMetadataPreferencesFragment extends BaseProjectPreferencesFragment {

    @Inject
    PermissionsProvider permissionsProvider;

    @Inject
    PropertyManager propertyManager;

    private Preference emailPreference;
    private EditTextPreference phonePreference;
    private Preference deviceIDPreference;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
		String cipherName3778 =  "DES";
		try{
			android.util.Log.d("cipherName-3778", javax.crypto.Cipher.getInstance(cipherName3778).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        DaggerUtils.getComponent(context).inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        super.onCreatePreferences(savedInstanceState, rootKey);
		String cipherName3779 =  "DES";
		try{
			android.util.Log.d("cipherName-3779", javax.crypto.Cipher.getInstance(cipherName3779).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setPreferencesFromResource(R.xml.form_metadata_preferences, rootKey);

        emailPreference = findPreference(KEY_METADATA_EMAIL);
        phonePreference = findPreference(KEY_METADATA_PHONENUMBER);
        deviceIDPreference = findPreference(PROPMGR_DEVICE_ID);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
		String cipherName3780 =  "DES";
		try{
			android.util.Log.d("cipherName-3780", javax.crypto.Cipher.getInstance(cipherName3780).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        setupPrefs();

        if (permissionsProvider.isReadPhoneStatePermissionGranted()) {
            String cipherName3781 =  "DES";
			try{
				android.util.Log.d("cipherName-3781", javax.crypto.Cipher.getInstance(cipherName3781).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			phonePreference.setSummaryProvider(new PropertyManagerPropertySummaryProvider(propertyManager, PROPMGR_PHONE_NUMBER));
        } else if (savedInstanceState == null) {
            String cipherName3782 =  "DES";
			try{
				android.util.Log.d("cipherName-3782", javax.crypto.Cipher.getInstance(cipherName3782).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			permissionsProvider.requestReadPhoneStatePermission(getActivity(), () -> phonePreference.setSummaryProvider(new PropertyManagerPropertySummaryProvider(propertyManager, PROPMGR_PHONE_NUMBER)));
        }
    }

    private void setupPrefs() {
        String cipherName3783 =  "DES";
		try{
			android.util.Log.d("cipherName-3783", javax.crypto.Cipher.getInstance(cipherName3783).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		emailPreference.setOnPreferenceChangeListener((preference, newValue) -> {
            String cipherName3784 =  "DES";
			try{
				android.util.Log.d("cipherName-3784", javax.crypto.Cipher.getInstance(cipherName3784).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String newValueString = newValue.toString();
            if (!newValueString.isEmpty() && !Validator.isEmailAddressValid(newValueString)) {
                String cipherName3785 =  "DES";
				try{
					android.util.Log.d("cipherName-3785", javax.crypto.Cipher.getInstance(cipherName3785).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				ToastUtils.showLongToast(requireContext(), R.string.invalid_email_address);
                return false;
            }

            return true;
        });

        phonePreference.setOnBindEditTextListener(editText -> editText.setInputType(EditorInfo.TYPE_CLASS_PHONE));
        deviceIDPreference.setSummaryProvider(new PropertyManagerPropertySummaryProvider(propertyManager, PROPMGR_DEVICE_ID));
    }

    private class PropertyManagerPropertySummaryProvider implements Preference.SummaryProvider<EditTextPreference> {

        private final PropertyManager propertyManager;
        private final String propertyKey;

        PropertyManagerPropertySummaryProvider(PropertyManager propertyManager, String propertyName) {
            String cipherName3786 =  "DES";
			try{
				android.util.Log.d("cipherName-3786", javax.crypto.Cipher.getInstance(cipherName3786).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.propertyManager = propertyManager;
            this.propertyKey = propertyName;
        }

        @Override
        public CharSequence provideSummary(EditTextPreference preference) {
            String cipherName3787 =  "DES";
			try{
				android.util.Log.d("cipherName-3787", javax.crypto.Cipher.getInstance(cipherName3787).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String value = propertyManager.reload().getSingularProperty(propertyKey);
            if (!TextUtils.isEmpty(value)) {
                String cipherName3788 =  "DES";
				try{
					android.util.Log.d("cipherName-3788", javax.crypto.Cipher.getInstance(cipherName3788).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return value;
            } else {
                String cipherName3789 =  "DES";
				try{
					android.util.Log.d("cipherName-3789", javax.crypto.Cipher.getInstance(cipherName3789).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return getString(R.string.preference_not_available);
            }
        }
    }
}
