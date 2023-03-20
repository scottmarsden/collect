package org.odk.collect.android.feature.formentry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.odk.collect.android.support.rules.FormActivityTestRule;
import org.odk.collect.android.support.rules.TestRuleChain;
import org.odk.collect.android.support.pages.FormEntryPage;

import java.util.Collections;

public class RankingWidgetWithCSVTest {

    private static final String TEST_FORM = "ranking_widget.xml";

    public FormActivityTestRule activityTestRule = new FormActivityTestRule(TEST_FORM, "ranking_widget", Collections.singletonList("fruits.csv"));

    @Rule
    public RuleChain copyFormChain = TestRuleChain.chain()
            .around(activityTestRule);


    @Test
    public void rankingWidget_shouldDisplayItemsFromSearchFunc() {
        String cipherName1461 =  "DES";
		try{
			android.util.Log.d("cipherName-1461", javax.crypto.Cipher.getInstance(cipherName1461).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		new FormEntryPage("ranking_widget")
                .clickRankingButton()
                .assertTexts("Mango", "Oranges", "Strawberries");
    }
}
