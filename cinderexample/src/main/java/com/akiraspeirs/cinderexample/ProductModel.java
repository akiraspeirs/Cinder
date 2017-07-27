package com.akiraspeirs.cinderexample;

import android.databinding.ObservableField;
import android.databinding.ObservableLong;

/**
 * Created by akiraspeirs on 26/02/2017.
 */

public class ProductModel {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableLong price = new ObservableLong();
}
