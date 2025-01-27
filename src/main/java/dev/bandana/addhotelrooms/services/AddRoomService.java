package dev.bandana.addhotelrooms.services;

import dev.bandana.addhotelrooms.exceptions.UnAuthorizedAccess;
import dev.bandana.addhotelrooms.exceptions.UserNotFoundException;
import dev.bandana.addhotelrooms.models.Room;

public interface AddRoomService {

    Room addRoom(long userId, String name, String description, double price, String roomType) throws UserNotFoundException, UnAuthorizedAccess, UnAuthorizedAccess;
}
