package org.altervista.breve.maze.collector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.altervista.breve.maze.mapper.RoomMapper;
import org.altervista.breve.maze.mapper.RoomMapperImpl;
import org.altervista.breve.maze.model.Room;
import org.altervista.breve.maze.model.RoomObject;
import org.altervista.breve.maze.model.VisitResult;
import org.altervista.breve.maze.util.RoomObjectsUtils;
import org.junit.jupiter.api.Test;

public class WalkerImplTest {
	
	private final RoomMapper mapper = new RoomMapperImpl();
	
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
		
		final Map<Integer, Room> map = mapper.getRooms(new File(roomsMap))
    			.stream()
    			.collect(Collectors.toMap(Room::getId, Function.identity()));
		
		final int start = 2;
		
		final Set<RoomObject> objects = RoomObjectsUtils.buildObjects("Knife", "Potted Plant");
    	
    	final Walker walker = new WalkerImpl(map);
    	assertNotNull(walker.visit(start, objects));
    	assertEquals(6, walker.visit(start, objects).size());
    	
    	final int[] path = {2, 1, 2, 3, 2, 4};
	}
	
	@Test
	public void visitCase2() {
		final String roomsMap = getClass()
				.getClassLoader()
				.getResource("maze-2.json")
				.getFile();
		
		final Map<Integer, Room> map = mapper.getRooms(new File(roomsMap))
    			.stream()
    			.collect(Collectors.toMap(Room::getId, Function.identity()));
		
		final int start = 4;
		
		final Set<RoomObject> objects = RoomObjectsUtils.buildObjects("Knife", "Potted Plant", "Pillow");
    	
    	final Walker walker = new WalkerImpl(map);
    	assertNotNull(walker.visit(start, objects));
    	assertEquals(11, walker.visit(start, objects).size());
    	
    	final int[] expected = {4, 6, 4, 7, 4, 2, 5, 2, 1, 2, 3};
	}
}