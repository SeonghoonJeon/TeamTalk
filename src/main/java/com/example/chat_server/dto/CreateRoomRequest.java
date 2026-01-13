package com.example.chat_server.dto;

public class CreateRoomRequest {
    private Long serverId;     // chat_room.server_id
    private String roomName;   // chat_room.room_name

    public Long getServerId() { return serverId; }
    public void setServerId(Long serverId) { this.serverId = serverId; }

    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
}