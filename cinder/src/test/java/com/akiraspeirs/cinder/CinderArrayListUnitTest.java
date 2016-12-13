package com.akiraspeirs.cinder;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.akiraspeirs.cinder.Cinder;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CinderArrayListUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableField<String> observed = new ObservableField<>(string1);
        ObservableArrayList<String> observing = Cinder.<String>computeArrayList((list)->
            list.add(0, observed.get()), observed).once().immediate();
        assertEquals(string1, observing.get(0));

        observed.set(string2);
        assertEquals(string1, observing.get(0));
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        String string3 = "STRING 3";
        ObservableField<String> observed = new ObservableField<>(string1);
        ObservableArrayList<String> observing = Cinder.<String>computeArrayList((list)->{
            list.add(0, observed.get());
        }, observed).take(2).immediate();
        assertEquals(string1, observing.get(0));

        observed.set(string2);
        assertEquals(string2, observing.get(0));

        observed.set(string3);
        assertEquals(string2, observing.get(0));
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableField<String> observed = new ObservableField<>();
        ObservableArrayList<String> observing = Cinder.<String>computeArrayList((list)->{
            list.add(0, observed.get());
        }, observed).skip(1);

        observed.set(string1);
        assertEquals(0, observing.size());

        observed.set(string2);
        assertEquals(string2, observing.get(0));
    }

    @Test
    public void filterFilters() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        ObservableField<String> observed = new ObservableField<>("");
        ObservableArrayList<String> observing = Cinder.<String>computeArrayList((list)->{
            list.add(0, observed.get());
        }, observed).filter(()->observed.get().startsWith("GOOD"));

        observed.set(badString);
        assertEquals(0, observing.size());

        observed.set(goodString);
        assertEquals(goodString, observing.get(0));
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        String gooderString = "GOODER STRING";
        ObservableField<String> observed = new ObservableField<>("");
        ObservableArrayList<String> observing = Cinder.<String>computeArrayList((list)->{
            list.add(0, observed.get());
        }, observed).takeWhile(()->observed.get().startsWith("GOOD"));

        observed.set(goodString);
        assertEquals(goodString, observing.get(0));

        observed.set(badString);
        assertEquals(goodString, observing.get(0));

        observed.set(gooderString);
        assertEquals(goodString, observing.get(0));
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        String gooderString = "GOODER STRING";
        ObservableField<String> observed = new ObservableField<>("");
        ObservableArrayList<String> observing = Cinder.<String>computeArrayList((list)->{
            list.add(0, observed.get());
        }, observed).takeWhile(()->observed.get().startsWith("GOOD"));

        observed.set(goodString);
        assertEquals(goodString, observing.get(0));

        observed.set(badString);
        assertEquals(goodString, observing.get(0));

        observed.set(gooderString);
        assertEquals(goodString, observing.get(0));
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        String testString = "TEST";
        ArrayList<String> defaultValue = new ArrayList<>();
        defaultValue.add(testString);

        ObservableArrayList<String> observing = Cinder.<String>computeArrayList((list)->{
        }).withDefault(defaultValue);

        assertEquals(testString, observing.get(0));
    }

    @Test
    public void stopStops() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableField<String> observed = new ObservableField<>(string1);
        CinderArrayList<String> observing = Cinder.<String>computeArrayList((list)->
                list.add(0, observed.get()), observed).immediate();
        assertEquals(string1, observing.get(0));

        observing.stop();
        observed.set(string2);
        assertEquals(string1, observing.get(0));
    }
}