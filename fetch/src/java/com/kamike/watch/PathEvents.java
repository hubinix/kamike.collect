/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kamike.watch;
 
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathEvents {

    private final List<PathEvent> pathEvents = new ArrayList<PathEvent>();
    private final Path watchedDirectory;
    private final boolean isValid;

    PathEvents(boolean valid, Path watchedDirectory) {
        isValid = valid;
        this.watchedDirectory = watchedDirectory;
    }

   
    public boolean isValid(){
        return isValid;
    }

   
    public Path getWatchedDirectory(){
        return watchedDirectory;
    }

   
    public List<PathEvent> getEvents() {
        return Collections.unmodifiableList(pathEvents);
    }

    public void add(PathEvent pathEvent) {
        pathEvents.add(pathEvent);
    }
}
