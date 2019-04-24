package org.altervista.breve.maze.collector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.altervista.breve.maze.model.Room;
import org.altervista.breve.maze.model.RoomObject;
import org.altervista.breve.maze.model.VisitResult;
import org.altervista.breve.maze.model.Visited;

public class WalkerImpl implements Walker {

	// Input
	private final Map<Integer, Room> rooms = new HashMap<>();
	private final int start;
	private final Set<RoomObject> toFind = new HashSet<>();

	private final Set<Visited> visited = new HashSet<>();
	private final VisitResult path = new VisitResult();
	private final Set<RoomObject> found = new HashSet<>();

	public WalkerImpl(final Map<Integer, Room> rooms, final int start, final Set<RoomObject> objects) {
		if(Objects.nonNull(rooms)) this.rooms.putAll(rooms);
		this.start = start;
		if(Objects.nonNull(objects)) this.toFind.addAll(objects);
	}

	public VisitResult visit() {
		// Reset visited and result for a clear walk
		visited.clear();
		path.clear();
		found.clear();
		
		// I walk only if I really need it
		if(!rooms.isEmpty() && rooms.keySet().contains(start)) {
			visit(start);
		}
		
		return path;
	}

	private void visit(final int id) {
		// Data is not consistent
		if(!rooms.keySet().contains(id)) return;
		
		final Room room = rooms.get(id);

		// There's something wrong with the map
		if(Objects.isNull(room)) return;

		path.add(room);
		found.addAll(room.getObjects());

		final Integer[] neighborhood = {room.getEast(), room.getWest(), room.getSouth(), room.getNorth()};
		int i = neighborhood.length;

		while(!toFind.equals(found) && i != 0) {
			visit(id, neighborhood[--i]);
		}
	}
	
	private void visit(final int from, final Integer to) {
		if (Objects.nonNull(to)) {
			final Visited path = Visited.of(from, to);
			if (!visited.contains(path)) {
				visited.add(path);
				visit(to);
			}
		}
	}
}