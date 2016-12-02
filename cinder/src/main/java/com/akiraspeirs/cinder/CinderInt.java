package com.akiraspeirs.cinder;

import android.databinding.ObservableInt;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderInt extends ObservableInt{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderInt once(){
        cinderObservable.once();
        return this;
    }

    public CinderInt take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderInt skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderInt filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderInt takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderInt skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderInt onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderInt withDefault(int defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderInt immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderInt debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
