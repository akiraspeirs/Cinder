package com.akiraspeirs.cinder;

import android.databinding.ObservableLong;

import org.junit.Test;

import static org.junit.Assert.*;

public class CinderLongUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        long long1 = 123;
        long long2 = 43;
        ObservableLong observed = new ObservableLong(long1);
        ObservableLong observing = Cinder.computeLong(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == long1);

        observed.set(long2);
        assertTrue(observing.get() == long1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        long long1 = 123;
        long long2 = 32;
        ObservableLong observed = new ObservableLong(long1);
        ObservableLong observing = Cinder.computeLong(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == long1);

        observed.set(long2);
        assertTrue(observing.get() == long2);

        observed.set(long1);
        assertTrue(observing.get() == long2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        long long1 = 123;
        long long2 = 32;
        ObservableLong observed = new ObservableLong();
        ObservableLong observing = Cinder.computeLong(()->
                        observed.get()
                , observed).skip(1);

        observed.set(long1);
        assertTrue(observing.get() == 0);

        observed.set(long2);
        assertTrue(observing.get() == long2);

        observed.set(long1);
        assertTrue(observing.get() == long1);
    }

    @Test
    public void filterFilters() throws Exception {
        long long1 = 123;
        long long2 = 32;
        ObservableLong observed = new ObservableLong();
        ObservableLong observing = Cinder.computeLong(()->
                        observed.get()
                , observed).filter(()->observed.get() == long1);

        observed.set(long2);
        assertTrue(observing.get() == 0);

        observed.set(long1);
        assertTrue(observing.get() == long1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        long long1 = 123;
        long long2 = 32;
        ObservableLong observed = new ObservableLong();
        ObservableLong observing = Cinder.computeLong(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == long1);

        observed.set(long1);
        assertTrue(observing.get() == long1);

        observed.set(long2);
        assertTrue(observing.get() == long1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        long long1 = 123;
        long long2 = 32;
        ObservableLong observed = new ObservableLong();
        ObservableLong observing = Cinder.computeLong(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == long1);

        observed.set(long1);
        assertTrue(observing.get() == 0);

        observed.set(long2);
        assertTrue(observing.get() == long2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        long long1 = 12;
        ObservableLong observing = Cinder.computeLong(()->0
        ).withDefault(long1);

        assertTrue(observing.get() == long1);
    }
}