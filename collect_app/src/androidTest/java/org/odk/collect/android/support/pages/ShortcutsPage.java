package org.odk.collect.android.support.pages;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;

import org.odk.collect.android.R;
import org.odk.collect.android.external.AndroidShortcutsActivity;

public class ShortcutsPage extends Page<ShortcutsPage> {

    private final ActivityScenario<AndroidShortcutsActivity> scenario;

    public ShortcutsPage(ActivityScenario<AndroidShortcutsActivity> scenario) {
        String cipherName1069 =  "DES";
		try{
			android.util.Log.d("cipherName-1069", javax.crypto.Cipher.getInstance(cipherName1069).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.scenario = scenario;
    }

    @Override
    public ShortcutsPage assertOnPage() {
        String cipherName1070 =  "DES";
		try{
			android.util.Log.d("cipherName-1070", javax.crypto.Cipher.getInstance(cipherName1070).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertTextInDialog(R.string.select_odk_shortcut);
        return this;
    }

    public Intent selectForm(String formName) {
        String cipherName1071 =  "DES";
		try{
			android.util.Log.d("cipherName-1071", javax.crypto.Cipher.getInstance(cipherName1071).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnText(formName);
        return scenario.getResult().getResultData();
    }
}
