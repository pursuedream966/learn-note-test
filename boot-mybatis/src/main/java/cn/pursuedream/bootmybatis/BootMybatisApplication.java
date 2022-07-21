package cn.pursuedream.bootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.pursuedream.bootmybatis.persistence.mapper")
@SpringBootApplication
public class BootMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootMybatisApplication.class, args);
    }
}