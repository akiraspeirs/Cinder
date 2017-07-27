package com.akiraspeirs.cinderexample;

import android.databinding.ObservableLong;

import com.akiraspeirs.cinder.Cinder;

/**
 * Created by akiraspeirs on 26/02/2017.
 */

public class LineItemModel {
    public final ProductModel product;
    public final ObservableLong quantity;
    public final ObservableLong totalPrice;

    public LineItemModel(ProductModel product){
        this.product = product;
        this.quantity = new ObservableLong();
        this.totalPrice = Cinder.computeLong(()->{
            return product.price.get() * quantity.get();
        }, product.price, quantity);
    }
}