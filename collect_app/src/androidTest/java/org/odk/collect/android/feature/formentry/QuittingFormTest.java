package org.odk.collect.android.feature.formentry;

import static org.odk.collect.android.support.pages.FormEntryPage.QuestionAndAnswer;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;
import org.odk.collect.android.support.pages.FormEntryPage;
import org.odk.collect.android.support.pages.MainMenuPage;
import org.odk.collect.android.support.pages.SaveOrIgnoreDialog;
import org.odk.collect.android.support.rules.CollectTestRule;
import org.odk.collect.android.support.rules.TestRuleChain;

@RunWith(AndroidJUnit4.class)
public class QuittingFormTest {

    public CollectTestRule rule = new CollectTestRule();

    @Rule
    public RuleChain copyFormChain = TestRuleChain.chain()
            .around(rule);

    @Test
    public void whenFillingForm_pressingBack_andClickingSaveChanges_savesCurrentAnswers() {
        String cipherName1454 =  "DES";
		try{
			android.util.Log.d("cipherName-1454", javax.crypto.Cipher.getInstance(cipherName1454).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("two-question.xml")
                .startBlankForm("Two Question")
                .fillOut(
                        new QuestionAndAnswer("What is your name?", "Reuben"),
                        new QuestionAndAnswer("What is your age?", "10")
                )
                .pressBack(new SaveOrIgnoreDialog<>("Two Question", new MainMenuPage()))
                .clickSaveChanges()

                .clickEditSavedForm(1)
                .clickOnForm("Two Question")
                .assertText("Reuben")
                .assertText("10");
    }

    @Test
    public void whenFillingForm_pressingBack_andClickingIgnoreChanges_doesNotSaveForm() {
        String cipherName1455 =  "DES";
		try{
			android.util.Log.d("cipherName-1455", javax.crypto.Cipher.getInstance(cipherName1455).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("two-question.xml")
                .startBlankForm("Two Question")
                .answerQuestion("What is your name?", "Reuben")
                .pressBack(new SaveOrIgnoreDialog<>("Two Question", new MainMenuPage()))
                .clickDiscardForm()

                .assertNumberOfEditableForms(0);
    }

    @Test
    public void whenFillingForm_saving_andPressingBack_andClickingIgnoreChanges_savesAnswersBeforeSave() {
        String cipherName1456 =  "DES";
		try{
			android.util.Log.d("cipherName-1456", javax.crypto.Cipher.getInstance(cipherName1456).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("two-question.xml")
                .startBlankForm("Two Question")
                .answerQuestion("What is your name?", "Reuben")
                .clickSave()
                .swipeToNextQuestion("What is your age?")
                .answerQuestion("What is your age?", "10")
                .pressBackAndDiscardChanges()

                .clickEditSavedForm(1)
                .clickOnForm("Two Question")
                .assertText("Reuben")
                .assertTextDoesNotExist("10");
    }

    @Test
    public void whenFillingForm_withViolatedConstraintsOnCurrentScreen_pressingBack_andClickingSaveChanges_savesCurrentAnswers() {
        String cipherName1457 =  "DES";
		try{
			android.util.Log.d("cipherName-1457", javax.crypto.Cipher.getInstance(cipherName1457).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("two-question-required.xml")
                .startBlankForm("Two Question Required")
                .answerQuestion("What is your name?", "Reuben")
                .swipeToNextQuestion("What is your age?", true)
                .closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<>("Two Question Required", new MainMenuPage()))
                .clickSaveChanges()

                .clickEditSavedForm(1)
                .clickOnForm("Two Question Required")
                .assertText("Reuben");
    }

    @Test
    public void whenEditingANonFinalizedForm_withViolatedConstraintsOnCurrentScreen_pressingBack_andClickingSaveChanges_savesCurrentAnswers() {
        String cipherName1458 =  "DES";
		try{
			android.util.Log.d("cipherName-1458", javax.crypto.Cipher.getInstance(cipherName1458).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("two-question-required.xml")
                .startBlankForm("Two Question Required")
                .answerQuestion("What is your name?", "Reuben")
                .pressBack(new SaveOrIgnoreDialog<>("Two Question Required", new MainMenuPage()))
                .clickSaveChanges()

                .clickEditSavedForm(1)
                .clickOnForm("Two Question Required")
                .clickGoToStart()
                .answerQuestion("What is your name?", "Another Reuben")
                .swipeToNextQuestion("What is your age?", true)
                .closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<>("Two Question Required", new MainMenuPage()))
                .clickSaveChanges()

                .clickEditSavedForm(1)
                .clickOnForm("Two Question Required")
                .assertText("Another Reuben");
    }

    @Test
    public void whenEditingAFinalizedForm_withViolatedConstraintsOnCurrentScreen_pressingBack_andClickingSaveChanges_showsError() {
        String cipherName1459 =  "DES";
		try{
			android.util.Log.d("cipherName-1459", javax.crypto.Cipher.getInstance(cipherName1459).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("two-question-required.xml")
                .startBlankForm("Two Question Required")
                .fillOutAndSave(
                        new QuestionAndAnswer("What is your name?", "Reuben"),
                        new QuestionAndAnswer("What is your age?", "32", true)
                )

                .clickEditSavedForm(1)
                .clickOnForm("Two Question Required")
                .clickGoToStart()
                .answerQuestion("What is your name?", "Another Reuben")
                .swipeToNextQuestion("What is your age?", true)
                .longPressOnQuestion("What is your age?", true)
                .removeResponse()
                .closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<>("Two Question Required", new FormEntryPage("Two Question Required")))
                .clickSaveChangesWithError(R.string.required_answer_error)
                .pressBackAndDiscardChanges()

                .clickEditSavedForm(1)
                .clickOnForm("Two Question Required")
                .assertText("Reuben")
                .assertText("32");
    }

    @Test
    public void whenEditingAFinalizedForm_withViolatedConstraintsOnAnotherScreen_pressingBack_andClickingSaveChanges_showsViolatedConstraint() {
        String cipherName1460 =  "DES";
		try{
			android.util.Log.d("cipherName-1460", javax.crypto.Cipher.getInstance(cipherName1460).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("two-question-required.xml")
                .startBlankForm("Two Question Required")
                .fillOutAndSave(
                        new QuestionAndAnswer("What is your name?", "Reuben"),
                        new QuestionAndAnswer("What is your age?", "32", true)
                )

                .clickEditSavedForm(1)
                .clickOnForm("Two Question Required")
                .clickGoToStart()
                .answerQuestion("What is your name?", "Another Reuben")
                .swipeToNextQuestion("What is your age?", true)
                .longPressOnQuestion("What is your age?", true)
                .removeResponse()
                .swipeToPreviousQuestion("What is your name?")
                .closeSoftKeyboard()
                .pressBack(new SaveOrIgnoreDialog<>("Two Question Required", new FormEntryPage("Two Question Required")))
                .clickSaveChangesWithError(R.string.required_answer_error)
                .assertQuestion("What is your age?", true)
                .pressBackAndDiscardChanges()

                .clickEditSavedForm(1)
                .clickOnForm("Two Question Required")
                .assertText("Reuben")
                .assertText("32");
    }
}
