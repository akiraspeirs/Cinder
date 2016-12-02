package com.akiraspeirs.cinder;

import android.databinding.ObservableByte;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderByte extends ObservableByte{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderByte once(){
        cinderObservable.once();
        return this;
    }

    public CinderByte take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderByte skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderByte filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderByte takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderByte skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderByte onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderByte withDefault(byte defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderByte immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderByte debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
