package com.akiraspeirs.cinder;

import android.databinding.ObservableArrayMap;

import java.util.Map;

/**
 * Created by akiraspeirs on 9/11/2016.
 * Does NOT extended ObservableArrayMap, we want to treat it like other observables.
 */

public class CinderArrayMap<K, V> extends ObservableArrayMap<K, V>{
    private CinderObservable cinderObservable;

    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderArrayMap<K, V> once(){
        cinderObservable.once();
        return this;
    }

    public CinderArrayMap<K, V> take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderArrayMap<K, V> skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderArrayMap<K, V> filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderArrayMap<K, V> takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderArrayMap<K, V> skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderArrayMap<K, V> onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderArrayMap<K, V> withDefault(Map<K, V> defaultValue){
        this.putAll(defaultValue);
        return this;
    }

    public CinderArrayMap<K, V> immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderArrayMap<K, V> debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
