package org.altervista.breve.maze.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class Labyrinth {
	
	private int start;
	private String[] objects;
	private List<Room> rooms;
}
