package cn.pursuedream.bootoss.config;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    private String bucketName;


    @Bean
    public OSS oss(){
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
