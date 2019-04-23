package org.altervista.breve.maze.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class VisitResult extends ArrayList<Room> {

    private static final long serialVersionUID = -4972028359676740609L;
    private static final String TABLE_FORMAT = "%-3s %-13s %-15s\n";
    private static final String NONE = "None";

    public void print() {

        System.out.format(TABLE_FORMAT, "ID", "Room", "Object collected");
        System.out.println(String.format("%s", "----------------------------------"));

        this.forEach(room -> {
            final String objects = room.getObjects().isEmpty() ? NONE : room.getObjects()
                .stream()
                .map(RoomObject::getName)
                .collect(Collectors.joining(","));

            System.out.println(String.format(TABLE_FORMAT, room.getId(), room.getName(), objects));
        });
    }    
}
