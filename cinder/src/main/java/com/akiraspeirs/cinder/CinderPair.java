package com.akiraspeirs.cinder;

import android.databinding.BaseObservable;
import android.databinding.Observable;

/**
 * Created by geoffspeirs on 14/09/2016.
 */

class CinderPair {
    private Observable.OnPropertyChangedCallback callback;
    private BaseObservable observable;

    CinderPair(BaseObservable observable,
               Observable.OnPropertyChangedCallback callback){
        this.observable = observable;
        this.callback = callback;
    }

    public void setCallback(Observable.OnPropertyChangedCallback callback){
        this.callback = callback;
    }

    public Observable.OnPropertyChangedCallback getCallback(){
        return callback;
    }

    public BaseObservable getObservable(){
        return this.observable;
    }
}
