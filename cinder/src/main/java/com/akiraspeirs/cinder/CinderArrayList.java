package com.akiraspeirs.cinder;

import android.databinding.ObservableArrayList;

import java.util.Collection;

/**
 * Created by akiraspeirs on 9/11/2016.
 * Does NOT extended ObservableArrayList, we want to treat it like other observables.
 */

public class CinderArrayList<T> extends ObservableArrayList<T>{
    private CinderObservable cinderObservable;

    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderArrayList<T> once(){
        cinderObservable.once();
        return this;
    }

    public CinderArrayList<T> take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderArrayList<T> skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderArrayList<T> filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderArrayList<T> takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderArrayList<T> skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderArrayList<T> onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderArrayList<T> withDefault(Collection<T> defaultValue){
        this.addAll(defaultValue);
        return this;
    }

    public CinderArrayList<T> immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderArrayList<T> debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
