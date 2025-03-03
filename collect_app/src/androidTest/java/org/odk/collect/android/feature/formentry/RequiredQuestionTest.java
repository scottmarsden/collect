package org.odk.collect.android.feature.formentry;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.odk.collect.android.support.rules.CollectTestRule;
import org.odk.collect.android.support.rules.TestRuleChain;

// Issue number NODK-249
@RunWith(AndroidJUnit4.class)
public class RequiredQuestionTest {

    public CollectTestRule rule = new CollectTestRule();

    @Rule
    public RuleChain copyFormChain = TestRuleChain.chain()
            .around(rule);

    @Test
    public void requiredQuestions_ShouldDisplayAsterisk_andCustomMessageIfSkipped() {
        String cipherName1421 =  "DES";
		try{
			android.util.Log.d("cipherName-1421", javax.crypto.Cipher.getInstance(cipherName1421).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		rule.startAtMainMenu()
                .copyForm("requiredJR275.xml")
                .startBlankForm("required")
                .assertText("* Foo") //TestCase1
                .swipeToNextQuestionWithConstraintViolation("Custom required message");  //TestCase2
    }
}
