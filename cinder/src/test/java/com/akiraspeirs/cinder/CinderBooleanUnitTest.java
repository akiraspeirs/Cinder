package com.akiraspeirs.cinder;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.akiraspeirs.cinder.Cinder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CinderBooleanUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableField<String> observed = new ObservableField<>(string1);
        ObservableBoolean observing = Cinder.computeBoolean(()->
            observed.get().equals(string1), observed).once().immediate();
        assertTrue(observing.get());

        observed.set(string2);
        assertTrue(observing.get());
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableField<String> observed = new ObservableField<>(string1);
        ObservableBoolean observing = Cinder.computeBoolean(()->
            observed.get().equals(string1)
            , observed).take(2).immediate();
        assertTrue(observing.get());

        observed.set(string2);
        assertFalse(observing.get());

        observed.set(string1);
        assertFalse(observing.get());
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableField<String> observed = new ObservableField<>(string1);
        ObservableBoolean observing = Cinder.computeBoolean(()->
            observed.get().equals(string1)
            , observed).skip(1);

        observed.set(string1);
        assertFalse(observing.get());

        observed.set(string2);
        assertFalse(observing.get());

        observed.set(string1);
        assertTrue(observing.get());
    }

    @Test
    public void filterFilters() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        ObservableField<String> observed = new ObservableField<>("");
        ObservableBoolean observing = Cinder.computeBoolean(()->
            observed.get().equals(goodString)
            , observed).filter(()->observed.get().startsWith("GOOD"));

        observed.set(badString);
        assertFalse(observing.get());

        observed.set(goodString);
        assertTrue(observing.get());
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        ObservableField<String> observed = new ObservableField<>("");
        ObservableBoolean observing = Cinder.computeBoolean(()->
            observed.get().equals(goodString)
            , observed).takeWhile(()->observed.get().startsWith("GOOD"));

        observed.set(goodString);
        assertTrue(observing.get());

        observed.set(badString);
        assertTrue(observing.get());
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        ObservableField<String> observed = new ObservableField<>("");
        ObservableBoolean observing = Cinder.computeBoolean(()->
            observed.get().equals(goodString)
            , observed).skipWhile(()->observed.get().startsWith("BAD"));

        observed.set(badString);
        assertFalse(observing.get());

        observed.set(goodString);
        assertTrue(observing.get());
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        ObservableBoolean observing = Cinder.computeBoolean(()->false
        ).withDefault(true);

        assertTrue(observing.get());
    }
}