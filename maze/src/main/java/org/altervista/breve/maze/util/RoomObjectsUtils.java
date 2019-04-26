package org.altervista.breve.maze.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.altervista.breve.maze.model.RoomObject;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomObjectsUtils {

    public static Set<RoomObject> buildObjects(String[] objects) {
        final Set<RoomObject> set = new HashSet<>();
        
        Arrays.stream(objects).forEach(o -> set.add(new RoomObject(o)));

        return set;
    }
}