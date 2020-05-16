package com.example.espressotesting.ui;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.espressotesting.R;
import com.example.espressotesting.ReceipeRobot;
import com.example.espressotesting.TestingApplication;
import com.example.espressotesting.data.InMemorySharedPref;
import com.example.espressotesting.data.SharedMemory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class RecipeActivityTest {
    @Rule
    public ActivityTestRule<RecipeActivity> activityRule = new ActivityTestRule<>(RecipeActivity.class, true, false);
    private InMemorySharedPref sharedMemory;
    private String id = "creamed_carrots";

    @Test
    public void launchEmptyActivity() {
        activityRule.launchActivity(null);
        onView(withId(R.id.description)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(withText(R.string.recipe_not_found)));

    }

    @Test
    public void launchIntentActivity() {
        sharedMemory.clear();
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, "creamed_carrots");
        activityRule.launchActivity(intent);

        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.title))
                .check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));


    }

    @Test
    public void test_alreadyFavtour() {
        sharedMemory.put(id,true);
        launchRecipe(id);
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.title)).check(matches(isSelected()))
                .perform(click())
                .check(matches(not(isSelected())));
    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        activityRule.launchActivity(intent);
    }

    @Before
    public void before() {
       TestingApplication app=  (TestingApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        sharedMemory = (InMemorySharedPref) app.getSharedMemory();
    }



    @Test
    public void recipeNotFound() {
        new ReceipeRobot()
                .launch(activityRule)
                .noTitle()
                .description(R.string.recipe_not_found);
    }


    @Test
    public void alreadyFavorite() {
        new ReceipeRobot()
                .setFavorite(id)
                .launch(activityRule, id)
                .isFavorite();
    }

}