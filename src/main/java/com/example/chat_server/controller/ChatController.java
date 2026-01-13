package com.example.chat_server.controller;

import com.example.chat_server.dto.CreateRoomRequest;
import com.example.chat_server.dto.SendMessageRequest;
import com.example.chat_server.service.ChatService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // ⚠️ 여기서 memberId, deptId를 "로그인 정보에서" 꺼내야 함.
    // 지금 프로젝트가 세션 기반이면 authentication.getName()은 보통 username/email 같은 문자열임.
    // 너희는 DB가 member_id(Long), dept_id(Long)라서 "현재 로그인한 사용자의 memberId, deptId"를 얻는 로직이 필요.
    // (이 부분은 A의 로그인 구현(UserDetails)에 따라 1줄로 해결 가능)

    @PostMapping("/rooms")
    public Map<String, Object> createRoom(Authentication authentication,
                                          @RequestBody CreateRoomRequest req) {
        // TODO: 로그인 유저에서 memberId, deptId 꺼내기
        Long memberId = 1L;
        Long deptId = 1L;

        Long roomId = chatService.createRoom(memberId, deptId, req.getServerId(), req.getRoomName());
        return Map.of("roomId", roomId);
    }

    @PostMapping("/rooms/{roomId}/join")
    public Map<String, Object> join(Authentication authentication, @PathVariable Long roomId) {
        Long memberId = 1L;
        Long deptId = 1L;

        chatService.joinRoom(memberId, deptId, roomId);
        return Map.of("ok", true);
    }

    @PostMapping("/rooms/{roomId}/leave")
    public Map<String, Object> leave(Authentication authentication, @PathVariable Long roomId) {
        Long memberId = 1L;

        chatService.leaveRoom(memberId, roomId);
        return Map.of("ok", true);
    }

    @PostMapping("/rooms/{roomId}/messages")
    public Map<String, Object> send(Authentication authentication,
                                    @PathVariable Long roomId,
                                    @RequestBody SendMessageRequest req) {
        Long memberId = 1L;
        Long deptId = 1L;

        chatService.sendMessage(memberId, deptId, roomId, req.getMessageDetail());
        return Map.of("ok", true);
    }

    @GetMapping("/rooms/{roomId}/messages")
    public List<Map<String, Object>> messages(Authentication authentication,
                                              @PathVariable Long roomId) {
        Long memberId = 1L;

        return chatService.getMessages(memberId, roomId);
    }
}