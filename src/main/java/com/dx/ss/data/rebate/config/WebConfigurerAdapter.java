package com.dx.ss.data.rebate.config;

import com.dx.ss.data.rebate.interceptor.DefaultInterceptor;
import com.dx.ss.data.rebate.interceptor.exception.WebExceptionInterceptor;
import com.dx.ss.data.rebate.interceptor.session.WebSessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfigurerAdapter extends WebMvcConfigurerAdapter {

	/**
	 * 注册拦截器
	 * @see WebMvcConfigurerAdapter#addInterceptors(InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//WEB端的请求拦截器
		registry.addInterceptor(new WebSessionInterceptor()).addPathPatterns("/web/**/*.web");
		//默认的请求拦截器，只拦截后缀为 .do 的请求
		registry.addInterceptor(new DefaultInterceptor()).addPathPatterns("/**/*.do");
		//Web端全局异常拦截器，尽量往后注册
		registry.addInterceptor(new WebExceptionInterceptor()).addPathPatterns("/web/**");
		super.addInterceptors(registry);
	}
}
