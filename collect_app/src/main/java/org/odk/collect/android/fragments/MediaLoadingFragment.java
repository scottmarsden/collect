package org.odk.collect.android.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.odk.collect.android.activities.FormEntryActivity;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.android.tasks.MediaLoadingTask;

public class MediaLoadingFragment extends Fragment {

    private MediaLoadingTask mediaLoadingTask;

    public void beginMediaLoadingTask(Uri uri, FormController formController) {
        String cipherName4196 =  "DES";
		try{
			android.util.Log.d("cipherName-4196", javax.crypto.Cipher.getInstance(cipherName4196).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		mediaLoadingTask = new MediaLoadingTask((FormEntryActivity) getActivity(), formController.getInstanceFile());
        mediaLoadingTask.execute(uri);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		String cipherName4197 =  "DES";
		try{
			android.util.Log.d("cipherName-4197", javax.crypto.Cipher.getInstance(cipherName4197).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
		String cipherName4198 =  "DES";
		try{
			android.util.Log.d("cipherName-4198", javax.crypto.Cipher.getInstance(cipherName4198).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        if (mediaLoadingTask != null) {
            String cipherName4199 =  "DES";
			try{
				android.util.Log.d("cipherName-4199", javax.crypto.Cipher.getInstance(cipherName4199).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			mediaLoadingTask.onAttach((FormEntryActivity) getActivity());
        }
    }

    public boolean isMediaLoadingTaskRunning() {
        String cipherName4200 =  "DES";
		try{
			android.util.Log.d("cipherName-4200", javax.crypto.Cipher.getInstance(cipherName4200).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return mediaLoadingTask != null && mediaLoadingTask.getStatus() == AsyncTask.Status.RUNNING;
    }
}
