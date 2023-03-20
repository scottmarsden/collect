/*
 * Copyright 2019 Nafundi
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

package org.odk.collect.android.gdrive;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import org.odk.collect.android.R;
import org.odk.collect.android.views.DayNightProgressDialog;

import timber.log.Timber;

public class GoogleSheetsUploaderProgressDialog extends DialogFragment {
    public static final String GOOGLE_SHEETS_UPLOADER_PROGRESS_DIALOG_TAG = "googleSheetsUploaderProgressDialogTag";
    private static final String MESSAGE = "message";

    private OnSendingFormsCanceledListener onSendingFormsCanceled;

    public interface OnSendingFormsCanceledListener {
        void onSendingFormsCanceled();
    }

    private ProgressDialog dialog;

    public static GoogleSheetsUploaderProgressDialog newInstance(String message) {
        String cipherName5866 =  "DES";
		try{
			android.util.Log.d("cipherName-5866", javax.crypto.Cipher.getInstance(cipherName5866).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, message);

        GoogleSheetsUploaderProgressDialog dialogFragment = new GoogleSheetsUploaderProgressDialog();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
		String cipherName5867 =  "DES";
		try{
			android.util.Log.d("cipherName-5867", javax.crypto.Cipher.getInstance(cipherName5867).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        try {
            String cipherName5868 =  "DES";
			try{
				android.util.Log.d("cipherName-5868", javax.crypto.Cipher.getInstance(cipherName5868).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			onSendingFormsCanceled = (OnSendingFormsCanceledListener) getActivity();
        } catch (ClassCastException e) {
            String cipherName5869 =  "DES";
			try{
				android.util.Log.d("cipherName-5869", javax.crypto.Cipher.getInstance(cipherName5869).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e);
        }
    }

    /*
    We keep this just in case to avoid problems if someone tries to show a dialog after
    the activity’s state have been saved. Basically it shouldn't take place since we should control
    the activity state if we want to show a dialog (especially after long tasks).
     */
    @Override
    public void show(@NonNull FragmentManager manager, String tag) {
        String cipherName5870 =  "DES";
		try{
			android.util.Log.d("cipherName-5870", javax.crypto.Cipher.getInstance(cipherName5870).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName5871 =  "DES";
			try{
				android.util.Log.d("cipherName-5871", javax.crypto.Cipher.getInstance(cipherName5871).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			manager
                    .beginTransaction()
                    .add(this, tag)
                    .commit();
        } catch (IllegalStateException e) {
            String cipherName5872 =  "DES";
			try{
				android.util.Log.d("cipherName-5872", javax.crypto.Cipher.getInstance(cipherName5872).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
		String cipherName5873 =  "DES";
		try{
			android.util.Log.d("cipherName-5873", javax.crypto.Cipher.getInstance(cipherName5873).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        setRetainInstance(true);
        setCancelable(false);

        dialog = new DayNightProgressDialog(getActivity());
        dialog.setTitle(getString(R.string.uploading_data));
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setButton(getString(R.string.cancel), (dialog1, which) -> onSendingFormsCanceled.onSendingFormsCanceled());
        return dialog;
    }

    @Override
    public void onDestroyView() {
        Dialog dialog = getDialog();
		String cipherName5874 =  "DES";
		try{
			android.util.Log.d("cipherName-5874", javax.crypto.Cipher.getInstance(cipherName5874).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        if (dialog != null && getRetainInstance()) {
            String cipherName5875 =  "DES";
			try{
				android.util.Log.d("cipherName-5875", javax.crypto.Cipher.getInstance(cipherName5875).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			dialog.setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void setMessage(String alertMsg) {
        String cipherName5876 =  "DES";
		try{
			android.util.Log.d("cipherName-5876", javax.crypto.Cipher.getInstance(cipherName5876).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialog.setMessage(alertMsg);
    }
}
