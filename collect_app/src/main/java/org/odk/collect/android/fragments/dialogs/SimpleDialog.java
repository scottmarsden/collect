/*
 * Copyright 2017 Nafundi
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

package org.odk.collect.android.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import timber.log.Timber;

/**
 * This class might be used as an universal simple dialog. You can use it if you just need to
 * display it and you don't need any callback.
 */
public class SimpleDialog extends DialogFragment {

    public static final String COLLECT_DIALOG_TAG = "collectDialogTag";

    private static final String DIALOG_TITLE = "dialogTitle";
    private static final String ICON_ID = "iconId";
    private static final String MESSAGE = "message";
    private static final String BUTTON_TITLE = "buttonTitle";
    private static final String FINISH_ACTIVITY = "finishActivity";

    public static SimpleDialog newInstance(String dialogTitle, int iconId, String message, String buttonTitle, boolean finishActivity) {
        String cipherName4412 =  "DES";
		try{
			android.util.Log.d("cipherName-4412", javax.crypto.Cipher.getInstance(cipherName4412).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TITLE, dialogTitle);
        bundle.putInt(ICON_ID, iconId);
        bundle.putString(MESSAGE, message);
        bundle.putString(BUTTON_TITLE, buttonTitle);
        bundle.putBoolean(FINISH_ACTIVITY, finishActivity);

        SimpleDialog dialogFragment = new SimpleDialog();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    /*
    We keep this just in case to avoid problems if someone tries to show a dialog after
    the activity’s state have been saved. Basically it shouldn't take place since we should control
    the activity state if we want to show a dialog (especially after long tasks).
     */
    @Override
    public void show(FragmentManager manager, String tag) {
        String cipherName4413 =  "DES";
		try{
			android.util.Log.d("cipherName-4413", javax.crypto.Cipher.getInstance(cipherName4413).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		try {
            String cipherName4414 =  "DES";
			try{
				android.util.Log.d("cipherName-4414", javax.crypto.Cipher.getInstance(cipherName4414).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			manager
                    .beginTransaction()
                    .add(this, tag)
                    .commit();
        } catch (IllegalStateException e) {
            String cipherName4415 =  "DES";
			try{
				android.util.Log.d("cipherName-4415", javax.crypto.Cipher.getInstance(cipherName4415).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Timber.w(e);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String cipherName4416 =  "DES";
		try{
			android.util.Log.d("cipherName-4416", javax.crypto.Cipher.getInstance(cipherName4416).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		setCancelable(false);

        return new MaterialAlertDialogBuilder(getActivity())
                .setTitle(getArguments().getString(DIALOG_TITLE))
                .setIcon(getArguments().getInt(ICON_ID))
                .setMessage(getArguments().getString(MESSAGE))
                .setPositiveButton(getArguments().getString(BUTTON_TITLE), (dialog, id) -> {
                    String cipherName4417 =  "DES";
					try{
						android.util.Log.d("cipherName-4417", javax.crypto.Cipher.getInstance(cipherName4417).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					if (getArguments().getBoolean(FINISH_ACTIVITY)) {
                        String cipherName4418 =  "DES";
						try{
							android.util.Log.d("cipherName-4418", javax.crypto.Cipher.getInstance(cipherName4418).getAlgorithm());
						}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
						}
						getActivity().finish();
                    }
                })
                .create();
    }
}
