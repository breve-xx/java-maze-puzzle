package org.altervista.breve.maze.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class Room {
	
	private Integer id;
	private String name;
	private Integer north;
	private Integer south;
	private Integer west;
	private Integer east;
	private List<RoomObject> objects;
}
