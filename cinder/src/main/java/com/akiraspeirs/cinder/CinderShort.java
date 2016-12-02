package com.akiraspeirs.cinder;

import android.databinding.ObservableShort;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderShort extends ObservableShort{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderShort once(){
        cinderObservable.once();
        return this;
    }

    public CinderShort take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderShort skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderShort filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderShort takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderShort skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderShort onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderShort withDefault(short defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderShort immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderShort debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
