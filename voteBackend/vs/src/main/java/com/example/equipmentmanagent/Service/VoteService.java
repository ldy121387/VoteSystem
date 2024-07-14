package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.voteUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

    IPage<Vote> getVoteByPage(Integer current, Integer size, String voteName);

    List<Vote> getVoteByTime();

    List<Vote> getVoteByTimeAndUser(List<Integer> voteList);

    List<voteUser> getHashByVoteId(Integer voteId);

    IPage<Vote> getVotePageByVoteId(Integer page, Integer current, String voteName,List<Integer> voteIdList);


}
