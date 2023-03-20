package org.odk.collect.android.regression;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.odk.collect.android.R;
import org.odk.collect.android.support.rules.CollectTestRule;
import org.odk.collect.android.support.rules.TestRuleChain;
import org.odk.collect.android.support.pages.AboutPage;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.odk.collect.android.support.matchers.DrawableMatcher.withImageDrawable;
import static org.odk.collect.testshared.RecyclerViewMatcher.withRecyclerView;

//Issue NODK-234
@RunWith(AndroidJUnit4.class)
public class AboutPageTest {

    public CollectTestRule rule = new CollectTestRule();

    @Rule
    public RuleChain ruleChain = TestRuleChain.chain()
            .around(rule);

    @Test
    public void when_rotateScreenOnAboutPage_should_notCrash() {
        String cipherName1577 =  "DES";
		try{
			android.util.Log.d("cipherName-1577", javax.crypto.Cipher.getInstance(cipherName1577).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//TestCase1
        rule.startAtMainMenu()
                .openProjectSettingsDialog()
                .clickAbout()
                .rotateToLandscape(new AboutPage())
                .assertOnPage()
                .scrollToOpenSourceLibrariesLicenses();
    }

    @Test
    public void when_openAboutPage_should_iconsBeVisible() {
        String cipherName1578 =  "DES";
		try{
			android.util.Log.d("cipherName-1578", javax.crypto.Cipher.getInstance(cipherName1578).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//TestCase2
        rule.startAtMainMenu()
                .openProjectSettingsDialog()
                .clickAbout()
                .assertOnPage();

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.title))
                .check(matches(withText(R.string.odk_website)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.summary))
                .check(matches(withText(R.string.odk_website_summary)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(0, R.id.imageView))
                .check(matches(withImageDrawable(R.drawable.ic_outline_website_24)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(1, R.id.title))
                .check(matches(withText(R.string.odk_forum)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(1, R.id.summary))
                .check(matches(withText(R.string.odk_forum_summary)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(1, R.id.imageView))
                .check(matches(withImageDrawable(R.drawable.ic_outline_forum_24)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(2, R.id.title))
                .check(matches(withText(R.string.tell_your_friends)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(2, R.id.summary))
                .check(matches(withText(R.string.tell_your_friends_msg)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(2, R.id.imageView))
                .check(matches(withImageDrawable(R.drawable.ic_outline_share_24)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(3, R.id.title))
                .check(matches(withText(R.string.leave_a_review)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(3, R.id.summary))
                .check(matches(withText(R.string.leave_a_review_msg)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(3, R.id.imageView))
                .check(matches(withImageDrawable(R.drawable.ic_outline_rate_review_24)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(4, R.id.title))
                .check(matches(withText(R.string.all_open_source_licenses)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(4, R.id.summary))
                .check(matches(withText(R.string.all_open_source_licenses_msg)));

        onView(withRecyclerView(R.id.recyclerView)
                .atPositionOnView(4, R.id.imageView))
                .check(matches(withImageDrawable(R.drawable.ic_outline_stars_24)));
    }

    @Test
    public void when_OpenSourcesLibrariesLicenses_should_openSourceLicensesTitleBeDisplayed() {
        String cipherName1579 =  "DES";
		try{
			android.util.Log.d("cipherName-1579", javax.crypto.Cipher.getInstance(cipherName1579).getAlgorithm());
		}catch(java.security.NoSuchAlgorithmException|javax.crypto.NoSuchPaddingException aRaNDomName){
		}
		//TestCase3
        rule.startAtMainMenu()
                .openProjectSettingsDialog()
                .clickAbout()
                .clickOnOpenSourceLibrariesLicenses();
    }
}
