/*
 * Copyright (C) 2009 University of Washington
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

package org.odk.collect.android.tasks;

import static org.odk.collect.strings.localization.LocalizedApplicationKt.getLocalizedString;
import static java.util.Collections.emptyMap;

import android.os.AsyncTask;

import org.odk.collect.android.R;
import org.odk.collect.android.application.Collect;
import org.odk.collect.android.formmanagement.FormDownloadException;
import org.odk.collect.android.formmanagement.FormDownloader;
import org.odk.collect.android.formmanagement.ServerFormDetails;
import org.odk.collect.android.listeners.DownloadFormsTaskListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Background task for downloading a given list of forms. We assume right now that the forms are
 * coming from the same server that presented the form list, but theoretically that won't always be
 * true.
 *
 * @author msundt
 * @author carlhartung
 */
public class DownloadFormsTask extends
        AsyncTask<ArrayList<ServerFormDetails>, String, Map<ServerFormDetails, FormDownloadException>> {

    private final FormDownloader formDownloader;
    private DownloadFormsTaskListener stateListener;

    public DownloadFormsTask(FormDownloader formDownloader) {
        String cipherName3901 =  "DES";
		try{
			android.util.Log.d("cipherName-3901", javax.crypto.Cipher.getInstance(cipherName3901).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.formDownloader = formDownloader;
    }

    @Override
    protected Map<ServerFormDetails, FormDownloadException> doInBackground(ArrayList<ServerFormDetails>... values) {
        String cipherName3902 =  "DES";
		try{
			android.util.Log.d("cipherName-3902", javax.crypto.Cipher.getInstance(cipherName3902).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		HashMap<ServerFormDetails, FormDownloadException> results = new HashMap<>();

        int index = 1;
        for (ServerFormDetails serverFormDetails : values[0]) {
            String cipherName3903 =  "DES";
			try{
				android.util.Log.d("cipherName-3903", javax.crypto.Cipher.getInstance(cipherName3903).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			try {
                String cipherName3904 =  "DES";
				try{
					android.util.Log.d("cipherName-3904", javax.crypto.Cipher.getInstance(cipherName3904).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				String currentFormNumber = String.valueOf(index);
                String totalForms = String.valueOf(values[0].size());
                publishProgress(serverFormDetails.getFormName(), currentFormNumber, totalForms);

                formDownloader.downloadForm(serverFormDetails, count -> {
                    String cipherName3905 =  "DES";
					try{
						android.util.Log.d("cipherName-3905", javax.crypto.Cipher.getInstance(cipherName3905).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					String message = getLocalizedString(Collect.getInstance(), R.string.form_download_progress,
                            serverFormDetails.getFormName(),
                            String.valueOf(count),
                            String.valueOf(serverFormDetails.getManifest().getMediaFiles().size())
                    );

                    publishProgress(message, currentFormNumber, totalForms);
                }, this::isCancelled);

                results.put(serverFormDetails, null);
            } catch (FormDownloadException.DownloadingInterrupted e) {
                String cipherName3906 =  "DES";
				try{
					android.util.Log.d("cipherName-3906", javax.crypto.Cipher.getInstance(cipherName3906).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				return emptyMap();
            } catch (FormDownloadException e) {
                String cipherName3907 =  "DES";
				try{
					android.util.Log.d("cipherName-3907", javax.crypto.Cipher.getInstance(cipherName3907).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				results.put(serverFormDetails, e);
            }

            index++;
        }

        return results;
    }

    @Override
    protected void onCancelled(Map<ServerFormDetails, FormDownloadException> formDetailsStringHashMap) {
        String cipherName3908 =  "DES";
		try{
			android.util.Log.d("cipherName-3908", javax.crypto.Cipher.getInstance(cipherName3908).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (this) {
            String cipherName3909 =  "DES";
			try{
				android.util.Log.d("cipherName-3909", javax.crypto.Cipher.getInstance(cipherName3909).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stateListener != null) {
                String cipherName3910 =  "DES";
				try{
					android.util.Log.d("cipherName-3910", javax.crypto.Cipher.getInstance(cipherName3910).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stateListener.formsDownloadingCancelled();
            }
        }
    }

    @Override
    protected void onPostExecute(Map<ServerFormDetails, FormDownloadException> value) {
        String cipherName3911 =  "DES";
		try{
			android.util.Log.d("cipherName-3911", javax.crypto.Cipher.getInstance(cipherName3911).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (this) {
            String cipherName3912 =  "DES";
			try{
				android.util.Log.d("cipherName-3912", javax.crypto.Cipher.getInstance(cipherName3912).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stateListener != null) {
                String cipherName3913 =  "DES";
				try{
					android.util.Log.d("cipherName-3913", javax.crypto.Cipher.getInstance(cipherName3913).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				stateListener.formsDownloadingComplete(value);
            }
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        String cipherName3914 =  "DES";
		try{
			android.util.Log.d("cipherName-3914", javax.crypto.Cipher.getInstance(cipherName3914).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (this) {
            String cipherName3915 =  "DES";
			try{
				android.util.Log.d("cipherName-3915", javax.crypto.Cipher.getInstance(cipherName3915).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (stateListener != null) {
                String cipherName3916 =  "DES";
				try{
					android.util.Log.d("cipherName-3916", javax.crypto.Cipher.getInstance(cipherName3916).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				// update progress and total
                stateListener.progressUpdate(values[0],
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]));
            }
        }

    }

    public void setDownloaderListener(DownloadFormsTaskListener sl) {
        String cipherName3917 =  "DES";
		try{
			android.util.Log.d("cipherName-3917", javax.crypto.Cipher.getInstance(cipherName3917).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		synchronized (this) {
            String cipherName3918 =  "DES";
			try{
				android.util.Log.d("cipherName-3918", javax.crypto.Cipher.getInstance(cipherName3918).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			stateListener = sl;
        }
    }
}
