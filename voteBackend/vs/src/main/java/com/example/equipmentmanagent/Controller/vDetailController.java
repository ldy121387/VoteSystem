package com.example.equipmentmanagent.Controller;

import com.example.equipmentmanagent.DTO.Vote;
import com.example.equipmentmanagent.DTO.vDetail;
import com.example.equipmentmanagent.Manage.vDetailManage;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vDetail")
public class vDetailController {

    private final vDetailManage vDetailManage;

    @RequestMapping("/add")
    public R save(@RequestBody vDetail vDetail){
        return vDetailManage.add(vDetail);
    }

    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        return vDetailManage.delete(id);
    }

    @GetMapping("/select/{id}")
    public R search(@PathVariable Integer id){
        return vDetailManage.getById(id);
    }


    @PostMapping("/update")
    public R update(@RequestBody vDetail vDetail){
        return vDetailManage.update(vDetail);
    }

    @GetMapping("/getVoteByPage")
    public R getVoteByPage(@RequestParam Integer current,@RequestParam Integer size,@RequestParam String voteName,@RequestParam Integer voteId){
        return vDetailManage.getVoteByPage(current,size,voteName,voteId);
    }

    @PostMapping("/voteById")
    public R voteById(@RequestParam Integer voteId){
        return vDetailManage.voteById(voteId);
    }

    @GetMapping("/getVoteByBlock")
    public R getVoteByBlock(@RequestParam Integer voteId){
        return vDetailManage.getVoteByBlock(voteId);
    }

    @GetMapping("/getVoteDetailByBlock")
    public R getVoteDetailByBlock(@RequestParam Integer voteId){
        return vDetailManage.getVoteDetailByBlock(voteId);
    }

}
