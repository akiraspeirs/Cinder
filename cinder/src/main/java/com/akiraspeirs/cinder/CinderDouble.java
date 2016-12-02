package com.akiraspeirs.cinder;

import android.databinding.ObservableDouble;

/**
 * Created by akiraspeirs on 9/11/2016.
 */

public class CinderDouble extends ObservableDouble{
    private CinderObservable cinderObservable;

    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderDouble once(){
        cinderObservable.once();
        return this;
    }

    public CinderDouble take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderDouble skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderDouble filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderDouble takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderDouble skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderDouble onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderDouble withDefault(double defaultValue){
        this.set(defaultValue);
        return this;
    }

    public CinderDouble immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderDouble debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
