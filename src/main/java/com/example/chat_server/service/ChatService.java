package com.example.chat_server.service;

import com.example.chat_server.mapper.ChatJoinMapper;
import com.example.chat_server.mapper.ChatRoomMapper;
import com.example.chat_server.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final ChatRoomMapper chatRoomMapper;
    private final ChatJoinMapper chatJoinMapper;
    private final MessageMapper messageMapper;

    public ChatService(ChatRoomMapper chatRoomMapper, ChatJoinMapper chatJoinMapper, MessageMapper messageMapper) {
        this.chatRoomMapper = chatRoomMapper;
        this.chatJoinMapper = chatJoinMapper;
        this.messageMapper = messageMapper;
    }

    @Transactional
    public Long createRoom(Long memberId, Long deptId, Long serverId, String roomName) {
        chatRoomMapper.insertRoom(serverId, roomName);
        Long roomId = chatRoomMapper.selectLastInsertId();
        chatJoinMapper.insertJoin(roomId, memberId, deptId); // 만든 사람 자동 참가
        return roomId;
    }

    @Transactional
    public void joinRoom(Long memberId, Long deptId, Long roomId) {
        int exists = chatJoinMapper.existsJoin(roomId, memberId);
        if (exists > 0) return; // 이미 참가면 그냥 무시
        chatJoinMapper.insertJoin(roomId, memberId, deptId);
    }

    @Transactional
    public void leaveRoom(Long memberId, Long roomId) {
        chatJoinMapper.deleteJoin(roomId, memberId);
    }

    @Transactional
    public void sendMessage(Long memberId, Long deptId, Long roomId, String messageDetail) {
        int exists = chatJoinMapper.existsJoin(roomId, memberId);
        if (exists == 0) throw new IllegalArgumentException("방에 참가한 사람만 메시지 전송 가능");
        messageMapper.insertMessage(roomId, memberId, deptId, messageDetail);
        // room updated_at 갱신하고 싶으면 여기서 update SQL 추가 가능(선택)
    }

    public List<Map<String, Object>> getMessages(Long memberId, Long roomId) {
        int exists = chatJoinMapper.existsJoin(roomId, memberId);
        if (exists == 0) throw new IllegalArgumentException("방에 참가한 사람만 메시지 조회 가능");
        return messageMapper.selectMessagesByRoomId(roomId);
    }
}