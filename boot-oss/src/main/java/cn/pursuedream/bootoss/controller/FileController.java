package cn.pursuedream.bootoss.controller;

import cn.pursuedream.bootoss.service.OssService;
import cn.pursuedream.commonlib.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private OssService ossService;

    @PostMapping("upload")
    public Result upload(@RequestPart("file")MultipartFile file) throws IOException {
        return ossService.upload(file);
    }

}
