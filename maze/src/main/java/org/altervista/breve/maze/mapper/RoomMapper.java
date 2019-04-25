package org.altervista.breve.maze.mapper;

import java.io.File;
import java.util.List;

import org.altervista.breve.maze.model.Room;

public interface RoomMapper {
	List<Room> getRooms(File map);
}
