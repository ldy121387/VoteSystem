package com.example.equipmentmanagent.Manage;

import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.utils.R;
import org.springframework.stereotype.Component;


@Component
public interface VoteManage {
    R add(Vote vote);

    R delete(Integer id);

    R update(Vote vote);

    R getById(Integer id);

    R getVoteByPage(Integer current,Integer page,String voteName);

    R getVoteByTime();

    R verify(Integer voteId);

    R getHash(Integer voteId);

    R bind(String hashValue);

    R getVoteByPageAdmin(Integer current, Integer size, String voteName);

    R getVoteByTimeAndUser();


//    R getBorrow(Integer current,Integer size,String reason);
}
