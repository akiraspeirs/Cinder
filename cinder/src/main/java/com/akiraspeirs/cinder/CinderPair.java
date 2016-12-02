package com.akiraspeirs.cinder;

import android.databinding.BaseObservable;
import android.databinding.Observable;

/**
 * Created by geoffspeirs on 14/09/2016.
 */

class CinderPair {
    public Observable.OnPropertyChangedCallback callback;

    BaseObservable observable;

    CinderPair(BaseObservable observable,
               Observable.OnPropertyChangedCallback callback){
        this.observable = observable;
        this.callback = callback;
    }
}
