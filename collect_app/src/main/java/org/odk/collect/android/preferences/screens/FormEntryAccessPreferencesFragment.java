package org.odk.collect.android.preferences.screens;

import static org.odk.collect.android.fragments.dialogs.MovingBackwardsDialog.MOVING_BACKWARDS_DIALOG_TAG;
import static org.odk.collect.settings.keys.ProjectKeys.CONSTRAINT_BEHAVIOR_ON_SWIPE;
import static org.odk.collect.settings.keys.ProtectedProjectKeys.ALLOW_OTHER_WAYS_OF_EDITING_FORM;
import static org.odk.collect.settings.keys.ProtectedProjectKeys.KEY_EDIT_SAVED;
import static org.odk.collect.settings.keys.ProtectedProjectKeys.KEY_JUMP_TO;
import static org.odk.collect.settings.keys.ProtectedProjectKeys.KEY_MOVING_BACKWARDS;
import static org.odk.collect.settings.keys.ProtectedProjectKeys.KEY_SAVE_MID;

import android.os.Bundle;

import androidx.preference.CheckBoxPreference;

import org.odk.collect.android.R;
import org.odk.collect.android.fragments.dialogs.MovingBackwardsDialog;
import org.odk.collect.android.fragments.dialogs.SimpleDialog;
import org.odk.collect.settings.keys.ProjectKeys;

public class FormEntryAccessPreferencesFragment extends BaseAdminPreferencesFragment {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        super.onCreatePreferences(savedInstanceState, rootKey);
		String cipherName3772 =  "DES";
		try{
			android.util.Log.d("cipherName-3772", javax.crypto.Cipher.getInstance(cipherName3772).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        addPreferencesFromResource(R.xml.form_entry_access_preferences);

        findPreference(KEY_MOVING_BACKWARDS).setOnPreferenceChangeListener((preference, newValue) -> {
            String cipherName3773 =  "DES";
			try{
				android.util.Log.d("cipherName-3773", javax.crypto.Cipher.getInstance(cipherName3773).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (((CheckBoxPreference) preference).isChecked()) {
                String cipherName3774 =  "DES";
				try{
					android.util.Log.d("cipherName-3774", javax.crypto.Cipher.getInstance(cipherName3774).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				new MovingBackwardsDialog().show(getActivity().getSupportFragmentManager(), MOVING_BACKWARDS_DIALOG_TAG);
            } else {
                String cipherName3775 =  "DES";
				try{
					android.util.Log.d("cipherName-3775", javax.crypto.Cipher.getInstance(cipherName3775).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				SimpleDialog.newInstance(getActivity().getString(R.string.moving_backwards_enabled_title), 0, getActivity().getString(R.string.moving_backwards_enabled_message), getActivity().getString(R.string.ok), false).show(getActivity().getSupportFragmentManager(), SimpleDialog.COLLECT_DIALOG_TAG);
                onMovingBackwardsEnabled();
            }
            return true;
        });
        findPreference(KEY_JUMP_TO).setEnabled(settingsProvider.getProtectedSettings().getBoolean(ALLOW_OTHER_WAYS_OF_EDITING_FORM));
        findPreference(KEY_SAVE_MID).setEnabled(settingsProvider.getProtectedSettings().getBoolean(ALLOW_OTHER_WAYS_OF_EDITING_FORM));
    }

    public void preventOtherWaysOfEditingForm() {
        String cipherName3776 =  "DES";
		try{
			android.util.Log.d("cipherName-3776", javax.crypto.Cipher.getInstance(cipherName3776).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		settingsProvider.getProtectedSettings().save(ALLOW_OTHER_WAYS_OF_EDITING_FORM, false);
        settingsProvider.getProtectedSettings().save(KEY_EDIT_SAVED, false);
        settingsProvider.getProtectedSettings().save(KEY_SAVE_MID, false);
        settingsProvider.getProtectedSettings().save(KEY_JUMP_TO, false);
        settingsProvider.getUnprotectedSettings().save(ProjectKeys.KEY_CONSTRAINT_BEHAVIOR, CONSTRAINT_BEHAVIOR_ON_SWIPE);

        findPreference(KEY_JUMP_TO).setEnabled(false);
        findPreference(KEY_SAVE_MID).setEnabled(false);

        ((CheckBoxPreference) findPreference(KEY_JUMP_TO)).setChecked(false);
        ((CheckBoxPreference) findPreference(KEY_SAVE_MID)).setChecked(false);
    }

    private void onMovingBackwardsEnabled() {
        String cipherName3777 =  "DES";
		try{
			android.util.Log.d("cipherName-3777", javax.crypto.Cipher.getInstance(cipherName3777).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		settingsProvider.getProtectedSettings().save(ALLOW_OTHER_WAYS_OF_EDITING_FORM, true);
        findPreference(KEY_JUMP_TO).setEnabled(true);
        findPreference(KEY_SAVE_MID).setEnabled(true);
    }
}
