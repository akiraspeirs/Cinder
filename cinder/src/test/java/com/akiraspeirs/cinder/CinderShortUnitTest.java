package com.akiraspeirs.cinder;

import android.databinding.ObservableShort;

import org.junit.Test;

import static org.junit.Assert.*;

public class CinderShortUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        short short1 = 123;
        short short2 = 43;
        ObservableShort observed = new ObservableShort(short1);
        ObservableShort observing = Cinder.computeShort(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == short1);

        observed.set(short2);
        assertTrue(observing.get() == short1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        short short1 = 123;
        short short2 = 32;
        ObservableShort observed = new ObservableShort(short1);
        ObservableShort observing = Cinder.computeShort(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == short1);

        observed.set(short2);
        assertTrue(observing.get() == short2);

        observed.set(short1);
        assertTrue(observing.get() == short2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        short short1 = 123;
        short short2 = 32;
        ObservableShort observed = new ObservableShort();
        ObservableShort observing = Cinder.computeShort(()->
                        observed.get()
                , observed).skip(1);

        observed.set(short1);
        assertTrue(observing.get() == 0);

        observed.set(short2);
        assertTrue(observing.get() == short2);

        observed.set(short1);
        assertTrue(observing.get() == short1);
    }

    @Test
    public void filterFilters() throws Exception {
        short short1 = 123;
        short short2 = 32;
        ObservableShort observed = new ObservableShort();
        ObservableShort observing = Cinder.computeShort(()->
                        observed.get()
                , observed).filter(()->observed.get() == short1);

        observed.set(short2);
        assertTrue(observing.get() == 0);

        observed.set(short1);
        assertTrue(observing.get() == short1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        short short1 = 123;
        short short2 = 32;
        ObservableShort observed = new ObservableShort();
        ObservableShort observing = Cinder.computeShort(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == short1);

        observed.set(short1);
        assertTrue(observing.get() == short1);

        observed.set(short2);
        assertTrue(observing.get() == short1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        short short1 = 123;
        short short2 = 32;
        ObservableShort observed = new ObservableShort();
        ObservableShort observing = Cinder.computeShort(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == short1);

        observed.set(short1);
        assertTrue(observing.get() == 0);

        observed.set(short2);
        assertTrue(observing.get() == short2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        short short1 = 12;
        ObservableShort observing = Cinder.computeShort(()->0
        ).withDefault(short1);

        assertTrue(observing.get() == short1);
    }

    @Test
    public void stopStops() throws Exception {
        short short1 = 123;
        short short2 = 43;
        ObservableShort observed = new ObservableShort(short1);
        CinderShort observing = Cinder.computeShort(()->
                observed.get(), observed).immediate();
        assertTrue(observing.get() == short1);

        observing.stop();
        observed.set(short2);
        assertTrue(observing.get() == short1);
    }
}