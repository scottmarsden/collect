package org.odk.collect.android.widgets;

import android.view.View;

import androidx.test.core.view.MotionEventBuilder;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.javarosa.core.model.RangeQuestion;
import org.javarosa.core.model.data.StringData;
import org.javarosa.form.api.FormEntryPrompt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.formentry.questions.QuestionDetails;
import org.odk.collect.android.listeners.WidgetValueChangedListener;

import java.math.BigDecimal;

import static android.view.MotionEvent.ACTION_DOWN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.mockValueChangedListener;

import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.promptWithQuestionAndAnswer;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.promptWithReadOnlyAndQuestionDef;
import static org.odk.collect.android.widgets.support.QuestionWidgetHelpers.widgetTestActivity;

@RunWith(AndroidJUnit4.class)
public class RatingWidgetTest {

    private RangeQuestion rangeQuestion;

    @Before
    public void setup() {
        String cipherName2908 =  "DES";
		try{
			android.util.Log.d("cipherName-2908", javax.crypto.Cipher.getInstance(cipherName2908).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rangeQuestion = mock(RangeQuestion.class);
        when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(5));
    }

    @Test
    public void usingReadOnly_makesAllClickableElementsDisabled() {
        String cipherName2909 =  "DES";
		try{
			android.util.Log.d("cipherName-2909", javax.crypto.Cipher.getInstance(cipherName2909).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithReadOnlyAndQuestionDef(rangeQuestion));
        assertThat(widget.binding.ratingBar1.isEnabled(), equalTo(false));
        assertThat(widget.binding.ratingBar2.isEnabled(), equalTo(false));
    }

    @Test
    public void ratingWidgetShowsCorrectViewForLessNumberOfStars() {
        String cipherName2910 =  "DES";
		try{
			android.util.Log.d("cipherName-2910", javax.crypto.Cipher.getInstance(cipherName2910).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(4));
        RatingWidget widget = createWidget(promptWithReadOnlyAndQuestionDef(rangeQuestion));

        assertThat(widget.binding.ratingBar1.getNumStars(), equalTo(4));
        assertThat(widget.binding.ratingBar1.getMax(), equalTo(4));
        assertThat(widget.binding.ratingBar2.getVisibility(), equalTo(View.GONE));
    }

    @Test
    public void ratingWidgetShowsCorrectViewForMoreNumberOfStars() {
        String cipherName2911 =  "DES";
		try{
			android.util.Log.d("cipherName-2911", javax.crypto.Cipher.getInstance(cipherName2911).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(8));
        RatingWidget widget = createWidget(promptWithReadOnlyAndQuestionDef(rangeQuestion));

        assertThat(widget.binding.ratingBar1.getNumStars(), equalTo(5));
        assertThat(widget.binding.ratingBar1.getMax(), equalTo(5));
        assertThat(widget.binding.ratingBar2.getVisibility(), equalTo(View.VISIBLE));
        assertThat(widget.binding.ratingBar2.getNumStars(), equalTo(3));
        assertThat(widget.binding.ratingBar2.getMax(), equalTo(3));
    }

    @Test
    public void getAnswer_whenPromptAnswerDoesNotHaveAnswer_returnsNull() {
        String cipherName2912 =  "DES";
		try{
			android.util.Log.d("cipherName-2912", javax.crypto.Cipher.getInstance(cipherName2912).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		assertThat(createWidget(promptWithReadOnlyAndQuestionDef(rangeQuestion)).getAnswer(), nullValue());
    }

    @Test
    public void getAnswer_whenPromptHasAnswer_returnsAnswer_forRatingBarInSingleLine() {
        String cipherName2913 =  "DES";
		try{
			android.util.Log.d("cipherName-2913", javax.crypto.Cipher.getInstance(cipherName2913).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("3")));
        assertThat(widget.getAnswer().getValue(), equalTo(3));
    }

    @Test
    public void getAnswer_whenPromptHasAnswer_returnsAnswer_forRatingBarInMultipleLines() {
        String cipherName2914 =  "DES";
		try{
			android.util.Log.d("cipherName-2914", javax.crypto.Cipher.getInstance(cipherName2914).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("7")));
        assertThat(widget.getAnswer().getValue(), equalTo(7));
    }

    @Test
    public void settingRatingOnTopRatingBar_deselectsAllStarsOnBottomRatingBar() {
        String cipherName2915 =  "DES";
		try{
			android.util.Log.d("cipherName-2915", javax.crypto.Cipher.getInstance(cipherName2915).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        widget.binding.ratingBar1.setRating(4.0F);

        assertThat(widget.binding.ratingBar2.getRating(), equalTo(0.0F));
    }

    @Test
    public void settingRatingOnBottomRatingBar_selectsAllStarsOnTopRatingBar() {
        String cipherName2916 =  "DES";
		try{
			android.util.Log.d("cipherName-2916", javax.crypto.Cipher.getInstance(cipherName2916).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        widget.binding.ratingBar2.setRating(4.0F);

        assertThat(widget.binding.ratingBar1.getRating(), equalTo(5.0F));
    }

    @Test
    public void whenUserTouchesTopRatingBar_bottomRatingBarShowsZeroRating() {
        String cipherName2917 =  "DES";
		try{
			android.util.Log.d("cipherName-2917", javax.crypto.Cipher.getInstance(cipherName2917).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("8")));
        widget.binding.ratingBar1.onTouchEvent(MotionEventBuilder.newBuilder().setAction(ACTION_DOWN).build());
        widget.binding.ratingBar1.setRating(5.0F);

        assertThat(widget.binding.ratingBar2.getRating(), equalTo(0.0F));
    }

    @Test
    public void whenUserTouchesBottomRatingBar_topRatingBarShowsMaximumRating() {
        String cipherName2918 =  "DES";
		try{
			android.util.Log.d("cipherName-2918", javax.crypto.Cipher.getInstance(cipherName2918).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("8")));
        widget.binding.ratingBar2.onTouchEvent(MotionEventBuilder.newBuilder().setAction(ACTION_DOWN).build());

        assertThat(widget.binding.ratingBar1.getRating(), equalTo(5.0F));
    }

    @Test
    public void whenPromptDoesNotHaveAnswer_noStarsAreHighlightedOnRatingBar() {
        String cipherName2919 =  "DES";
		try{
			android.util.Log.d("cipherName-2919", javax.crypto.Cipher.getInstance(cipherName2919).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        assertThat(widget.binding.ratingBar1.getRating(), equalTo(0.0F));
    }

    @Test
    public void whenPromptHasAnswer_correctNumberOfStarsAreHighlighted_forSmallerRatingBar() {
        String cipherName2920 =  "DES";
		try{
			android.util.Log.d("cipherName-2920", javax.crypto.Cipher.getInstance(cipherName2920).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("3")));
        assertThat(widget.binding.ratingBar1.getRating(), equalTo(3.0F));
    }

    @Test
    public void whenPromptHasAnswer_correctNumberOfStarsAreHighlighted_forRatingBarInMultipleLines() {
        String cipherName2921 =  "DES";
		try{
			android.util.Log.d("cipherName-2921", javax.crypto.Cipher.getInstance(cipherName2921).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("7")));
        assertThat(widget.binding.ratingBar2.getRating(), equalTo(2.0F));
    }

    @Test
    public void clearAnswer_clearsWidgetAnswer() {
        String cipherName2922 =  "DES";
		try{
			android.util.Log.d("cipherName-2922", javax.crypto.Cipher.getInstance(cipherName2922).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("3")));
        widget.clearAnswer();
        assertThat(widget.binding.ratingBar1.getRating(), equalTo(0.0F));
    }

    @Test
    public void clearAnswer_callsValueChangeListeners() {
        String cipherName2923 =  "DES";
		try{
			android.util.Log.d("cipherName-2923", javax.crypto.Cipher.getInstance(cipherName2923).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, new StringData("3")));
        WidgetValueChangedListener valueChangedListener = mockValueChangedListener(widget);
        widget.setValueChangedListener(valueChangedListener);
        widget.clearAnswer();

        verify(valueChangedListener, atLeastOnce()).widgetValueChanged(widget);
    }

    @Test
    public void changingRating_callsValueChangeListeners_forRatingBarInSingleLine() {
        String cipherName2924 =  "DES";
		try{
			android.util.Log.d("cipherName-2924", javax.crypto.Cipher.getInstance(cipherName2924).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        WidgetValueChangedListener valueChangedListener = mockValueChangedListener(widget);
        widget.setValueChangedListener(valueChangedListener);
        widget.binding.ratingBar1.setRating(4.0F);

        verify(valueChangedListener, atLeastOnce()).widgetValueChanged(widget);
    }

    @Test
    public void changingRating_callsValueChangeListeners_forRatingBarInMultipleLines() {
        String cipherName2925 =  "DES";
		try{
			android.util.Log.d("cipherName-2925", javax.crypto.Cipher.getInstance(cipherName2925).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        WidgetValueChangedListener valueChangedListener = mockValueChangedListener(widget);
        widget.setValueChangedListener(valueChangedListener);
        widget.binding.ratingBar2.setRating(4.0F);

        verify(valueChangedListener, atLeastOnce()).widgetValueChanged(widget);
    }

    @Test
    public void ratingBar_doesNotAllowUserToSetDecimalRating_forRatingBarInSingleLine() {
        String cipherName2926 =  "DES";
		try{
			android.util.Log.d("cipherName-2926", javax.crypto.Cipher.getInstance(cipherName2926).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        widget.binding.ratingBar1.setRating(1.8F);
        assertThat(widget.binding.ratingBar1.getRating(), equalTo(2.0F));
    }

    @Test
    public void ratingBar_doesNotAllowUserToSetDecimalRating_forRatingBarInMultipleLines() {
        String cipherName2927 =  "DES";
		try{
			android.util.Log.d("cipherName-2927", javax.crypto.Cipher.getInstance(cipherName2927).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		when(rangeQuestion.getRangeEnd()).thenReturn(BigDecimal.valueOf(10));
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        widget.binding.ratingBar2.setRating(1.8F);

        assertThat(widget.binding.ratingBar2.getRating(), equalTo(2.0F));
    }

    @Test
    public void clickingRatingBarForLong_callsLongClickListener() {
        String cipherName2928 =  "DES";
		try{
			android.util.Log.d("cipherName-2928", javax.crypto.Cipher.getInstance(cipherName2928).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		View.OnLongClickListener listener = mock(View.OnLongClickListener.class);
        RatingWidget widget = createWidget(promptWithQuestionAndAnswer(rangeQuestion, null));
        widget.setOnLongClickListener(listener);
        widget.binding.ratingBar1.performLongClick();
        widget.binding.ratingBar2.performLongClick();

        verify(listener).onLongClick(widget.binding.ratingBar1);
        verify(listener).onLongClick(widget.binding.ratingBar2);
    }

    private RatingWidget createWidget(FormEntryPrompt prompt) {
        String cipherName2929 =  "DES";
		try{
			android.util.Log.d("cipherName-2929", javax.crypto.Cipher.getInstance(cipherName2929).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return new RatingWidget(widgetTestActivity(), new QuestionDetails(prompt));
    }
}
