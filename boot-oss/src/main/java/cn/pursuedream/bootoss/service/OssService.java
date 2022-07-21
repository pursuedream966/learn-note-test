package cn.pursuedream.bootoss.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.pursuedream.bootoss.config.OssConfig;
import cn.pursuedream.commonlib.utils.Result;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

@Slf4j
@Service
public class OssService {
    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private OSS oss;

    public Result upload(MultipartFile file) throws IOException {

        String key = RandomUtil.randomString(12) + StrUtil.DOT + FileUtil.getSuffix(file.getOriginalFilename());

        PutObjectResult putObjectResult = oss.putObject(ossConfig.getBucketName(), key, file.getInputStream());

        log.info("putObjectResult => {}", putObjectResult);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);

        URL url = oss.generatePresignedUrl(ossConfig.getBucketName(), key, calendar.getTime());

        return Result.ok(url.toString(), putObjectResult);
    }
}
