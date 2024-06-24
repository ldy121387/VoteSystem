package com.example.equipmentmanagent.Manage;

import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.vDetail;
import com.example.equipmentmanagent.utils.R;
import org.springframework.stereotype.Component;

@Component
public interface vDetailManage {
    R add(vDetail vDetail);

    R delete(Integer id);

    R update(vDetail vDetail);

    R getById(Integer id);

    R getVoteByPage(Integer current,Integer page,String voteName,Integer voteId);

    R voteById(Integer voteId);

    R getVoteByBlock(Integer voteId);

    R getVoteDetailByBlock(Integer voteId);
}
