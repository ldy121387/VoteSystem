package com.example.equipmentmanagent.Manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.User;
import com.example.equipmentmanagent.Mapper.UserMapper;
import com.example.equipmentmanagent.Service.UserService;
import com.example.equipmentmanagent.utils.HttpClientUtils;
import com.example.equipmentmanagent.utils.JwtUtil;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class UserManageImpl implements UserManage{

    private final UserMapper userMapper;
    private final UserService userService;
//    private final RedisTemplate<String,String> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    public R addUser(User user) {

        String url = "http://127.0.0.1:5000/enc/keys";
        String privateKeyPEM = null;
        String publicKeyPEM = null;
        try {
            String json = HttpClientUtils.get(url);
            JSONObject jsonObject = JSON.parseObject(json);
            JSONArray detailsArray = jsonObject.getJSONArray("details");
            String result = null;
            if (detailsArray != null && !detailsArray.isEmpty()) {
                // 获取数组中的第一个元素
                result = detailsArray.getString(0);
                System.out.println(result);
            } else {
                System.out.println("details 数组为空或不存在");
            }

            System.out.println("result");
            System.out.println(result);
            privateKeyPEM = extractKey(result, "RSA PRIVATE KEY");
            publicKeyPEM = extractKey(result, "PUBLIC KEY");

            // 打印私钥和公钥
            System.out.println("Private Key: \n" + privateKeyPEM);
            System.out.println("Public Key: \n" + publicKeyPEM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setPk(publicKeyPEM);
        if (userMapper.insert(user) == 1) {
            //注册成功时 需要写入公钥返回私钥
            return R.success(privateKeyPEM);
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
            String nickName = userList.get(0).getNickName();
            String pk = userList.get(0).getPk();
            Integer userId = userList.get(0).getId();
            stringRedisTemplate.opsForValue().set("nickName",nickName);
            stringRedisTemplate.opsForValue().set("pk",pk);
            stringRedisTemplate.opsForValue().set("userId",String.valueOf(userId));
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

    private static String extractKey(String result, String keyType) {
        String beginMarker = "-----BEGIN " + keyType + "-----";
        String endMarker = "-----END " + keyType + "-----";

        int beginIndex = result.indexOf(beginMarker);
        if (beginIndex == -1) {
            return null; // 没有找到起始标记
        }
        int endIndex = result.indexOf(endMarker, beginIndex);
        if (endIndex == -1) {
            return null; // 没有找到结束标记
        }

        // 提取密钥内容，不包括起始和结束标记
        String keyContent = result.substring(beginIndex + beginMarker.length(), endIndex).trim();
        return keyContent.replaceAll("\\s+", ""); // 移除所有空格换行
    }

}
