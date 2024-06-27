package com.example.equipmentmanagent.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoteUserService {
    boolean bind(String hashValue,Integer userId);

    List<Integer> getVoteIdByUserId(Integer userId);
}
