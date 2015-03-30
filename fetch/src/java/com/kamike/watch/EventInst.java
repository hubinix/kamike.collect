/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kamike.watch;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executors;

/**
 *
 * @author THiNk
 */
public class EventInst {

    private static volatile EventInst eventInst = new EventInst();
    
    private EventBus eventBus;
    private AsyncEventBus asyncEventBus;

    private EventInst() {
        eventBus=new EventBus();
        asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(5));
    }

    public static EventInst getInstance() {

        return eventInst;
    }

    /**
     * @return the eventBus
     */
    public EventBus getEventBus() {
        return eventBus;
    }
     /**
     * @return the eventBus
     */
    public AsyncEventBus getAsyncEventBus() {
        return asyncEventBus;
    }

    
}
