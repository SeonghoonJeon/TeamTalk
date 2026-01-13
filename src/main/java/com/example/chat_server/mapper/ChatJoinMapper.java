package com.example.chat_server.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatJoinMapper {
    int existsJoin(@Param("roomId") Long roomId, @Param("memberId") Long memberId);

    int insertJoin(@Param("roomId") Long roomId,
                   @Param("memberId") Long memberId,
                   @Param("deptId") Long deptId);

    int deleteJoin(@Param("roomId") Long roomId, @Param("memberId") Long memberId);
}