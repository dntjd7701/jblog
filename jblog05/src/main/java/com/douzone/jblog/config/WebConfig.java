package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.jblog.config.web.FileuploadConfig;
import com.douzone.jblog.config.web.MessageConfig;
import com.douzone.jblog.config.web.MvcConfig;
import com.douzone.jblog.config.web.SecurityConfig;
/** spring-servlet.xml에서 대체된 것들. 
 * 
 * @ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception"})
 *  <context:annotation-config />
	<context:component-scan base-package="com.douzone.jblog.controller, com.douzone.jblog.exception" />
 * 
 * 
 * @EnableAspectJAutoProxy
 *	<aop:aspectj-autoproxy/>
 */


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.jblog.controller", "com.douzone.jblog.exception"})
@Import({MvcConfig.class, MessageConfig.class, FileuploadConfig.class, SecurityConfig.class})
public class WebConfig {

}
