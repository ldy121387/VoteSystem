package com.example.equipmentmanagent.Manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.Mapper.VoteMapper;
import com.example.equipmentmanagent.Service.VoteService;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VoteManageImpl implements VoteManage{

    private final VoteMapper voteMapper;
    private final VoteService voteService;

    @Override
    public R add(Vote vote) {
        if (voteMapper.insert(vote) == 1) {
            return R.ok("新增投票记录成功");
        }
        return R.failed("新增投票记录失败");
    }

    @Override
    public R delete(Integer id) {
        if (voteMapper.deleteById(id) == 1) {
            return R.ok("删除投票记录成功");
        }
        return R.failed("删除投票记录失败");
    }

    @Override
    public R update(Vote vote) {
        if (voteMapper.updateById(vote) == 1) {
            return R.ok("更新投票记录成功");
        }
        return R.failed("更新投票记录失败");
    }

    @Override
    public R getById(Integer id) {
        return null;
    }

    @Override
    public R getVoteByPage(Integer current, Integer page,String voteName) {
        IPage<Vote> voteIPage = voteService.getVoteByPage(current,page,voteName);
        if (voteIPage.getSize()>0){
            return R.success(voteIPage);
        }
        else return R.failed("获取投票失败");
    }
}
