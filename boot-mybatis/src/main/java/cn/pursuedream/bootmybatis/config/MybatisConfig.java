package cn.pursuedream.bootmybatis.config;

import org.springframework.context.annotation.Configuration;

/**
 * 1.一级缓存和二级缓存
 * 整合spring失效问题
 * 2.插件原理
 * 四大对象
 * 动态代理
 * 3.与spring整合原理 FactoryBean
 * 通过SqlSessionFactoryBean创建SqlSessionFactory
 * 通过自己的扫描器，注入mapper的BeanDefination并修改接口的实际类为代理对象
 */
@Configuration
public class MybatisConfig {
}
