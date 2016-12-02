package com.akiraspeirs.cinder;

import android.databinding.BaseObservable;
import android.os.Handler;
import android.os.Looper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by geoffspeirs on 14/09/2016.
 */


public class CinderObservable extends BaseObservable{

    public interface Filter {
        boolean filter();
    }
    public Filter filter;

    boolean onUiThread = false;
    int skip = 0;
    int skipCount = 0;
    int take = Integer.MAX_VALUE;
    int takeCount = 0;
    Filter takeFilter;
    Filter skipFilter;
    long debounce = 0;
    Cinder.OnChangeCallback onChangeCallback;
    Timer timer;
    TimerTask timerTask;

    CinderObservable(Cinder.OnChangeCallback onChangeCallback){this.onChangeCallback = onChangeCallback;}

    public CinderObservable once(){
        this.take(1);
        return this;
    }

    public CinderObservable take(int take){
        this.take = take;
        return this;
    }

    public CinderObservable skip(int skip){
        this.skip = skip;
        return this;
    }

    public CinderObservable filter(Filter filter){
        this.filter = filter;
        return this;
    }

    public CinderObservable takeWhile(Filter filter){
        this.takeFilter = filter;
        return this;
    }

    public CinderObservable skipWhile(Filter filter){
        this.skipFilter = filter;
        return this;
    }

    public CinderObservable onUiThread(){
        this.onUiThread = true;
        return this;
    }

    public CinderObservable immediate(){
        process();
        return this;
    }

    public CinderObservable debounce(long debounce){
        this.debounce = debounce;
        return this;
    }

    public void stop(){}

    public boolean process(){
        if (filter != null && !filter.filter()){
            return false;
        }

        if (skipFilter != null && skipFilter.filter()){
            return false;
        } else {
            skipFilter = null;
        }

        if (takeFilter != null && !takeFilter.filter()){
            stop();
            return false;
        }

        skipCount++;
        if (skipCount <= skip){
            return false;
        }

        takeCount++;
        if (takeCount > take){
            stop();
            return false;
        }

        if (debounce > 0){
            if (timer != null) {
                timer.cancel();
            }
            if (timerTask != null) {
                timerTask.cancel();
            }
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    processCallback();
                }
            };
            timer.schedule(timerTask, debounce);
        }
        else {
            processCallback();
        }

        notifyChange();
        return true;
    }

    private void processCallback(){
        if (onUiThread) {
            new Handler(Looper.getMainLooper()).post(()-> onChangeCallback.onChange());
        } else {
            onChangeCallback.onChange();
        }
    }
}