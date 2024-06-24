package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.Manage.VoteManage;
import com.example.equipmentmanagent.Mapper.VoteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService{

    private final VoteMapper voteMapper;

    @Override
    public IPage<Vote> getVoteByPage(Integer current, Integer size, String voteName) {
        Page<Vote> page =  new Page<>(current , size);
        QueryWrapper<Vote> wrapper = new QueryWrapper<>();
        wrapper.like(!voteName.isEmpty(),"name",voteName);
        return voteMapper.selectPage(page,wrapper);
    }

    @Override
    public List<Vote> getVoteByTime() {
        // 获取当前时间
        Date currentTime = new Date(); // 或者使用其他时间处理类来获取当前时间

        // 创建 QueryWrapper 对象
        QueryWrapper<Vote> wrapper = new QueryWrapper<>();

        // 设置查询条件：endTime 大于当前时间
        wrapper.gt("end_time", currentTime);

        List<Vote> votes = new ArrayList<>();
        // 执行查询
        votes = voteMapper.selectList(wrapper);

        return votes;
    }


}
