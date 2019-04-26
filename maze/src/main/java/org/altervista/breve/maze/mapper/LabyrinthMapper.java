package org.altervista.breve.maze.mapper;

import java.io.File;

import org.altervista.breve.maze.model.Labyrinth;

public interface LabyrinthMapper {
	Labyrinth fromJson(File map);
}
