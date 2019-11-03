package com.nelsonlopes.bakingapp;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.runner.AndroidJUnit4;

import com.nelsonlopes.bakingapp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class BakingAppBasicTest {
    private static final int KEY_ITEM_POSITION = 1;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void clickOnRecipe_checkIfIngredientsTileShows() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(KEY_ITEM_POSITION, click()));

        // Match the Ingredients title text in the Master List Fragment and check that it's displayed.
        String itemElementText = "Ingredients";
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnRecipe_checkIfIngredientsAreListed() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(KEY_ITEM_POSITION, click()));

        // Match the Ingredients RecyclerView in the Master List Fragment and check that it's displayed.
        onView(ViewMatchers.withId(R.id.rv_ingredients)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnRecipe_checkIfStepsTitleShows() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(KEY_ITEM_POSITION, click()));

        // Match the Steps title text in the Master List Fragment and check that it's displayed.
        String itemElementText = "Steps";
        onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnRecipe_checkIfStepsAreListed() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(KEY_ITEM_POSITION, click()));

        // Match the Steps RecyclerView in the Master List Fragment and check that it's displayed.
        onView(ViewMatchers.withId(R.id.rv_steps)).check(matches(isDisplayed()));
    }
}
