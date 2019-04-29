package org.altervista.breve.maze.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class VisitResult extends ArrayList<Room> {

    private static final long serialVersionUID = -4972028359676740609L;
    private static final String TABLE_FORMAT = "%-5s%-15s%-20s";
    private static final String NONE = "None";
    
    public static final VisitResult EMPTY = new VisitResult();
    
    public void print() {

        System.out.println(String.format(TABLE_FORMAT, "ID", "Room", "Object collected"));
        System.out.println("--------------------------------------");

        this.forEach(room -> {
            final String objects = room.getObjects().isEmpty() ? NONE : room.getObjects()
                .stream()
                .map(RoomObject::getName)
                .collect(Collectors.joining(","));

            System.out.println(String.format(TABLE_FORMAT, room.getId(), room.getName(), objects));
        });
    }    
}
