package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.equipmentmanagent.DTO.User;
import com.example.equipmentmanagent.Manage.UserManage;
import com.example.equipmentmanagent.Mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    @Override
    public List<User> login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account",user.getAccount())
                .eq("password",user.getPassword());
        return userMapper.selectList(wrapper);
    }

    @Override
    public IPage<User> getUser(Integer current, Integer size, String userName) {
        Page<User> page =  new Page<>(current , size);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(!userName.isEmpty(),"nick_name",userName);
        return userMapper.selectPage(page,wrapper);
    }

    @Override
    public List<User> getAdmin() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("role","admin");
        return userMapper.selectList(wrapper);
    }
}
