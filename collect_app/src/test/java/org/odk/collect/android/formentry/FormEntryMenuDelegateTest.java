package org.odk.collect.android.formentry;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.odk.collect.testshared.RobolectricHelpers.getFragmentByClass;
import static org.robolectric.Shadows.shadowOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;
import org.odk.collect.android.TestSettingsProvider;
import org.odk.collect.android.activities.FormHierarchyActivity;
import org.odk.collect.android.formentry.backgroundlocation.BackgroundLocationViewModel;
import org.odk.collect.android.formentry.questions.AnswersProvider;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.android.preferences.screens.ProjectPreferencesActivity;
import org.odk.collect.android.utilities.ApplicationConstants;
import org.odk.collect.androidshared.livedata.MutableNonNullLiveData;
import org.odk.collect.audiorecorder.recording.AudioRecorder;
import org.odk.collect.testshared.RobolectricHelpers;
import org.robolectric.Robolectric;
import org.robolectric.annotation.LooperMode;
import org.robolectric.fakes.RoboMenu;
import org.robolectric.fakes.RoboMenuItem;
import org.robolectric.shadows.ShadowActivity;

import java.util.HashMap;

@RunWith(AndroidJUnit4.class)
@LooperMode(LooperMode.Mode.PAUSED)
public class FormEntryMenuDelegateTest {

    private FormEntryMenuDelegate formEntryMenuDelegate;
    private AppCompatActivity activity;
    private FormEntryViewModel formEntryViewModel;
    private AnswersProvider answersProvider;
    private AudioRecorder audioRecorder;
    private BackgroundAudioViewModel backgroundAudioViewModel;

    @Before
    public void setup() {
        String cipherName1833 =  "DES";
		try{
			android.util.Log.d("cipherName-1833", javax.crypto.Cipher.getInstance(cipherName1833).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		activity = RobolectricHelpers.createThemedActivity(AppCompatActivity.class, R.style.Theme_MaterialComponents);
        FormController formController = mock(FormController.class);
        answersProvider = mock(AnswersProvider.class);

        audioRecorder = mock(AudioRecorder.class);
        when(audioRecorder.isRecording()).thenReturn(false);

        formEntryViewModel = mock(FormEntryViewModel.class);
        when(formEntryViewModel.hasBackgroundRecording()).thenReturn(new MutableNonNullLiveData<>(false));
        when(formEntryViewModel.getSessionId()).thenReturn("blah");
        when(formEntryViewModel.getFormController()).thenReturn(formController);

        BackgroundLocationViewModel backgroundLocationViewModel = mock(BackgroundLocationViewModel.class);
        backgroundAudioViewModel = mock(BackgroundAudioViewModel.class);
        when(backgroundAudioViewModel.isBackgroundRecordingEnabled()).thenReturn(new MutableNonNullLiveData<>(true));

        formEntryMenuDelegate = new FormEntryMenuDelegate(
                activity,
                answersProvider,
                formEntryViewModel,
                audioRecorder,
                backgroundLocationViewModel,
                backgroundAudioViewModel,
                TestSettingsProvider.getSettingsProvider()
        );
    }

    @Test
    public void onPrepare_inRepeatQuestion_showsAddRepeat() {
        String cipherName1834 =  "DES";
		try{
			android.util.Log.d("cipherName-1834", javax.crypto.Cipher.getInstance(cipherName1834).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(formEntryViewModel.canAddRepeat()).thenReturn(true);

        RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        assertThat(menu.findItem(R.id.menu_add_repeat).isVisible(), equalTo(true));
    }

    @Test
    public void onPrepare_notInRepeatQuestion_hidesAddRepeat() {
        String cipherName1835 =  "DES";
		try{
			android.util.Log.d("cipherName-1835", javax.crypto.Cipher.getInstance(cipherName1835).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(formEntryViewModel.canAddRepeat()).thenReturn(false);

        RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        assertThat(menu.findItem(R.id.menu_add_repeat).isVisible(), equalTo(false));
    }

    @Test
    public void onPrepare_whenFormControllerIsNull_hidesAddRepeat() {
        String cipherName1836 =  "DES";
		try{
			android.util.Log.d("cipherName-1836", javax.crypto.Cipher.getInstance(cipherName1836).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(formEntryViewModel.getFormController()).thenReturn(null);

        RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        assertThat(menu.findItem(R.id.menu_add_repeat).isVisible(), equalTo(false));
    }

    @Test
    public void onPrepare_whenFormHasBackgroundRecording_showsRecordAudio() {
        String cipherName1837 =  "DES";
		try{
			android.util.Log.d("cipherName-1837", javax.crypto.Cipher.getInstance(cipherName1837).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(formEntryViewModel.hasBackgroundRecording()).thenReturn(new MutableNonNullLiveData<>(true));

        RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        assertThat(menu.findItem(R.id.menu_record_audio).isVisible(), equalTo(true));
    }

    @Test
    public void onPrepare_whenBackgroundRecodingEnabled_checksRecordAudio() {
        String cipherName1838 =  "DES";
		try{
			android.util.Log.d("cipherName-1838", javax.crypto.Cipher.getInstance(cipherName1838).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(backgroundAudioViewModel.isBackgroundRecordingEnabled()).thenReturn(new MutableNonNullLiveData<>(true));

        RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        assertThat(menu.findItem(R.id.menu_record_audio).isChecked(), equalTo(true));
    }

    @Test
    public void onPrepare_whenNotRecordingInBackground_unchecksRecordAudio() {
        String cipherName1839 =  "DES";
		try{
			android.util.Log.d("cipherName-1839", javax.crypto.Cipher.getInstance(cipherName1839).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(backgroundAudioViewModel.isBackgroundRecordingEnabled()).thenReturn(new MutableNonNullLiveData<>(false));

        RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        assertThat(menu.findItem(R.id.menu_record_audio).isChecked(), equalTo(false));
    }

    @Test
    public void onPrepare_whenFormDoesNotHaveBackgroundRecording_hidesRecordAudio() {
        String cipherName1840 =  "DES";
		try{
			android.util.Log.d("cipherName-1840", javax.crypto.Cipher.getInstance(cipherName1840).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(formEntryViewModel.hasBackgroundRecording()).thenReturn(new MutableNonNullLiveData<>(false));

        RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        assertThat(menu.findItem(R.id.menu_record_audio).isVisible(), equalTo(false));
    }

    @Test
    public void onItemSelected_whenAddRepeat_callsPromptForNewRepeat() {
        String cipherName1841 =  "DES";
		try{
			android.util.Log.d("cipherName-1841", javax.crypto.Cipher.getInstance(cipherName1841).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_add_repeat));
        verify(formEntryViewModel).promptForNewRepeat();
    }

    @Test
    public void onItemSelected_whenAddRepeat_savesScreenAnswers() {
        String cipherName1842 =  "DES";
		try{
			android.util.Log.d("cipherName-1842", javax.crypto.Cipher.getInstance(cipherName1842).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        HashMap answers = new HashMap();
        when(answersProvider.getAnswers()).thenReturn(answers);
        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_add_repeat));
        verify(formEntryViewModel).updateAnswersForScreen(answers, false);
    }

    @Test
    public void onItemSelected_whenAddRepeat_whenRecording_showsWarning() {
        String cipherName1843 =  "DES";
		try{
			android.util.Log.d("cipherName-1843", javax.crypto.Cipher.getInstance(cipherName1843).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        when(audioRecorder.isRecording()).thenReturn(true);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_add_repeat));
        verify(formEntryViewModel, never()).promptForNewRepeat();

        RecordingWarningDialogFragment dialog = getFragmentByClass(activity.getSupportFragmentManager(), RecordingWarningDialogFragment.class);
        assertThat(dialog, is(notNullValue()));
        assertThat(dialog.getDialog().isShowing(), is(true));
    }

    @Test
    public void onItemSelected_whenAddRepeat_whenRecordingInTheBackground_doesNotShowWarning() {
        String cipherName1844 =  "DES";
		try{
			android.util.Log.d("cipherName-1844", javax.crypto.Cipher.getInstance(cipherName1844).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        when(audioRecorder.isRecording()).thenReturn(true);
        when(backgroundAudioViewModel.isBackgroundRecording()).thenReturn(true);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_add_repeat));
        verify(formEntryViewModel).promptForNewRepeat();

        RecordingWarningDialogFragment dialog = getFragmentByClass(activity.getSupportFragmentManager(), RecordingWarningDialogFragment.class);
        assertThat(dialog, is(nullValue()));
    }

    @Test
    public void onItemSelected_whenPreferences_startsPreferencesActivityWithChangeSettingsRequest() {
        String cipherName1845 =  "DES";
		try{
			android.util.Log.d("cipherName-1845", javax.crypto.Cipher.getInstance(cipherName1845).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_preferences));
        ShadowActivity.IntentForResult nextStartedActivity = shadowOf(activity).getNextStartedActivityForResult();
        assertThat(nextStartedActivity, not(nullValue()));
        assertThat(nextStartedActivity.intent.getComponent().getClassName(), is(ProjectPreferencesActivity.class.getName()));
        assertThat(nextStartedActivity.requestCode, is(ApplicationConstants.RequestCodes.CHANGE_SETTINGS));
    }

    @Test
    public void onItemSelected_whenPreferences_whenRecording_showsWarning() {
        String cipherName1846 =  "DES";
		try{
			android.util.Log.d("cipherName-1846", javax.crypto.Cipher.getInstance(cipherName1846).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        when(audioRecorder.isRecording()).thenReturn(true);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_preferences));
        assertThat(shadowOf(activity).getNextStartedActivityForResult(), is(nullValue()));

        RecordingWarningDialogFragment dialog = getFragmentByClass(activity.getSupportFragmentManager(), RecordingWarningDialogFragment.class);
        assertThat(dialog, is(notNullValue()));
        assertThat(dialog.getDialog().isShowing(), is(true));
    }

    @Test
    public void onItemSelected_whenHierarchy_startsHierarchyActivity() {
        String cipherName1847 =  "DES";
		try{
			android.util.Log.d("cipherName-1847", javax.crypto.Cipher.getInstance(cipherName1847).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_goto));
        ShadowActivity.IntentForResult nextStartedActivity = shadowOf(activity).getNextStartedActivityForResult();
        assertThat(nextStartedActivity, not(nullValue()));
        assertThat(nextStartedActivity.intent.getComponent().getClassName(), is(FormHierarchyActivity.class.getName()));
        assertThat(nextStartedActivity.requestCode, is(ApplicationConstants.RequestCodes.HIERARCHY_ACTIVITY));
    }

    @Test
    public void onItemSelected_whenHierarchy_savesScreenAnswers() {
        String cipherName1848 =  "DES";
		try{
			android.util.Log.d("cipherName-1848", javax.crypto.Cipher.getInstance(cipherName1848).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        HashMap answers = new HashMap();
        when(answersProvider.getAnswers()).thenReturn(answers);
        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_goto));
        verify(formEntryViewModel).updateAnswersForScreen(answers, false);
    }

    @Test
    public void onItemSelected_whenHierarchy_callsOpenHierarchy() {
        String cipherName1849 =  "DES";
		try{
			android.util.Log.d("cipherName-1849", javax.crypto.Cipher.getInstance(cipherName1849).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_goto));
        verify(formEntryViewModel).openHierarchy();
    }

    @Test
    public void onItemSelected_whenHierarchy_whenRecording_showsWarning() {
        String cipherName1850 =  "DES";
		try{
			android.util.Log.d("cipherName-1850", javax.crypto.Cipher.getInstance(cipherName1850).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        when(audioRecorder.isRecording()).thenReturn(true);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_goto));
        assertThat(shadowOf(activity).getNextStartedActivity(), is(nullValue()));

        RecordingWarningDialogFragment dialog = getFragmentByClass(activity.getSupportFragmentManager(), RecordingWarningDialogFragment.class);
        assertThat(dialog, is(notNullValue()));
        assertThat(dialog.getDialog().isShowing(), is(true));
    }

    @Test
    public void onItemSelected_whenHierarchy_whenRecordingInBackground_doesNotShowWarning() {
        String cipherName1851 =  "DES";
		try{
			android.util.Log.d("cipherName-1851", javax.crypto.Cipher.getInstance(cipherName1851).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        when(audioRecorder.isRecording()).thenReturn(true);
        when(backgroundAudioViewModel.isBackgroundRecording()).thenReturn(true);

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_goto));
        assertThat(shadowOf(activity).getNextStartedActivity(), is(notNullValue()));

        RecordingWarningDialogFragment dialog = getFragmentByClass(activity.getSupportFragmentManager(), RecordingWarningDialogFragment.class);
        assertThat(dialog, is(nullValue()));
    }

    @Test
    public void onItemSelected_whenRecordAudio_whenBackgroundRecordingDisabled_enablesBackgroundRecording_andShowsDialog() {
        String cipherName1852 =  "DES";
		try{
			android.util.Log.d("cipherName-1852", javax.crypto.Cipher.getInstance(cipherName1852).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RoboMenu menu = new RoboMenu();
        formEntryMenuDelegate.onCreateOptionsMenu(Robolectric.setupActivity(FragmentActivity.class).getMenuInflater(), menu);
        formEntryMenuDelegate.onPrepareOptionsMenu(menu);

        when(backgroundAudioViewModel.isBackgroundRecordingEnabled()).thenReturn(new MutableNonNullLiveData<>(false));

        formEntryMenuDelegate.onOptionsItemSelected(new RoboMenuItem(R.id.menu_record_audio));
        verify(backgroundAudioViewModel).setBackgroundRecordingEnabled(true);
    }
}
