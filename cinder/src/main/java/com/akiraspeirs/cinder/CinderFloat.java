package com.akiraspeirs.cinder;

import android.databinding.ObservableFloat;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderFloat extends ObservableFloat{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderFloat once(){
        cinderObservable.once();
        return this;
    }

    public CinderFloat take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderFloat skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderFloat filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderFloat takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderFloat skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderFloat onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderFloat withDefault(float defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderFloat immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderFloat debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
