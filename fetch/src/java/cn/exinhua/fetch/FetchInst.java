/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch;

import com.kamike.watch.EventInst;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Brin
 */
public class FetchInst{

    private static volatile FetchInst jwztInst = new FetchInst();
    private final FetchWatch subscriber;
    public boolean running=true;
    
    protected List<Fetcher> fetchers=new CopyOnWriteArrayList<>();

     

    
    private FetchInst() {
        subscriber=new FetchWatch(EventInst.getInstance().getAsyncEventBus());
    }

    public static FetchInst getInstance() {

        return jwztInst;
    }

    /**
     * @return the subscriber
     */
    public FetchWatch getSubscriber() {
        return subscriber;
    }

    /**
     * @return the fetchers
     */
    public List<Fetcher> getFetchers() {
        return fetchers;
    }

    
}

