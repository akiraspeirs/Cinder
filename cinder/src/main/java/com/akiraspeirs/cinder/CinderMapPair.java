package com.akiraspeirs.cinder;

import android.databinding.ObservableMap;
import android.support.v4.util.ArrayMap;

/**
 * Created by geoffspeirs on 14/09/2016.
 */


class CinderMapPair<K,V>{
    private ObservableMap.OnMapChangedCallback callback;

    private ObservableMap<K,V> observableMap;
    private ArrayMap<K, CinderPair> pairs = new ArrayMap<>();

    CinderMapPair(ObservableMap<K,V> observableMap){
        this.observableMap = observableMap;
    }

    public void setCallback(ObservableMap.OnMapChangedCallback callback){
        this.callback = callback;
    }

    public void setPairs(ArrayMap<K, CinderPair> pairs){
        this.pairs = pairs;
    }

    public ArrayMap<K, CinderPair> getPairs(){
        return pairs;
    }
    public void stop(){
        observableMap.removeOnMapChangedCallback(callback);
        for (CinderPair pair : pairs.values()){
            pair.getObservable().removeOnPropertyChangedCallback(pair.getCallback());
        }
    }
}