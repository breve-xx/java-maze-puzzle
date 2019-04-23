package org.altervista.breve.maze.collector;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.altervista.breve.maze.model.Room;
import org.altervista.breve.maze.model.RoomObject;

public class WalkerImpl implements Walker {
	
	private Map<Integer, Room> rooms;
	private Set<Room> visiting = new HashSet<>();
	
	private int start;
	
	private Set<RoomObject> toFind = new HashSet<>();
	private Set<RoomObject> found = new HashSet<>();
	
	public WalkerImpl(final Map<Integer, Room> rooms, final int start, final Set<RoomObject> objects) {
		this.rooms = rooms;
		this.start = start;
		this.toFind.addAll(objects);
	}
	
    public void visit() {
    	visit(start);
    }
    
    private void visit(final Integer id) {
    	if(Objects.isNull(id)) return;
    	
    	final Room room = rooms.get(id);
    	
    	System.out.println(room.getId() + "\t" + room.getName() + "\t" + room.getObjects());
    	
    	if(!room.getObjects().isEmpty()) {
    		found.addAll(room.getObjects());
    	}
    	
    	if(!toFind.equals(found)) {
    		visit(room.getNorth());
    		visit(room.getSouth());
    		visit(room.getEast());
    		visit(room.getWest());
    	}
    }

}