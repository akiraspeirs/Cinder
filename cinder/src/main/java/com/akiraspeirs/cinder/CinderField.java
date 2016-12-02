package com.akiraspeirs.cinder;

import android.databinding.ObservableField;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderField<T> extends ObservableField<T>{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderField<T> once(){
        cinderObservable.once();
        return this;
    }

    public CinderField<T> take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderField<T> skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderField<T> filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderField<T> takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderField<T> skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderField<T> onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderField<T> withDefault(T defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderField<T> immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderField<T> debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
