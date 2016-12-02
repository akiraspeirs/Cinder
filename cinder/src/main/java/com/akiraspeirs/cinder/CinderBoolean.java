package com.akiraspeirs.cinder;

import android.databinding.ObservableBoolean;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderBoolean extends ObservableBoolean{
    private CinderObservable cinderObservable;


    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderBoolean once(){
        cinderObservable.once();
        return this;
    }

    public CinderBoolean take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderBoolean skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderBoolean filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderBoolean takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderBoolean skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderBoolean onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderBoolean withDefault(boolean defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderBoolean immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderBoolean debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
