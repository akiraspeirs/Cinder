package com.akiraspeirs.cinder;

import java.util.ArrayList;

/**
 * Created by geoffspeirs on 14/11/16.
 */

public class CinderMapObservable<K, V> extends CinderObservable {

    ArrayList<CinderMapPair<K, V>> pairs = new ArrayList<>();
    CinderMapPair defaultPair;

    public CinderMapObservable(Cinder.OnChangeCallback onChangeCallback){
        super(onChangeCallback);
    }

    public void stop(){
        if (pairs != null) {
            for (CinderMapPair pair : pairs) {
                pair.stop();
            }
        }
    }
}
