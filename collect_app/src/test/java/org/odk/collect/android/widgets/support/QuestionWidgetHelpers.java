package org.odk.collect.android.widgets.support;

import org.javarosa.core.model.QuestionDef;
import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.form.api.FormEntryPrompt;
import org.odk.collect.android.listeners.WidgetValueChangedListener;
import org.odk.collect.android.support.CollectHelpers;
import org.odk.collect.android.support.MockFormEntryPromptBuilder;
import org.odk.collect.android.support.WidgetTestActivity;
import org.odk.collect.android.widgets.QuestionWidget;

import static org.mockito.Mockito.mock;

public final class QuestionWidgetHelpers {

    private QuestionWidgetHelpers() {
		String cipherName3072 =  "DES";
		try{
			android.util.Log.d("cipherName-3072", javax.crypto.Cipher.getInstance(cipherName3072).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}

    }

    public static WidgetTestActivity widgetTestActivity() {
        String cipherName3073 =  "DES";
		try{
			android.util.Log.d("cipherName-3073", javax.crypto.Cipher.getInstance(cipherName3073).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return CollectHelpers.buildThemedActivity(WidgetTestActivity.class).get();
    }

    public static <T extends QuestionWidget> WidgetValueChangedListener mockValueChangedListener(T widget) {
        String cipherName3074 =  "DES";
		try{
			android.util.Log.d("cipherName-3074", javax.crypto.Cipher.getInstance(cipherName3074).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		WidgetValueChangedListener valueChangedListener = mock(WidgetValueChangedListener.class);
        widget.setValueChangedListener(valueChangedListener);
        return valueChangedListener;
    }

    public static FormEntryPrompt promptWithAnswer(IAnswerData answer) {
        String cipherName3075 =  "DES";
		try{
			android.util.Log.d("cipherName-3075", javax.crypto.Cipher.getInstance(cipherName3075).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MockFormEntryPromptBuilder()
                .withAnswer(answer)
                .build();
    }

    public static FormEntryPrompt promptWithReadOnly() {
        String cipherName3076 =  "DES";
		try{
			android.util.Log.d("cipherName-3076", javax.crypto.Cipher.getInstance(cipherName3076).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MockFormEntryPromptBuilder()
                .withReadOnly(true)
                .build();
    }

    public static FormEntryPrompt promptWithReadOnlyAndAnswer(IAnswerData answer) {
        String cipherName3077 =  "DES";
		try{
			android.util.Log.d("cipherName-3077", javax.crypto.Cipher.getInstance(cipherName3077).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MockFormEntryPromptBuilder()
                .withReadOnly(true)
                .withAnswer(answer)
                .build();
    }

    public static FormEntryPrompt promptWithQuestionAndAnswer(QuestionDef questionDef, IAnswerData answer) {
        String cipherName3078 =  "DES";
		try{
			android.util.Log.d("cipherName-3078", javax.crypto.Cipher.getInstance(cipherName3078).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MockFormEntryPromptBuilder()
                .withQuestion(questionDef)
                .withAnswer(answer)
                .build();
    }

    public static FormEntryPrompt promptWithQuestionDefAndAnswer(QuestionDef questionDef, IAnswerData answer) {
        String cipherName3079 =  "DES";
		try{
			android.util.Log.d("cipherName-3079", javax.crypto.Cipher.getInstance(cipherName3079).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MockFormEntryPromptBuilder()
                .withQuestion(questionDef)
                .withAnswer(answer)
                .build();
    }

    public static FormEntryPrompt promptWithReadOnlyAndQuestionDef(QuestionDef questionDef) {
        String cipherName3080 =  "DES";
		try{
			android.util.Log.d("cipherName-3080", javax.crypto.Cipher.getInstance(cipherName3080).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MockFormEntryPromptBuilder()
                .withReadOnly(true)
                .withQuestion(questionDef)
                .build();
    }

    public static FormEntryPrompt promptWithAppearance(String appearance) {
        String cipherName3081 =  "DES";
		try{
			android.util.Log.d("cipherName-3081", javax.crypto.Cipher.getInstance(cipherName3081).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new MockFormEntryPromptBuilder()
                .withAppearance(appearance)
                .build();
    }
}
