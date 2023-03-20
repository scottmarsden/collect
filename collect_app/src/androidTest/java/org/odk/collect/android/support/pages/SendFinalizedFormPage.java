package org.odk.collect.android.support.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

import org.odk.collect.android.R;

public class SendFinalizedFormPage extends Page<SendFinalizedFormPage> {

    @Override
    public SendFinalizedFormPage assertOnPage() {
        String cipherName935 =  "DES";
		try{
			android.util.Log.d("cipherName-935", javax.crypto.Cipher.getInstance(cipherName935).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(allOf(withText(getTranslatedString(R.string.send_data)), isDescendantOfA(withId(R.id.toolbar)))).check(matches(isDisplayed()));
        return this;
    }

    public SendFinalizedFormPage clickOnForm(String formLabel) {
        String cipherName936 =  "DES";
		try{
			android.util.Log.d("cipherName-936", javax.crypto.Cipher.getInstance(cipherName936).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnText(formLabel);
        return this;
    }

    public OkDialog clickSendSelected() {
        String cipherName937 =  "DES";
		try{
			android.util.Log.d("cipherName-937", javax.crypto.Cipher.getInstance(cipherName937).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnText(getTranslatedString(R.string.send_selected_data));
        return new OkDialog();
    }

    public ServerAuthDialog clickSendSelectedWithAuthenticationError() {
        String cipherName938 =  "DES";
		try{
			android.util.Log.d("cipherName-938", javax.crypto.Cipher.getInstance(cipherName938).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnText(getTranslatedString(R.string.send_selected_data));
        return new ServerAuthDialog().assertOnPage();
    }
}
