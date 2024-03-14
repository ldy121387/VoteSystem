package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.Manage.VoteManage;
import com.example.equipmentmanagent.Mapper.VoteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
