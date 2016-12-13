package com.akiraspeirs.cinder;

import android.databinding.ObservableChar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CinderCharUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        char char1 = 123;
        char char2 = 43;
        ObservableChar observed = new ObservableChar(char1);
        ObservableChar observing = Cinder.computeChar(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == char1);

        observed.set(char2);
        assertTrue(observing.get() == char1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        char char1 = 123;
        char char2 = 32;
        ObservableChar observed = new ObservableChar(char1);
        ObservableChar observing = Cinder.computeChar(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == char1);

        observed.set(char2);
        assertTrue(observing.get() == char2);

        observed.set(char1);
        assertTrue(observing.get() == char2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        char char1 = 123;
        char char2 = 32;
        ObservableChar observed = new ObservableChar();
        ObservableChar observing = Cinder.computeChar(()->
                        observed.get()
                , observed).skip(1);

        observed.set(char1);
        assertTrue(observing.get() == 0);

        observed.set(char2);
        assertTrue(observing.get() == char2);

        observed.set(char1);
        assertTrue(observing.get() == char1);
    }

    @Test
    public void filterFilters() throws Exception {
        char char1 = 123;
        char char2 = 32;
        ObservableChar observed = new ObservableChar();
        ObservableChar observing = Cinder.computeChar(()->
                        observed.get()
                , observed).filter(()->observed.get() == char1);

        observed.set(char2);
        assertTrue(observing.get() == 0);

        observed.set(char1);
        assertTrue(observing.get() == char1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        char char1 = 123;
        char char2 = 32;
        ObservableChar observed = new ObservableChar();
        ObservableChar observing = Cinder.computeChar(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == char1);

        observed.set(char1);
        assertTrue(observing.get() == char1);

        observed.set(char2);
        assertTrue(observing.get() == char1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        char char1 = 123;
        char char2 = 32;
        ObservableChar observed = new ObservableChar();
        ObservableChar observing = Cinder.computeChar(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == char1);

        observed.set(char1);
        assertTrue(observing.get() == 0);

        observed.set(char2);
        assertTrue(observing.get() == char2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        char char1 = 12;
        ObservableChar observing = Cinder.computeChar(()->0
        ).withDefault(char1);

        assertTrue(observing.get() == char1);
    }

    @Test
    public void stopStops() throws Exception {
        char char1 = 123;
        char char2 = 43;
        ObservableChar observed = new ObservableChar(char1);
        CinderChar observing = Cinder.computeChar(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == char1);

        observing.stop();

        observed.set(char2);
        assertTrue(observing.get() == char1);
    }
}