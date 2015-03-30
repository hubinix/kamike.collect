/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kamike.watch;

import com.google.common.eventbus.EventBus;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THiNk
 */
public class DeleteWatcher {
    private FutureTask<Integer> watchTask;
    private EventBus eventBus;
    private WatchService watchService;
    private volatile boolean keepWatching = true;
    private Path startPath;
      


    public DeleteWatcher(EventBus eventBus, Path startPath) {
        this.eventBus = Objects.requireNonNull(eventBus);
        this.startPath = Objects.requireNonNull(startPath);
    }
    

 
    public void start() throws IOException {
        initWatchService();
        registerDirectories();
        createWatchTask();
        startWatching();
    }

   
    public boolean isRunning() {
        return watchTask != null && !watchTask.isDone();
    }
 
    public void stop() {
        keepWatching = false;
    }

    public void close()
    {
        try {
            this.stop();
            this.watchService.close();
        } catch (IOException ex) {
            Logger.getLogger(DeleteWatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   //Used for testing purposes
   Integer getEventCount() {
        try {
            return watchTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException ex) {
             throw new RuntimeException(ex);
        }
    }


    private void createWatchTask() {
        watchTask = new FutureTask<Integer>(new Callable<Integer>() {
            private int totalEventCount;

            
            @Override
            public Integer call() throws Exception {
                      
                while (keepWatching) {
                    WatchKey watchKey = watchService.poll(10, TimeUnit.SECONDS);
                    if (watchKey != null) {
                        List<WatchEvent<?>> events = watchKey.pollEvents();
                        Path watched = (Path) watchKey.watchable();
//                        PathEvents pathEvents = new PathEvents(watchKey.isValid(), watched);
//                        for (WatchEvent event : events) {
//                            pathEvents.add(new PathEvent((Path) event.context(), event.kind()));
//                            totalEventCount++;
//                        }
//                        watchKey.reset();
//                      
                        
                        for(WatchEvent event : events)
                        {
                           PathEvent pathEvent= new PathEvent((Path) event.context(), event.kind());
                           eventBus.post(pathEvent);
                        }
                        watchKey.reset();
                       
                    }
                }
                return totalEventCount;
            }
        });
    }

    private void startWatching() {
        new Thread(watchTask).start();
    }

    private void registerDirectories() throws IOException {
        Files.walkFileTree(startPath, new WatchServiceRegisteringVisitor());
    }

    private WatchService initWatchService() throws IOException {
        if (watchService == null) {
            watchService = FileSystems.getDefault().newWatchService();
        }
        return watchService;
    }

   
    private class WatchServiceRegisteringVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            //dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            dir.register(watchService, ENTRY_DELETE);
            return FileVisitResult.CONTINUE;
        }
    }
}
