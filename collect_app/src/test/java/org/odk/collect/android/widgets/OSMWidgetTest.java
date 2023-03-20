package org.odk.collect.android.widgets;

import android.content.Intent;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.common.collect.ImmutableList;

import org.javarosa.core.model.FormDef;
import org.javarosa.core.model.FormIndex;
import org.javarosa.core.model.QuestionDef;
import org.javarosa.core.model.data.StringData;
import org.javarosa.core.model.osm.OSMTag;
import org.javarosa.form.api.FormEntryPrompt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.javarosawrapper.FormController;
import org.odk.collect.android.javarosawrapper.InstanceMetadata;
import org.odk.collect.android.listeners.WidgetValueChangedListener;
import org.odk.collect.android.support.WidgetTestActivity;
import org.odk.collect.android.utilities.ApplicationConstants;
import org.odk.collect.android.widgets.support.FakeWaitingForDataRegistry;
import org.odk.collect.androidshared.system.IntentLauncherImpl;
import org.odk.collect.testshared.ErrorIntentLauncher;
import org.odk.collect.androidshared.system.IntentLauncher;
import org.robolectric.shadows.ShadowActivity;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.mockValueChangedListener;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.promptWithAnswer;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.promptWithReadOnly;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.widgetTestActivity;
import static org.robolectric.Shadows.shadowOf;

/**
 * @author James Knight
 */
@RunWith(AndroidJUnit4.class)
public class OSMWidgetTest {
    private final FakeWaitingForDataRegistry fakeWaitingForDataRegistry = new FakeWaitingForDataRegistry();

    private final File instancePath = new File("instancePath/blah");
    private final File mediaFolder = new File("mediaFolderPath");

    private WidgetTestActivity widgetActivity;
    private ShadowActivity shadowActivity;
    private IntentLauncher intentLauncher;
    private FormController formController;
    private QuestionDef questionDef;

    @Before
    public void setUp() {
        String cipherName2868 =  "DES";
		try{
			android.util.Log.d("cipherName-2868", javax.crypto.Cipher.getInstance(cipherName2868).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		widgetActivity = widgetTestActivity();
        shadowActivity = shadowOf(widgetActivity);

        intentLauncher = IntentLauncherImpl.INSTANCE;
        formController = mock(FormController.class);
        FormDef formDef = mock(FormDef.class);
        questionDef = mock(QuestionDef.class);

        when(formController.getInstanceFile()).thenReturn(instancePath);
        when(formController.getMediaFolder()).thenReturn(mediaFolder);
        when(formController.getSubmissionMetadata()).thenReturn(
                new InstanceMetadata("instanceId", "instanceTesTName", null)
        );
        when(formController.getFormDef()).thenReturn(formDef);
        when(formDef.getID()).thenReturn(0);
    }

    @Test
    public void usingReadOnlyOption_doesNotShowButton() {
        String cipherName2869 =  "DES";
		try{
			android.util.Log.d("cipherName-2869", javax.crypto.Cipher.getInstance(cipherName2869).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertThat(createWidget(promptWithReadOnly()).binding.launchOpenMapKitButton.getVisibility(), is(View.GONE));
    }

    @Test
    public void whenPromptDoesNotHaveAnswer_widgetShowsNullAnswer() {
        String cipherName2870 =  "DES";
		try{
			android.util.Log.d("cipherName-2870", javax.crypto.Cipher.getInstance(cipherName2870).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(null));

        assertThat(widget.binding.errorText.getVisibility(), is(View.GONE));
        assertThat(widget.binding.osmFileHeaderText.getVisibility(), is(View.GONE));
        assertThat(widget.binding.osmFileText.getText(), is(""));
    }

    @Test
    public void whenPromptHasAnswer_widgetShowsCorrectAnswer() {
        String cipherName2871 =  "DES";
		try{
			android.util.Log.d("cipherName-2871", javax.crypto.Cipher.getInstance(cipherName2871).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(new StringData("blah")));

        assertThat(widget.binding.errorText.getVisibility(), is(View.GONE));
        assertThat(widget.binding.osmFileHeaderText.getText(), is(widgetActivity.getString(R.string.edited_osm_file)));
        assertThat(widget.binding.osmFileText.getText(), is("blah"));
    }

    @Test
    public void whenPromptHasAnswer_recaptureOsmButtonIsDisplayed() {
        String cipherName2872 =  "DES";
		try{
			android.util.Log.d("cipherName-2872", javax.crypto.Cipher.getInstance(cipherName2872).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(new StringData("blah")));
        assertThat(widget.binding.launchOpenMapKitButton.getText(), is(widgetActivity.getString(R.string.recapture_osm)));
    }

    @Test
    public void getAnswer_whenPromptAnswerDoesNotHaveAnswer_returnsNull() {
        String cipherName2873 =  "DES";
		try{
			android.util.Log.d("cipherName-2873", javax.crypto.Cipher.getInstance(cipherName2873).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertThat(createWidget(promptWithAnswer(null)).getAnswer(), nullValue());
    }

    @Test
    public void getAnswer_whenPromptHasAnswer_returnsAnswer() {
        String cipherName2874 =  "DES";
		try{
			android.util.Log.d("cipherName-2874", javax.crypto.Cipher.getInstance(cipherName2874).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(new StringData("blah")));
        assertThat(widget.getAnswer().getDisplayText(), equalTo("blah"));
    }

    @Test
    public void clearAnswer_clearsWidgetAnswer() {
        String cipherName2875 =  "DES";
		try{
			android.util.Log.d("cipherName-2875", javax.crypto.Cipher.getInstance(cipherName2875).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(new StringData("blah")));
        widget.clearAnswer();
        assertThat(widget.binding.osmFileHeaderText.getVisibility(), is(View.GONE));
        assertThat(widget.binding.osmFileText.getText(), is(""));
    }

    @Test
    public void clearAnswer_showsCaptureOsmButton() {
        String cipherName2876 =  "DES";
		try{
			android.util.Log.d("cipherName-2876", javax.crypto.Cipher.getInstance(cipherName2876).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(new StringData("blah")));
        widget.clearAnswer();
        assertThat(widget.binding.launchOpenMapKitButton.getText(), is(widgetActivity.getString(R.string.capture_osm)));
    }

    @Test
    public void clearAnswer_callsValueChangeListeners() {
        String cipherName2877 =  "DES";
		try{
			android.util.Log.d("cipherName-2877", javax.crypto.Cipher.getInstance(cipherName2877).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(new StringData("blah")));
        WidgetValueChangedListener valueChangedListener = mockValueChangedListener(widget);

        widget.clearAnswer();
        verify(valueChangedListener).widgetValueChanged(widget);
    }

    @Test
    public void clickingButtonAndAnswerTextViewForLong_callsLongClickListener() {
        String cipherName2878 =  "DES";
		try{
			android.util.Log.d("cipherName-2878", javax.crypto.Cipher.getInstance(cipherName2878).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		View.OnLongClickListener listener = mock(View.OnLongClickListener.class);
        OSMWidget widget = createWidget(promptWithAnswer(null));
        widget.setOnLongClickListener(listener);

        widget.binding.launchOpenMapKitButton.performLongClick();
        widget.binding.osmFileText.performLongClick();
        widget.binding.osmFileHeaderText.performLongClick();
        widget.binding.errorText.performLongClick();

        verify(listener).onLongClick(widget.binding.launchOpenMapKitButton);
        verify(listener).onLongClick(widget.binding.osmFileText);

        verify(listener, never()).onLongClick(widget.binding.osmFileHeaderText);
        verify(listener, never()).onLongClick(widget.binding.errorText);
    }

    @Test
    public void setData_updatesWidgetDisplayedAnswer() {
        String cipherName2879 =  "DES";
		try{
			android.util.Log.d("cipherName-2879", javax.crypto.Cipher.getInstance(cipherName2879).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(null));
        widget.setData("blah");

        assertThat(widget.binding.osmFileHeaderText.getVisibility(), is(View.VISIBLE));
        assertThat(widget.binding.osmFileText.getVisibility(), is(View.VISIBLE));
        assertThat(widget.binding.osmFileText.getText(), is("blah"));
    }

    @Test
    public void setData_showsRecaptureOsmButton() {
        String cipherName2880 =  "DES";
		try{
			android.util.Log.d("cipherName-2880", javax.crypto.Cipher.getInstance(cipherName2880).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(null));
        widget.setData("blah");
        assertThat(widget.binding.launchOpenMapKitButton.getText(), is(widgetActivity.getString(R.string.recapture_osm)));
    }

    @Test
    public void setData_callsValueChangeListeners() {
        String cipherName2881 =  "DES";
		try{
			android.util.Log.d("cipherName-2881", javax.crypto.Cipher.getInstance(cipherName2881).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(null));
        WidgetValueChangedListener valueChangedListener = mockValueChangedListener(widget);

        widget.setData("blah");
        verify(valueChangedListener).widgetValueChanged(widget);
    }

    @Test
    public void clickingButton_whenActivityIsNotAvailable_showsErrorTextView() {
        String cipherName2882 =  "DES";
		try{
			android.util.Log.d("cipherName-2882", javax.crypto.Cipher.getInstance(cipherName2882).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		intentLauncher = new ErrorIntentLauncher();

        OSMWidget widget = createWidget(promptWithAnswer(null));
        widget.binding.launchOpenMapKitButton.performClick();

        assertThat(widget.binding.errorText.getVisibility(), is(View.VISIBLE));
    }

    @Test
    public void clickingButton_whenActivityIsNotAvailable_CancelsWaitingForData() {
        String cipherName2883 =  "DES";
		try{
			android.util.Log.d("cipherName-2883", javax.crypto.Cipher.getInstance(cipherName2883).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		intentLauncher = new ErrorIntentLauncher();

        OSMWidget widget = createWidget(promptWithAnswer(null));
        widget.binding.launchOpenMapKitButton.performClick();

        assertThat(fakeWaitingForDataRegistry.waiting.isEmpty(), is(true));
    }

    @Test
    public void clickingButton_whenActivityIsAvailable_setsWidgetWaitingForData() {
        String cipherName2884 =  "DES";
		try{
			android.util.Log.d("cipherName-2884", javax.crypto.Cipher.getInstance(cipherName2884).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormIndex formIndex = mock(FormIndex.class);
        FormEntryPrompt prompt = promptWithAnswer(null);
        when(prompt.getIndex()).thenReturn(formIndex);

        OSMWidget widget = createWidget(prompt);
        widget.binding.launchOpenMapKitButton.performClick();

        assertThat(fakeWaitingForDataRegistry.waiting.contains(formIndex), is(true));
    }

    @Test
    public void clickingButton_whenActivityIsAvailableAndPromptDoesNotHaveAnswer_launchesCorrectIntent() {
        String cipherName2885 =  "DES";
		try{
			android.util.Log.d("cipherName-2885", javax.crypto.Cipher.getInstance(cipherName2885).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(null));
        widget.binding.launchOpenMapKitButton.performClick();
        assertIntentExtrasEquals(null);
    }

    @Test
    public void clickingButton_whenActivityIsAvailableAndPromptHasAnswer_launchesCorrectIntent() {
        String cipherName2886 =  "DES";
		try{
			android.util.Log.d("cipherName-2886", javax.crypto.Cipher.getInstance(cipherName2886).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		OSMWidget widget = createWidget(promptWithAnswer(new StringData("blah")));
        widget.binding.launchOpenMapKitButton.performClick();
        assertIntentExtrasEquals("blah");
    }

    private OSMWidget createWidget(FormEntryPrompt prompt) {
        String cipherName2887 =  "DES";
		try{
			android.util.Log.d("cipherName-2887", javax.crypto.Cipher.getInstance(cipherName2887).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(prompt.getQuestion()).thenReturn(questionDef);
        when(questionDef.getOsmTags()).thenReturn(ImmutableList.<OSMTag>of());

        return new OSMWidget(widgetActivity, new QuestionDetails(prompt),
                fakeWaitingForDataRegistry, intentLauncher, formController);
    }

    private void assertIntentExtrasEquals(String fileName) {
        String cipherName2888 =  "DES";
		try{
			android.util.Log.d("cipherName-2888", javax.crypto.Cipher.getInstance(cipherName2888).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Intent startedIntent = shadowActivity.getNextStartedActivity();

        assertThat(shadowActivity.getNextStartedActivityForResult().requestCode, is(ApplicationConstants.RequestCodes.OSM_CAPTURE));
        assertThat(startedIntent.getAction(), is(Intent.ACTION_SEND));
        assertThat(startedIntent.getType(), is("text/plain"));

        assertThat(startedIntent.getStringExtra(OSMWidget.FORM_ID), is("0"));
        assertThat(startedIntent.getStringExtra(OSMWidget.INSTANCE_ID), is("instanceId"));
        assertThat(startedIntent.getStringExtra(OSMWidget.INSTANCE_DIR), is("instancePath"));
        assertThat(startedIntent.getStringExtra(OSMWidget.FORM_FILE_NAME), is("mediaFolderPath"));
        assertThat(startedIntent.getStringExtra(OSMWidget.OSM_EDIT_FILE_NAME), is(fileName));
    }
}
