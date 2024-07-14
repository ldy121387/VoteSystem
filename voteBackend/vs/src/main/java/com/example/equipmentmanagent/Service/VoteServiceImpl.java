package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.voteUser;
import com.example.equipmentmanagent.Manage.VoteManage;
import com.example.equipmentmanagent.Mapper.VoteMapper;
import com.example.equipmentmanagent.Mapper.voteUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VoteServiceImpl implements VoteService{

    private final VoteMapper voteMapper;
    private final voteUserMapper voteUserMapper;

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

    @Override
    public List<Vote> getVoteByTimeAndUser(List<Integer> voteList) {
        Date currentTime = new Date(); // 获取当前时间，假设已经有实现

        // 创建 QueryWrapper 对象
        QueryWrapper<Vote> wrapper = new QueryWrapper<>();
        // 设置时间条件：endTime 大于当前时间
        wrapper.gt("end_time", currentTime);
        // 设置 ID 在给定列表中的条件
        wrapper.in("id", voteList);

        // 执行查询
        List<Vote> filteredVotes = voteMapper.selectList(wrapper);

        return filteredVotes;
    }

    @Override
    public List<voteUser> getHashByVoteId(Integer voteId) {
        QueryWrapper<voteUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vote_id", voteId); // 添加查询条件，vote_id = voteId

        List<voteUser> voteRecords = voteUserMapper.selectList(queryWrapper);

        // 提取 hashValue 到 List<String> 中
//        List<String> hashValues = new ArrayList<>();
//        for (voteUser record : voteRecords) {
//            hashValues.add(record.getHashValue()); // 假设 VoteRecord 类有 getHashValue() 方法
//        }

        return voteRecords;
    }

    @Override
    public IPage<Vote> getVotePageByVoteId(Integer current, Integer size, String voteName, List<Integer> voteIdList) {
        Page<Vote> page =  new Page<>(current , size);

        QueryWrapper<Vote> wrapper = new QueryWrapper<>();
        wrapper.like(!voteName.isEmpty(), "name", voteName);

        if (voteIdList != null && !voteIdList.isEmpty()) {
            wrapper.in("id", voteIdList);
        } else {
            // 如果 voteIdList 为空，直接返回空的分页结果
            return new Page<>();
        }

        return voteMapper.selectPage(page,wrapper);
    }


}
