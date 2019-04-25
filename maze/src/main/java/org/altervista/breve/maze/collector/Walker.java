package org.altervista.breve.maze.collector;

import java.util.Set;

import org.altervista.breve.maze.model.RoomObject;
import org.altervista.breve.maze.model.VisitResult;

public interface Walker {
    VisitResult visit(int start, Set<RoomObject> objects);
}