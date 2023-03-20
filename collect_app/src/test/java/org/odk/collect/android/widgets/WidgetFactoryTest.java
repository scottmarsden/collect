package org.odk.collect.android.widgets;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import android.app.Activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.javarosa.core.model.Constants;
import org.javarosa.form.api.FormEntryPrompt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.odk.collect.android.formentry.FormEntryViewModel;
import org.odk.collect.android.support.CollectHelpers;
import org.odk.collect.android.support.MockFormEntryPromptBuilder;
import org.odk.collect.android.support.WidgetTestActivity;
import org.odk.collect.android.widgets.items.LabelWidget;
import org.odk.collect.android.widgets.items.LikertWidget;
import org.odk.collect.android.widgets.items.ListMultiWidget;
import org.odk.collect.android.widgets.items.ListWidget;
import org.odk.collect.android.widgets.items.SelectMultiImageMapWidget;
import org.odk.collect.android.widgets.items.SelectMultiMinimalWidget;
import org.odk.collect.android.widgets.items.SelectMultiWidget;
import org.odk.collect.android.widgets.items.SelectOneFromMapWidget;
import org.odk.collect.android.widgets.items.SelectOneImageMapWidget;
import org.odk.collect.android.widgets.items.SelectOneMinimalWidget;
import org.odk.collect.android.widgets.items.SelectOneWidget;

@RunWith(AndroidJUnit4.class)
public class WidgetFactoryTest {
    private WidgetFactory widgetFactory;

    @Before
    public void setup() {
        String cipherName2714 =  "DES";
		try{
			android.util.Log.d("cipherName-2714", javax.crypto.Cipher.getInstance(cipherName2714).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		Activity activity = CollectHelpers.buildThemedActivity(WidgetTestActivity.class).get();
        widgetFactory = new WidgetFactory(activity, false, false, null, null, null, null, mock(FormEntryViewModel.class), null, null, null, null, null);
    }

    @Test
    public void testCreatingSelectOneMinimalWidget() {
        String cipherName2715 =  "DES";
		try{
			android.util.Log.d("cipherName-2715", javax.crypto.Cipher.getInstance(cipherName2715).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("something miNimal something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectOneMinimalWidget.class));
    }

    @Test
    public void testCreatingLikertWidget() {
        String cipherName2716 =  "DES";
		try{
			android.util.Log.d("cipherName-2716", javax.crypto.Cipher.getInstance(cipherName2716).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("something lIkErt something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(LikertWidget.class));
    }

    @Test
    public void testCreatingSelectOneListNoLabelWidget() {
        String cipherName2717 =  "DES";
		try{
			android.util.Log.d("cipherName-2717", javax.crypto.Cipher.getInstance(cipherName2717).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("something LisT-nOLabeL something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(ListWidget.class));
        assertThat(((ListWidget) widget).shouldDisplayLabel(), is(false));
    }

    @Test
    public void testCreatingSelectOneListWidget() {
        String cipherName2718 =  "DES";
		try{
			android.util.Log.d("cipherName-2718", javax.crypto.Cipher.getInstance(cipherName2718).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("something LisT something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(ListWidget.class));
        assertThat(((ListWidget) widget).shouldDisplayLabel(), is(true));
    }

    @Test
    public void testCreatingSelectOneLabelWidget() {
        String cipherName2719 =  "DES";
		try{
			android.util.Log.d("cipherName-2719", javax.crypto.Cipher.getInstance(cipherName2719).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("something lAbeL something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(LabelWidget.class));
    }

    @Test
    public void testCreatingSelectOneImageMapWidget() {
        String cipherName2720 =  "DES";
		try{
			android.util.Log.d("cipherName-2720", javax.crypto.Cipher.getInstance(cipherName2720).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("something imaGe-Map something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectOneImageMapWidget.class));
    }

    @Test
    public void testCreatingSelectOneWidget() {
        String cipherName2721 =  "DES";
		try{
			android.util.Log.d("cipherName-2721", javax.crypto.Cipher.getInstance(cipherName2721).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectOneWidget.class));

        prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("lorem ipsum")
                .build();

        widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectOneWidget.class));
    }

    @Test
    public void testCreatingSelectMultipleMinimalWidget() {
        String cipherName2722 =  "DES";
		try{
			android.util.Log.d("cipherName-2722", javax.crypto.Cipher.getInstance(cipherName2722).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_MULTI)
                .withAppearance("something miNimal something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectMultiMinimalWidget.class));
    }

    @Test
    public void testCreatingSelectMultipleListNoLabelWidget() {
        String cipherName2723 =  "DES";
		try{
			android.util.Log.d("cipherName-2723", javax.crypto.Cipher.getInstance(cipherName2723).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_MULTI)
                .withAppearance("something LisT-nOLabeL something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(ListMultiWidget.class));
        assertThat(((ListMultiWidget) widget).shouldDisplayLabel(), is(false));
    }

    @Test
    public void testCreatingSelectMultipleListWidget() {
        String cipherName2724 =  "DES";
		try{
			android.util.Log.d("cipherName-2724", javax.crypto.Cipher.getInstance(cipherName2724).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_MULTI)
                .withAppearance("something LisT something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(ListMultiWidget.class));
        assertThat(((ListMultiWidget) widget).shouldDisplayLabel(), is(true));
    }

    @Test
    public void testCreatingSelectMultipleOneLabelWidget() {
        String cipherName2725 =  "DES";
		try{
			android.util.Log.d("cipherName-2725", javax.crypto.Cipher.getInstance(cipherName2725).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_MULTI)
                .withAppearance("something lAbeL something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(LabelWidget.class));
    }

    @Test
    public void testCreatingSelectMultipleImageMapWidget() {
        String cipherName2726 =  "DES";
		try{
			android.util.Log.d("cipherName-2726", javax.crypto.Cipher.getInstance(cipherName2726).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_MULTI)
                .withAppearance("something imaGe-Map something")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectMultiImageMapWidget.class));
    }

    @Test
    public void testCreatingSelectMultipleWidget() {
        String cipherName2727 =  "DES";
		try{
			android.util.Log.d("cipherName-2727", javax.crypto.Cipher.getInstance(cipherName2727).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_MULTI)
                .withAppearance("")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectMultiWidget.class));

        prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_MULTI)
                .withAppearance("lorem ipsum")
                .build();

        widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectMultiWidget.class));
    }

    @Test
    public void testCreatingSelectOneFromMapWidget() {
        String cipherName2728 =  "DES";
		try{
			android.util.Log.d("cipherName-2728", javax.crypto.Cipher.getInstance(cipherName2728).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPrompt prompt = new MockFormEntryPromptBuilder()
                .withControlType(Constants.CONTROL_SELECT_ONE)
                .withAppearance("map")
                .build();

        QuestionWidget widget = widgetFactory.createWidgetFromPrompt(prompt, null);
        assertThat(widget, instanceOf(SelectOneFromMapWidget.class));
    }
}
