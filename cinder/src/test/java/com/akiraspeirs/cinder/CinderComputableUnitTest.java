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
public class CinderComputableUnitTest {

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
}