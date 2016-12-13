package com.akiraspeirs.cinder;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CinderComputableUnitTest {

    private class TestClass {
        public ObservableInt number = new ObservableInt();
    }

    @Test
    public void onceTakesOnce() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableArrayList<String> observed = new ObservableArrayList<>();
        observed.add(string1);
        CinderComputable computing = Cinder.observable(observed).once().immediate();
        ObservableField<String> observing = Cinder.<String>computeField(()-> {
            return observed.get(0);
        }, computing).immediate();
        assertEquals(string1, observing.get());

        observed.add(0, string2);
        assertEquals(string1, observing.get());
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        String string3 = "STRING 3";
        ObservableArrayList<String> observed = new ObservableArrayList<>();
        observed.add(string1);
        CinderComputable computing = Cinder.observable(observed).take(2).immediate();
        ObservableField<String> observing = Cinder.<String>computeField(()-> {
            return observed.get(0);
        }, computing).immediate();
        assertEquals(string1, observing.get());

        observed.add(0, string2);
        assertEquals(string2, observing.get());

        observed.add(0, string3);
        assertEquals(string2, observing.get());
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableArrayList<String> observed = new ObservableArrayList<>();
        observed.add(string1);
        CinderComputable computing = Cinder.observable(observed).skip(1);
        ObservableField<String> observing = Cinder.<String>computeField(()-> {
            return observed.size() > 0 ?  observed.get(0) : "";
        }, computing);

        observed.add(0, string1);
        assertEquals(null, observing.get());

        observed.add(0, string2);
        assertEquals(string2, observing.get());
    }

    @Test
    public void filterFilters() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        ObservableArrayList<String> observed = new ObservableArrayList<>();
        observed.add(goodString);
        CinderComputable computing = Cinder.observable(observed).filter(()->observed.get(0).startsWith("GOOD"));
        ObservableField<String> observing = Cinder.<String>computeField(()-> {
            return observed.get(0);
        }, computing).immediate();
        assertEquals(goodString, observing.get());

        observed.add(0, badString);
        assertEquals(goodString, observing.get());

    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        String gooderString = "GOODER STRING";
        ObservableArrayList<String> observed = new ObservableArrayList<>();
        observed.add(goodString);
        CinderComputable computing = Cinder.observable(observed).takeWhile(()->observed.get(0).startsWith("GOOD"));
        ObservableField<String> observing = Cinder.<String>computeField(()-> {
            return observed.get(0);
        }, computing).immediate();

        observed.add(0, goodString);
        assertEquals(goodString, observing.get());

        observed.add(0, badString);
        assertEquals(goodString, observing.get());

        observed.add(0, gooderString);
        assertEquals(goodString, observing.get());
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        String badString = "BAD STRING";
        String goodString = "GOOD STRING";
        String gooderString = "GOODER STRING";
        ObservableArrayList<String> observed = new ObservableArrayList<>();
        observed.add(goodString);
        CinderComputable computing = Cinder.observable(observed).takeWhile(()->observed.get(0).startsWith("GOOD"));
        ObservableField<String> observing = Cinder.<String>computeField(()-> {
            return observed.get(0);
        }, computing).immediate();

        observed.add(0, goodString);
        assertEquals(goodString, observing.get());

        observed.add(0, badString);
        assertEquals(goodString, observing.get());

        observed.add(0, gooderString);
        assertEquals(goodString, observing.get());
    }

    @Test
    public void stopArrayListStops() throws Exception {
        int TEST = 43;
        ObservableArrayList<TestClass> observed = new ObservableArrayList<>();
        TestClass testClass = new TestClass();
        testClass.number.set(TEST);
        observed.add(testClass);
        CinderComputable computing = Cinder.observable(observed, TestClass.class, "number").immediate();
        CinderInt observing = Cinder.computeInt(()->
                observed.get(0).number.get(), computing).immediate();
        assertEquals(TEST, observing.get());

        computing.stop();

        observed.get(0).number.set(23);
        assertEquals(TEST, observing.get());

        observed.add(new TestClass());
        assertEquals(TEST, observing.get());
    }

    @Test
    public void stopArrayMapStops() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        int TEST = 43;
        ObservableArrayMap<String, TestClass> observed = new ObservableArrayMap<>();
        TestClass testClass = new TestClass();
        testClass.number.set(TEST);
        observed.put(string1, testClass);
        CinderComputable computing = Cinder.observable(observed, TestClass.class, "number").immediate();
        CinderInt observing = Cinder.computeInt(()->
            observed.get(string1).number.get(), computing).immediate();
        assertEquals(TEST, observing.get());

        computing.stop();

        observed.get(string1).number.set(23);
        assertEquals(TEST, observing.get());

        observed.put(string2, new TestClass());
        assertEquals(TEST, observing.get());
    }
}