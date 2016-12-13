package com.akiraspeirs.cinder;

import android.databinding.ObservableInt;

import org.junit.Test;

import static org.junit.Assert.*;

public class CinderIntUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        int int1 = 123;
        int int2 = 43;
        ObservableInt observed = new ObservableInt(int1);
        ObservableInt observing = Cinder.computeInt(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == int1);

        observed.set(int2);
        assertTrue(observing.get() == int1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        int int1 = 123;
        int int2 = 32;
        ObservableInt observed = new ObservableInt(int1);
        ObservableInt observing = Cinder.computeInt(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == int1);

        observed.set(int2);
        assertTrue(observing.get() == int2);

        observed.set(int1);
        assertTrue(observing.get() == int2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        int int1 = 123;
        int int2 = 32;
        ObservableInt observed = new ObservableInt();
        ObservableInt observing = Cinder.computeInt(()->
                        observed.get()
                , observed).skip(1);

        observed.set(int1);
        assertTrue(observing.get() == 0);

        observed.set(int2);
        assertTrue(observing.get() == int2);

        observed.set(int1);
        assertTrue(observing.get() == int1);
    }

    @Test
    public void filterFilters() throws Exception {
        int int1 = 123;
        int int2 = 32;
        ObservableInt observed = new ObservableInt();
        ObservableInt observing = Cinder.computeInt(()->
                        observed.get()
                , observed).filter(()->observed.get() == int1);

        observed.set(int2);
        assertTrue(observing.get() == 0);

        observed.set(int1);
        assertTrue(observing.get() == int1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        int int1 = 123;
        int int2 = 32;
        ObservableInt observed = new ObservableInt();
        ObservableInt observing = Cinder.computeInt(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == int1);

        observed.set(int1);
        assertTrue(observing.get() == int1);

        observed.set(int2);
        assertTrue(observing.get() == int1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        int int1 = 123;
        int int2 = 32;
        ObservableInt observed = new ObservableInt();
        ObservableInt observing = Cinder.computeInt(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == int1);

        observed.set(int1);
        assertTrue(observing.get() == 0);

        observed.set(int2);
        assertTrue(observing.get() == int2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        int int1 = 12;
        ObservableInt observing = Cinder.computeInt(()->0
        ).withDefault(int1);

        assertTrue(observing.get() == int1);
    }

    @Test
    public void stopStops() throws Exception {
        int int1 = 123;
        int int2 = 43;
        ObservableInt observed = new ObservableInt(int1);
        CinderInt observing = Cinder.computeInt(()->
                observed.get(), observed).immediate();
        assertTrue(observing.get() == int1);

        observing.stop();

        observed.set(int2);
        assertTrue(observing.get() == int1);
    }
}