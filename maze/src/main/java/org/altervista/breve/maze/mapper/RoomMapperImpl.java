package org.altervista.breve.maze.mapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.altervista.breve.maze.model.Labyrinth;
import org.altervista.breve.maze.model.Room;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
public class RoomMapperImpl implements RoomMapper {

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public List<Room> getRooms(final File map) {

		try {
			return mapper.readValue(map, Labyrinth.class).getRooms();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return Collections.emptyList();
		}
	}

}
