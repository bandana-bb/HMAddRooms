package dev.bandana.addhotelrooms.repositories;

import dev.bandana.addhotelrooms.models.Room;
import dev.bandana.addhotelrooms.models.RoomType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepositoryImpl implements RoomRepository {

    private Map<Long,List<Room>> userMap=new HashMap<>();
    private Map<RoomType, List<Room>> mapType=new HashMap<>();
    @Override
    public Room add(Room room) {

        List<Room> roomList =null;

        long id=room.getId();

        if(userMap.containsKey(id)){
            roomList=userMap.get(id);
        }
        else{
            roomList=new ArrayList<>();
        }
        roomList.add(room);
        userMap.put(id,roomList);
        return room;
    }

    @Override
    public List<Room> getRooms() {

        List<Room> totalRooms=new ArrayList<>();
        for(List<Room> roomList:userMap.values()){
            totalRooms.addAll(roomList);
        }
        return totalRooms;
    }

    @Override
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return mapType.get(roomType);
    }

    @Override
    public Room save(Room room) {
        RoomType roomType=room.getRoomType();

        List<Room> roomList =null;
        if(mapType.containsKey(roomType)){
            roomList=mapType.get(roomType);
        }
        else{
            roomList=new ArrayList<>();
        }
         roomList.add(room);
        mapType.put(roomType,roomList);
        return room;
    }
}
