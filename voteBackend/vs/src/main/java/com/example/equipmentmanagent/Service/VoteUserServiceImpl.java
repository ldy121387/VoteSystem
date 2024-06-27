package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.equipmentmanagent.DTO.User;
import com.example.equipmentmanagent.DTO.voteUser;
import com.example.equipmentmanagent.Mapper.voteUserMapper;
import jnr.ffi.annotations.In;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteUserServiceImpl implements VoteUserService {
    private final voteUserMapper voteUserMapper1;

    @Override
    public boolean bind(String hashValue, Integer userId) {

        QueryWrapper<voteUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hash_value", hashValue); // 设置查询条件：hash_value = hashValue

        voteUser voteUser = voteUserMapper1.selectOne(queryWrapper);

        if (voteUser != null) {
            // 找到记录，更新 userId 字段
            voteUser.setUserId(userId);

            // 更新数据库记录
            int rows = voteUserMapper1.updateById(voteUser);
            return rows > 0; // 更新成功返回 true
        } else {
            // 找不到记录，返回 false 或抛出异常，视业务逻辑而定
            return false;
        }

    }

    @Override
    public List<Integer> getVoteIdByUserId(Integer userId) {
        QueryWrapper<voteUser> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", userId);

        List<voteUser> voteUserList = voteUserMapper1.selectList(queryWrapper);

        // 提取 voteId 列并转换为 List<Integer>
        List<Integer> voteIds = voteUserList.stream()
                .map(voteUser::getVoteId)
                .collect(Collectors.toList());

        return voteIds;
    }


}
