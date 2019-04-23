package org.altervista.breve.maze.collector;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.altervista.breve.maze.model.Room;
import org.altervista.breve.maze.model.RoomObject;
import org.altervista.breve.maze.model.Visited;

public class WalkerImpl implements Walker {
	
	private Map<Integer, Room> rooms;
	private Set<Visited> visited = new HashSet<>();
	
	private int start;
	
	private Set<RoomObject> toFind = new HashSet<>();
	
	public WalkerImpl(final Map<Integer, Room> rooms, final int start, final Set<RoomObject> objects) {
		this.rooms = rooms;
		this.start = start;
		this.toFind.addAll(objects);
	}
	
    public void visit() {
    	visit(start);
    }
    
    private void visit(final int id) {
    	final Room room = rooms.get(id);
    	
    	System.out.println(room.getId() + "\t" + room.getName() + "\t" + room.getObjects());
    	
    	if(!room.getObjects().isEmpty()) {
    		toFind.removeAll(room.getObjects());
    	}
    	
    	System.out.println(toFind);
    	if(toFind.isEmpty()) return;
    	
		visit(id, room.getNorth());
		visit(id, room.getSouth());
		visit(id, room.getWest());
		visit(id, room.getEast());
    }

    private void visit(final int form, final Integer to) {
    	if(Objects.nonNull(to)) {
			final Visited path = new Visited(form, to);
			if(!visited.contains(path)) {
				visited.add(path);
				visit(to);
			}
		}
    }
}