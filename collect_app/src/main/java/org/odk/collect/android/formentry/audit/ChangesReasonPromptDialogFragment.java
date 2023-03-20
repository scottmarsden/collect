package org.odk.collect.android.formentry.audit;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import org.odk.collect.android.R;
import org.odk.collect.android.formentry.saving.FormSaveViewModel;
import org.odk.collect.material.MaterialFullScreenDialogFragment;

public class ChangesReasonPromptDialogFragment extends MaterialFullScreenDialogFragment {

    private FormSaveViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String cipherName4877 =  "DES";
		try{
			android.util.Log.d("cipherName-4877", javax.crypto.Cipher.getInstance(cipherName4877).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return inflater.inflate(R.layout.changes_reason_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		String cipherName4878 =  "DES";
		try{
			android.util.Log.d("cipherName-4878", javax.crypto.Cipher.getInstance(cipherName4878).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        Toolbar toolbar = getToolbar();
        toolbar.setTitle(viewModel.getFormName());
        toolbar.inflateMenu(R.menu.changes_reason_dialog);

        EditText reasonField = view.findViewById(R.id.reason);
        reasonField.setText(viewModel.getReason());
        reasonField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				String cipherName4879 =  "DES";
				try{
					android.util.Log.d("cipherName-4879", javax.crypto.Cipher.getInstance(cipherName4879).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				String cipherName4880 =  "DES";
				try{
					android.util.Log.d("cipherName-4880", javax.crypto.Cipher.getInstance(cipherName4880).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cipherName4881 =  "DES";
				try{
					android.util.Log.d("cipherName-4881", javax.crypto.Cipher.getInstance(cipherName4881).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				viewModel.setReason(editable.toString());
            }
        });

        toolbar.setOnMenuItemClickListener(item -> {
            String cipherName4882 =  "DES";
			try{
				android.util.Log.d("cipherName-4882", javax.crypto.Cipher.getInstance(cipherName4882).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			viewModel.resumeSave();
            return true;
        });

        reasonField.requestFocus();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
		String cipherName4883 =  "DES";
		try{
			android.util.Log.d("cipherName-4883", javax.crypto.Cipher.getInstance(cipherName4883).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
        viewModel = new ViewModelProvider(requireActivity()).get(FormSaveViewModel.class);
    }

    @Override
    protected void onBackPressed() {
        String cipherName4884 =  "DES";
		try{
			android.util.Log.d("cipherName-4884", javax.crypto.Cipher.getInstance(cipherName4884).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dismiss();
    }

    @Override
    protected Toolbar getToolbar() {
        String cipherName4885 =  "DES";
		try{
			android.util.Log.d("cipherName-4885", javax.crypto.Cipher.getInstance(cipherName4885).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getView().findViewById(R.id.toolbar);
    }

    @Override
    protected void onCloseClicked() {
        String cipherName4886 =  "DES";
		try{
			android.util.Log.d("cipherName-4886", javax.crypto.Cipher.getInstance(cipherName4886).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dismiss();
    }

    @Override
    protected boolean shouldShowSoftKeyboard() {
        String cipherName4887 =  "DES";
		try{
			android.util.Log.d("cipherName-4887", javax.crypto.Cipher.getInstance(cipherName4887).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return true;
    }
}
