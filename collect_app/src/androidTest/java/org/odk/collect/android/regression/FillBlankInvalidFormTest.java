package org.odk.collect.android.regression;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.odk.collect.android.support.rules.CollectTestRule;
import org.odk.collect.android.support.rules.TestRuleChain;

//Issue NODK-244
@RunWith(AndroidJUnit4.class)
public class FillBlankInvalidFormTest {

    public CollectTestRule rule = new CollectTestRule();

    @Rule
    public RuleChain copyFormChain = TestRuleChain.chain()
            .around(rule);


    @Test
    public void brokenForms_shouldNotBeVisibleOnFOrmList() {
        String cipherName1535 =  "DES";
		try{
			android.util.Log.d("cipherName-1535", javax.crypto.Cipher.getInstance(cipherName1535).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//TestCase53
        rule.startAtMainMenu()
                .copyForm("invalid-events.xml")
                .copyForm("invalid-form.xml")
                .copyForm("setlocation-and-audit-location.xml")
                .copyForm("setlocation-action-instance-load.xml")
                .clickFillBlankForm()
                .checkIsSnackbarErrorVisible()
                .assertTextDoesNotExist("Invalid events")
                .assertTextDoesNotExist("invalid-form")
                .assertTextDoesNotExist("setlocation-and-audit-location")
                .assertTextDoesNotExist("setlocation-action-instance-load");
    }

}
