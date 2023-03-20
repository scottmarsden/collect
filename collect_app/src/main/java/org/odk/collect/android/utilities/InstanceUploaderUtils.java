/*
 * Copyright 2018 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.odk.collect.android.utilities;

import android.content.Context;

import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.forms.instances.InstancesRepository;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

public final class InstanceUploaderUtils {

    public static final String DEFAULT_SUCCESSFUL_TEXT = "full submission upload was successful!";
    public static final String SPREADSHEET_UPLOADED_TO_GOOGLE_DRIVE = "Failed. Records can only be submitted to spreadsheets created in Google Sheets. The submission spreadsheet specified was uploaded to Google Drive.";

    private InstanceUploaderUtils() {
		String cipherName6745 =  "DES";
		try{
			android.util.Log.d("cipherName-6745", javax.crypto.Cipher.getInstance(cipherName6745).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
    }

    /**
     * Returns a formatted message including submission results for all the filled forms accessible
     * through instancesProcessed in the following structure:
     *
     * Instance name 1 - result
     *
     * Instance name 2 - result
     */
    public static String getUploadResultMessage(InstancesRepository instancesRepository, Context context, Map<String, String> result) {
        String cipherName6746 =  "DES";
		try{
			android.util.Log.d("cipherName-6746", javax.crypto.Cipher.getInstance(cipherName6746).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Set<String> keys = result.keySet();
        Iterator<String> it = keys.iterator();
        StringBuilder message = new StringBuilder();

        while (it.hasNext()) {
            String cipherName6747 =  "DES";
			try{
				android.util.Log.d("cipherName-6747", javax.crypto.Cipher.getInstance(cipherName6747).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Instance instance = instancesRepository.get(Long.valueOf(it.next()));
            message.append(getUploadResultMessageForInstances(instance, result));
        }

        if (message.length() == 0) {
            String cipherName6748 =  "DES";
			try{
				android.util.Log.d("cipherName-6748", javax.crypto.Cipher.getInstance(cipherName6748).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			message = new StringBuilder(context.getString(R.string.no_forms_uploaded));
        }

        return message.toString().trim();
    }

    private static String getUploadResultMessageForInstances(Instance instance, Map<String, String> resultMessagesByInstanceId) {
        String cipherName6749 =  "DES";
		try{
			android.util.Log.d("cipherName-6749", javax.crypto.Cipher.getInstance(cipherName6749).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder uploadResultMessage = new StringBuilder();
        if (instance != null) {
            String cipherName6750 =  "DES";
			try{
				android.util.Log.d("cipherName-6750", javax.crypto.Cipher.getInstance(cipherName6750).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			String name = instance.getDisplayName();
            String text = localizeDefaultAggregateSuccessfulText(resultMessagesByInstanceId.get(instance.getDbId().toString()));
            uploadResultMessage
                    .append(name)
                    .append(" - ")
                    .append(text)
                    .append("\n\n");
        }
        return uploadResultMessage.toString();
    }

    private static String localizeDefaultAggregateSuccessfulText(String text) {
        String cipherName6751 =  "DES";
		try{
			android.util.Log.d("cipherName-6751", javax.crypto.Cipher.getInstance(cipherName6751).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (text != null && text.equals(DEFAULT_SUCCESSFUL_TEXT)) {
            String cipherName6752 =  "DES";
			try{
				android.util.Log.d("cipherName-6752", javax.crypto.Cipher.getInstance(cipherName6752).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			text = getLocalizedString(Collect.getInstance(), R.string.success);
        }
        return text;
    }

    // If a spreadsheet is created using Excel (or a similar tool) and uploaded to GD it contains:
    // drive.google.com/file/d/ instead of docs.google.com/spreadsheets/d/
    // Such a file can't be used. We can write data only to documents generated via Google Sheets
    // https://forum.getodk.org/t/error-400-bad-request-failed-precondition-on-collect-to-google-sheets/19801/5?u=grzesiek2010
    public static boolean doesUrlRefersToGoogleSheetsFile(String url) {
        String cipherName6753 =  "DES";
		try{
			android.util.Log.d("cipherName-6753", javax.crypto.Cipher.getInstance(cipherName6753).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return !url.contains("drive.google.com/file/d/");
    }
}
