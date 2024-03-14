package com.example.equipmentmanagent.Manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.vDetail;
import com.example.equipmentmanagent.Mapper.vDetailMapper;
import com.example.equipmentmanagent.Service.vDetailService;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class vDetailManageImpl implements vDetailManage{

    private final vDetailService vDetailService;
    private final vDetailMapper vDetailMapper;

    @Override
    public R add(vDetail vDetail) {
        if (vDetailMapper.insert(vDetail) == 1) {
            return R.ok("新增投票记录成功");
        }
        return R.failed("新增投票记录失败");
    }

    @Override
    public R delete(Integer id) {
        if (vDetailMapper.deleteById(id) == 1) {
            return R.ok("删除投票记录成功");
        }
        return R.failed("删除投票记录失败");
    }

    @Override
    public R update(vDetail vDetail) {
        if (vDetailMapper.updateById(vDetail) == 1) {
            return R.ok("更新投票记录成功");
        }
        return R.failed("更新投票记录失败");
    }

    @Override
    public R getById(Integer id) {
        return null;
    }

    @Override
    public R getVoteByPage(Integer current, Integer page,String voteName,Integer voteId) {
        IPage<vDetail> voteIPage = vDetailService.getVoteDetailByPage(current,page,voteName,voteId);
        if (voteIPage.getSize()>0){
            return R.success(voteIPage);
        }
        else return R.failed("获取投票失败");
    }

    @Override
    public R voteById(Integer voteId) {
        vDetail vDetailOne = vDetailMapper.selectById(voteId);
        int number = vDetailOne.getNumber();
        number++;
        vDetailOne.setNumber(number);

        if (vDetailMapper.updateById(vDetailOne) == 1) {
            return R.ok("投票成功");
        }
        return R.failed("更新失败");

    }
}
