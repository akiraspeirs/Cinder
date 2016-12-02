package com.akiraspeirs.cinder;

import android.databinding.ObservableList;

import java.util.ArrayList;

/**
 * Created by geoffspeirs on 14/09/2016.
 */


class CinderListPair<T>{
    public ObservableList.OnListChangedCallback callback;

    ObservableList<T> observableList;
    ArrayList<CinderPair> cinderPairs = new ArrayList<>();

    CinderListPair(ObservableList<T> observableList){
        this.observableList = observableList;
    }

    public void stop(){
        observableList.removeOnListChangedCallback(callback);
        for (CinderPair pair : cinderPairs){
            pair.observable.removeOnPropertyChangedCallback(pair.callback);
        }
    }
}