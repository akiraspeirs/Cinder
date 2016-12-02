package com.akiraspeirs.cinder;

import android.databinding.ObservableMap;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

/**
 * Created by geoffspeirs on 14/09/2016.
 */


class CinderMapPair<K,V>{
    public ObservableMap.OnMapChangedCallback callback;

    ObservableMap<K,V> observableMap;
    ArrayMap<K, CinderPair> cinderPairs = new ArrayMap<>();

    CinderMapPair(ObservableMap<K,V> observableMap){
        this.observableMap = observableMap;
    }

    public void stop(){
        observableMap.removeOnMapChangedCallback(callback);
        for (CinderPair pair : cinderPairs.values()){
            pair.observable.removeOnPropertyChangedCallback(pair.callback);
        }
    }
}