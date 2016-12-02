package com.akiraspeirs.cinder;

import android.databinding.BaseObservable;

/**
 * Created by akiraspeirs on 1/12/2016.
 */

public class ObservableEvent extends BaseObservable {

    public void fire(){
        notifyChange();
    }
}
