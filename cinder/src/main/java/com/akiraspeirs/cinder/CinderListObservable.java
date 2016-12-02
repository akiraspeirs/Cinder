package com.akiraspeirs.cinder;

import java.util.ArrayList;

/**
 * Created by geoffspeirs on 14/11/16.
 */

public class CinderListObservable<T> extends CinderObservable {

    ArrayList<CinderListPair<T>> pairs = new ArrayList<>();

    public CinderListObservable(Cinder.OnChangeCallback onChangeCallback){
        super(onChangeCallback);
    }

    public void stop(){
        if (pairs != null) {
            for (CinderListPair<T> pair : pairs) {
                pair.stop();
            }
        }
    }
}
