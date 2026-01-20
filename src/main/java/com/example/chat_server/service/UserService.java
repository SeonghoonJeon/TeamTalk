package com.example.chat_server.service;

import com.example.chat_server.entity.User;
import com.example.chat_server.mapper.UserMapper;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ memberId, deptId를 같이 저장하도록 변경
    public void register(Long memberId, Long deptId, String username, String rawPassword) {
        if (userMapper.findByUsername(username) != null) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        User u = new User();
        u.setMemberId(memberId);
        u.setDeptId(deptId);
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(rawPassword));
        u.setRole("ROLE_USER");

        userMapper.insert(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userMapper.findByUsername(username);
        if (u == null) throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        return new CustomUserDetails(u);
    }
}