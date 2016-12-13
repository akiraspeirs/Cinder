package com.akiraspeirs.cinder;

import java.util.ArrayList;

/**
 * Created by geoffspeirs on 14/11/16.
 */

public class CinderMapObservable<K, V> extends CinderObservable {

    private ArrayList<CinderMapPair<K, V>> pairs = new ArrayList<>();
    private CinderMapPair defaultPair;

    public CinderMapObservable(Cinder.OnChangeCallback onChangeCallback){
        super(onChangeCallback);
    }

    public void addPair(CinderMapPair<K, V> pair){
        this.pairs.add(pair);
    }

    public void setDefaultPair(CinderMapPair defaultPair){
        this.defaultPair = defaultPair;
    }

    public void stop(){
        defaultPair.stop();
        if (pairs != null) {
            for (CinderMapPair pair : pairs) {
                pair.stop();
            }
        }
    }
}
