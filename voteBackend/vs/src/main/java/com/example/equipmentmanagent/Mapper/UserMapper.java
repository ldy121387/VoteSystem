package com.example.equipmentmanagent.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipmentmanagent.DTO.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
