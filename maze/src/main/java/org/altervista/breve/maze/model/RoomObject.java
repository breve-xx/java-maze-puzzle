package org.altervista.breve.maze.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class RoomObject {
	
	private String name;
}
