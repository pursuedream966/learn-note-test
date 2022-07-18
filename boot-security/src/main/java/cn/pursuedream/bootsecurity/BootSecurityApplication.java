package cn.pursuedream.bootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * security调用原理
 * https://juejin.cn/post/7046236598832201758
 *
 * 新版security注意事项
 * https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
 */
@SpringBootApplication
public class BootSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootSecurityApplication.class, args);
    }
}