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

import lombok.NonNull;

public class WalkerImpl implements Walker {

	// Input
	private final Map<Integer, Room> rooms = new HashMap<>();
	private Set<RoomObject> toFind;

	private final Set<Visited> visited = new HashSet<>();
	private final VisitResult path = new VisitResult();
	private final Set<RoomObject> found = new HashSet<>();

	public WalkerImpl(final Map<Integer, Room> rooms) {
		if(Objects.nonNull(rooms)) this.rooms.putAll(rooms);
	}

	public VisitResult visit(final int start, @NonNull final Set<RoomObject> objects) {
		// Reset visited and result for a clear walk
		visited.clear();
		path.clear();
		found.clear();
		
		// Setting the new list of objets
		toFind = objects;

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