package com.akiraspeirs.cinder;

import android.databinding.ObservableParcelable;
import android.os.Parcelable;

/**
 * Created by akiraspeirs on 9/11/2016.
 * Does NOT extended ObservableParcelable, we want to treat it like other observables.
 */

public class CinderParcelable<T extends Parcelable> extends ObservableParcelable<T>{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderParcelable<T> once(){
        cinderObservable.once();
        return this;
    }

    public CinderParcelable<T> take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderParcelable<T> skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderParcelable<T> filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderParcelable<T> takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderParcelable<T> skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderParcelable<T> onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderParcelable<T> withDefault(T defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderParcelable<T> immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderParcelable<T> debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
