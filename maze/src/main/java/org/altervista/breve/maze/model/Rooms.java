package org.altervista.breve.maze.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Rooms {
	
	@Setter(AccessLevel.NONE)
	private List<Room> rooms;
}
