package com.akiraspeirs.cinder;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableByte;
import android.databinding.ObservableChar;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.databinding.ObservableLong;
import android.databinding.ObservableParcelable;
import android.databinding.ObservableShort;
import android.graphics.Point;

import com.akiraspeirs.cinder.Cinder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CinderUnitTest {

    public class StringClass {
        public ObservableField<String> element;
        StringClass(String element){
            this.element = new ObservableField<>(element);
        }
    }

    @Test
    public void computesArrayList() throws Exception {
        final int NUMBER_1 = 23;
        final int NUMBER_2 = 56;
        final ObservableInt number = new ObservableInt(NUMBER_1);
        final ObservableArrayList<Integer> ints =
                Cinder.<Integer>computeArrayList((list)->list.add(number.get()), number).immediate();
        assertEquals(ints.get(0).longValue(), NUMBER_1);

        number.set(NUMBER_2);
        assertEquals(ints.get(1).longValue(), NUMBER_2);
    }

    @Test
    public void computesArrayMap() throws Exception {
        class TestTuple {
            String key;
            int number;
            TestTuple(String key, int number){
                this.key = key;
                this.number = number;
            }
        }

        final String STRING_1 = "Twenty Three";
        final String STRING_2 = "Fifty Six";
        final int NUMBER_1 = 23;
        final int NUMBER_2 = 56;
        final TestTuple tuple1 = new TestTuple(STRING_1, NUMBER_1);
        final TestTuple tuple2 = new TestTuple(STRING_2, NUMBER_2);

        final ObservableField<TestTuple> tuple = new ObservableField<>(tuple1);
        final ObservableArrayMap<String, Integer> tuples =
                Cinder.<String, Integer>computeArrayMap((map)->map.put(tuple.get().key, tuple.get().number), tuple).immediate();
        assertEquals(tuples.get(tuple1.key).longValue(), tuple1.number);

        tuple.set(tuple2);
        assertEquals(tuples.get(tuple2.key).longValue(), tuple2.number);
    }

    @Test
    public void computesBoolean() throws Exception {
        boolean bool1 = true;
        boolean bool2 = false;
        ObservableBoolean truth = new ObservableBoolean(bool1);
        ObservableBoolean computedTruth = Cinder.computeBoolean(()->truth.get(), truth).immediate();
        assertEquals(computedTruth.get(), bool1);

        truth.set(bool2);
        assertEquals(computedTruth.get(), bool2);
    }

    @Test
    public void computesByte() throws Exception {
        byte byte1 = 34;
        byte byte2 = 9;
        ObservableByte obsByte = new ObservableByte(byte1);
        ObservableByte computedByte = Cinder.computeByte(()->obsByte.get(), obsByte).immediate();
        assertEquals(computedByte.get(), byte1);

        obsByte.set(byte2);
        assertEquals(computedByte.get(), byte2);
    }

    @Test
    public void computesChar() throws Exception {
        char char1 = '\u0909';
        char char2 = '\uf3a1';
        ObservableChar obsChar = new ObservableChar(char1);
        ObservableChar computedChar = Cinder.computeChar(()->obsChar.get(), obsChar).immediate();
        assertEquals(computedChar.get(), char1);

        obsChar.set(char2);
        assertEquals(computedChar.get(), char2);
    }

    @Test
    public void computesDouble() throws Exception {
        double double1 = 0.35;
        double double2 = 234.09;
        ObservableDouble obsDouble = new ObservableDouble(double1);
        ObservableDouble computedDouble = Cinder.computeDouble(()->obsDouble.get(), obsDouble).immediate();
        assertEquals(computedDouble.get(), double1, 0.001);

        obsDouble.set(double2);
        assertEquals(computedDouble.get(), double2, 0.001);
    }

    @Test
    public void computesField() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableField<String> obsString = new ObservableField<>(string1);
        ObservableField<String> computedString = Cinder.computeField(()->obsString.get(), obsString).immediate();
        assertEquals(computedString.get(), string1);

        obsString.set(string2);
        assertEquals(computedString.get(), string2);
    }

    @Test
    public void computesFloat() throws Exception {
        float float1 = 0.4321f;
        float float2 = 1234.4321f;
        ObservableFloat obsFloat = new ObservableFloat(float1);
        ObservableFloat computedFloat = Cinder.computeFloat(()->obsFloat.get(), obsFloat).immediate();
        assertEquals(computedFloat.get(), float1, 0.0001f);

        obsFloat.set(float2);
        assertEquals(computedFloat.get(), float2, 0.0001f);
    }

    @Test
    public void computesInt() throws Exception {
        int int1 = 4321;
        int int2 = 23;
        ObservableInt obsInt = new ObservableInt(int1);
        ObservableInt computedInt = Cinder.computeInt(()->obsInt.get(), obsInt).immediate();
        assertEquals(computedInt.get(), int1);

        obsInt.set(int2);
        assertEquals(computedInt.get(), int2);
    }

    @Test
    public void computesLong() throws Exception {
        long long1 = 432;
        long long2 = 123415;
        ObservableLong obsLong = new ObservableLong(long1);
        ObservableLong computedLong = Cinder.computeLong(()->obsLong.get(), obsLong).immediate();
        assertEquals(computedLong.get(), long1);

        obsLong.set(long2);
        assertEquals(computedLong.get(), long2);
    }

    @Test
    public void computesParcelable() throws Exception {
        Point point1 = new Point(6, 7);
        Point point2 = new Point(76, 90);
        ObservableParcelable<Point> obsPoint = new ObservableParcelable<>(point1);
        ObservableParcelable<Point> computedPoint = Cinder.computeParcelable(()->obsPoint.get(), obsPoint).immediate();
        assertEquals(computedPoint.get().x, point1.x);
        assertEquals(computedPoint.get().y, point1.y);

        obsPoint.set(point2);
        assertEquals(computedPoint.get().x, point2.x);
        assertEquals(computedPoint.get().y, point2.y);
    }

    @Test
    public void computesShort() throws Exception {
        short short1 = 432;
        short short2 = 1215;
        ObservableShort obsShort = new ObservableShort(short1);
        ObservableShort computedShort = Cinder.computeShort(()->obsShort.get(), obsShort).immediate();
        assertEquals(computedShort.get(), short1);

        obsShort.set(short2);
        assertEquals(computedShort.get(), short2);
    }

    @Test
    public void computableListsAreComputable() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        ObservableList<String> strings = new ObservableArrayList<>();
        strings.add(0, string1);
        ObservableField<String> computedString = Cinder.computeField(()->strings.get(0),
                Cinder.observable(strings)).immediate();
        assertEquals(computedString.get(), string1);

        strings.add(0, string2);
        assertEquals(computedString.get(), string2);
    }

    @Test
    public void computableListElementsAreComputable() throws Exception {

        String string1 = "STRING 1";
        String string2 = "STRING 2";
        StringClass stringClass = new StringClass(string1);

        ObservableList<StringClass> strings = new ObservableArrayList<>();
        strings.add(0, stringClass);
        ObservableField<String> computedString = Cinder.computeField(()->strings.get(0).element.get(),
                Cinder.observable(strings, StringClass.class, "element")).immediate();
        assertEquals(computedString.get(), string1);

        strings.get(0).element.set(string2);
        assertEquals(computedString.get(), string2);
    }

    @Test
    public void computableMapsAreComputable() throws Exception {
        String key1 = "KEY 1";
        String key2 = "KEY 2";
        String value1 = "VALUE 1";
        String value2 = "VALUE 2";
        ObservableArrayMap<String, String> strings = new ObservableArrayMap<>();
        strings.put(key1, value1);
        ObservableInt computedCount = Cinder.computeInt(()->strings.size(),
                Cinder.observable(strings)).immediate();
        assertEquals(computedCount.get(), 1);

        strings.put(key2, value2);
        assertEquals(computedCount.get(), 2);
    }

    @Test
    public void computableMapElementsAreComputable() throws Exception {
        String key = "KEY";
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        StringClass stringClass = new StringClass(string1);

        ObservableArrayMap<String, StringClass> strings = new ObservableArrayMap<>();
        strings.put(key, stringClass);
        ObservableField<String> computedString = Cinder.computeField(()->strings.get(key).element.get(),
                Cinder.observable(strings, StringClass.class, "element")).immediate();
        assertEquals(computedString.get(), string1);

        strings.get(key).element.set(string2);
        assertEquals(computedString.get(), string2);
    }

    @Test
    public void observesObservables() throws Exception {
        int int1 = 4321;
        int int2 = 23;
        ObservableInt observedInt = new ObservableInt();
        ObservableInt observingInt = new ObservableInt();

        Cinder.observe(()->observingInt.set(observedInt.get()), observedInt);
        observedInt.set(int1);
        assertEquals(observingInt.get(), int1);

        observedInt.set(int2);
        assertEquals(observingInt.get(), int2);
    }

    @Test
    public void observesObservableLists() throws Exception {
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        String string3 = "STRING 3";
        StringClass stringClass1 = new StringClass(string1);
        StringClass stringClass2 = new StringClass(string2);
        ObservableArrayList<StringClass> observableList = new ObservableArrayList<>();

        ObservableField<String> observingString = new ObservableField<>("");
        Cinder.observe(observableList, StringClass.class,
                ()->observingString.set(observableList.get(0).element.get()), "element");
        observableList.add(0, stringClass1);
        assertEquals(observingString.get(), string1);

        observableList.add(0, stringClass2);
        assertEquals(observingString.get(), string2);

        stringClass2.element.set(string3);
        assertEquals(observingString.get(), string3);

        observableList.remove(0);
        assertEquals(observingString.get(), string1);
    }

    @Test
    public void observesObservableMaps() throws Exception {
        String key1 = "KEY 1";
        String string1 = "STRING 1";
        String string2 = "STRING 2";
        String string3 = "STRING 3";
        String stringMissing = "MISSING";
        StringClass stringClass1 = new StringClass(string1);
        StringClass stringClass2 = new StringClass(string2);
        ObservableArrayMap<String, StringClass> observableMap = new ObservableArrayMap<>();

        ObservableField<String> observingString = new ObservableField<>("");
        Cinder.observe(observableMap, StringClass.class,
                ()->observingString.set((observableMap.get(key1) != null) ?
                        observableMap.get(key1).element.get() : stringMissing), "element");
        observableMap.put(key1, stringClass1);
        assertEquals(observingString.get(), string1);

        observableMap.put(key1, stringClass2);
        assertEquals(observingString.get(), string2);

        stringClass2.element.set(string3);
        assertEquals(observingString.get(), string3);

        observableMap.remove(key1);
        assertEquals(observingString.get(), stringMissing);
    }
}