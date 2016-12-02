package com.akiraspeirs.cinder;

import java.util.ArrayList;

/**
 * Created by geoffspeirs on 14/11/16.
 */

public class CinderInternalObservable extends CinderObservable {

    ArrayList<CinderPair> pairs = new ArrayList<>();

    public CinderInternalObservable(Cinder.OnChangeCallback onChangeCallback){
        super(onChangeCallback);
    }

    public void stop(){
        if (pairs != null) {
            for (CinderPair pair : pairs) {
                pair.observable.removeOnPropertyChangedCallback(pair.callback);
            }
        }
    }
}
