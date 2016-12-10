package com.akiraspeirs.cinder;

import android.databinding.ObservableList;

import java.util.ArrayList;

/**
 * Created by geoffspeirs on 14/09/2016.
 */


class CinderListPair<T>{
    private ObservableList.OnListChangedCallback callback;

    private ObservableList<T> observableList;
    private ArrayList<CinderPair> pairs = new ArrayList<>();

    CinderListPair(ObservableList<T> observableList){
        this.observableList = observableList;
    }

    public void setCallback(ObservableList.OnListChangedCallback callback){
        this.callback = callback;
    }

    public void setPairs(ArrayList<CinderPair> pairs){
        this.pairs = pairs;
    }

    public ArrayList<CinderPair> getPairs(){
        return pairs;
    }

    public void stop(){
        observableList.removeOnListChangedCallback(callback);
        for (CinderPair pair : pairs){
            pair.getObservable().removeOnPropertyChangedCallback(pair.getCallback());
        }
    }
}