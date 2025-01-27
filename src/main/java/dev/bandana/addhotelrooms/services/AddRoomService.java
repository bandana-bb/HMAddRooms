package dev.bandana.addhotelrooms.services;

import dev.bandana.addhotelrooms.exceptions.UnAuthorizedAccess;
import dev.bandana.addhotelrooms.exceptions.UserNotFoundException;
import dev.bandana.addhotelrooms.models.Room;

import java.util.List;

public interface AddRoomService {
    List<Room> getRooms(String roomType) throws UnAuthorizedAccess,UserNotFoundException;
    Room addRoom(long userId, String name, String description, double price, String roomType) throws UserNotFoundException, UnAuthorizedAccess, UnAuthorizedAccess;
}
