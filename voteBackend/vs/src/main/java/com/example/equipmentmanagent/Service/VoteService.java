package com.example.equipmentmanagent.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteService {

    IPage<Vote> getVoteByPage(Integer page, Integer current, String voteName);

    List<Vote> getVoteByTime();

}
