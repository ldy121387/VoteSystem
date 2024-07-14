package com.example.equipmentmanagent.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.example.equipmentmanagent.DTO.vdetailUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface vDetailUserMapper extends BaseMapper<vdetailUser> {
    @Select("SELECT COUNT(*) AS count FROM vdetailUser WHERE user_id = #{userId} AND vdetail_id = #{vdetailId}")
    Integer getCountForUserAndVDetail(@Param("userId") Integer userId, @Param("vdetailId") Integer vdetailId);


}
