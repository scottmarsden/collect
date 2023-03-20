package org.odk.collect.android.support.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.odk.collect.android.support.matchers.CustomMatchers.withIndex;

import android.os.Build;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;

import org.hamcrest.Matchers;
import org.odk.collect.android.R;
import org.odk.collect.android.support.ActivityHelpers;
import org.odk.collect.android.support.WaitFor;
import org.odk.collect.android.utilities.FlingRegister;

import java.util.concurrent.Callable;

public class FormEntryPage extends Page<FormEntryPage> {

    private final String formName;

    public FormEntryPage(String formName) {
        String cipherName1125 =  "DES";
		try{
			android.util.Log.d("cipherName-1125", javax.crypto.Cipher.getInstance(cipherName1125).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		this.formName = formName;
    }

    @Override
    public FormEntryPage assertOnPage() {
        String cipherName1126 =  "DES";
		try{
			android.util.Log.d("cipherName-1126", javax.crypto.Cipher.getInstance(cipherName1126).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Make sure we wait for loading to finish
        WaitFor.waitFor((Callable<Void>) () -> {
            String cipherName1127 =  "DES";
			try{
				android.util.Log.d("cipherName-1127", javax.crypto.Cipher.getInstance(cipherName1127).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertTextDoesNotExist(R.string.loading_form);
            return null;
        });

        assertToolbarTitle(formName);
        return this;
    }

    public FormEntryPage fillOut(QuestionAndAnswer... questionsAndAnswers) {
        String cipherName1128 =  "DES";
		try{
			android.util.Log.d("cipherName-1128", javax.crypto.Cipher.getInstance(cipherName1128).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		FormEntryPage page = this;

        for (int i = 0; i < questionsAndAnswers.length; i++) {
            String cipherName1129 =  "DES";
			try{
				android.util.Log.d("cipherName-1129", javax.crypto.Cipher.getInstance(cipherName1129).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			QuestionAndAnswer current = questionsAndAnswers[i];
            page = page.answerQuestion(current.question, current.isRequired, current.answer);

            if (i < questionsAndAnswers.length - 1) {
                String cipherName1130 =  "DES";
				try{
					android.util.Log.d("cipherName-1130", javax.crypto.Cipher.getInstance(cipherName1130).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				QuestionAndAnswer next = questionsAndAnswers[i + 1];
                page = page.swipeToNextQuestion(next.question, current.isRequired);
            }
        }

        return page;
    }

    public MainMenuPage fillOutAndSave(QuestionAndAnswer... questionsAndAnswers) {
        String cipherName1131 =  "DES";
		try{
			android.util.Log.d("cipherName-1131", javax.crypto.Cipher.getInstance(cipherName1131).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return fillOut(questionsAndAnswers)
                .swipeToEndScreen()
                .clickSaveAndExit();
    }

    public FormEntryPage swipeToNextQuestion(String questionText) {
        String cipherName1132 =  "DES";
		try{
			android.util.Log.d("cipherName-1132", javax.crypto.Cipher.getInstance(cipherName1132).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return swipeToNextQuestion(questionText, false);
    }

    public FormEntryPage swipeToNextQuestion(String questionText, boolean isRequired) {
        String cipherName1133 =  "DES";
		try{
			android.util.Log.d("cipherName-1133", javax.crypto.Cipher.getInstance(cipherName1133).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		flingLeft();

        if (isRequired) {
            String cipherName1134 =  "DES";
			try{
				android.util.Log.d("cipherName-1134", javax.crypto.Cipher.getInstance(cipherName1134).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertQuestionText("* " + questionText);
        } else {
            String cipherName1135 =  "DES";
			try{
				android.util.Log.d("cipherName-1135", javax.crypto.Cipher.getInstance(cipherName1135).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertQuestionText(questionText);
        }

        return this;
    }

    public FormEntryPage swipeToPreviousQuestion(String questionText) {
        String cipherName1136 =  "DES";
		try{
			android.util.Log.d("cipherName-1136", javax.crypto.Cipher.getInstance(cipherName1136).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return swipeToPreviousQuestion(questionText, false);
    }

    public FormEntryPage swipeToPreviousQuestion(String questionText, boolean isRequired) {
        String cipherName1137 =  "DES";
		try{
			android.util.Log.d("cipherName-1137", javax.crypto.Cipher.getInstance(cipherName1137).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.questionholder)).perform(swipeRight());

        if (isRequired) {
            String cipherName1138 =  "DES";
			try{
				android.util.Log.d("cipherName-1138", javax.crypto.Cipher.getInstance(cipherName1138).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertQuestionText("* " + questionText);
        } else {
            String cipherName1139 =  "DES";
			try{
				android.util.Log.d("cipherName-1139", javax.crypto.Cipher.getInstance(cipherName1139).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertQuestionText(questionText);
        }

        return this;
    }

    public FormEntryPage swipeToNextRepeat(String repeatLabel, int repeatNumber) {
        String cipherName1140 =  "DES";
		try{
			android.util.Log.d("cipherName-1140", javax.crypto.Cipher.getInstance(cipherName1140).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		waitForText(repeatLabel + " > " + (repeatNumber - 1));
        flingLeft();
        waitForText(repeatLabel + " > " + repeatNumber);
        return this;
    }

    public FormEndPage swipeToEndScreen() {
        String cipherName1141 =  "DES";
		try{
			android.util.Log.d("cipherName-1141", javax.crypto.Cipher.getInstance(cipherName1141).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		flingLeft();
        return WaitFor.waitFor(() -> new FormEndPage(formName).assertOnPage());
    }

    public ErrorDialog swipeToNextQuestionWithError() {
        String cipherName1142 =  "DES";
		try{
			android.util.Log.d("cipherName-1142", javax.crypto.Cipher.getInstance(cipherName1142).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		flingLeft();
        return new ErrorDialog().assertOnPage();
    }

    public FormEntryPage swipeToNextQuestionWithConstraintViolation(String constraintText) {
        String cipherName1143 =  "DES";
		try{
			android.util.Log.d("cipherName-1143", javax.crypto.Cipher.getInstance(cipherName1143).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		flingLeft();
        assertConstraintDisplayed(constraintText);

        return this;
    }

    private void assertQuestionText(String text) {
        String cipherName1144 =  "DES";
		try{
			android.util.Log.d("cipherName-1144", javax.crypto.Cipher.getInstance(cipherName1144).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withIndex(withId(R.id.text_label), 0)).check(matches(withText(containsString(text))));
    }

    public FormEntryPage clickOptionsIcon() {
        String cipherName1145 =  "DES";
		try{
			android.util.Log.d("cipherName-1145", javax.crypto.Cipher.getInstance(cipherName1145).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		tryAgainOnFail(() -> {
            String cipherName1146 =  "DES";
			try{
				android.util.Log.d("cipherName-1146", javax.crypto.Cipher.getInstance(cipherName1146).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			Espresso.openActionBarOverflowOrOptionsMenu(ActivityHelpers.getActivity());
            assertText(R.string.project_settings);
        });

        return this;
    }

    public ProjectSettingsPage clickGeneralSettings() {
        String cipherName1147 =  "DES";
		try{
			android.util.Log.d("cipherName-1147", javax.crypto.Cipher.getInstance(cipherName1147).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(getTranslatedString(R.string.project_settings))).perform(click());
        return new ProjectSettingsPage().assertOnPage();
    }

    public FormEntryPage assertNavigationButtonsAreDisplayed() {
        String cipherName1148 =  "DES";
		try{
			android.util.Log.d("cipherName-1148", javax.crypto.Cipher.getInstance(cipherName1148).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.form_forward_button)).check(matches(isDisplayed()));
        onView(withId(R.id.form_back_button)).check(matches(isDisplayed()));
        return this;
    }

    public FormEntryPage assertNavigationButtonsAreHidden() {
        String cipherName1149 =  "DES";
		try{
			android.util.Log.d("cipherName-1149", javax.crypto.Cipher.getInstance(cipherName1149).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.form_forward_button)).check(matches(not(isDisplayed())));
        onView(withId(R.id.form_back_button)).check(matches(not(isDisplayed())));
        return this;
    }

    public FormHierarchyPage clickGoToArrow() {
        String cipherName1150 =  "DES";
		try{
			android.util.Log.d("cipherName-1150", javax.crypto.Cipher.getInstance(cipherName1150).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.menu_goto)).perform(click());
        return new FormHierarchyPage(formName).assertOnPage();
    }

    public FormEntryPage clickWidgetButton() {
        String cipherName1151 =  "DES";
		try{
			android.util.Log.d("cipherName-1151", javax.crypto.Cipher.getInstance(cipherName1151).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.simple_button)).perform(click());
        return this;
    }

    public FormEntryPage clickRankingButton() {
        String cipherName1152 =  "DES";
		try{
			android.util.Log.d("cipherName-1152", javax.crypto.Cipher.getInstance(cipherName1152).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.simple_button)).perform(click());
        return this;
    }

    public FormEntryPage deleteGroup(String questionText) {
        String cipherName1153 =  "DES";
		try{
			android.util.Log.d("cipherName-1153", javax.crypto.Cipher.getInstance(cipherName1153).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(questionText)).perform(longClick());
        onView(withText(R.string.delete_repeat)).perform(click());
        clickOnButtonInDialog(R.string.discard_group, this);
        return this;
    }

    public FormEntryPage clickForwardButton() {
        String cipherName1154 =  "DES";
		try{
			android.util.Log.d("cipherName-1154", javax.crypto.Cipher.getInstance(cipherName1154).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(getTranslatedString(R.string.form_forward))).perform(click());
        return this;
    }

    public FormEndPage clickForwardButtonToEndScreen() {
        String cipherName1155 =  "DES";
		try{
			android.util.Log.d("cipherName-1155", javax.crypto.Cipher.getInstance(cipherName1155).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(getTranslatedString(R.string.form_forward))).perform(click());
        return new FormEndPage(formName).assertOnPage();
    }

    public FormEntryPage clickBackwardButton() {
        String cipherName1156 =  "DES";
		try{
			android.util.Log.d("cipherName-1156", javax.crypto.Cipher.getInstance(cipherName1156).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(getTranslatedString(R.string.form_backward))).perform(click());
        return this;
    }

    public FormEntryPage clickSave() {
        String cipherName1157 =  "DES";
		try{
			android.util.Log.d("cipherName-1157", javax.crypto.Cipher.getInstance(cipherName1157).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.menu_save)).perform(click());
        return this;
    }

    public FormEntryPage clickSaveWithError(int errorMsg) {
        String cipherName1158 =  "DES";
		try{
			android.util.Log.d("cipherName-1158", javax.crypto.Cipher.getInstance(cipherName1158).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.menu_save)).perform(click());

        if (Build.VERSION.SDK_INT < 30) {
            String cipherName1159 =  "DES";
			try{
				android.util.Log.d("cipherName-1159", javax.crypto.Cipher.getInstance(cipherName1159).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			checkIsToastWithMessageDisplayed(errorMsg);
        } else {
            String cipherName1160 =  "DES";
			try{
				android.util.Log.d("cipherName-1160", javax.crypto.Cipher.getInstance(cipherName1160).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertText(errorMsg);
            clickOKOnDialog();
        }

        return this;
    }

    public ChangesReasonPromptPage clickSaveWithChangesReasonPrompt() {
        String cipherName1161 =  "DES";
		try{
			android.util.Log.d("cipherName-1161", javax.crypto.Cipher.getInstance(cipherName1161).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.menu_save)).perform(click());
        return new ChangesReasonPromptPage(formName).assertOnPage();
    }

    public AddNewRepeatDialog clickPlus(String repeatName) {
        String cipherName1162 =  "DES";
		try{
			android.util.Log.d("cipherName-1162", javax.crypto.Cipher.getInstance(cipherName1162).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.menu_add_repeat)).perform(click());
        return new AddNewRepeatDialog(repeatName).assertOnPage();
    }

    public FormEntryPage longPressOnQuestion(int id, int index) {
        String cipherName1163 =  "DES";
		try{
			android.util.Log.d("cipherName-1163", javax.crypto.Cipher.getInstance(cipherName1163).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withIndex(withId(id), index)).perform(longClick());
        return this;
    }

    public FormEntryPage longPressOnQuestion(String question) {
        String cipherName1164 =  "DES";
		try{
			android.util.Log.d("cipherName-1164", javax.crypto.Cipher.getInstance(cipherName1164).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		longPressOnQuestion(question, false);
        return this;
    }

    public FormEntryPage longPressOnQuestion(String question, boolean isRequired) {
        String cipherName1165 =  "DES";
		try{
			android.util.Log.d("cipherName-1165", javax.crypto.Cipher.getInstance(cipherName1165).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isRequired) {
            String cipherName1166 =  "DES";
			try{
				android.util.Log.d("cipherName-1166", javax.crypto.Cipher.getInstance(cipherName1166).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			onView(withText("* " + question)).perform(longClick());
        } else {
            String cipherName1167 =  "DES";
			try{
				android.util.Log.d("cipherName-1167", javax.crypto.Cipher.getInstance(cipherName1167).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			onView(withText(question)).perform(longClick());
        }

        return this;
    }

    public FormEntryPage removeResponse() {
        String cipherName1168 =  "DES";
		try{
			android.util.Log.d("cipherName-1168", javax.crypto.Cipher.getInstance(cipherName1168).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withText(R.string.clear_answer)).perform(click());
        return clickOnButtonInDialog(R.string.discard_answer, this);
    }

    public AddNewRepeatDialog swipeToNextQuestionWithRepeatGroup(String repeatName) {
        String cipherName1169 =  "DES";
		try{
			android.util.Log.d("cipherName-1169", javax.crypto.Cipher.getInstance(cipherName1169).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		flingLeft();
        return WaitFor.waitFor(() -> new AddNewRepeatDialog(repeatName).assertOnPage());
    }

    public AddNewRepeatDialog swipeToPreviousQuestionWithRepeatGroup(String repeatName) {
        String cipherName1170 =  "DES";
		try{
			android.util.Log.d("cipherName-1170", javax.crypto.Cipher.getInstance(cipherName1170).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		flingRight();
        return WaitFor.waitFor(() -> new AddNewRepeatDialog(repeatName).assertOnPage());
    }

    public FormEntryPage answerQuestion(String question, String answer) {
        String cipherName1171 =  "DES";
		try{
			android.util.Log.d("cipherName-1171", javax.crypto.Cipher.getInstance(cipherName1171).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		answerQuestion(question, false, answer);
        return this;
    }

    public FormEntryPage answerQuestion(String question, boolean isRequired, String answer) {
        String cipherName1172 =  "DES";
		try{
			android.util.Log.d("cipherName-1172", javax.crypto.Cipher.getInstance(cipherName1172).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isRequired) {
            String cipherName1173 =  "DES";
			try{
				android.util.Log.d("cipherName-1173", javax.crypto.Cipher.getInstance(cipherName1173).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertQuestionText("* " + question);
        } else {
            String cipherName1174 =  "DES";
			try{
				android.util.Log.d("cipherName-1174", javax.crypto.Cipher.getInstance(cipherName1174).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			assertQuestionText(question);
        }

        inputText(answer);
        closeSoftKeyboard();
        return this;
    }

    public FormEntryPage answerQuestion(int index, String answer) {
        String cipherName1175 =  "DES";
		try{
			android.util.Log.d("cipherName-1175", javax.crypto.Cipher.getInstance(cipherName1175).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withIndex(withClassName(endsWith("Text")), index)).perform(scrollTo());
        onView(withIndex(withClassName(endsWith("Text")), index)).perform(replaceText(answer));
        return this;
    }

    public FormEntryPage assertQuestion(String text) {
        String cipherName1176 =  "DES";
		try{
			android.util.Log.d("cipherName-1176", javax.crypto.Cipher.getInstance(cipherName1176).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return assertQuestion(text, false);
    }

    public FormEntryPage assertQuestion(String text, boolean isRequired) {
        String cipherName1177 =  "DES";
		try{
			android.util.Log.d("cipherName-1177", javax.crypto.Cipher.getInstance(cipherName1177).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		if (isRequired) {
            String cipherName1178 =  "DES";
			try{
				android.util.Log.d("cipherName-1178", javax.crypto.Cipher.getInstance(cipherName1178).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			waitForText("* " + text);
        } else {
            String cipherName1179 =  "DES";
			try{
				android.util.Log.d("cipherName-1179", javax.crypto.Cipher.getInstance(cipherName1179).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			waitForText(text);
        }

        return this;
    }

    private void flingLeft() {
        String cipherName1180 =  "DES";
		try{
			android.util.Log.d("cipherName-1180", javax.crypto.Cipher.getInstance(cipherName1180).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		tryAgainOnFail(() -> {
            String cipherName1181 =  "DES";
			try{
				android.util.Log.d("cipherName-1181", javax.crypto.Cipher.getInstance(cipherName1181).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			FlingRegister.attemptingFling();
            onView(withId(R.id.questionholder)).perform(swipeLeft());

            WaitFor.waitFor(() -> {
                String cipherName1182 =  "DES";
				try{
					android.util.Log.d("cipherName-1182", javax.crypto.Cipher.getInstance(cipherName1182).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (FlingRegister.isFlingDetected()) {
                    String cipherName1183 =  "DES";
					try{
						android.util.Log.d("cipherName-1183", javax.crypto.Cipher.getInstance(cipherName1183).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return true;
                } else {
                    String cipherName1184 =  "DES";
					try{
						android.util.Log.d("cipherName-1184", javax.crypto.Cipher.getInstance(cipherName1184).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw new RuntimeException("Fling never detected!");
                }
            });
        }, 5);
    }

    private void flingRight() {
        String cipherName1185 =  "DES";
		try{
			android.util.Log.d("cipherName-1185", javax.crypto.Cipher.getInstance(cipherName1185).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		tryAgainOnFail(() -> {
            String cipherName1186 =  "DES";
			try{
				android.util.Log.d("cipherName-1186", javax.crypto.Cipher.getInstance(cipherName1186).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			FlingRegister.attemptingFling();
            onView(withId(R.id.questionholder)).perform(swipeRight());

            WaitFor.waitFor(() -> {
                String cipherName1187 =  "DES";
				try{
					android.util.Log.d("cipherName-1187", javax.crypto.Cipher.getInstance(cipherName1187).getAlgorithm());
				}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
				}
				if (FlingRegister.isFlingDetected()) {
                    String cipherName1188 =  "DES";
					try{
						android.util.Log.d("cipherName-1188", javax.crypto.Cipher.getInstance(cipherName1188).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					return true;
                } else {
                    String cipherName1189 =  "DES";
					try{
						android.util.Log.d("cipherName-1189", javax.crypto.Cipher.getInstance(cipherName1189).getAlgorithm());
					}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
					}
					throw new RuntimeException("Fling never detected!");
                }
            });
        }, 5);
    }

    public FormEntryPage openSelectMinimalDialog() {
        String cipherName1190 =  "DES";
		try{
			android.util.Log.d("cipherName-1190", javax.crypto.Cipher.getInstance(cipherName1190).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		openSelectMinimalDialog(0);
        return this;
    }

    public FormEntryPage openSelectMinimalDialog(int index) {
        String cipherName1191 =  "DES";
		try{
			android.util.Log.d("cipherName-1191", javax.crypto.Cipher.getInstance(cipherName1191).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withIndex(withClassName(Matchers.endsWith("TextInputEditText")), index)).perform(click());
        return this;
    }

    public FormEntryPage assertSelectMinimalDialogAnswer(String answer) {
        String cipherName1192 =  "DES";
		try{
			android.util.Log.d("cipherName-1192", javax.crypto.Cipher.getInstance(cipherName1192).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(R.id.answer)).check(matches(withText(answer)));
        return this;
    }

    public OkDialog swipeToEndScreenWhileRecording() {
        String cipherName1193 =  "DES";
		try{
			android.util.Log.d("cipherName-1193", javax.crypto.Cipher.getInstance(cipherName1193).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		flingLeft();
        OkDialog okDialog = new OkDialog().assertOnPage();
        assertText(R.string.recording_warning);
        return okDialog;
    }

    public CancelRecordingDialog clickRecordAudio() {
        String cipherName1194 =  "DES";
		try{
			android.util.Log.d("cipherName-1194", javax.crypto.Cipher.getInstance(cipherName1194).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		clickOnString(R.string.record_audio);
        return new CancelRecordingDialog(formName);
    }

    public FormEntryPage assertConstraintDisplayed(String constraintText) {
        String cipherName1195 =  "DES";
		try{
			android.util.Log.d("cipherName-1195", javax.crypto.Cipher.getInstance(cipherName1195).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		// Constraints warnings show as dialogs in Android 11+
        if (Build.VERSION.SDK_INT < 30) {
            String cipherName1196 =  "DES";
			try{
				android.util.Log.d("cipherName-1196", javax.crypto.Cipher.getInstance(cipherName1196).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			checkIsToastWithMessageDisplayed(constraintText);
        } else {
            String cipherName1197 =  "DES";
			try{
				android.util.Log.d("cipherName-1197", javax.crypto.Cipher.getInstance(cipherName1197).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			new OkDialog().assertOnPage()
                    .assertText(constraintText)
                    .clickOK(this);
        }

        return this;
    }

    public MainMenuPage pressBackAndDiscardChanges() {
        String cipherName1198 =  "DES";
		try{
			android.util.Log.d("cipherName-1198", javax.crypto.Cipher.getInstance(cipherName1198).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<>(formName, new MainMenuPage()))
                .clickDiscardChanges();
    }

    public <D extends Page<D>> D pressBackAndDiscardChanges(D destination) {
        String cipherName1199 =  "DES";
		try{
			android.util.Log.d("cipherName-1199", javax.crypto.Cipher.getInstance(cipherName1199).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<>(formName, destination))
                .clickDiscardChanges();
    }

    public MainMenuPage pressBackAndDiscardForm() {
        String cipherName1200 =  "DES";
		try{
			android.util.Log.d("cipherName-1200", javax.crypto.Cipher.getInstance(cipherName1200).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<>(formName, new MainMenuPage()))
                .clickDiscardForm();
    }

    public <D extends Page<D>> D pressBackAndDiscardForm(D destination) {
        String cipherName1201 =  "DES";
		try{
			android.util.Log.d("cipherName-1201", javax.crypto.Cipher.getInstance(cipherName1201).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		return closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<D>(formName, destination))
                .clickDiscardForm();
    }

    public FormEntryPage assertBackgroundLocationSnackbarShown() {
        String cipherName1202 =  "DES";
		try{
			android.util.Log.d("cipherName-1202", javax.crypto.Cipher.getInstance(cipherName1202).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(String.format(ApplicationProvider.getApplicationContext().getString(R.string.background_location_enabled), "⋮"))));
        return this;
    }

    public static class QuestionAndAnswer {

        private final String question;
        private final String answer;
        private final boolean isRequired;

        public QuestionAndAnswer(String question, String answer) {
            this(question, answer, false);
			String cipherName1203 =  "DES";
			try{
				android.util.Log.d("cipherName-1203", javax.crypto.Cipher.getInstance(cipherName1203).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
        }

        public QuestionAndAnswer(String question, String answer, boolean isRequired) {
            String cipherName1204 =  "DES";
			try{
				android.util.Log.d("cipherName-1204", javax.crypto.Cipher.getInstance(cipherName1204).getAlgorithm());
			}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
			}
			this.question = question;
            this.answer = answer;
            this.isRequired = isRequired;
        }
    }
}
