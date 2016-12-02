package com.akiraspeirs.cinder;

import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableList;
import android.databinding.ObservableMap;
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * Created by geoffspeirs on 14/09/2016.
 */

public class Cinder {
    
    private static WeakHashMap<Observable.OnPropertyChangedCallback, String> callbackMap = new WeakHashMap<>();
    public static long getCallbackCount(){
        return callbackMap.size();
    }

    public interface OnChangeCallback {
        void onChange();
    }

    public interface OnComputeArrayListCallback<T> {
        void onComputeChange(ObservableArrayList<T> list);
    }

    public interface OnComputeArrayMapCallback<K, V> {
        void onComputeChange(ObservableArrayMap<K, V> map);
    }

    public interface OnComputeBooleanCallback {
        boolean onComputeChange();
    }

    public interface OnComputeByteCallback {
        byte onComputeChange();
    }

    public interface OnComputeCharCallback {
        char onComputeChange();
    }

    public interface OnComputeDoubleCallback {
        double onComputeChange();
    }

    public interface OnComputeFieldCallback<T> {
        T onComputeChange();
    }

    public interface OnComputeFloatCallback {
        float onComputeChange();
    }

    public interface OnComputeIntCallback {
        int onComputeChange();
    }

    public interface OnComputeLongCallback {
        long onComputeChange();
    }

    public interface OnComputeShortCallback {
        short onComputeChange();
    }

    public interface OnComputeParcelableCallback<T> {
        T onComputeChange();
    }

    public static <T> CinderArrayList<T> computeArrayList(OnComputeArrayListCallback<T> computeCallback, BaseObservable... observables) {
        CinderArrayList<T> cinderArrayList = new CinderArrayList<>();
        cinderArrayList.setObservableBehaviour(Cinder.observe(() -> computeCallback.onComputeChange(cinderArrayList), observables));
        return cinderArrayList;
    }

    public static <K, V> CinderArrayMap<K, V> computeArrayMap(OnComputeArrayMapCallback<K, V> computeCallback, BaseObservable... observables) {
        CinderArrayMap<K, V> cinderArrayMap = new CinderArrayMap<>();
        cinderArrayMap.setObservableBehaviour(Cinder.observe(() -> computeCallback.onComputeChange(cinderArrayMap), observables));
        return cinderArrayMap;
    }

    public static CinderBoolean computeBoolean(OnComputeBooleanCallback computeCallback, BaseObservable... observables) {
        CinderBoolean cinderBoolean = new CinderBoolean();
        cinderBoolean.setObservableBehaviour(Cinder.observe(() -> cinderBoolean.set(computeCallback.onComputeChange()), observables));
        return cinderBoolean;
    }

    public static CinderByte computeByte(OnComputeByteCallback computeCallback, BaseObservable... observables) {
        CinderByte cinderByte = new CinderByte();
        cinderByte.setObservableBehaviour(Cinder.observe(() -> cinderByte.set(computeCallback.onComputeChange()), observables));
        return cinderByte;
    }

    public static CinderChar computeChar(OnComputeCharCallback computeCallback, BaseObservable... observables) {
        CinderChar cinderChar = new CinderChar();
        cinderChar.setObservableBehaviour(Cinder.observe(() -> cinderChar.set(computeCallback.onComputeChange()), observables));
        return cinderChar;
    }

    public static CinderDouble computeDouble(OnComputeDoubleCallback computeCallback, BaseObservable... observables) {
        CinderDouble cinderDouble = new CinderDouble();
        cinderDouble.setObservableBehaviour(Cinder.observe(() -> cinderDouble.set(computeCallback.onComputeChange()), observables));
        return cinderDouble;
    }

    public static <T> CinderField<T> computeField(OnComputeFieldCallback<T> computeCallback, BaseObservable... observables) {
        CinderField<T> cinderField = new CinderField<>();
        cinderField.setObservableBehaviour(Cinder.observe(() -> cinderField.set(computeCallback.onComputeChange()), observables));
        return cinderField;
    }

    public static CinderFloat computeFloat(OnComputeFloatCallback computeCallback, BaseObservable... observables) {
        CinderFloat cinderFloat = new CinderFloat();
        cinderFloat.setObservableBehaviour(Cinder.observe(() -> cinderFloat.set(computeCallback.onComputeChange()), observables));
        return cinderFloat;
    }

    public static CinderInt computeInt(OnComputeIntCallback computeCallback, BaseObservable... observables) {
        CinderInt cinderInt = new CinderInt();
        cinderInt.setObservableBehaviour(Cinder.observe(() -> cinderInt.set(computeCallback.onComputeChange()), observables));
        return cinderInt;
    }

    public static CinderLong computeLong(OnComputeLongCallback computeCallback, BaseObservable... observables) {
        CinderLong cinderLong = new CinderLong();
        cinderLong.setObservableBehaviour(Cinder.observe(() -> cinderLong.set(computeCallback.onComputeChange()), observables));
        return cinderLong;
    }

    public static <T extends Parcelable> CinderParcelable<T> computeParcelable(OnComputeParcelableCallback<T> computeCallback, BaseObservable... observables) {
        CinderParcelable<T> cinderParcelable = new CinderParcelable<>();
        cinderParcelable.setObservableBehaviour(Cinder.observe(() -> cinderParcelable.set(computeCallback.onComputeChange()), observables));
        return cinderParcelable;
    }

    public static CinderShort computeShort(OnComputeShortCallback computeCallback, BaseObservable... observables) {
        CinderShort cinderShort = new CinderShort();
        cinderShort.setObservableBehaviour(Cinder.observe(() -> cinderShort.set(computeCallback.onComputeChange()), observables));
        return cinderShort;
    }

    public static CinderComputable observable(ObservableList list){
        CinderComputable computable = new CinderComputable();
        computable.setBehaviour(Cinder.observe(list, null, computable::notifyChange));
        return computable;
    }

    public static CinderComputable observable(ObservableList list, Class c, String... fields){
        CinderComputable computable = new CinderComputable();
        computable.setBehaviour(Cinder.observe(list, c, computable::notifyChange, fields));
        return computable;
    }

    public static CinderComputable observable(ObservableMap map){
        CinderComputable computable = new CinderComputable();
        computable.setBehaviour(Cinder.observe(map, null, computable::notifyChange));
        return computable;
    }

    public static CinderComputable observable(ObservableMap map, Class c, String... fields){
        CinderComputable computable = new CinderComputable();
        computable.setBehaviour(Cinder.observe(map, c, computable::notifyChange, fields));
        return computable;
    }

    public static CinderObservable observe(OnChangeCallback onChangeCallback, BaseObservable... observables) {
        CinderInternalObservable observation = new CinderInternalObservable(onChangeCallback);
        for (BaseObservable observable : observables) {
            Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable observable, int i) {
                    observation.process();
                }
            };
            observable.addOnPropertyChangedCallback(callback);
            observation.pairs.add(new CinderPair(observable, callback));
            callbackMap.put(callback, "");
        }
        return observation;
    }

    public static <T> CinderObservable observe(ObservableList<T> list, Class c, OnChangeCallback onChangeCallback, String... fields){
        CinderListObservable observation = new CinderListObservable(onChangeCallback);
        {
            CinderListPair<T> cinderListPair = new CinderListPair<>(list);
            ObservableList.OnListChangedCallback<ObservableList<T>> callback = new ObservableList.OnListChangedCallback<ObservableList<T>>() {
                @Override
                public void onChanged(ObservableList<T> items) {}

                @Override
                public void onItemRangeChanged(ObservableList<T> list, int startIndex, int itemCount) {}

                @Override
                public void onItemRangeInserted(ObservableList<T> list, int startIndex, int itemCount) {
                    observation.process();
                }

                @Override
                public void onItemRangeMoved(ObservableList<T> list, int i, int i1, int i2) {}

                @Override
                public void onItemRangeRemoved(ObservableList<T> list, int startIndex, int itemCount) {
                    observation.process();
                }
            };
            list.addOnListChangedCallback(callback);
            cinderListPair.callback = callback;
            observation.pairs.add(cinderListPair);
        }

        for (String field : fields) {
            CinderListPair<T> cinderListPair = new CinderListPair<>(list);
            ArrayList<CinderPair> cinderPairs = new ArrayList<>();
            for (T item : list) {
                observeListFieldForClass(c, field, item, cinderPairs, observation);
            }
            cinderListPair.cinderPairs = cinderPairs;
            ObservableList.OnListChangedCallback<ObservableList<T>> callback = new ObservableList.OnListChangedCallback<ObservableList<T>>() {
                @Override
                public void onChanged(ObservableList<T> items) {}

                @Override
                public void onItemRangeChanged(ObservableList<T> list, int startIndex, int itemCount) {}

                @Override
                public void onItemRangeInserted(ObservableList<T> list, int startIndex, int itemCount) {
                    observeItemRangeInserted(list, startIndex, itemCount, c, field, cinderListPair.cinderPairs, observation);
                }

                @Override
                public void onItemRangeMoved(ObservableList<T> list, int i, int i1, int i2) {}

                @Override
                public void onItemRangeRemoved(ObservableList<T> list, int startIndex, int itemCount) {
                    unobserveItemRangeRemoved(startIndex, itemCount, cinderListPair.cinderPairs);
                }
            };
            list.addOnListChangedCallback(callback);
            cinderListPair.callback = callback;
            observation.pairs.add(cinderListPair);
        }
        return observation;
    }

    public static <K, V> CinderObservable observe(ObservableMap<K, V> map, Class c, OnChangeCallback onChangeCallback, String... fields){
        CinderMapObservable<K, V> observation = new CinderMapObservable<>(onChangeCallback);
        {
            CinderMapPair<K, V> cinderMapPair = new CinderMapPair<>(map);
            ObservableMap.OnMapChangedCallback<ObservableMap<K, V>, K, V> callback = new ObservableMap.OnMapChangedCallback<ObservableMap<K, V>, K, V>() {
                @Override
                public void onMapChanged(ObservableMap<K, V> map, K key){
                    observation.process();
                }
            };
            map.addOnMapChangedCallback(callback);
            cinderMapPair.callback = callback;
            observation.defaultPair = cinderMapPair;
        }

        for (String field : fields) {
            CinderMapPair<K, V> cinderMapPair = new CinderMapPair<>(map);
            ArrayMap<K, CinderPair> cinderPairs = new ArrayMap<>();
            for (K key : map.keySet()) {
                observeMapFieldForClass(c, field, key, map.get(key), cinderPairs, observation);
            }
            cinderMapPair.cinderPairs = cinderPairs;
            ObservableMap.OnMapChangedCallback<ObservableMap<K, V>, K, V> callback = new ObservableMap.OnMapChangedCallback<ObservableMap<K, V>, K, V>() {
                @Override
                public void onMapChanged(ObservableMap<K, V> map, K key){
                    handleMapChange(map, key, c, field, cinderMapPair.cinderPairs, observation);
                }
            };
            map.addOnMapChangedCallback(callback);
            cinderMapPair.callback = callback;
            observation.pairs.add(cinderMapPair);
        }
        return observation;
    }

    private static <T> void observeItemRangeInserted(ObservableList<T> list, int startIndex, int itemCount, Class c, String field, ArrayList<CinderPair> cinderPairs, CinderObservable observation) {
        for (int i = 0; i < itemCount; ++i) {
            observeListFieldForClass(c, field, list.get(startIndex + i), cinderPairs, observation);
        }
    }

    private static <T> void unobserveItemRangeRemoved(int startIndex, int itemCount, ArrayList<CinderPair> cinderPairs) {
        for (int i = startIndex + itemCount - 1; i >= startIndex; --i){
            CinderPair pair = cinderPairs.get(i);
            pair.observable.removeOnPropertyChangedCallback(pair.callback);
            cinderPairs.remove(i);
        }
    }

    private static <K, V> void handleMapChange(ObservableMap<K, V> map, K key, Class c, String field, ArrayMap<K, CinderPair> cinderPairs, CinderObservable observation) {
        if (cinderPairs.get(key) == null) {
            observeMapFieldForClass(c, field, key, map.get(key), cinderPairs, observation);
        } else {
            CinderPair cinderPair = cinderPairs.get(key);
            cinderPair.observable.removeOnPropertyChangedCallback(cinderPair.callback);
            cinderPairs.remove(key);
            if (map.get(key) != null){
                observeMapFieldForClass(c, field, key, map.get(key), cinderPairs, observation);
            }
        }
    }

    private static <T> void observeListFieldForClass(Class c, String field, T item, ArrayList<CinderPair> cinderPairs, CinderObservable observation){
        Field observableField;
        try {
            observableField = c.getField(field);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Field specified does not exist on object: " + field);
        }
        if (observableField == null){
            return;
        }

        BaseObservable observable;
        try {
            observable = (BaseObservable) observableField.get(item);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Cannot access field " + field + ". Check class and property access levels.");
        }
        if (observable == null){
            return;
        }

        Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                observation.process();
            }
        };
        observable.addOnPropertyChangedCallback(callback);
        cinderPairs.add(new CinderPair(observable, callback));
    }

    private static <T, K> void observeMapFieldForClass(Class c, String field, K key, T item, ArrayMap<K, CinderPair> cinderPairs, CinderObservable observation){
        Field observableField;
        try {
            observableField = c.getField(field);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Field specified does not exist on object: " + field);
        }
        if (observableField == null){
            return;
        }

        BaseObservable observable;
        try {
            observable = (BaseObservable) observableField.get(item);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Cannot access field " + field + ". is the class and element public?");
        }
        if (observable == null){
            return;
        }

        Observable.OnPropertyChangedCallback callback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                observation.process();
            }
        };
        observable.addOnPropertyChangedCallback(callback);
        cinderPairs.put(key, new CinderPair(observable, callback));
    }
}