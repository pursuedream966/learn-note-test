package cn.pursuedream.bootmybatis.controller;

import cn.pursuedream.bootmybatis.persistence.entity.TUser;
import cn.pursuedream.bootmybatis.service.TUserService;
import cn.pursuedream.commonlib.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tuser")
public class TUserController {

    @Autowired
    private TUserService userService;

    @PostMapping("save/{rollback}")
    public Result save(@PathVariable("rollback")Boolean rollback){

        try {
            userService.save(rollback);
        } catch (Exception e) {
            System.out.println("rollback");
        }

        return Result.ok();

    }

    @GetMapping("getById/{id}")
    public Result<TUser> getById(@PathVariable("id") Long id){
        TUser user = userService.getById(id);

        return Result.ok(null, user);
    }


}
