package org.altervista.breve.maze.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Visited {
	
	public static Visited of(final int from, final Integer to) {
		final Visited visited = new Visited();
		
		visited.setFrom(from);
		visited.setTo(to);
		
		return visited; 
	}
	
	public Integer from;
	public Integer to;
}
