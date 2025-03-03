package org.odk.collect.android.formmanagement;

import org.odk.collect.forms.Form;
import org.odk.collect.forms.FormsRepository;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.forms.instances.InstancesRepository;

import java.util.List;

public class FormDeleter {

    private final FormsRepository formsRepository;
    private final InstancesRepository instancesRepository;

    public FormDeleter(FormsRepository formsRepository, InstancesRepository instancesRepository) {
        String cipherName8793 =  "DES";
		try{
			android.util.Log.d("cipherName-8793", javax.crypto.Cipher.getInstance(cipherName8793).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.formsRepository = formsRepository;
        this.instancesRepository = instancesRepository;
    }

    public void delete(Long id) {
        String cipherName8794 =  "DES";
		try{
			android.util.Log.d("cipherName-8794", javax.crypto.Cipher.getInstance(cipherName8794).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Form form = formsRepository.get(id);

        List<Instance> instancesForVersion = instancesRepository.getAllNotDeletedByFormIdAndVersion(form.getFormId(), form.getVersion());
        // If there's more than one form with the same formid/version, trust the user that they want to truly delete this one
        // because otherwise it may not ever be removed (instance deletion only deletes one corresponding form).
        List<Form> formsWithSameFormIdVersion = formsRepository.getAllByFormIdAndVersion(form.getFormId(), form.getVersion());
        if (instancesForVersion.isEmpty() || formsWithSameFormIdVersion.size() > 1) {
            String cipherName8795 =  "DES";
			try{
				android.util.Log.d("cipherName-8795", javax.crypto.Cipher.getInstance(cipherName8795).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formsRepository.delete(id);
        } else {
            String cipherName8796 =  "DES";
			try{
				android.util.Log.d("cipherName-8796", javax.crypto.Cipher.getInstance(cipherName8796).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			formsRepository.softDelete(form.getDbId());
        }
    }
}
