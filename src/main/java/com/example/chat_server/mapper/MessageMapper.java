package com.example.chat_server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {
    int insertMessage(@Param("roomId") Long roomId,
                      @Param("memberId") Long memberId,
                      @Param("deptId") Long deptId,
                      @Param("messageDetail") String messageDetail);

    List<Map<String, Object>> selectMessagesByRoomId(@Param("roomId") Long roomId);
}