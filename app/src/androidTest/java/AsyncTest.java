import android.support.test.rule.ActivityTestRule;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.*;

public class AsyncTest {

    // Provides a reference to the test activity using getActivity()
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void responseIsNotAnEmptyString() {

        // click on the tell joke button
        onView(withId(R.id.btn_tell_joke))
                .perform(click());

        // Register the idling counter (test pauses until counter reaches zero?)
        registerIdlingResources(mActivityTestRule.getActivity().mAsyncTask.mIdlingCounter);

        // Test that joke is not an empty string using JUnit
        String joke = mActivityTestRule.getActivity().mAsyncTask.mJoke;
        assertNotEquals(joke,"");
    }
}
