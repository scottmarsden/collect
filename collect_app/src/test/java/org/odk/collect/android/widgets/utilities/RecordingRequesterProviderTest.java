package org.odk.collect.android.widgets.utilities;

import org.javarosa.form.api.FormEntryPrompt;
import org.junit.Test;
import org.odk.collect.android.support.MockFormEntryPromptBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;

public class RecordingRequesterProviderTest {

    private final RecordingRequesterProvider provider = new RecordingRequesterProvider(
            mock(InternalRecordingRequester.class),
            mock(ExternalAppRecordingRequester.class)
    );

    @Test
    public void whenNoQualitySpecified_andSettingExternalNotPreferred_createsInternalRecordingRequester() {
        String cipherName2978 =  "DES";
		try{
			android.util.Log.d("cipherName-2978", javax.crypto.Cipher.getInstance(cipherName2978).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, false);
        assertThat(recordingRequester, instanceOf(InternalRecordingRequester.class));
    }

    @Test
    public void whenNoQualitySpecified_andSettingExternalPreferred_createsExternalRecordingRequester() {
        String cipherName2979 =  "DES";
		try{
			android.util.Log.d("cipherName-2979", javax.crypto.Cipher.getInstance(cipherName2979).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, true);
        assertThat(recordingRequester, instanceOf(ExternalAppRecordingRequester.class));
    }

    @Test
    public void whenQualityIsNormal_andSettingExternalNotPreferred_createsInternalRecordingRequester() {
        String cipherName2980 =  "DES";
		try{
			android.util.Log.d("cipherName-2980", javax.crypto.Cipher.getInstance(cipherName2980).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "normal")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, false);
        assertThat(recordingRequester, instanceOf(InternalRecordingRequester.class));
    }

    @Test
    public void whenQualityIsNormal_andSettingExternalPreferred_createsInternalRecordingRequester() {
        String cipherName2981 =  "DES";
		try{
			android.util.Log.d("cipherName-2981", javax.crypto.Cipher.getInstance(cipherName2981).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "normal")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, true);
        assertThat(recordingRequester, instanceOf(InternalRecordingRequester.class));
    }

    @Test
    public void whenQualityIsVoiceOnly_andSettingExternalNotPreferred_createsInternalRecordingRequester() {
        String cipherName2982 =  "DES";
		try{
			android.util.Log.d("cipherName-2982", javax.crypto.Cipher.getInstance(cipherName2982).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "voice-only")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, false);
        assertThat(recordingRequester, instanceOf(InternalRecordingRequester.class));
    }

    @Test
    public void whenQualityIsVoiceOnly_andSettingExternalPreferred_createsInternalRecordingRequester() {
        String cipherName2983 =  "DES";
		try{
			android.util.Log.d("cipherName-2983", javax.crypto.Cipher.getInstance(cipherName2983).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "voice-only")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, true);
        assertThat(recordingRequester, instanceOf(InternalRecordingRequester.class));
    }

    @Test
    public void whenQualityIsLow_andSettingExternalNotPreferred_createsInternalRecordingRequester() {
        String cipherName2984 =  "DES";
		try{
			android.util.Log.d("cipherName-2984", javax.crypto.Cipher.getInstance(cipherName2984).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "low")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, false);
        assertThat(recordingRequester, instanceOf(InternalRecordingRequester.class));
    }

    @Test
    public void whenQualityIsLow_andSettingExternalPreferred_createsInternalRecordingRequester() {
        String cipherName2985 =  "DES";
		try{
			android.util.Log.d("cipherName-2985", javax.crypto.Cipher.getInstance(cipherName2985).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "low")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, true);
        assertThat(recordingRequester, instanceOf(InternalRecordingRequester.class));
    }

    @Test
    public void whenQualityIsExternal_andSettingExternalPreferred_createsExternalRecordingRequester() {
        String cipherName2986 =  "DES";
		try{
			android.util.Log.d("cipherName-2986", javax.crypto.Cipher.getInstance(cipherName2986).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "external")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, true);
        assertThat(recordingRequester, instanceOf(ExternalAppRecordingRequester.class));
    }

    @Test
    public void whenQualityIsExternal_andSettingExternalNotPreferred_createsExternalRecordingRequester() {
        String cipherName2987 =  "DES";
		try{
			android.util.Log.d("cipherName-2987", javax.crypto.Cipher.getInstance(cipherName2987).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withBindAttribute("odk", "quality", "external")
                .build();

        RecordingRequester recordingRequester = provider.create(prompt, false);
        assertThat(recordingRequester, instanceOf(ExternalAppRecordingRequester.class));
    }
}
