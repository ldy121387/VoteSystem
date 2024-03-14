package com.example.equipmentmanagent.Controller;

import com.example.equipmentmanagent.DTO.User;
import com.example.equipmentmanagent.Manage.UserManage;
import com.example.equipmentmanagent.utils.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserManage userManage;

    @RequestMapping("/add")
    public R save(@RequestBody User user){
        return userManage.addUser(user);
    }

    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        return userManage.deleteUser(id);
    }

    @GetMapping("/select/{id}")
    public R search(@PathVariable Integer id){
        return userManage.getUserById(id);
    }


    @PostMapping("/update")
    public R update(@RequestBody User user){
        return userManage.updateUser(user);
    }

    @PostMapping("/login")
    public R login(@RequestBody User user){
        return userManage.login(user);
    }

    @GetMapping("/getUser")
    public R getDeviceByPage(@RequestParam Integer current,@RequestParam Integer size,@RequestParam String userName){
        return userManage.getUser(current,size,userName);
    }

    @GetMapping("/getAdmin")
    public R getAdmin(){
        return userManage.getAdmin();
    }
}
