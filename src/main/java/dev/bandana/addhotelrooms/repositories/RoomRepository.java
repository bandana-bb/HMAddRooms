package dev.bandana.addhotelrooms.repositories;

import dev.bandana.addhotelrooms.models.Room;
import dev.bandana.addhotelrooms.models.RoomType;

import java.util.List;

public interface RoomRepository {

    Room add(Room room);
    List<Room> getRooms();
    List<Room> getRoomsByRoomType(RoomType roomType);
    Room save(Room room);
}
