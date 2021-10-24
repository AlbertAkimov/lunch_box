package com.example.lunchbox;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.database.AppDatabase;
import com.example.model.User;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
 /*       AppDatabase appDatabase = AppDatabase.getInstance(appContext);
        List<User> users = appDatabase.userRepository().getAll();
        for(User user: users)
            appDatabase.userRepository().delete(user);*/
        assertEquals("com.example.lunchbox", appContext.getPackageName());
    }
}