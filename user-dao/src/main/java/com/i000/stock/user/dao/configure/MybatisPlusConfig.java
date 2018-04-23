package com.i000.stock.user.dao.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 15:48 2018/4/16
 * @Modified By:
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public LogicSqlInjector logicSqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    public GlobalConfiguration globalConfig(LogicSqlInjector logicSqlInjector) {
        GlobalConfiguration configuration = new GlobalConfiguration();
        configuration.setIdType(0);
        configuration.setDbType("mysql");
        configuration.setDbColumnUnderline(true);
        configuration.setSqlInjector(logicSqlInjector);
        configuration.setLogicDeleteValue("0");
        configuration.setLogicNotDeleteValue("0");
//        configuration.setDbColumnUnderline(false);
        return configuration;
    }

    @Bean
    @ConfigurationProperties(prefix = "dao.mybatis.plus.config.sql.session.factory")
    public MybatisSqlSessionFactoryBean sessionFactoryBean(
            @Autowired DataSource dataSource,
            @Autowired GlobalConfiguration configuration) {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setGlobalConfig(configuration);
        factoryBean.setPlugins(createInterceptors());
        return factoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.i000.stock.user.dao.mapper");
        return mapperScannerConfigurer;
    }

    private Interceptor[] createInterceptors() {
        Interceptor[] interceptors = new Interceptor[1];
        interceptors[0] = new PaginationInterceptor();
        return interceptors;
    }
}
