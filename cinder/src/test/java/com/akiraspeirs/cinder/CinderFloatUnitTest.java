package com.akiraspeirs.cinder;

import android.databinding.ObservableFloat;

import org.junit.Test;

import static org.junit.Assert.*;

public class CinderFloatUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        float float1 = 123;
        float float2 = 43;
        ObservableFloat observed = new ObservableFloat(float1);
        ObservableFloat observing = Cinder.computeFloat(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == float1);

        observed.set(float2);
        assertTrue(observing.get() == float1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        float float1 = 123;
        float float2 = 32;
        ObservableFloat observed = new ObservableFloat(float1);
        ObservableFloat observing = Cinder.computeFloat(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == float1);

        observed.set(float2);
        assertTrue(observing.get() == float2);

        observed.set(float1);
        assertTrue(observing.get() == float2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        float float1 = 123;
        float float2 = 32;
        ObservableFloat observed = new ObservableFloat();
        ObservableFloat observing = Cinder.computeFloat(()->
                        observed.get()
                , observed).skip(1);

        observed.set(float1);
        assertTrue(observing.get() == 0);

        observed.set(float2);
        assertTrue(observing.get() == float2);

        observed.set(float1);
        assertTrue(observing.get() == float1);
    }

    @Test
    public void filterFilters() throws Exception {
        float float1 = 123;
        float float2 = 32;
        ObservableFloat observed = new ObservableFloat();
        ObservableFloat observing = Cinder.computeFloat(()->
                        observed.get()
                , observed).filter(()->observed.get() == float1);

        observed.set(float2);
        assertTrue(observing.get() == 0);

        observed.set(float1);
        assertTrue(observing.get() == float1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        float float1 = 123;
        float float2 = 32;
        ObservableFloat observed = new ObservableFloat();
        ObservableFloat observing = Cinder.computeFloat(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == float1);

        observed.set(float1);
        assertTrue(observing.get() == float1);

        observed.set(float2);
        assertTrue(observing.get() == float1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        float float1 = 123;
        float float2 = 32;
        ObservableFloat observed = new ObservableFloat();
        ObservableFloat observing = Cinder.computeFloat(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == float1);

        observed.set(float1);
        assertTrue(observing.get() == 0);

        observed.set(float2);
        assertTrue(observing.get() == float2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        float float1 = 12;
        ObservableFloat observing = Cinder.computeFloat(()->0
        ).withDefault(float1);

        assertTrue(observing.get() == float1);
    }

    @Test
    public void stopStops() throws Exception {
        float float1 = 123;
        float float2 = 43;
        ObservableFloat observed = new ObservableFloat(float1);
        CinderFloat observing = Cinder.computeFloat(()->
                observed.get(), observed).immediate();
        assertTrue(observing.get() == float1);

        observing.stop();

        observed.set(float2);
        assertTrue(observing.get() == float1);
    }
}