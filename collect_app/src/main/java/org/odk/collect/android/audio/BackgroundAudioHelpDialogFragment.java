package org.odk.collect.android.audio;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.odk.collect.android.R;

public class BackgroundAudioHelpDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String cipherName7318 =  "DES";
		try{
			android.util.Log.d("cipherName-7318", javax.crypto.Cipher.getInstance(cipherName7318).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		LayoutInflater inflater = LayoutInflater.from(requireContext());
        View view = inflater.inflate(R.layout.background_audio_help_fragment_layout, null);
        return new MaterialAlertDialogBuilder(requireContext())
                .setView(view)
                .setPositiveButton(R.string.ok, null)
                .create();
    }
}
