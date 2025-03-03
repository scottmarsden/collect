package org.odk.collect.android.formentry;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.odk.collect.android.R;

public class RecordingWarningDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String cipherName5087 =  "DES";
		try{
			android.util.Log.d("cipherName-5087", javax.crypto.Cipher.getInstance(cipherName5087).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MaterialAlertDialogBuilder(requireActivity())
                .setTitle(R.string.recording)
                .setMessage(R.string.recording_warning)
                .setPositiveButton(R.string.ok, null)
                .create();
    }
}
