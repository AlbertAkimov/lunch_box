package com.example.lunchbox;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.util.SHAUtil;

import java.security.NoSuchAlgorithmException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws NoSuchAlgorithmException {
        assertEquals(4, 2 + 2);
    }
}