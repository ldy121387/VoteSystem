package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> login(User user);

    IPage<User> getUser(Integer current, Integer size, String userName);

    List<User> getAdmin();
}
