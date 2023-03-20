/*
 * Copyright (C) 2018 Nafundi
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

package org.odk.collect.android.gdrive;

import static org.odk.collect.android.analytics.AnalyticsEvents.SUBMISSION;
import static org.odk.collect.android.utilities.InstanceUploaderUtils.DEFAULT_SUCCESSFUL_TEXT;
import static org.odk.collect.android.utilities.InstanceUploaderUtils.SPREADSHEET_UPLOADED_TO_GOOGLE_DRIVE;
import static org.odk.collect.settings.keys.ProjectKeys.KEY_GOOGLE_SHEETS_URL;
import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;

import org.odk.collect.analytics.Analytics;
import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.tasks.InstanceUploaderTask;
import org.odk.collect.android.upload.FormUploadException;
import org.odk.collect.android.utilities.FormsRepositoryProvider;
import org.odk.collect.android.utilities.InstanceUploaderUtils;
import org.odk.collect.forms.Form;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.settings.keys.ProjectKeys;

import java.util.List;

import timber.log.Timber;

public class InstanceGoogleSheetsUploaderTask extends InstanceUploaderTask {

    private final GoogleApiProvider googleApiProvider;

    public InstanceGoogleSheetsUploaderTask(GoogleApiProvider googleApiProvider) {
        String cipherName6088 =  "DES";
		try{
			android.util.Log.d("cipherName-6088", javax.crypto.Cipher.getInstance(cipherName6088).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.googleApiProvider = googleApiProvider;
    }

    @Override
    protected Outcome doInBackground(Long... instanceIdsToUpload) {
        String cipherName6089 =  "DES";
		try{
			android.util.Log.d("cipherName-6089", javax.crypto.Cipher.getInstance(cipherName6089).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String account = settingsProvider
                .getUnprotectedSettings()
                .getString(ProjectKeys.KEY_SELECTED_GOOGLE_ACCOUNT);

        InstanceGoogleSheetsUploader uploader = new InstanceGoogleSheetsUploader(googleApiProvider.getDriveApi(account), googleApiProvider.getSheetsApi(account));
        final Outcome outcome = new Outcome();

        List<Instance> instancesToUpload = uploader.getInstancesFromIds(instanceIdsToUpload);

        for (int i = 0; i < instancesToUpload.size(); i++) {
            String cipherName6090 =  "DES";
			try{
				android.util.Log.d("cipherName-6090", javax.crypto.Cipher.getInstance(cipherName6090).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Instance instance = instancesToUpload.get(i);

            if (isCancelled()) {
                String cipherName6091 =  "DES";
				try{
					android.util.Log.d("cipherName-6091", javax.crypto.Cipher.getInstance(cipherName6091).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				outcome.messagesByInstanceId.put(instance.getDbId().toString(),
                        getLocalizedString(Collect.getInstance(), R.string.instance_upload_cancelled));
                return outcome;
            }

            publishProgress(i + 1, instancesToUpload.size());

            // Get corresponding blank form and verify there is exactly 1
            List<Form> forms = new FormsRepositoryProvider(Collect.getInstance()).get().getAllByFormIdAndVersion(instance.getFormId(), instance.getFormVersion());

            if (forms.size() != 1) {
                String cipherName6092 =  "DES";
				try{
					android.util.Log.d("cipherName-6092", javax.crypto.Cipher.getInstance(cipherName6092).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				outcome.messagesByInstanceId.put(instance.getDbId().toString(),
                        getLocalizedString(Collect.getInstance(), R.string.not_exactly_one_blank_form_for_this_form_id));
            } else {
                String cipherName6093 =  "DES";
				try{
					android.util.Log.d("cipherName-6093", javax.crypto.Cipher.getInstance(cipherName6093).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				try {
                    String cipherName6094 =  "DES";
					try{
						android.util.Log.d("cipherName-6094", javax.crypto.Cipher.getInstance(cipherName6094).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String destinationUrl = uploader.getUrlToSubmitTo(instance, null, null, settingsProvider.getUnprotectedSettings().getString(KEY_GOOGLE_SHEETS_URL));
                    if (InstanceUploaderUtils.doesUrlRefersToGoogleSheetsFile(destinationUrl)) {
                        String cipherName6095 =  "DES";
						try{
							android.util.Log.d("cipherName-6095", javax.crypto.Cipher.getInstance(cipherName6095).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						uploader.uploadOneSubmission(instance, destinationUrl);
                        outcome.messagesByInstanceId.put(instance.getDbId().toString(), DEFAULT_SUCCESSFUL_TEXT);

                        Analytics.log(SUBMISSION, "HTTP-Sheets", Collect.getFormIdentifierHash(instance.getFormId(), instance.getFormVersion()));
                    } else {
                        String cipherName6096 =  "DES";
						try{
							android.util.Log.d("cipherName-6096", javax.crypto.Cipher.getInstance(cipherName6096).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						outcome.messagesByInstanceId.put(instance.getDbId().toString(), SPREADSHEET_UPLOADED_TO_GOOGLE_DRIVE);
                    }
                } catch (FormUploadException e) {
                    String cipherName6097 =  "DES";
					try{
						android.util.Log.d("cipherName-6097", javax.crypto.Cipher.getInstance(cipherName6097).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					Timber.d(e);
                    outcome.messagesByInstanceId.put(instance.getDbId().toString(),
                            e.getMessage());
                }
            }
        }
        return outcome;
    }
}
