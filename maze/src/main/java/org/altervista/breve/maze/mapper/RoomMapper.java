package org.altervista.breve.maze.mapper;

import java.io.IOException;
import java.util.List;

import org.altervista.breve.maze.model.Room;

public interface RoomMapper {
	List<Room> getRooms(String map) throws IOException;
}
