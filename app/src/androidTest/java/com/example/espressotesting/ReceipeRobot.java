package com.example.espressotesting;

import android.content.Intent;

import androidx.annotation.StringRes;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.espressotesting.data.InMemorySharedPref;
import com.example.espressotesting.ui.RecipeActivity;

public class ReceipeRobot extends ScreenRobot<ReceipeRobot> {

    private final InMemorySharedPref favorites;

    public ReceipeRobot() {
        TestingApplication app = (TestingApplication)
                InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        favorites  = (InMemorySharedPref) app.getSharedMemory();

    }



    public ReceipeRobot launch(ActivityTestRule rule) {
        rule.launchActivity(null);
        return this;
    }

    public ReceipeRobot launch(ActivityTestRule rule, String id){
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        rule.launchActivity(intent);
        return this;

    }

    public ReceipeRobot noTitle() {
        return  checkIsHidden(R.id.title);
    }
    public ReceipeRobot description(@StringRes int stringId) {
        return checkMatchesText(R.id.description, stringId);
    }

    public ReceipeRobot setFavorite(String id) {
        favorites.put(id, true);
        return this;
    }

    public ReceipeRobot isFavorite() {
        return checkIsSelected(R.id.title);
    }


}