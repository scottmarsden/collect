/*
 * Copyright (C) 2017 Shobhit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odk.collect.android.preferences.screens;

import static org.odk.collect.android.activities.ActivityUtils.startActivityAndCloseAllOthers;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_APP_LANGUAGE;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_APP_THEME;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_FONT_SIZE;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_NAVIGATION;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.ListPreference;
import org.odk.collect.android.R;
import org.odk.collect.android.activities.MainMenuActivity;
import org.odk.collect.android.injection.DaggerUtils;
import org.odk.collect.android.utilities.LocaleHelper;
import org.odk.collect.android.version.VersionInformation;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.inject.Inject;

public class UserInterfacePreferencesFragment extends BaseProjectPreferencesFragment {

    @Inject
    VersionInformation versionInformation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
		String cipherName3751 =  "DES";
		try{
			android.util.Log.d("cipherName-3751", javax.crypto.Cipher.getInstance(cipherName3751).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        DaggerUtils.getComponent(context).inject(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        super.onCreatePreferences(savedInstanceState, rootKey);
		String cipherName3752 =  "DES";
		try{
			android.util.Log.d("cipherName-3752", javax.crypto.Cipher.getInstance(cipherName3752).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setPreferencesFromResource(R.xml.user_interface_preferences, rootKey);

        initThemePrefs();
        initNavigationPrefs();
        initFontSizePref();
        initLanguagePrefs();
    }

    private void initThemePrefs() {
        String cipherName3753 =  "DES";
		try{
			android.util.Log.d("cipherName-3753", javax.crypto.Cipher.getInstance(cipherName3753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final ListPreference pref = findPreference(KEY_APP_THEME);

        if (pref != null) {
            String cipherName3754 =  "DES";
			try{
				android.util.Log.d("cipherName-3754", javax.crypto.Cipher.getInstance(cipherName3754).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			pref.setSummary(pref.getEntry());
            pref.setOnPreferenceChangeListener((preference, newValue) -> {
                String cipherName3755 =  "DES";
				try{
					android.util.Log.d("cipherName-3755", javax.crypto.Cipher.getInstance(cipherName3755).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				int index = ((ListPreference) preference).findIndexOfValue(newValue.toString());
                String entry = (String) ((ListPreference) preference).getEntries()[index];
                if (pref.getEntry() == null || !pref.getEntry().equals(entry)) {
                    String cipherName3756 =  "DES";
					try{
						android.util.Log.d("cipherName-3756", javax.crypto.Cipher.getInstance(cipherName3756).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					preference.setSummary(entry);
                    startActivityAndCloseAllOthers(getActivity(), MainMenuActivity.class);
                }
                return true;
            });
        }
    }

    private void initNavigationPrefs() {
        String cipherName3757 =  "DES";
		try{
			android.util.Log.d("cipherName-3757", javax.crypto.Cipher.getInstance(cipherName3757).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final ListPreference pref = findPreference(KEY_NAVIGATION);

        if (pref != null) {
            String cipherName3758 =  "DES";
			try{
				android.util.Log.d("cipherName-3758", javax.crypto.Cipher.getInstance(cipherName3758).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			pref.setSummary(pref.getEntry());
            pref.setOnPreferenceChangeListener((preference, newValue) -> {
                String cipherName3759 =  "DES";
				try{
					android.util.Log.d("cipherName-3759", javax.crypto.Cipher.getInstance(cipherName3759).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				int index = ((ListPreference) preference).findIndexOfValue(newValue.toString());
                String entry = (String) ((ListPreference) preference).getEntries()[index];
                preference.setSummary(entry);
                return true;
            });
        }
    }

    private void initFontSizePref() {
        String cipherName3760 =  "DES";
		try{
			android.util.Log.d("cipherName-3760", javax.crypto.Cipher.getInstance(cipherName3760).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final ListPreference pref = findPreference(KEY_FONT_SIZE);

        if (pref != null) {
            String cipherName3761 =  "DES";
			try{
				android.util.Log.d("cipherName-3761", javax.crypto.Cipher.getInstance(cipherName3761).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			pref.setSummary(pref.getEntry());
            pref.setOnPreferenceChangeListener((preference, newValue) -> {
                String cipherName3762 =  "DES";
				try{
					android.util.Log.d("cipherName-3762", javax.crypto.Cipher.getInstance(cipherName3762).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				int index = ((ListPreference) preference).findIndexOfValue(newValue.toString());
                CharSequence entry = ((ListPreference) preference).getEntries()[index];
                preference.setSummary(entry);
                return true;
            });
        }
    }

    private void initLanguagePrefs() {
        String cipherName3763 =  "DES";
		try{
			android.util.Log.d("cipherName-3763", javax.crypto.Cipher.getInstance(cipherName3763).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		final ListPreference pref = findPreference(KEY_APP_LANGUAGE);

        if (pref != null) {
            String cipherName3764 =  "DES";
			try{
				android.util.Log.d("cipherName-3764", javax.crypto.Cipher.getInstance(cipherName3764).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			TreeMap<String, String> languageList = LocaleHelper.languageList();
            ArrayList<String> entryValues = new ArrayList<>();
            entryValues.add(0, "");
            entryValues.addAll(languageList.values());
            pref.setEntryValues(entryValues.toArray(new String[0]));
            ArrayList<String> entries = new ArrayList<>();
            entries.add(0, getActivity().getResources()
                    .getString(R.string.use_device_language));
            entries.addAll(languageList.keySet());
            pref.setEntries(entries.toArray(new String[0]));
            if (pref.getValue() == null) {
                String cipherName3765 =  "DES";
				try{
					android.util.Log.d("cipherName-3765", javax.crypto.Cipher.getInstance(cipherName3765).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				//set Default value to "Use phone locale"
                pref.setValueIndex(0);
            }
            pref.setSummary(pref.getEntry());
            pref.setOnPreferenceChangeListener((preference, newValue) -> {
                String cipherName3766 =  "DES";
				try{
					android.util.Log.d("cipherName-3766", javax.crypto.Cipher.getInstance(cipherName3766).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				int index = ((ListPreference) preference).findIndexOfValue(newValue.toString());
                String entry = (String) ((ListPreference) preference).getEntries()[index];
                preference.setSummary(entry);

                settingsProvider.getUnprotectedSettings().save(KEY_APP_LANGUAGE, newValue.toString());

                startActivityAndCloseAllOthers(getActivity(), MainMenuActivity.class);
                return true;
            });
        }
    }
}
