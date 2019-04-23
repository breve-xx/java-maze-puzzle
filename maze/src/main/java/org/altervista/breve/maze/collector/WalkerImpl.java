package org.altervista.breve.maze.collector;

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
	private final Map<Integer, Room> rooms;
	private final int start;
	private final Set<RoomObject> toFind = new HashSet<>();

	private final Set<Visited> visited = new HashSet<>();

	private final VisitResult path = new VisitResult();

	public WalkerImpl(final Map<Integer, Room> rooms, final int start, final Set<RoomObject> objects) {
		this.rooms = rooms;
		this.start = start;
		this.toFind.addAll(objects);
	}

	public VisitResult visit() {
		visit(start);
		return path;
	}

	private void visit(final int id) {
		final Room room = rooms.get(id);

		if(Objects.isNull(room)) return;

		path.add(room);

		toFind.removeAll(room.getObjects());

		final Integer[] neighborhood = {room.getEast(), room.getWest(), room.getSouth(), room.getNorth()};
		int i = neighborhood.length;

		while(!toFind.isEmpty() && i != 0) {
			visit(id, neighborhood[--i]);
		}
	}

	private void visit(final int form, final Integer to) {
		if (Objects.nonNull(to)) {
			final Visited path = new Visited(form, to);
			if (!visited.contains(path)) {
				visited.add(path);
				visit(to);
			}
		}
	}
}