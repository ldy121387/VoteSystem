package com.example.equipmentmanagent.Service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.vDetail;
import org.springframework.stereotype.Service;

@Service
public interface vDetailService {

    IPage<vDetail> getVoteDetailByPage(Integer page, Integer current, String voteName,Integer voteId);

}
