package org.altervista.breve.maze.mapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.altervista.breve.maze.model.Labyrinth;
import org.altervista.breve.maze.model.Room;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RoomMapperImpl implements RoomMapper {

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public List<Room> getRooms(String map) {
		try {
			final String resource = getClass()
					.getClassLoader()
					.getResource(map)
					.getFile();
			
			return mapper.readValue(new File(resource), Labyrinth.class).getRooms();
		} catch (IOException e) {
			return Collections.emptyList();
		}
	}

}
