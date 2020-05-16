package com.example.espressotesting;

import androidx.annotation.IdRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.StringRes;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public abstract class ScreenRobot<T extends  ScreenRobot> {

    public T checkIsHidden(@IdRes  int... viewIds){
        for (int view:viewIds){
            onView(withId(view)).check(matches(not(isDisplayed())));
        }
        return (T) this;
    }
    public T checkMatchesText(@IdRes int id, @StringRes int value){
        onView(withId(id)).check(matches(withText(value)));
        return (T) this;
    }

    public T checkIsSelected(@IdRes int...viewIds){

        for (int view:viewIds){
            onView(withId(view)).check(matches(isSelected()));
        }
        return (T) this;
    }

}
