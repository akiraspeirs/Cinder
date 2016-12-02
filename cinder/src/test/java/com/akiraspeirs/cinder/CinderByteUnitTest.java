package com.akiraspeirs.cinder;

import android.databinding.ObservableByte;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CinderByteUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        byte byte1 = 123;
        byte byte2 = 43;
        ObservableByte observed = new ObservableByte(byte1);
        ObservableByte observing = Cinder.computeByte(()->
            observed.get(), observed).once().immediate();
        assertTrue(observing.get() == byte1);

        observed.set(byte2);
        assertTrue(observing.get() == byte1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        byte byte1 = 123;
        byte byte2 = 32;
        ObservableByte observed = new ObservableByte(byte1);
        ObservableByte observing = Cinder.computeByte(()->
                observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == byte1);

        observed.set(byte2);
        assertTrue(observing.get() == byte2);

        observed.set(byte1);
        assertTrue(observing.get() == byte2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        byte byte1 = 123;
        byte byte2 = 32;
        ObservableByte observed = new ObservableByte();
        ObservableByte observing = Cinder.computeByte(()->
                observed.get()
                , observed).skip(1);

        observed.set(byte1);
        assertTrue(observing.get() == 0);

        observed.set(byte2);
        assertTrue(observing.get() == byte2);

        observed.set(byte1);
        assertTrue(observing.get() == byte1);
    }

    @Test
    public void filterFilters() throws Exception {
        byte byte1 = 123;
        byte byte2 = 32;
        ObservableByte observed = new ObservableByte();
        ObservableByte observing = Cinder.computeByte(()->
                        observed.get()
                , observed).filter(()->observed.get() == byte1);

        observed.set(byte2);
        assertTrue(observing.get() == 0);

        observed.set(byte1);
        assertTrue(observing.get() == byte1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        byte byte1 = 123;
        byte byte2 = 32;
        ObservableByte observed = new ObservableByte();
        ObservableByte observing = Cinder.computeByte(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == byte1);

        observed.set(byte1);
        assertTrue(observing.get() == byte1);

        observed.set(byte2);
        assertTrue(observing.get() == byte1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        byte byte1 = 123;
        byte byte2 = 32;
        ObservableByte observed = new ObservableByte();
        ObservableByte observing = Cinder.computeByte(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == byte1);

        observed.set(byte1);
        assertTrue(observing.get() == 0);

        observed.set(byte2);
        assertTrue(observing.get() == byte2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        byte byte1 = 12;
        ObservableByte observing = Cinder.computeByte(()->0
        ).withDefault(byte1);

        assertTrue(observing.get() == byte1);
    }
}