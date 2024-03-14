package com.example.equipmentmanagent.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipmentmanagent.DTO.User;
import com.example.equipmentmanagent.DTO.Vote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoteMapper extends BaseMapper<Vote> {
}
