package com.akiraspeirs.cinderexample;

import android.databinding.ObservableField;

import com.akiraspeirs.cinder_rx.RxCinder;

import io.reactivex.Observable;

/**
 * Created by akiraspeirs on 27/02/2017.
 */

public class CinderField<T> extends ObservableField<T> {
    public final Observable<T> rx = RxCinder.convert(this);

    public void test(){}
}
