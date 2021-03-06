package org.altervista.breve.maze.mapper;

import java.io.File;
import java.io.IOException;

import org.altervista.breve.maze.model.Labyrinth;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LabyrinthMapperImpl implements LabyrinthMapper {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Labyrinth fromJson(final File map) {
		log.info("fromJson: {}", map);

		try {
			return mapper.readValue(map, Labyrinth.class);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return new Labyrinth();
		}
		
	}
}
