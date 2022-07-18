package cn.pursuedream.bootsecurity.controller;

import cn.pursuedream.commonlib.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("security")
public class SecurityController {

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("hello")
    public Result<Boolean> hello(){
        log.info("hello 执行");
        return Result.ok();
    }

}
