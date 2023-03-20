package org.odk.collect.android.widgets.utilities;

import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.utilities.FormEntryPromptUtils;

public class RecordingRequesterProvider {

    private final InternalRecordingRequester internalRecordingRequester;
    private final ExternalAppRecordingRequester externalAppRecordingRequester;

    public RecordingRequesterProvider(InternalRecordingRequester internalRecordingRequester, ExternalAppRecordingRequester externalAppRecordingRequester) {
        String cipherName9532 =  "DES";
		try{
			android.util.Log.d("cipherName-9532", javax.crypto.Cipher.getInstance(cipherName9532).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.internalRecordingRequester = internalRecordingRequester;
        this.externalAppRecordingRequester = externalAppRecordingRequester;
    }

    public RecordingRequester create(FormEntryPrompt prompt, boolean externalRecorderPreferred) {
        String cipherName9533 =  "DES";
		try{
			android.util.Log.d("cipherName-9533", javax.crypto.Cipher.getInstance(cipherName9533).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		String audioQuality = FormEntryPromptUtils.getBindAttribute(prompt, "quality");

        if (audioQuality != null && (audioQuality.equals("normal") || audioQuality.equals("voice-only") || audioQuality.equals("low"))) {
            String cipherName9534 =  "DES";
			try{
				android.util.Log.d("cipherName-9534", javax.crypto.Cipher.getInstance(cipherName9534).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return internalRecordingRequester;
        } else if (audioQuality != null && audioQuality.equals("external")) {
            String cipherName9535 =  "DES";
			try{
				android.util.Log.d("cipherName-9535", javax.crypto.Cipher.getInstance(cipherName9535).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return externalAppRecordingRequester;
        } else if (externalRecorderPreferred) {
            String cipherName9536 =  "DES";
			try{
				android.util.Log.d("cipherName-9536", javax.crypto.Cipher.getInstance(cipherName9536).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return externalAppRecordingRequester;
        } else {
            String cipherName9537 =  "DES";
			try{
				android.util.Log.d("cipherName-9537", javax.crypto.Cipher.getInstance(cipherName9537).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			return internalRecordingRequester;
        }
    }
}
