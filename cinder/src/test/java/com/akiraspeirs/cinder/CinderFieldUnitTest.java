package com.akiraspeirs.cinder;

import android.databinding.ObservableField;

import org.junit.Test;

import static org.junit.Assert.*;

public class CinderFieldUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        String string1 = "123";
        String string2 = "43";
        ObservableField<String> observed = new ObservableField<>(string1);
        ObservableField<String> observing = Cinder.<String>computeField(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == string1);

        observed.set(string2);
        assertTrue(observing.get() == string1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        String string1 = "123";
        String string2 = "32";
        ObservableField<String> observed = new ObservableField<>(string1);
        ObservableField<String> observing = Cinder.<String>computeField(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == string1);

        observed.set(string2);
        assertTrue(observing.get() == string2);

        observed.set(string1);
        assertTrue(observing.get() == string2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        String string1 = "123";
        String string2 = "32";
        ObservableField<String> observed = new ObservableField<>();
        ObservableField<String> observing = Cinder.<String>computeField(()->
                        observed.get()
                , observed).skip(1);

        observed.set(string1);
        assertTrue(observing.get() == null);

        observed.set(string2);
        assertTrue(observing.get() == string2);

        observed.set(string1);
        assertTrue(observing.get() == string1);
    }

    @Test
    public void filterFilters() throws Exception {
        String string1 = "123";
        String string2 = "32";
        ObservableField<String> observed = new ObservableField<>();
        ObservableField<String> observing = Cinder.<String>computeField(()->
                        observed.get()
                , observed).filter(()->observed.get() == string1);

        observed.set(string2);
        assertTrue(observing.get() == null);

        observed.set(string1);
        assertTrue(observing.get() == string1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        String string1 = "123";
        String string2 = "32";
        ObservableField<String> observed = new ObservableField<>();
        ObservableField<String> observing = Cinder.<String>computeField(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == string1);

        observed.set(string1);
        assertTrue(observing.get() == string1);

        observed.set(string2);
        assertTrue(observing.get() == string1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        String string1 = "123";
        String string2 = "32";
        ObservableField<String> observed = new ObservableField<>();
        ObservableField<String> observing = Cinder.<String>computeField(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == string1);

        observed.set(string1);
        assertTrue(observing.get() == null);

        observed.set(string2);
        assertTrue(observing.get() == string2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        String string1 = "12";
        ObservableField<String> observing = Cinder.<String>computeField(()->""
        ).withDefault(string1);

        assertTrue(observing.get() == string1);
    }

    @Test
    public void stopStops() throws Exception {
        String string1 = "123";
        String string2 = "43";
        ObservableField<String> observed = new ObservableField<>(string1);
        CinderField<String> observing = Cinder.<String>computeField(()->
                observed.get(), observed).immediate();
        assertTrue(observing.get() == string1);

        observing.stop();
        observed.set(string2);
        assertTrue(observing.get() == string1);
    }
}