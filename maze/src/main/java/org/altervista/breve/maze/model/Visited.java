package org.altervista.breve.maze.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Visited {
	
	public Integer from;
	public Integer to;
}
