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
import android.os.Parcelable;
import android.support.v4.util.ArrayMap;

import com.akiraspeirs.cinder.Cinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by akiraspeirs on 8/01/2017.
 */

public class RxCinder {

    public static <T> Observable<ArrayList<T>> convert(ObservableArrayList<T> observableArrayList){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableArrayList), observableArrayList)
        );
    }

    public static <K, V> Observable<ArrayMap<K, V>> convert(ObservableArrayMap<K, V> observableArrayMap){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableArrayMap), observableArrayMap)
        );
    }

    public static Observable<Boolean> convert(ObservableBoolean observableBoolean){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableBoolean.get()), observableBoolean)
        );
    }
    
    public static Observable<Byte> convert(ObservableByte observableByte){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableByte.get()), observableByte)
        );
    }

    public static Observable<Character> convert(ObservableChar observableChar){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableChar.get()), observableChar)
        );
    }

    public static Observable<Double> convert(ObservableDouble observableDouble){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableDouble.get()), observableDouble)
        );
    }

    public static <T> Observable<T> convert(ObservableField<T> observableField){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableField.get()), observableField)
        );
    }

    public static Observable<Float> convert(ObservableFloat observableFloat){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableFloat.get()), observableFloat)
        );
    }

    public static Observable<Integer> convert(ObservableInt observableInt){
        return Observable.create((e)->{
            Cinder.observe(()->e.onNext(observableInt.get()), observableInt);
        });
    }

    public static Observable<Long> convert(ObservableLong observableLong){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableLong.get()), observableLong)
        );
    }

    public static <T extends Parcelable> Observable<T> convert(ObservableParcelable<T> observableParcelable){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableParcelable.get()), observableParcelable)
        );
    }

    public static Observable<Short> convert(ObservableShort observableShort){
        return Observable.create((e)->
            Cinder.observe(()->e.onNext(observableShort.get()), observableShort)
        );
    }

    public static <T> ObservableArrayList<T> convertArrayList(Observable<List<T>> observable){
        ObservableArrayList<T> observableArrayList = new ObservableArrayList<>();
        observable.subscribe((a)->{
            observableArrayList.clear();
            observableArrayList.addAll(a);
        });
        return observableArrayList;
    }

    public static <K, V> ObservableArrayMap<K, V> convertArrayMap(Observable<Map<K, V>> observable){
        ObservableArrayMap<K, V> observableArrayMap = new ObservableArrayMap<>();
        observable.subscribe((Map<K, V> a)->{
            observableArrayMap.clear();
            observableArrayMap.putAll(a);
        });
        return observableArrayMap;
    }

    public static ObservableBoolean convertBoolean(Observable<Boolean> observable){
        ObservableBoolean observableBoolean = new ObservableBoolean();
        observable.subscribe((b)->observableBoolean.set(b));
        return observableBoolean;
    }
    
    public static ObservableByte convertByte(Observable<Byte> observable){
        ObservableByte observableByte = new ObservableByte();
        observable.subscribe((b)->observableByte.set(b));
        return observableByte;
    }

    public static ObservableChar convertChar(Observable<Character> observable){
        ObservableChar observableChar = new ObservableChar();
        observable.subscribe((c)->observableChar.set(c));
        return observableChar;
    }

    public static ObservableDouble convertDouble(Observable<Double> observable){
        ObservableDouble observableDouble = new ObservableDouble();
        observable.subscribe((d)->observableDouble.set(d));
        return observableDouble;
    }

    public static <T> ObservableField<T> convertField(Observable<T> observable){
        ObservableField<T> observableField = new ObservableField<>();
        observable.subscribe((t)->observableField.set(t));
        return observableField;
    }
    
    public static ObservableFloat convertFloat(Observable<Float> observable){
        ObservableFloat observableFloat = new ObservableFloat();
        observable.subscribe((b)->observableFloat.set(b));
        return observableFloat;
    }

    public static ObservableInt convertInt(Observable<Integer> observable){
        ObservableInt observableInt = new ObservableInt();
        observable.subscribe((i)->observableInt.set(i));
        return observableInt;
    }

    public static ObservableLong convertLong(Observable<Long> observable){
        ObservableLong observableLong = new ObservableLong();
        observable.subscribe((l)->observableLong.set(l));
        return observableLong;
    }

    public static <T extends Parcelable> ObservableParcelable<T> convertParcelable(Observable<T> observable){
        ObservableParcelable<T> observableParcelable = new ObservableParcelable<>();
        observable.subscribe((p)->observableParcelable.set(p));
        return observableParcelable;
    }

    public static ObservableShort convertShort(Observable<Short> observable){
        ObservableShort observableShort = new ObservableShort();
        observable.subscribe((l)->observableShort.set(l));
        return observableShort;
    }
}