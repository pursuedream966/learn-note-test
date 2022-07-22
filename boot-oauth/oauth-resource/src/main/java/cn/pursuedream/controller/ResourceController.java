package cn.pursuedream.controller;


import cn.pursuedream.commonlib.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resource")
public class ResourceController {

    @GetMapping("hello")
    public Result<Boolean> hello(String username){
        return Result.ok(String.format("welcome, %s", username == null ? "anymous": username), true);
    }

    @PreAuthorize("@hasRole('ROLE_ADMIN')")
    @GetMapping("admin")
    public Result<Boolean> admin(){
        return Result.ok("admin", true);
    }

}
