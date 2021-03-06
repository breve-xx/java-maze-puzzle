package org.altervista.breve.maze;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.altervista.breve.maze.collector.Walker;
import org.altervista.breve.maze.collector.WalkerImpl;
import org.altervista.breve.maze.mapper.LabyrinthMapper;
import org.altervista.breve.maze.mapper.LabyrinthMapperImpl;
import org.altervista.breve.maze.model.Labyrinth;
import org.altervista.breve.maze.model.Room;
import org.altervista.breve.maze.model.RoomObject;
import org.altervista.breve.maze.model.VisitResult;
import org.altervista.breve.maze.util.RoomObjectsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

	public static void main(String[] args) throws IOException {
		log.info("Starting java-maze-puzzle");

		final LabyrinthMapper mapper = new LabyrinthMapperImpl();

		final File folder = searchPath();
		final List<File> files = Arrays.stream(folder.listFiles())
			.filter(f -> f.getName().contains(".json"))
			.collect(Collectors.toList());

		if(files.isEmpty()) {
			log.warn("No valid JSON found in {}", folder);
		} else {
			files.forEach(f -> {
				final Labyrinth labyrinth = mapper.fromJson(f);
				
				// Get the rooms from the JSON, if not present uses an empty labyrinth.
				final Map<Integer, Room> map = Objects.isNull(labyrinth.getRooms()) ? Collections.emptyMap() : labyrinth.getRooms()
					.stream()
					.collect(Collectors.toMap(Room::getId, Function.identity()));
				
				// Get the start from the JSON, if not present starts any room.
				final int start = Objects.nonNull(labyrinth.getStart()) ? labyrinth.getStart() : labyrinth.getRooms()
						.stream()
						.map(Room::getId)
						.findAny()
						.orElse(0);
				
				// Get the object list from the JSON, if not present collects all the object in the labyrinth.
				final Set<RoomObject> objects = Objects.nonNull(labyrinth.getObjects()) ? RoomObjectsUtils.buildObjects(labyrinth.getObjects()) : labyrinth.getRooms()
						.stream()
						.flatMap(r -> r.getObjects().stream())
						.collect(Collectors.toSet());

				final Walker walker = new WalkerImpl(map);
				final VisitResult path = walker.visit(start, objects);

				path.print();
			});
		}
	}

	private static File searchPath() {

		final Predicate<File> isGood = f -> f.exists() && f.isDirectory();
		final String[] options = { "/mazes", "examples", "maze/examples"};

		return Arrays.stream(options)
			.map(File::new)
			.filter(isGood)
			.findFirst()
			.orElseThrow(() -> new RuntimeException("No valid path found."));
	}
}
