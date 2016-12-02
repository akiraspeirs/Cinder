package com.akiraspeirs.cinder;

import android.databinding.ObservableChar;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderChar extends ObservableChar{
    private CinderObservable cinderObservable;

    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderChar once(){
        cinderObservable.once();
        return this;
    }

    public CinderChar take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderChar skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderChar filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderChar takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderChar skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderChar onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderChar withDefault(char defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderChar immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderChar debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
