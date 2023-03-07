package org.odk.collect.android.support.pages;

import org.odk.collect.android.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.odk.collect.android.support.matchers.CustomMatchers.withIndex;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.action.ViewActions;

public class UserInterfacePage extends Page<UserInterfacePage> {

    @Override
    public UserInterfacePage assertOnPage() {
        assertText(R.string.client);
        return this;
    }

    public UserInterfacePage clickOnLanguage() {
        onView(withText(getTranslatedString(R.string.language))).perform(click());
        return this;
    }

    public MainMenuPage clickOnSelectedLanguage(String language) {
        try {
            onView(withText(language)).perform(click());
        } catch (NoMatchingViewException e) {
            for (int i = 0; i < 10; i++) {
                onView(withIndex(withId(android.R.id.text1), 1)).perform(ViewActions.swipeUp());
            }
            clickOnSelectedLanguage(language);
        }

        return new MainMenuPage().assertOnPage();
    }

    public UserInterfacePage clickNavigation() {
        clickOnString(R.string.navigation);
        return this;
    }

    public UserInterfacePage clickUseSwipesAndButtons() {
        clickOnString(R.string.swipe_buttons_navigation);
        return this;
    }

    public UserInterfacePage clickOnTheme() {
        onView(withText(getTranslatedString(R.string.app_theme))).perform(click());
        return this;
    }

    public UserInterfacePage clickUseNavigationButtons() {
        clickOnString(R.string.buttons_navigation);
        return this;
    }

    public UserInterfacePage clickSwipes() {
        clickOnString(R.string.swipe_navigation);
        return this;
    }
}
