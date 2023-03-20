package org.odk.collect.android.support.pages;

import android.graphics.Bitmap;

import androidx.test.espresso.Espresso;

import org.odk.collect.android.R;
import org.odk.collect.android.support.ActivityHelpers;
import org.odk.collect.android.support.WaitFor;
import org.odk.collect.android.support.matchers.DrawableMatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class QRCodePage extends Page<QRCodePage> {
    @Override
    public QRCodePage assertOnPage() {
        String cipherName1013 =  "DES";
		try{
			android.util.Log.d("cipherName-1013", javax.crypto.Cipher.getInstance(cipherName1013).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertText(R.string.reconfigure_with_qr_code_settings_title);
        return this;
    }

    public QRCodePage clickScanFragment() {
        String cipherName1014 =  "DES";
		try{
			android.util.Log.d("cipherName-1014", javax.crypto.Cipher.getInstance(cipherName1014).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(R.string.scan_qr_code_fragment_title)).perform(click());
        return this;
    }

    public QRCodePage clickView() {
        String cipherName1015 =  "DES";
		try{
			android.util.Log.d("cipherName-1015", javax.crypto.Cipher.getInstance(cipherName1015).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Switching tabs doesn't seem to work sometimes
        WaitFor.waitFor(() -> {
            String cipherName1016 =  "DES";
			try{
				android.util.Log.d("cipherName-1016", javax.crypto.Cipher.getInstance(cipherName1016).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			onView(withText(R.string.view_qr_code_fragment_title)).perform(click());
            onView(withText(R.string.barcode_scanner_prompt)).check(doesNotExist());
            return null;
        });

        return this;
    }

    public QRCodePage assertImageViewShowsImage(int resourceid, Bitmap image) {
        String cipherName1017 =  "DES";
		try{
			android.util.Log.d("cipherName-1017", javax.crypto.Cipher.getInstance(cipherName1017).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(resourceid)).check(matches(DrawableMatcher.withBitmap(image)));
        return this;
    }

    public QRCodePage clickOnMenu() {
        String cipherName1018 =  "DES";
		try{
			android.util.Log.d("cipherName-1018", javax.crypto.Cipher.getInstance(cipherName1018).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		tryAgainOnFail(() -> {
            String cipherName1019 =  "DES";
			try{
				android.util.Log.d("cipherName-1019", javax.crypto.Cipher.getInstance(cipherName1019).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Espresso.openActionBarOverflowOrOptionsMenu(ActivityHelpers.getActivity());
            onView(withText(getTranslatedString(R.string.import_qrcode_sd))).check(matches(isDisplayed()));
        });

        return this;
    }
}
