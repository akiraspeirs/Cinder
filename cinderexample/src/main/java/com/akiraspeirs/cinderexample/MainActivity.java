package com.akiraspeirs.cinderexample;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.akiraspeirs.cinderexample.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        FruitStall fruitStall = new FruitStall();
        binding.setFruitStall(fruitStall);
    }
}
