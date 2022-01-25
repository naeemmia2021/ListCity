package com.example.simpleparadox.listycity;

import android.app.Activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
/**
 * Test class for MainActivity. All the UI tests are written here. Robotium test framework is
 used
 */
public class MainActivityTest{

    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);
    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{

        solo = new Solo(InstrumentationRegistry.getInstrumentation(),rule.getActivity());
    }
    /**
     * Gets the Activity
     * @throws Exception
     */
    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }

    @Test
    public void checkListItemClicks(){
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Narsingdi");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Narsingdi", 1, 1000));

        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        final ListView listView = activity.cityList;
        String city1 = (String) listView.getItemAtPosition(0);
        assertEquals("Narsingdi", city1);

        solo.clickOnButton("ClEAR ALL");
        assertFalse(solo.searchText("Narsingdi"));




        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Monohardi");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));
        assertTrue(solo.waitForText("Monohardi", 1, 1000));



        String string1 = listView.getItemAtPosition(0).toString();
        solo.clickOnText(string1);
        solo.assertCurrentActivity("Wrong Activity", MainActivity2.class);




        String text = ((TextView) solo.getView(R.id.text_view)).getText().toString();
        assertEquals("Monohardi", text);


        solo.clickOnButton("Back");

        solo.sleep(5000);
        solo.assertCurrentActivity("Wrong Activity", MainActivity.class);

    }






    /**
     * Close activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}