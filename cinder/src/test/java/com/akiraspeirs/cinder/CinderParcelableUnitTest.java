package com.akiraspeirs.cinder;

import android.databinding.ObservableParcelable;
import android.graphics.Point;

import org.junit.Test;

import static org.junit.Assert.*;

public class CinderParcelableUnitTest {

    @Test
    public void onceTakesOnce() throws Exception {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(14, 21);
        ObservableParcelable<Point> observed = new ObservableParcelable<>(point1);
        ObservableParcelable<Point> observing = Cinder.<Point>computeParcelable(()->
                observed.get(), observed).once().immediate();
        assertTrue(observing.get() == point1);

        observed.set(point2);
        assertTrue(observing.get() == point1);
    }

    @Test
    public void takeTakesCorrectTimes() throws Exception {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(41, 11);
        ObservableParcelable<Point> observed = new ObservableParcelable<>(point1);
        ObservableParcelable<Point> observing = Cinder.<Point>computeParcelable(()->
                        observed.get()
                , observed).take(2).immediate();
        assertTrue(observing.get() == point1);

        observed.set(point2);
        assertTrue(observing.get() == point2);

        observed.set(point1);
        assertTrue(observing.get() == point2);
    }

    @Test
    public void skipSkipsCorrectTimes() throws Exception {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(41, 11);
        ObservableParcelable<Point> observed = new ObservableParcelable<>();
        ObservableParcelable<Point> observing = Cinder.<Point>computeParcelable(()->
                        observed.get()
                , observed).skip(1);

        observed.set(point1);
        assertTrue(observing.get() == null);

        observed.set(point2);
        assertTrue(observing.get() == point2);

        observed.set(point1);
        assertTrue(observing.get() == point1);
    }

    @Test
    public void filterFilters() throws Exception {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(41, 11);
        ObservableParcelable<Point> observed = new ObservableParcelable<>();
        ObservableParcelable<Point> observing = Cinder.<Point>computeParcelable(()->
                        observed.get()
                , observed).filter(()->observed.get() == point1);

        observed.set(point2);
        assertTrue(observing.get() == null);

        observed.set(point1);
        assertTrue(observing.get() == point1);
    }

    @Test
    public void takeWhileTakesWhileTrue() throws Exception {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(41, 11);
        ObservableParcelable<Point> observed = new ObservableParcelable<>();
        ObservableParcelable<Point> observing = Cinder.<Point>computeParcelable(()->
                        observed.get()
                , observed).takeWhile(()->observed.get() == point1);

        observed.set(point1);
        assertTrue(observing.get() == point1);

        observed.set(point2);
        assertTrue(observing.get() == point1);
    }

    @Test
    public void skipWhileSkipsWhileTrue() throws Exception {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(41, 11);
        ObservableParcelable<Point> observed = new ObservableParcelable<>();
        ObservableParcelable<Point> observing = Cinder.<Point>computeParcelable(()->
                        observed.get()
                , observed).skipWhile(()->observed.get() == point1);

        observed.set(point1);
        assertTrue(observing.get() == null);

        observed.set(point2);
        assertTrue(observing.get() == point2);
    }

    @Test
    public void withDefaultAssignsDefault() throws Exception {
        Point point1 = new Point(56, 65);
        ObservableParcelable<Point> observing = Cinder.<Point>computeParcelable(()->new Point(14, 8)
        ).withDefault(point1);

        assertTrue(observing.get() == point1);
    }

    @Test
    public void stopStops() throws Exception {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(14, 21);
        ObservableParcelable<Point> observed = new ObservableParcelable<>(point1);
        CinderParcelable<Point> observing = Cinder.<Point>computeParcelable(()->
                observed.get(), observed).immediate();
        assertTrue(observing.get() == point1);

        observing.stop();

        observed.set(point2);
        assertTrue(observing.get() == point1);
    }
}