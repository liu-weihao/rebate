package com.dx.ss.data.rebate.interceptor.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dx.ss.data.rebate.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public abstract class ExceptionInterceptor extends HandlerInterceptorAdapter {

	private final Logger log = LoggerFactory.getLogger("INTERCEPTOR-EXCEPTION");
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null)	{
			doException(request, response, handler, ex);
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	public void doException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		String url = CommonUtils.getBasePath(request) + request.getServletPath();
		if(request.getMethod().equals("GET") && !StringUtils.isBlank(request.getQueryString())){
			url += "?";
			url += request.getQueryString();
			log.info("URL:  " + url);	//打印请求路径
		}else{	//不是get请求，输出参数
			log.info("URL:  " + url);	//打印请求路径
			Map<String, String[]> parameterMap = request.getParameterMap();
			log.info("params:  " + parameterMap.toString());	//打印请求参数
		}
		log.error(ex.getMessage());	//打印错误堆栈
	}
}
