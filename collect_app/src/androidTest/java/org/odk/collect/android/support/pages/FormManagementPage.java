package org.odk.collect.android.support.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.contrib.RecyclerViewActions;

import org.odk.collect.android.R;

public class FormManagementPage extends Page<FormManagementPage> {

    @Override
    public FormManagementPage assertOnPage() {
        String cipherName939 =  "DES";
		try{
			android.util.Log.d("cipherName-939", javax.crypto.Cipher.getInstance(cipherName939).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertToolbarTitle(getTranslatedString(R.string.form_management_preferences));
        return this;
    }

    public ListPreferenceDialog<FormManagementPage> clickUpdateForms() {
        String cipherName940 =  "DES";
		try{
			android.util.Log.d("cipherName-940", javax.crypto.Cipher.getInstance(cipherName940).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnString(R.string.form_update_mode_title);
        return new ListPreferenceDialog<>(R.string.form_update_mode_title, this).assertOnPage();
    }

    public ListPreferenceDialog<FormManagementPage> clickAutomaticUpdateFrequency() {
        String cipherName941 =  "DES";
		try{
			android.util.Log.d("cipherName-941", javax.crypto.Cipher.getInstance(cipherName941).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnString(R.string.form_update_frequency_title);
        return new ListPreferenceDialog<>(R.string.form_update_frequency_title, this).assertOnPage();
    }

    public ListPreferenceDialog<FormManagementPage> clickAutoSend() {
        String cipherName942 =  "DES";
		try{
			android.util.Log.d("cipherName-942", javax.crypto.Cipher.getInstance(cipherName942).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnString(R.string.autosend);
        return new ListPreferenceDialog<>(R.string.autosend, this).assertOnPage();
    }

    public FormManagementPage openShowGuidanceForQuestions() {
        String cipherName943 =  "DES";
		try{
			android.util.Log.d("cipherName-943", javax.crypto.Cipher.getInstance(cipherName943).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		scrollToRecyclerViewItemAndClickText(getTranslatedString(R.string.guidance_hint_title));
        return this;
    }

    public FormManagementPage clickOnDefaultToFinalized() {
        String cipherName944 =  "DES";
		try{
			android.util.Log.d("cipherName-944", javax.crypto.Cipher.getInstance(cipherName944).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		scrollToRecyclerViewItemAndClickText(getTranslatedString(R.string.default_completed));
        return this;
    }

    public FormManagementPage openConstraintProcessing() {
        String cipherName945 =  "DES";
		try{
			android.util.Log.d("cipherName-945", javax.crypto.Cipher.getInstance(cipherName945).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		scrollToRecyclerViewItemAndClickText(getTranslatedString(R.string.constraint_behavior_title));
        return this;
    }

    public FormManagementPage scrollToConstraintProcessing() {
        String cipherName946 =  "DES";
		try{
			android.util.Log.d("cipherName-946", javax.crypto.Cipher.getInstance(cipherName946).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.recycler_view)).perform(RecyclerViewActions
                .actionOnItem(hasDescendant(withText(getTranslatedString(R.string.constraint_behavior_title))), scrollTo()));
        return this;
    }

    public FormManagementPage checkIfConstraintProcessingIsDisabled() {
        String cipherName947 =  "DES";
		try{
			android.util.Log.d("cipherName-947", javax.crypto.Cipher.getInstance(cipherName947).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(getTranslatedString(R.string.constraint_behavior_title))).check(matches(not(isEnabled())));
        return this;
    }
}
