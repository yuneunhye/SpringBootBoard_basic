package com.example.Myapp1.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration  //@Configuration 이 있는 파일들은 프로그램이 시작되면 알아서 객체만들어서 호출된다 //web.xml 대체  설정과 관련있는 파일이다라는 의미의 애노테이션
public class MyBatisConfig {

	//hikari 설정하기   
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
		
		System.out.println("********");
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }
	    
	@Bean   //객체를 만든다
	public SqlSessionFactory makeSqlSessionFactory(
			DataSource  dataSource) throws Exception 
	{
		
		System.out.println("********");
		final SqlSessionFactoryBean factory  
		   = new SqlSessionFactoryBean();
		
		//저객체와 application.properties 파일에 있는  datasource
		//와 연결 
		factory.setDataSource(dataSource);
		//설정파일과 연동하기(mybatis-config.xml)파일과 연동
		PathMatchingResourcePatternResolver resolver 
		  = new PathMatchingResourcePatternResolver();
		//classpath - src/main/resource  : 스프링부트는 배포할때 작성할때 xml파일은 빼고 간다. 모든  xml 파일은 resouce폴더에 두자 
		Resource configLocation = resolver.getResource("classpath:mybatis-config.xml");
		
		factory.setConfigLocation(configLocation);
		return factory.getObject();
		
	}
	
	@Bean 
	public SqlSessionTemplate makeSqlSession(
			SqlSessionFactory factory)
	{
		return new SqlSessionTemplate(factory);
	}
	
	
}










