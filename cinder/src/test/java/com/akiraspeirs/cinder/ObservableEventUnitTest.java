package com.akiraspeirs.cinder;

import android.databinding.ObservableBoolean;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by akiraspeirs on 1/12/2016.
 */

public class ObservableEventUnitTest {
    @Test
    public void fireNotifiesChange() throws Exception {
        ObservableEvent event = new ObservableEvent();
        ObservableBoolean fired = Cinder.computeBoolean(()->true, event);
        event.fire();

        assertTrue(fired.get());
    }
}
