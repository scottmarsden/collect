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
public class CopticDatePickerDialogTest {
    private FragmentManager fragmentManager;
    private CopticDatePickerDialog dialogFragment;
    private DatePickerDetails datePickerDetails;
    private DialogFragmentHelpers.DatePickerTestActivity activity;

    @Before
    public void setup() {
        String cipherName1683 =  "DES";
		try{
			android.util.Log.d("cipherName-1683", javax.crypto.Cipher.getInstance(cipherName1683).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		activity = CollectHelpers.createThemedActivity(DialogFragmentHelpers.DatePickerTestActivity.class);
        fragmentManager = activity.getSupportFragmentManager();

        dialogFragment = new CopticDatePickerDialog();
        datePickerDetails = DialogFragmentHelpers.setUpDatePickerDetails(DatePickerDetails.DatePickerType.COPTIC);
        dialogFragment.setArguments(DialogFragmentHelpers.getDialogFragmentArguments(datePickerDetails));
    }

    @Test
    public void dialogIsCancellable() {
        String cipherName1684 =  "DES";
		try{
			android.util.Log.d("cipherName-1684", javax.crypto.Cipher.getInstance(cipherName1684).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogIsCancellable(true);
    }

    @Test
    public void dialogShouldShowCorrectDate() {
        String cipherName1685 =  "DES";
		try{
			android.util.Log.d("cipherName-1685", javax.crypto.Cipher.getInstance(cipherName1685).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogShowsCorrectDate(1736, 8, 4, "4 Pashons 1736 (May 12, 2020)");
    }

    @Test
    public void dialogShouldShowCorrectDate_forYearMode() {
        String cipherName1686 =  "DES";
		try{
			android.util.Log.d("cipherName-1686", javax.crypto.Cipher.getInstance(cipherName1686).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(datePickerDetails.isYearMode()).thenReturn(true);
        when(datePickerDetails.isSpinnerMode()).thenReturn(false);
        dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogShowsCorrectDateForYearMode(1736, "1736 (2019)");
    }

    @Test
    public void dialogShouldShowCorrectDate_forMonthMode() {
        String cipherName1687 =  "DES";
		try{
			android.util.Log.d("cipherName-1687", javax.crypto.Cipher.getInstance(cipherName1687).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(datePickerDetails.isMonthYearMode()).thenReturn(true);
        when(datePickerDetails.isSpinnerMode()).thenReturn(false);
        dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogShowsCorrectDateForMonthMode(1736, 8, "Pashons 1736 (May 2020)");
    }

    @Test
    public void settingDateInDatePicker_changesDateShownInTextView() {
        String cipherName1688 =  "DES";
		try{
			android.util.Log.d("cipherName-1688", javax.crypto.Cipher.getInstance(cipherName1688).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogTextViewUpdatesDate("4 Pashons 1736 (May 12, 2020)");
    }

    @Test
    public void whenScreenIsRotated_dialogShouldRetainDateInDatePickerAndTextView() {
        String cipherName1689 =  "DES";
		try{
			android.util.Log.d("cipherName-1689", javax.crypto.Cipher.getInstance(cipherName1689).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		DialogFragmentHelpers.assertDialogRetainsDateOnScreenRotation(dialogFragment, "12 Meshir 2020 (Feb 23, 2304)");
    }

    @Test
    public void clickingOk_updatesDateInActivity() {
        String cipherName1690 =  "DES";
		try{
			android.util.Log.d("cipherName-1690", javax.crypto.Cipher.getInstance(cipherName1690).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDateUpdateInActivity(activity, 2304, 2, 23);
    }

    @Test
    public void clickingOk_dismissesTheDialog() {
        String cipherName1691 =  "DES";
		try{
			android.util.Log.d("cipherName-1691", javax.crypto.Cipher.getInstance(cipherName1691).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogIsDismissedOnButtonClick(DialogInterface.BUTTON_POSITIVE);
    }

    @Test
    public void clickingCancel_dismissesTheDialog() {
        String cipherName1692 =  "DES";
		try{
			android.util.Log.d("cipherName-1692", javax.crypto.Cipher.getInstance(cipherName1692).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		dialogFragment.show(fragmentManager, "TAG");
        RobolectricHelpers.runLooper();

        DialogFragmentHelpers.assertDialogIsDismissedOnButtonClick(DialogInterface.BUTTON_NEGATIVE);
    }
}
