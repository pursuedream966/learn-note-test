package cn.pursuedream.bootmvc.controller;

import cn.pursuedream.commonlib.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("hi")
    public Result hi(String name){
        String processName = Optional.ofNullable(name).orElse("none name");
        return Result.ok("ok", processName);
    }
}
