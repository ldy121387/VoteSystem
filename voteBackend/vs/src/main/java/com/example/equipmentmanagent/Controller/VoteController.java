package com.example.equipmentmanagent.Controller;

import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.Manage.VoteManage;
import com.example.equipmentmanagent.Mapper.VoteMapper;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteMapper voteMapper;
    private final VoteManage voteManage;

    @RequestMapping("/add")
    public R save(@RequestBody Vote vote){
        return voteManage.add(vote);
    }

    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        return voteManage.delete(id);
    }

    @GetMapping("/select/{id}")
    public R search(@PathVariable Integer id){
        return voteManage.getById(id);
    }


    @PostMapping("/update")
    public R update(@RequestBody Vote vote){
        return voteManage.update(vote);
    }

    @GetMapping("/getVoteByPage")
    public R getVoteByPage(@RequestParam Integer current,@RequestParam Integer size,@RequestParam String voteName){
        return voteManage.getVoteByPage(current,size,voteName);
    }

}
