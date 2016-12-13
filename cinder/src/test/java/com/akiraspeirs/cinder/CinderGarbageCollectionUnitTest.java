package com.akiraspeirs.cinder;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by akiraspeirs on 1/12/2016.
 */

public class CinderGarbageCollectionUnitTest {

    private class TestClass {
        public ObservableInt number = new ObservableInt();
        public ObservableBoolean bool = Cinder.computeBoolean(()->true, number);
    }

    private class TestArrayClass{
        ObservableArrayList<TestClass> test = new ObservableArrayList<>();
        ObservableInt testSize = Cinder.computeInt(()->test.size(), Cinder.observable(test, TestClass.class, "number"));
        ObservableArrayMap<String, String> test2 = Cinder.computeArrayMap((map)->{map.put("test", "test");}, Cinder.observable(test));

        public TestArrayClass(){
            for (int i =0; i < 100; ++i){
                TestClass t = new TestClass();
                test.add(t);
                Cinder.observe(()->{}, testSize);
            }
            ObservableArrayMap<String, TestClass> observerd = new ObservableArrayMap<>();
            observerd.put("tes", new TestClass());
            observerd.put("pop", new TestClass());
            CinderComputable test = Cinder.observable(observerd, TestClass.class, "number");
        }
    }

    @Test
    @SuppressWarnings("unused")
    public void callbacksGetCleanedUp() throws Exception {
        for (int i =0; i < 100; ++i){
            //Intentionally unused, just testing garbage collection
            TestArrayClass t = new TestArrayClass();
        }
        System.gc();
        System.runFinalization();
        Thread.sleep(1000);

        assertEquals(Cinder.getCallbackCount(), 0);
    }
}
