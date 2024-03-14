package com.example.equipmentmanagent.Manage;

import com.example.equipmentmanagent.DTO.User;
import com.example.equipmentmanagent.utils.R;
import org.springframework.stereotype.Component;

@Component
public interface UserManage {
    R addUser(User user);

    R deleteUser(Integer id);

    R updateUser(User user);

    R getUserById(Integer id);

    R login(User user);

    R getUser(Integer current,Integer size,String userName);

    R getAdmin();
}
