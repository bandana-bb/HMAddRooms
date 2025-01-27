package dev.bandana.addhotelrooms.services;

import dev.bandana.addhotelrooms.exceptions.UnAuthorizedAccess;
import dev.bandana.addhotelrooms.exceptions.UserNotFoundException;
import dev.bandana.addhotelrooms.models.Room;
import dev.bandana.addhotelrooms.models.RoomType;
import dev.bandana.addhotelrooms.models.User;
import dev.bandana.addhotelrooms.models.UserType;
import dev.bandana.addhotelrooms.repositories.RoomRepository;
import dev.bandana.addhotelrooms.repositories.UserRepository;

import java.util.List;

public class AddRoomServicesImpl implements AddRoomService{

    RoomRepository repository;
    UserRepository userRepository;

    public AddRoomServicesImpl(RoomRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Room> getRooms(String roomType) throws UnAuthorizedAccess, UserNotFoundException {
        if(roomType==null){
            return repository.getRooms();
        }
        if(!roomType.equalsIgnoreCase(RoomType.DELUXE.name()) && !roomType.equalsIgnoreCase(RoomType.SUPER_DELUXE.name()) && !roomType.equalsIgnoreCase(RoomType.SUITE.name())){
            return  null;
        }
        List<Room> roomList = repository.getRoomsByRoomType(RoomType.valueOf(roomType));
        return roomList;
    }

    @Override
    public Room addRoom(long userId, String name, String description, double price, String roomType) throws UserNotFoundException, UnAuthorizedAccess {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        if(!user.getUserType().equals(UserType.ADMIN)){
            throw new UnAuthorizedAccess("You do not have permission to add room");
        }

        Room room=new Room();
        room.setName(name);
        room.setDescription(description);
        room.setPrice(price);
        room.setId(userId);

        if(roomType.equalsIgnoreCase(RoomType.DELUXE.name())){
            room.setRoomType(RoomType.DELUXE);
        }
        else if(roomType.equalsIgnoreCase(RoomType.SUPER_DELUXE.name())){
            room.setRoomType(RoomType.SUPER_DELUXE);
        }
        else if(roomType.equalsIgnoreCase(RoomType.SUITE.name())){
            room.setRoomType(RoomType.SUITE);
        }
          repository.add(room);
        return room;
    }
}
