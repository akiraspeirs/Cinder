package com.akiraspeirs.cinderexample;

import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableLong;

import com.akiraspeirs.cinder.Cinder;

/**
 * Created by akiraspeirs on 26/02/2017.
 */

public class ReceiptModel {

    public final ObservableArrayList<LineItemModel> lineItems = new ObservableArrayList<>();

    public final ObservableLong totalPrice = Cinder.computeLong(()->{
        long totalPrice = 0;
        for (LineItemModel lineItem : lineItems){
            totalPrice += lineItem.totalPrice.get();
        }
        return totalPrice;
    }, Cinder.observable(lineItems, LineItemModel.class, "totalPrice"));
}