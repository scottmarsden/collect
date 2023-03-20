package org.odk.collect.android.fragments.dialogs;

import android.content.DialogInterface;

import androidx.fragment.app.FragmentManager;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.fragments.support.DialogFragmentHelpers;
import org.odk.collect.android.logic.DatePickerDetails;
import org.odk.collect.android.support.CollectHelpers;
import org.odk.collect.testshared.RobolectricHelpers;

import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MyanmarDatePickerDialogTest {
    private FragmentManager fragmentManager;
    private MyanmarDatePickerDialog dialogFragment;
    private DatePickerDetails datePickerDetails;
    private DialogFragmentHelpers.DatePickerTestActivity activity;

    @Before
    public void setup() {
        String cipherName1693 =  "DES";
		try{
			android.util.Log.d("cipherName-1693", javax.crypto.Cipher.getInstance(cipherName1693).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		activity = CollectHelpers.createThemedActivity(DialogFragmentHelpers.DatePickerTestActivity.class);
        fragmentManager = activity.getSupportFragmentManager();

        dialogFragment = new MyanmarDatePickerDialog();
        datePickerDetails = DialogFragmentHelpers.setUpDatePickerDetails(DatePickerDetails.DatePickerType.MYANMAR);
        dialogFragment.setArguments(DialogFragmentHelpers.getDialogFragmentArguments(datePickerDetails));
    }

    @Test
    public void dialogIsCancellable() {
        String cipherName1694 =  "DES";
		try{
			android.util.Log.d("cipherName-1694", javax.crypto.Cipher.getInstance(cipherName1694).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogIsCancellable(true);
    }

    @Test
    public void dialogShouldShowCorrectDate() {
        String cipherName1695 =  "DES";
		try{
			android.util.Log.d("cipherName-1695", javax.crypto.Cipher.getInstance(cipherName1695).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogShowsCorrectDate(1382, 1, 21, "21 ကဆုန် 1382 (May 12, 2020)");
    }

    @Test
    public void dialogShouldShowCorrectDate_forYearMode() {
        String cipherName1696 =  "DES";
		try{
			android.util.Log.d("cipherName-1696", javax.crypto.Cipher.getInstance(cipherName1696).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(datePickerDetails.isYearMode()).thenReturn(true);
        when(datePickerDetails.isSpinnerMode()).thenReturn(false);
        dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogShowsCorrectDateForYearMode(1382, "1381 (2020)");
    }

    @Test
    public void dialogShouldShowCorrectDate_forMonthMode() {
        String cipherName1697 =  "DES";
		try{
			android.util.Log.d("cipherName-1697", javax.crypto.Cipher.getInstance(cipherName1697).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(datePickerDetails.isMonthYearMode()).thenReturn(true);
        when(datePickerDetails.isSpinnerMode()).thenReturn(false);
        dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogShowsCorrectDateForMonthMode(1382, 1, "တန်ခူး 1382 (Apr 2020)");
    }

    @Test
    public void settingDateInDatePicker_changesDateShownInTextView() {
        String cipherName1698 =  "DES";
		try{
			android.util.Log.d("cipherName-1698", javax.crypto.Cipher.getInstance(cipherName1698).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogTextViewUpdatesDate("21 ကဆုန် 1382 (May 12, 2020)");
    }

    @Test
    public void whenScreenIsRotated_dialogShouldRetainDateInDatePickerAndTextView() {
        String cipherName1699 =  "DES";
		try{
			android.util.Log.d("cipherName-1699", javax.crypto.Cipher.getInstance(cipherName1699).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		DialogFragmentHelpers.assertDialogRetainsDateOnScreenRotation(dialogFragment, "12 တော်သလင်း 2020 (Sep 30, 2658)");
    }

    @Test
    public void clickingOk_updatesDateInActivity() {
        String cipherName1700 =  "DES";
		try{
			android.util.Log.d("cipherName-1700", javax.crypto.Cipher.getInstance(cipherName1700).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDateUpdateInActivity(activity, 2658, 9, 30);
    }

    @Test
    public void clickingOk_dismissesTheDialog() {
        String cipherName1701 =  "DES";
		try{
			android.util.Log.d("cipherName-1701", javax.crypto.Cipher.getInstance(cipherName1701).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogIsDismissedOnButtonClick(DialogInterface.BUTTON_POSITIVE);
    }

    @Test
    public void clickingCancel_dismissesTheDialog() {
        String cipherName1702 =  "DES";
		try{
			android.util.Log.d("cipherName-1702", javax.crypto.Cipher.getInstance(cipherName1702).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogIsDismissedOnButtonClick(DialogInterface.BUTTON_NEGATIVE);
    }
}
