package com.akiraspeirs.cinder;

import android.databinding.ObservableLong;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderLong extends ObservableLong{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderLong once(){
        cinderObservable.once();
        return this;
    }

    public CinderLong take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderLong skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderLong filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderLong takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderLong skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderLong onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderLong withDefault(long defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderLong immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderLong debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
