package com.example.equipmentmanagent.Mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipmentmanagent.DTO.vDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface vDetailMapper extends BaseMapper<vDetail> {

    @Select("SELECT MAX(number) FROM vDetail")
    Integer getMaxColumnValue(Integer id);

}
