package org.altervista.breve.maze.collector;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.altervista.breve.maze.mapper.LabyrinthMapper;
import org.altervista.breve.maze.mapper.LabyrinthMapperImpl;
import org.altervista.breve.maze.model.Labyrinth;
import org.altervista.breve.maze.model.Room;
import org.altervista.breve.maze.model.RoomObject;
import org.altervista.breve.maze.model.VisitResult;
import org.altervista.breve.maze.util.RoomObjectsUtils;
import org.junit.jupiter.api.Test;

public class WalkerImplTest {

	private final LabyrinthMapper mapper = new LabyrinthMapperImpl();

	@Test
	public void visitNullMap() {
		final Walker walker = new WalkerImpl(null);
		assertEquals(VisitResult.EMPTY, walker.visit(0, Collections.emptySet()));
	}

	@Test
	public void visitNoStart() {
		final Map<Integer, Room> noZeroKey = new HashMap<>();
		noZeroKey.put(1, new Room());

		final Walker walker = new WalkerImpl(noZeroKey);
		assertEquals(VisitResult.EMPTY, walker.visit(0, Collections.emptySet()));
	}

	@Test
	public void visitCase1() {
		final String roomsMap = getClass()
				.getClassLoader()
				.getResource("maze-1.json")
				.getFile();

		final Labyrinth labyrinth = mapper.fromJson(new File(roomsMap));
		final Map<Integer, Room> map = labyrinth.getRooms()
				.stream()
				.collect(Collectors.toMap(Room::getId, Function.identity()));

		final Walker walker = new WalkerImpl(map);
		assertNotNull(walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())));
		assertEquals(6, walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())).size());

		final Integer[] expected = {2, 1, 2, 3, 2, 4};
		final Integer[] actual = walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())).stream()
				.map(Room::getId)
				.collect(Collectors.toList())
				.toArray(new Integer[6]);

		assertArrayEquals(expected, actual);
		
		final Set<RoomObject> objects = walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())).stream()
				.flatMap(r -> r.getObjects().stream())
				.collect(Collectors.toSet());

		assertEquals(RoomObjectsUtils.buildObjects(labyrinth.getObjects()), objects);
	}

	@Test
	public void visitCase2() {
		final String roomsMap = getClass()
				.getClassLoader()
				.getResource("maze-2.json")
				.getFile();

		final Labyrinth labyrinth = mapper.fromJson(new File(roomsMap));
		final Map<Integer, Room> map = labyrinth.getRooms()
				.stream()
				.collect(Collectors.toMap(Room::getId, Function.identity()));

		final Walker walker = new WalkerImpl(map);

		assertNotNull(walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())));
		assertEquals(11, walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())).size());

		final Integer[] expected = {4, 6, 4, 7, 4, 2, 5, 2, 1, 2, 3};
		final Integer[] actual = walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())).stream()
				.map(Room::getId)
				.collect(Collectors.toList())
				.toArray(new Integer[11]);

		assertArrayEquals(expected, actual);

		final Set<RoomObject> objects = walker.visit(labyrinth.getStart(), RoomObjectsUtils.buildObjects(labyrinth.getObjects())).stream()
				.flatMap(r -> r.getObjects().stream())
				.collect(Collectors.toSet());

		assertEquals(RoomObjectsUtils.buildObjects(labyrinth.getObjects()), objects);
	}
}