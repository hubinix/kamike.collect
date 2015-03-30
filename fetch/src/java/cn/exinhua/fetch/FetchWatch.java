/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.exinhua.fetch;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.kamike.watch.PathEvents;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brin
 */
public class FetchWatch  {
     public FetchWatch(AsyncEventBus asyncEventBus) {
        asyncEventBus.register(this);
    }

     //注意这里使用的多线程技术，利用了google的asyncEventBus
    @Subscribe
    @AllowConcurrentEvents
    public void watchEventConcurrent(Fetcher fetcher) {       
         try {
             fetcher.start();
        } catch (Exception ex) {
            Logger.getLogger(FetchWatch.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

             
        }
    }

    @Subscribe
    public void watchEventNonConcurrent(PathEvents events) {

    }
}

