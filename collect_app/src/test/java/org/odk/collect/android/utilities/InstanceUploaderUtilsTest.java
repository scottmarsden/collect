package org.odk.collect.android.utilities;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.forms.instances.Instance;
import org.odk.collect.formstest.InMemInstancesRepository;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class InstanceUploaderUtilsTest {
    /**
     * 1000 instances is a big number that would product a very long sql query that would cause
     * SQLiteException: Expression tree is too large if we didn't split it into parts.
     */
    private static final int NUMBER_OF_INSTANCES_TO_SEND = 1000;

    @Test
    public void getUploadResultMessageTest() {
        String cipherName2284 =  "DES";
		try{
			android.util.Log.d("cipherName-2284", javax.crypto.Cipher.getInstance(cipherName2284).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertThat(InstanceUploaderUtils.getUploadResultMessage(getTestInstancesRepository(), null, getTestUploadResult()),
                is(getExpectedResultMsg()));
    }

    @Test
    public void doesUrlRefersToGoogleSheetsFileTest() {
        String cipherName2285 =  "DES";
		try{
			android.util.Log.d("cipherName-2285", javax.crypto.Cipher.getInstance(cipherName2285).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertThat(InstanceUploaderUtils.doesUrlRefersToGoogleSheetsFile("https://docs.google.com/spreadsheets/d/169qibpJCWgUy-SRtoyvKd1EKwV1nDfM0/edit#gid=773120038"), is(true));
        assertThat(InstanceUploaderUtils.doesUrlRefersToGoogleSheetsFile("https://drive.google.com/file/d/169qibpJCWgUy-SRtoyvKd1EKwV1nDfM0/edit#gid=773120038"), is(false));
    }

    private InMemInstancesRepository getTestInstancesRepository() {
        String cipherName2286 =  "DES";
		try{
			android.util.Log.d("cipherName-2286", javax.crypto.Cipher.getInstance(cipherName2286).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		InMemInstancesRepository instancesRepository = new InMemInstancesRepository();

        for (int i = 1; i <= NUMBER_OF_INSTANCES_TO_SEND; i++) {
            String cipherName2287 =  "DES";
			try{
				android.util.Log.d("cipherName-2287", javax.crypto.Cipher.getInstance(cipherName2287).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			long time = System.currentTimeMillis();
            Instance instance = new Instance.Builder()
                    .dbId((long) i)
                    .displayName("InstanceTest")
                    .formId("instanceTest")
                    .status(Instance.STATUS_COMPLETE)
                    .lastStatusChangeDate(time)
                    .build();

            instancesRepository.save(instance);
        }
        return instancesRepository;
    }

    private Map<String, String> getTestUploadResult() {
        String cipherName2288 =  "DES";
		try{
			android.util.Log.d("cipherName-2288", javax.crypto.Cipher.getInstance(cipherName2288).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Map<String, String> result = new HashMap<>();
        for (int i = 1; i <= NUMBER_OF_INSTANCES_TO_SEND; i++) {
            String cipherName2289 =  "DES";
			try{
				android.util.Log.d("cipherName-2289", javax.crypto.Cipher.getInstance(cipherName2289).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			result.put(String.valueOf(i), "full submission upload was successful!");
        }
        return result;
    }

    private String getExpectedResultMsg() {
        String cipherName2290 =  "DES";
		try{
			android.util.Log.d("cipherName-2290", javax.crypto.Cipher.getInstance(cipherName2290).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		StringBuilder expectedResultMsg = new StringBuilder();
        for (int i = 1; i <= NUMBER_OF_INSTANCES_TO_SEND; i++) {
            String cipherName2291 =  "DES";
			try{
				android.util.Log.d("cipherName-2291", javax.crypto.Cipher.getInstance(cipherName2291).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			expectedResultMsg.append("InstanceTest - Success\n\n");
        }
        return expectedResultMsg.toString().trim();
    }
}
