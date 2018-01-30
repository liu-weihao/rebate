package com.dx.ss.data.rebate.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;


@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.dx.ss.data.rebate.**.dal.**.mapper");

        Properties properties = new Properties();
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.MySqlMapper,com.dx.ss.data.rebate.mapper.SuperMapper");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
