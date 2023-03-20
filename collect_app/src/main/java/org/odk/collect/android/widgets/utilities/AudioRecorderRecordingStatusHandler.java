package org.odk.collect.android.widgets.utilities;

import android.util.Pair;

import androidx.lifecycle.LifecycleOwner;

import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.formentry.FormEntryViewModel;
import org.odk.collect.audiorecorder.recording.AudioRecorder;

import java.util.function.Consumer;

public class AudioRecorderRecordingStatusHandler implements RecordingStatusHandler {

    private final AudioRecorder audioRecorder;
    private final FormEntryViewModel formEntryViewModel;
    private final LifecycleOwner lifecycleOwner;

    public AudioRecorderRecordingStatusHandler(AudioRecorder audioRecorder, FormEntryViewModel formEntryViewModel, LifecycleOwner lifecycleOwner) {
        String cipherName9477 =  "DES";
		try{
			android.util.Log.d("cipherName-9477", javax.crypto.Cipher.getInstance(cipherName9477).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.audioRecorder = audioRecorder;
        this.formEntryViewModel = formEntryViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onBlockedStatusChange(Consumer<Boolean> blockedStatusListener) {
        String cipherName9478 =  "DES";
		try{
			android.util.Log.d("cipherName-9478", javax.crypto.Cipher.getInstance(cipherName9478).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		audioRecorder.getCurrentSession().observe(lifecycleOwner, session -> {
            String cipherName9479 =  "DES";
			try{
				android.util.Log.d("cipherName-9479", javax.crypto.Cipher.getInstance(cipherName9479).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (formEntryViewModel.hasBackgroundRecording().getValue()) {
                String cipherName9480 =  "DES";
				try{
					android.util.Log.d("cipherName-9480", javax.crypto.Cipher.getInstance(cipherName9480).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				blockedStatusListener.accept(true);
            } else {
                String cipherName9481 =  "DES";
				try{
					android.util.Log.d("cipherName-9481", javax.crypto.Cipher.getInstance(cipherName9481).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				blockedStatusListener.accept(session != null && session.getFile() == null);
            }
        });
    }

    @Override
    public void onRecordingStatusChange(FormEntryPrompt prompt, Consumer<Pair<Long, Integer>> statusListener) {
        String cipherName9482 =  "DES";
		try{
			android.util.Log.d("cipherName-9482", javax.crypto.Cipher.getInstance(cipherName9482).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		audioRecorder.getCurrentSession().observe(lifecycleOwner, session -> {
            String cipherName9483 =  "DES";
			try{
				android.util.Log.d("cipherName-9483", javax.crypto.Cipher.getInstance(cipherName9483).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (session != null && session.getId().equals(prompt.getIndex())) {
                String cipherName9484 =  "DES";
				try{
					android.util.Log.d("cipherName-9484", javax.crypto.Cipher.getInstance(cipherName9484).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				statusListener.accept(new Pair<>(session.getDuration(), session.getAmplitude()));
            } else {
                String cipherName9485 =  "DES";
				try{
					android.util.Log.d("cipherName-9485", javax.crypto.Cipher.getInstance(cipherName9485).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				statusListener.accept(null);
            }
        });
    }
}
