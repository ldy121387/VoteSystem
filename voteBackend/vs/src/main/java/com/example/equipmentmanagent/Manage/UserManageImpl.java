package com.example.equipmentmanagent.Manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.User;
import com.example.equipmentmanagent.Mapper.UserMapper;
import com.example.equipmentmanagent.Service.UserService;
import com.example.equipmentmanagent.utils.JwtUtil;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class UserManageImpl implements UserManage{

    private final UserMapper userMapper;
    private final UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public R addUser(User user) {
        if (userMapper.insert(user) == 1) {
            return R.ok("新增用户成功");
        }
        return R.failed("新增用户失败");
    }

    @Override
    public R deleteUser(Integer id) {
        if (userMapper.deleteById(id) == 1) {
            return R.ok("删除用户成功");
        }
        return R.failed("删除用户失败");
    }

    @Override
    public R updateUser(User user) {
        if (userMapper.updateById(user) == 1) {
            return R.ok("更新用户成功");
        }
        return R.failed("更新用户失败");
    }

    @Override
    public R getUserById(Integer id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            return R.success(user);
        }
        return R.failed("查找用户失败");
    }

    @Override
    public R login(User user) {
        List<User> userList = userService.login(user);


        if (userList.size()>0) {
            String role = userList.get(0).getRole();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", JwtUtil.createToken());
            jsonObject.put("role", role);
//            Integer expire = Redisconstant.EXPIRE;
//            redisTemplate.opsForValue().set("role", role);
//            redisTemplate.opsForValue().set("token", JwtUtil.createToken());
            return R.success(jsonObject);
        } else {
            return R.failed("用户不存在");
        }
    }

    @Override
    public R getUser(Integer current, Integer size, String userName) {
        IPage<User> userIPage = userService.getUser(current,size,userName);
        if (userIPage.getSize()>0){
            return R.success(userIPage);
        }
        else return R.failed("获取用户失败");
    }

    @Override
    public R getAdmin() {
        List<User> userList = userService.getAdmin();
        if (userList != null) {
            return R.success(userList);
        }
        return R.failed("查找用户失败");
    }

}
