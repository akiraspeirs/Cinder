package com.akiraspeirs.cinder_rx;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableByte;
import android.databinding.ObservableChar;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.databinding.ObservableParcelable;
import android.databinding.ObservableShort;
import android.graphics.Point;
import android.support.v4.util.ArrayMap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RxCinderUnitTest {
    @Test
    public void convertsObservableArrayListToRx() throws Exception {
        ObservableArrayList<String> observableStrings = new ObservableArrayList<>();
        Observable<ArrayList<String>> rxStrings = RxCinder.convert(observableStrings);

        final ArrayList<String> testStrings = new ArrayList<>(Arrays.asList("Corndog"));
        rxStrings.subscribe((stringArray)->{
            Collections.copy(testStrings, stringArray);});

        final String TEST_STRING = "Banana";
        observableStrings.add(TEST_STRING);
        assertTrue(testStrings.get(0).equals(TEST_STRING));
    }

    @Test
    public void convertsObservableArrayMapToRx() throws Exception {
        ObservableArrayMap<String, String> observableStringMap = new ObservableArrayMap<>();
        Observable<ArrayMap<String, String>> rxStringMap = RxCinder.convert(observableStringMap);
        final String TEST_KEY = "TEST_KEY";

        final ArrayMap<String, String> testStringMap = new ArrayMap<>();
        rxStringMap.subscribe((stringMap)->{
            testStringMap.put(TEST_KEY, stringMap.get(TEST_KEY));});

        final String TEST_VALUE = "TEST_VALUE";
        observableStringMap.put(TEST_KEY, TEST_VALUE);
        assertTrue(testStringMap.get(TEST_KEY).equals(TEST_VALUE));
    }

    @Test
    public void convertsObservableBooleanToRx() throws Exception {
        ObservableBoolean observableBoolean = new ObservableBoolean();
        Observable<Boolean> rxBoolean = RxCinder.convert(observableBoolean);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Boolean> testMap = new ArrayMap<>();
        rxBoolean.subscribe((b)->{
            testMap.put(TEST_KEY, b);});

        observableBoolean.set(true);
        assertTrue(testMap.get(TEST_KEY));
    }

    @Test
    public void convertsObservableByteToRx() throws Exception {
        ObservableByte observableByte = new ObservableByte();
        Observable<Byte> rxByte = RxCinder.convert(observableByte);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Byte> testMap = new ArrayMap<>();
        rxByte.subscribe((b)->{
            testMap.put(TEST_KEY, b);});

        final byte TEST_BYTE = (byte)3;
        observableByte.set(TEST_BYTE);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_BYTE));
    }

    @Test
    public void convertsObservableCharToRx() throws Exception {
        ObservableChar observableChar = new ObservableChar();
        Observable<Character> rxChar = RxCinder.convert(observableChar);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Character> testMap = new ArrayMap<>();
        rxChar.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final char TEST_CHAR = 'X';
        observableChar.set(TEST_CHAR);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_CHAR));
    }

    @Test
    public void convertsObservableDoubleToRx() throws Exception {
        ObservableDouble observableDouble = new ObservableDouble();
        Observable<Double> rxDouble = RxCinder.convert(observableDouble);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Double> testMap = new ArrayMap<>();
        rxDouble.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final double TEST_DOUBLE = 23;
        observableDouble.set(TEST_DOUBLE);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_DOUBLE));
    }

    @Test
    public void convertsObservableFieldToRx() throws Exception {
        ObservableField<String> observableField = new ObservableField();
        Observable<String> rxString = RxCinder.convert(observableField);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, String> testMap = new ArrayMap<>();
        rxString.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final String TEST_STRING = "TEST_STRING";
        observableField.set(TEST_STRING);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_STRING));
    }

    @Test
    public void convertsObservableFloatToRx() throws Exception {
        ObservableFloat observableFloat = new ObservableFloat();
        Observable<Float> rxFloat = RxCinder.convert(observableFloat);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Float> testMap = new ArrayMap<>();
        rxFloat.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final float TEST_FLOAT = 23;
        observableFloat.set(TEST_FLOAT);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_FLOAT));
    }

    @Test
    public void convertsObservableIntToRx() throws Exception {
        ObservableInt observableInt = new ObservableInt();
        Observable<Integer> rxInt = RxCinder.convert(observableInt);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Integer> testMap = new ArrayMap<>();
        rxInt.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final int TEST_INT = 23;
        observableInt.set(TEST_INT);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_INT));
    }

    @Test
    public void convertsObservableLongToRx() throws Exception {
        ObservableLong observableLong = new ObservableLong();
        Observable<Long> rxLong = RxCinder.convert(observableLong);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Long> testMap = new ArrayMap<>();
        rxLong.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final long TEST_LONG = 23;
        observableLong.set(TEST_LONG);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_LONG));
    }

    @Test
    public void convertsObservableParcableToRx() throws Exception {
        ObservableParcelable<Point> observableParcalable = new ObservableParcelable<Point>();
        Observable<Point> rxParcalable = RxCinder.convert(observableParcalable);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Point> testMap = new ArrayMap<>();
        rxParcalable.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final Point TEST_POINT = new Point(12, 23);
        observableParcalable.set(TEST_POINT);
        assertTrue(testMap.get(TEST_KEY).x == TEST_POINT.x);
    }

    @Test
    public void convertsObservableShortToRx() throws Exception {
        ObservableShort observableShort = new ObservableShort();
        Observable<Short> rxShort = RxCinder.convert(observableShort);

        final String TEST_KEY = "TEST_KEY";
        final ArrayMap<String, Short> testMap = new ArrayMap<>();
        rxShort.subscribe((c)->{
            testMap.put(TEST_KEY, c);});

        final short TEST_SHORT = 23;
        observableShort.set(TEST_SHORT);
        assertTrue(testMap.get(TEST_KEY).equals(TEST_SHORT));
    }

    @Test
    public void convertsRxListToObservable() throws Exception {
        final String TEST_STRING = "Banana";
        Observable<List<String>> rxStrings = Observable.just(new ArrayList<>(Arrays.asList(TEST_STRING)));
        ObservableArrayList<String> observableStrings = RxCinder.convertArrayList(rxStrings);
        assertTrue(observableStrings.get(0).equals(TEST_STRING));
    }

    @Test
    public void convertsRxMapToObservable() throws Exception {
        final String TEST_KEY = "Apple";
        final String TEST_STRING = "Banana";
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put(TEST_KEY, TEST_STRING);
        Observable<Map<String, String>> rxMap= Observable.just(map);
        ObservableArrayMap<String, String> observableMap = RxCinder.convertArrayMap(rxMap);
        assertTrue(observableMap.get(TEST_KEY).equals(TEST_STRING));
    }

    @Test
    public void convertsRxBooleanToObservable() throws Exception {
        Observable<Boolean> rxBoolean = Observable.just(true);
        ObservableBoolean observableBoolean = RxCinder.convertBoolean(rxBoolean);
        assertTrue(observableBoolean.get());
    }

    @Test
    public void convertsRxByteToObservable() throws Exception {
        byte TEST_BYTE = (byte)3;
        Observable<Byte> rxByte = Observable.just(TEST_BYTE);
        ObservableByte observableByte = RxCinder.convertByte(rxByte);
        assertTrue(observableByte.get() == TEST_BYTE);
    }

    @Test
    public void convertsRxCharToObservable() throws Exception {
        char TEST_CHAR = 'R';
        Observable<Character> rxChar = Observable.just(TEST_CHAR);
        ObservableChar observableChar = RxCinder.convertChar(rxChar);
        assertTrue(observableChar.get() == TEST_CHAR);
    }

    @Test
    public void convertsRxDoubleToObservable() throws Exception {
        double TEST_DOUBLE = 234;
        Observable<Double> rxDouble = Observable.just(TEST_DOUBLE);
        ObservableDouble observableDouble = RxCinder.convertDouble(rxDouble);
        assertTrue(observableDouble.get() == TEST_DOUBLE);
    }

    @Test
    public void convertsRxFieldToObservable() throws Exception {
        final String TEST_STRING = "Banana";
        Observable<String> rxString = Observable.just(TEST_STRING);
        ObservableField<String> observableField = RxCinder.convertField(rxString);
        assertTrue(observableField.get().equals(TEST_STRING));
    }

    @Test
    public void convertsRxFloatToObservable() throws Exception {
        float TEST_FLOAT = 234;
        Observable<Float> rxFloat = Observable.just(TEST_FLOAT);
        ObservableFloat observableFloat = RxCinder.convertFloat(rxFloat);
        assertTrue(observableFloat.get() == TEST_FLOAT);
    }

    @Test
    public void convertsRxIntToObservable() throws Exception {
        int TEST_INT = 234;
        Observable<Integer> rxInt = Observable.just(TEST_INT);
        ObservableInt observableInt = RxCinder.convertInt(rxInt);
        assertTrue(observableInt.get() == TEST_INT);
    }

    @Test
    public void convertsRxLongToObservable() throws Exception {
        long TEST_LONG = 234;
        Observable<Long> rxLong = Observable.just(TEST_LONG);
        ObservableLong observableLong = RxCinder.convertLong(rxLong);
        assertTrue(observableLong.get() == TEST_LONG);
    }

    @Test
    public void convertsRxParcelableToObservable() throws Exception {
        final Point TEST_POINT = new Point(12, 13);
        Observable<Point> rxPoint = Observable.just(TEST_POINT);
        ObservableParcelable<Point> observableParcelable = RxCinder.convertParcelable(rxPoint);
        assertTrue(observableParcelable.get().x == TEST_POINT.x);
    }

    @Test
    public void convertsRxShortToObservable() throws Exception {
        short TEST_SHORT = 234;
        Observable<Short> rxShort = Observable.just(TEST_SHORT);
        ObservableShort observableShort = RxCinder.convertShort(rxShort);
        assertTrue(observableShort.get() == TEST_SHORT);
    }
}