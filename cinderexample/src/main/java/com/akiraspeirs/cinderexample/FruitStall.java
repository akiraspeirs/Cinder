package com.akiraspeirs.cinderexample;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import com.akiraspeirs.cinder.Cinder;
import com.akiraspeirs.cinder.ObservableEvent;

/**
 * Created by akiraspeirs on 1/12/2016.
 */

public class FruitStall {
    final public ObservableField<String> newFruit = new ObservableField<>("");
    final public ObservableEvent addFruit = new ObservableEvent();

    //A list that adds newFruit each time addFruit fires.
    final public ObservableArrayList<String> fruits = Cinder.<String>computeArrayList((list)->
            list.add(newFruit.get()),
        addFruit).filter(()->newFruit.get().length() > 0);

    //Remake a string of all fruits each time fruits is updated.
    final public ObservableField<String> fruitList = Cinder.<String>computeField(()-> {
            String fruitList = "";
            for (String fruit : fruits) {
                fruitList += fruit + " ";
            }
            return fruitList;
        }, Cinder.observable(fruits));

    //When fruits is updated, clear new fruit.
    final public Observable fruitsObserver = Cinder.observe(()->
        newFruit.set(""), Cinder.observable(fruits));
}