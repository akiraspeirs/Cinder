package com.akiraspeirs.cinder;

import android.databinding.BaseObservable;

/**
 * Created by akiraspeirs on 9/11/2016.
 * Does NOT extended ObservableComputable, we want to treat it like other observables.
 */

public class CinderComputable extends BaseObservable{
    private CinderObservable cinderObservable;

    public void setBehaviour(CinderObservable cinderObservable){
        this.cinderObservable = cinderObservable;
    }
    public CinderObservable getBehaviour(){
        return cinderObservable;
    }

    public void setObservableBehaviour(CinderObservable behaviour){
        this.cinderObservable = behaviour;
    }

    public CinderComputable once(){
        cinderObservable.once();
        return this;
    }

    public CinderComputable take(int take){
        cinderObservable.take(take);
        return this;
    }

    public CinderComputable skip(int skip){
        cinderObservable.skip(skip);
        return this;
    }

    public CinderComputable filter(CinderObservable.Filter filter){
        cinderObservable.filter(filter);
        return this;
    }

    public CinderComputable takeWhile(CinderObservable.Filter filter){
        cinderObservable.takeWhile(filter);
        return this;
    }

    public CinderComputable skipWhile(CinderObservable.Filter filter){
        cinderObservable.skipWhile(filter);
        return this;
    }

    public CinderComputable onUiThread(){
        cinderObservable.onUiThread();
        return this;
    }

    public CinderComputable immediate(){
        this.cinderObservable.immediate();
        return this;
    }

    public CinderComputable debounce(long debounce){
        this.cinderObservable.debounce(debounce);
        return this;
    }

    public void stop(){
        cinderObservable.stop();
    }
}
