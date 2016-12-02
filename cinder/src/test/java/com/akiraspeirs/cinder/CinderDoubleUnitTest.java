package com.akiraspeirs.cinder;

import android.databinding.ObservableDouble;

import org.junit.Test;

import static org.junit.Assert.*;

public class CinderDoubleUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        double double1 = 123;
        double double2 = 43;
        ObservableDouble observed = new ObservableDouble(double1);
        ObservableDouble observing = Cinder.computeDouble(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == double1);

        observed.set(double2);
        assertTrue(observing.get() == double1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        double double1 = 123;
        double double2 = 32;
        ObservableDouble observed = new ObservableDouble(double1);
        ObservableDouble observing = Cinder.computeDouble(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == double1);

        observed.set(double2);
        assertTrue(observing.get() == double2);

        observed.set(double1);
        assertTrue(observing.get() == double2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        double double1 = 123;
        double double2 = 32;
        ObservableDouble observed = new ObservableDouble();
        ObservableDouble observing = Cinder.computeDouble(()->
                        observed.get()
                , observed).skip(1);

        observed.set(double1);
        assertTrue(observing.get() == 0);

        observed.set(double2);
        assertTrue(observing.get() == double2);

        observed.set(double1);
        assertTrue(observing.get() == double1);
    }

    @Test
    public void filterFilters() throws Exception {
        double double1 = 123;
        double double2 = 32;
        ObservableDouble observed = new ObservableDouble();
        ObservableDouble observing = Cinder.computeDouble(()->
                        observed.get()
                , observed).filter(()->observed.get() == double1);

        observed.set(double2);
        assertTrue(observing.get() == 0);

        observed.set(double1);
        assertTrue(observing.get() == double1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        double double1 = 123;
        double double2 = 32;
        ObservableDouble observed = new ObservableDouble();
        ObservableDouble observing = Cinder.computeDouble(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == double1);

        observed.set(double1);
        assertTrue(observing.get() == double1);

        observed.set(double2);
        assertTrue(observing.get() == double1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        double double1 = 123;
        double double2 = 32;
        ObservableDouble observed = new ObservableDouble();
        ObservableDouble observing = Cinder.computeDouble(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == double1);

        observed.set(double1);
        assertTrue(observing.get() == 0);

        observed.set(double2);
        assertTrue(observing.get() == double2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        double double1 = 12;
        ObservableDouble observing = Cinder.computeDouble(()->0
        ).withDefault(double1);

        assertTrue(observing.get() == double1);
    }
}