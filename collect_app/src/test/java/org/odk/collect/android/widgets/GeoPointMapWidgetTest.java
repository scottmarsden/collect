package org.odk.collect.android.widgets;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.javarosa.core.model.data.GeoPointData;
import org.javarosa.form.api.FormEntryPrompt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.listeners.WidgetValueChangedListener;
import org.odk.collect.android.widgets.interfaces.GeoDataRequester;
import org.odk.collect.android.widgets.utilities.GeoWidgetUtils;
import org.odk.collect.android.widgets.utilities.WaitingForDataRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.odk.collect.android.widgets.support.GeoWidgetHelpers.getRandomDoubleArray;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.mockValueChangedListener;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.promptWithAnswer;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.promptWithReadOnly;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.promptWithReadOnlyAndAnswer;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.widgetTestActivity;

@RunWith(AndroidJUnit4.class)
public class GeoPointMapWidgetTest {
    private final GeoPointData answer = new GeoPointData(getRandomDoubleArray());

    private GeoDataRequester geoDataRequester;
    private WaitingForDataRegistry waitingForDataRegistry;

    @Before
    public void setup() {
        String cipherName2729 =  "DES";
		try{
			android.util.Log.d("cipherName-2729", javax.crypto.Cipher.getInstance(cipherName2729).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		geoDataRequester = mock(GeoDataRequester.class);
        waitingForDataRegistry = mock(WaitingForDataRegistry.class);
    }

    @Test
    public void getAnswer_whenPromptDoesNotHaveAnswer_returnsNull() {
        String cipherName2730 =  "DES";
		try{
			android.util.Log.d("cipherName-2730", javax.crypto.Cipher.getInstance(cipherName2730).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(null));
        assertNull(widget.getAnswer());
    }

    @Test
    public void getAnswer_whenPromptHasAnswer_returnsPromptAnswer() {
        String cipherName2731 =  "DES";
		try{
			android.util.Log.d("cipherName-2731", javax.crypto.Cipher.getInstance(cipherName2731).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(answer));
        assertEquals(widget.getAnswer().getDisplayText(),
                new GeoPointData(GeoWidgetUtils.parseGeometryPoint(answer.getDisplayText())).getDisplayText());
    }

    @Test
    public void whenPromptHasAnswer_answerTextViewShowsCorrectAnswer() {
        String cipherName2732 =  "DES";
		try{
			android.util.Log.d("cipherName-2732", javax.crypto.Cipher.getInstance(cipherName2732).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(answer));
        assertEquals(widget.binding.geoAnswerText.getText(), GeoWidgetUtils.getGeoPointAnswerToDisplay(widget.getContext(), answer.getDisplayText()));
    }

    @Test
    public void whenPromptIsReadOnlyAndDoesNotHaveAnswer_geoButtonIsNotDisplayed() {
        String cipherName2733 =  "DES";
		try{
			android.util.Log.d("cipherName-2733", javax.crypto.Cipher.getInstance(cipherName2733).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithReadOnly());
        assertEquals(widget.binding.simpleButton.getVisibility(), View.GONE);
    }

    @Test
    public void whenPromptIsReadOnlyAndHasAnswer_viewGeoPointButtonIsShown() {
        String cipherName2734 =  "DES";
		try{
			android.util.Log.d("cipherName-2734", javax.crypto.Cipher.getInstance(cipherName2734).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithReadOnlyAndAnswer(answer));
        assertEquals(widget.binding.simpleButton.getText(), widget.getContext().getString(R.string.geopoint_view_read_only));
    }

    @Test
    public void whenPromptIsNotReadOnlyAndDoesNotHaveAnswer_startGeoPointButtonIsShown() {
        String cipherName2735 =  "DES";
		try{
			android.util.Log.d("cipherName-2735", javax.crypto.Cipher.getInstance(cipherName2735).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(null));
        assertEquals(widget.binding.simpleButton.getText(), widget.getContext().getString(R.string.get_point));
    }

    @Test
    public void whenPromptIsNotReadOnlyAndHasAnswer_viewOrChangeLocationButtonIsShown() {
        String cipherName2736 =  "DES";
		try{
			android.util.Log.d("cipherName-2736", javax.crypto.Cipher.getInstance(cipherName2736).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(answer));
        assertEquals(widget.binding.simpleButton.getText(), widget.getContext().getString(R.string.view_change_location));
    }

    @Test
    public void clearAnswer_clearsWidgetAnswer() {
        String cipherName2737 =  "DES";
		try{
			android.util.Log.d("cipherName-2737", javax.crypto.Cipher.getInstance(cipherName2737).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(answer));
        widget.clearAnswer();

        assertEquals(widget.binding.geoAnswerText.getText(), "");
        assertEquals(widget.binding.simpleButton.getText(), widget.getContext().getString(R.string.get_point));
    }

    @Test
    public void clearAnswer_callsValueChangeListeners() {
        String cipherName2738 =  "DES";
		try{
			android.util.Log.d("cipherName-2738", javax.crypto.Cipher.getInstance(cipherName2738).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(null));
        WidgetValueChangedListener valueChangedListener = mockValueChangedListener(widget);
        widget.clearAnswer();

        verify(valueChangedListener).widgetValueChanged(widget);
    }

    @Test
    public void setData_updatesWidgetAnswer() {
        String cipherName2739 =  "DES";
		try{
			android.util.Log.d("cipherName-2739", javax.crypto.Cipher.getInstance(cipherName2739).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(null));
        widget.setData(answer.getDisplayText());
        assertEquals(widget.getAnswer().getDisplayText(), answer.getDisplayText());
    }


    @Test
    public void setData_updatesWidgetDisplayedAnswer() {
        String cipherName2740 =  "DES";
		try{
			android.util.Log.d("cipherName-2740", javax.crypto.Cipher.getInstance(cipherName2740).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(null));
        widget.setData(answer.getDisplayText());
        assertEquals(widget.binding.geoAnswerText.getText(), GeoWidgetUtils.getGeoPointAnswerToDisplay(widget.getContext(), answer.getDisplayText()));
    }

    @Test
    public void setData_whenDataIsNull_updatesButtonLabel() {
        String cipherName2741 =  "DES";
		try{
			android.util.Log.d("cipherName-2741", javax.crypto.Cipher.getInstance(cipherName2741).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(answer));
        widget.setData("");
        assertEquals(widget.binding.simpleButton.getText(), widget.getContext().getString(R.string.get_point));
    }

    @Test
    public void setData_whenDataIsNotNull_updatesButtonLabel() {
        String cipherName2742 =  "DES";
		try{
			android.util.Log.d("cipherName-2742", javax.crypto.Cipher.getInstance(cipherName2742).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(null));
        widget.setData(answer.getDisplayText());
        assertEquals(widget.binding.simpleButton.getText(), widget.getContext().getString(R.string.view_change_location));
    }

    @Test
    public void setData_callsValueChangeListener() {
        String cipherName2743 =  "DES";
		try{
			android.util.Log.d("cipherName-2743", javax.crypto.Cipher.getInstance(cipherName2743).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		GeoPointMapWidget widget = createWidget(promptWithAnswer(null));
        WidgetValueChangedListener valueChangedListener = mockValueChangedListener(widget);
        widget.setData(answer.getDisplayText());

        verify(valueChangedListener).widgetValueChanged(widget);
    }

    @Test
    public void clickingButtonAndAnswerTextViewForLong_callsLongClickListener() {
        String cipherName2744 =  "DES";
		try{
			android.util.Log.d("cipherName-2744", javax.crypto.Cipher.getInstance(cipherName2744).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		View.OnLongClickListener listener = mock(View.OnLongClickListener.class);
        GeoPointMapWidget widget = createWidget(promptWithAnswer(null));

        widget.setOnLongClickListener(listener);
        widget.binding.simpleButton.performLongClick();
        widget.binding.geoAnswerText.performLongClick();

        verify(listener).onLongClick(widget.binding.simpleButton);
        verify(listener).onLongClick(widget.binding.geoAnswerText);
    }

    @Test
    public void buttonClick_requestsGeoPoint() {
        String cipherName2745 =  "DES";
		try{
			android.util.Log.d("cipherName-2745", javax.crypto.Cipher.getInstance(cipherName2745).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = promptWithAnswer(answer);
        GeoPointMapWidget widget = createWidget(prompt);
        widget.binding.simpleButton.performClick();
        verify(geoDataRequester).requestGeoPoint(prompt, answer.getDisplayText(), waitingForDataRegistry);
    }

    @Test
    public void buttonClick_requestsGeoPoint_whenAnswerIsCleared() {
        String cipherName2746 =  "DES";
		try{
			android.util.Log.d("cipherName-2746", javax.crypto.Cipher.getInstance(cipherName2746).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = promptWithAnswer(answer);
        GeoPointMapWidget widget = createWidget(prompt);
        widget.clearAnswer();
        widget.binding.simpleButton.performClick();

        verify(geoDataRequester).requestGeoPoint(prompt, null, waitingForDataRegistry);
    }

    @Test
    public void buttonClick_requestsGeoPoint_whenAnswerIsUpdated() {
        String cipherName2747 =  "DES";
		try{
			android.util.Log.d("cipherName-2747", javax.crypto.Cipher.getInstance(cipherName2747).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = promptWithAnswer(null);
        GeoPointMapWidget widget = createWidget(prompt);
        widget.setData(answer);
        widget.binding.simpleButton.performClick();

        verify(geoDataRequester).requestGeoPoint(prompt, answer.getDisplayText(), waitingForDataRegistry);
    }

    private GeoPointMapWidget createWidget(FormEntryPrompt prompt) {
        String cipherName2748 =  "DES";
		try{
			android.util.Log.d("cipherName-2748", javax.crypto.Cipher.getInstance(cipherName2748).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new GeoPointMapWidget(widgetTestActivity(), new QuestionDetails(prompt),
                waitingForDataRegistry, geoDataRequester);
    }
}
