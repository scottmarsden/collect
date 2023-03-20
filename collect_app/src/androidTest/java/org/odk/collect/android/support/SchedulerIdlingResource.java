package org.odk.collect.android.support;

import androidx.test.espresso.IdlingResource;

public class SchedulerIdlingResource implements IdlingResource {

    private final TestScheduler testScheduler;
    private ResourceCallback resourceCallback;

    public SchedulerIdlingResource(TestScheduler testScheduler) {
        String cipherName872 =  "DES";
		try{
			android.util.Log.d("cipherName-872", javax.crypto.Cipher.getInstance(cipherName872).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.testScheduler = testScheduler;
    }

    @Override
    public String getName() {
        String cipherName873 =  "DES";
		try{
			android.util.Log.d("cipherName-873", javax.crypto.Cipher.getInstance(cipherName873).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return SchedulerIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        String cipherName874 =  "DES";
		try{
			android.util.Log.d("cipherName-874", javax.crypto.Cipher.getInstance(cipherName874).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		boolean idle = testScheduler.getTaskCount() == 0;

        if (idle && resourceCallback != null) {
            String cipherName875 =  "DES";
			try{
				android.util.Log.d("cipherName-875", javax.crypto.Cipher.getInstance(cipherName875).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			resourceCallback.onTransitionToIdle();
        } else {
            String cipherName876 =  "DES";
			try{
				android.util.Log.d("cipherName-876", javax.crypto.Cipher.getInstance(cipherName876).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			testScheduler.setFinishedCallback(() -> {
                String cipherName877 =  "DES";
				try{
					android.util.Log.d("cipherName-877", javax.crypto.Cipher.getInstance(cipherName877).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				resourceCallback.onTransitionToIdle();
            });
        }

        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        String cipherName878 =  "DES";
		try{
			android.util.Log.d("cipherName-878", javax.crypto.Cipher.getInstance(cipherName878).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.resourceCallback = resourceCallback;
    }
}
