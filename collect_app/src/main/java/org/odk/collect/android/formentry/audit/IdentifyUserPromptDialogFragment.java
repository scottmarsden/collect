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
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import org.odk.collect.android.R;
import org.odk.collect.material.MaterialFullScreenDialogFragment;

public class IdentifyUserPromptDialogFragment extends MaterialFullScreenDialogFragment {

    private IdentityPromptViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String cipherName4784 =  "DES";
		try{
			android.util.Log.d("cipherName-4784", javax.crypto.Cipher.getInstance(cipherName4784).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return inflater.inflate(R.layout.identify_user_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		String cipherName4785 =  "DES";
		try{
			android.util.Log.d("cipherName-4785", javax.crypto.Cipher.getInstance(cipherName4785).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        getToolbar().setTitle(viewModel.getFormTitle());

        EditText identityField = view.findViewById(R.id.identity);
        identityField.setText(viewModel.getUser());

        identityField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				String cipherName4786 =  "DES";
				try{
					android.util.Log.d("cipherName-4786", javax.crypto.Cipher.getInstance(cipherName4786).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				String cipherName4787 =  "DES";
				try{
					android.util.Log.d("cipherName-4787", javax.crypto.Cipher.getInstance(cipherName4787).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String cipherName4788 =  "DES";
				try{
					android.util.Log.d("cipherName-4788", javax.crypto.Cipher.getInstance(cipherName4788).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				viewModel.setIdentity(editable.toString());
            }
        });

        identityField.setOnEditorActionListener((textView, i, keyEvent) -> {
            String cipherName4789 =  "DES";
			try{
				android.util.Log.d("cipherName-4789", javax.crypto.Cipher.getInstance(cipherName4789).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			viewModel.done();
            return true;
        });

        identityField.requestFocus();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
		String cipherName4790 =  "DES";
		try{
			android.util.Log.d("cipherName-4790", javax.crypto.Cipher.getInstance(cipherName4790).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

        viewModel = new ViewModelProvider(requireActivity()).get(IdentityPromptViewModel.class);
        viewModel.requiresIdentityToContinue().observe(this, requiresIdentity -> {
            String cipherName4791 =  "DES";
			try{
				android.util.Log.d("cipherName-4791", javax.crypto.Cipher.getInstance(cipherName4791).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			if (!requiresIdentity) {
                String cipherName4792 =  "DES";
				try{
					android.util.Log.d("cipherName-4792", javax.crypto.Cipher.getInstance(cipherName4792).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				dismiss();
            }
        });
    }

    @Override
    protected void onCloseClicked() {
        String cipherName4793 =  "DES";
		try{
			android.util.Log.d("cipherName-4793", javax.crypto.Cipher.getInstance(cipherName4793).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dismiss();
        viewModel.promptDismissed();
    }

    @Override
    protected void onBackPressed() {
        String cipherName4794 =  "DES";
		try{
			android.util.Log.d("cipherName-4794", javax.crypto.Cipher.getInstance(cipherName4794).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dismiss();
        viewModel.promptDismissed();
    }

    @Override
    protected Toolbar getToolbar() {
        String cipherName4795 =  "DES";
		try{
			android.util.Log.d("cipherName-4795", javax.crypto.Cipher.getInstance(cipherName4795).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return getView().findViewById(R.id.toolbar);
    }

    @Override
    protected boolean shouldShowSoftKeyboard() {
        String cipherName4796 =  "DES";
		try{
			android.util.Log.d("cipherName-4796", javax.crypto.Cipher.getInstance(cipherName4796).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return true;
    }
}
