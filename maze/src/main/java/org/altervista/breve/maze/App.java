package org.altervista.breve.maze;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.altervista.breve.maze.collector.Walker;
import org.altervista.breve.maze.collector.WalkerImpl;
import org.altervista.breve.maze.mapper.RoomMapper;
import org.altervista.breve.maze.mapper.RoomMapperImpl;
import org.altervista.breve.maze.model.Room;
import org.altervista.breve.maze.model.RoomObject;
import org.altervista.breve.maze.model.VisitResult;
import org.altervista.breve.maze.util.RoomObjectsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

	public static void main(String[] args) throws IOException {
		log.info("Starting java-maze-puzzle");
		final RoomMapper mapper = new RoomMapperImpl();
		final Map<Integer, Room> map = mapper.getRooms(new File("/mnt/maze-2.json"))
			.stream()
			.collect(Collectors.toMap(Room::getId, Function.identity()));

		final int start = 4;
		final Set<RoomObject> objects = RoomObjectsUtils.buildObjects("Knife", "Potted Plant", "Pillow");

		final Walker walker = new WalkerImpl(map);
		final VisitResult path = walker.visit(start, objects);

		path.print();
	}
}
