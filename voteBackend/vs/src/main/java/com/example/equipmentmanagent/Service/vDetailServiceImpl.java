package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.vDetail;
import com.example.equipmentmanagent.Mapper.vDetailMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class vDetailServiceImpl implements vDetailService{

    private final vDetailMapper vDetailMapper;

    @Override
    public IPage<vDetail> getVoteDetailByPage(Integer current, Integer size, String voteName,Integer voteId) {
        Page<vDetail> page =  new Page<>(current , size);
        QueryWrapper<vDetail> wrapper = new QueryWrapper<>();
        wrapper.like(!voteName.isEmpty(),"name",voteName);
        wrapper.eq("vote_id",voteId);
        return vDetailMapper.selectPage(page,wrapper);
    }

}
