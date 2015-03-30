/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kamike.watch;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 *
 * @author THiNk
 */
 

public class PathEvent {
    private final Path eventTarget;
    private final WatchEvent.Kind type;

    PathEvent(Path eventTarget, WatchEvent.Kind type) {
        this.eventTarget = eventTarget;
        this.type = type;
    }

    public Path getEventTarget() {
        return eventTarget;
    }

    public WatchEvent.Kind getType() {
        return type;
    }
}