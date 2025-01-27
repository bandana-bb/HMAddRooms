package dev.bandana.addhotelrooms.controllers;

import dev.bandana.addhotelrooms.dtos.AddRoomRequestDto;
import dev.bandana.addhotelrooms.dtos.AddRoomResponseDto;
import dev.bandana.addhotelrooms.dtos.ResponseStatus;
import dev.bandana.addhotelrooms.exceptions.UnAuthorizedAccess;
import dev.bandana.addhotelrooms.exceptions.UserNotFoundException;
import dev.bandana.addhotelrooms.models.Room;
import dev.bandana.addhotelrooms.services.AddRoomService;

public class RoomController {
    AddRoomService addRoomService;
    public RoomController(AddRoomService addRoomService) {
        this.addRoomService = addRoomService;
    }

    public AddRoomResponseDto addRoom(AddRoomRequestDto addRoomRequestDto) {
        Room room = null;
        AddRoomResponseDto addRoomResponseDto = new AddRoomResponseDto();
        try{
            room=addRoomService.addRoom(addRoomRequestDto.getUserId(),addRoomRequestDto.getName(),addRoomRequestDto.getDescription(),addRoomRequestDto.getPrice(),addRoomRequestDto.getRoomType());
        }catch(UnAuthorizedAccess | UserNotFoundException e){
            addRoomResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            addRoomResponseDto.setRoom(room);
            return addRoomResponseDto;
        }
        addRoomResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        addRoomResponseDto.setRoom(room);
        return addRoomResponseDto;
    }
}
