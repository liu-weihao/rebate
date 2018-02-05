package com.dx.ss.data.rebate.interceptor.session;

import com.dx.ss.data.rebate.constants.ViewConstants;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.utils.CommonUtils;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class WebSessionInterceptor extends SessionInterceptor {

	private final Logger sessionLog = LoggerFactory.getLogger("INTERCEPTOR-SESSION");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object obj = request.getSession().getAttribute(ViewConstants.LOGIN_TICKET_USER);
		if(obj == null){	//未登录
			notLogin(request, response, handler);
			return false;
		}
		return true;
	}

	@Override
	public void notLogin(HttpServletRequest request, HttpServletResponse response, Object handler) {
		sessionLog.warn("User not login. Reqeust information below: \n");
		sessionLog.warn("ip: " + CommonUtils.getIpAddress(request));//打印请求的ip地址
		String url = CommonUtils.getBasePath(request) + request.getServletPath();
		if(request.getMethod().equals("GET") && !StringUtils.isBlank(request.getQueryString())){
			url += "?";
			url += request.getQueryString();
			sessionLog.warn("URL:  " + url);	//打印请求路径
		}else{	//不是get请求，输出参数
			sessionLog.warn("URL:  " + url);	//打印请求路径
			Map<String, String[]> parameterMap = request.getParameterMap();
			sessionLog.warn("params:  " + parameterMap.toString());	//打印请求参数
		}
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
        	writer = response.getWriter();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			writer.write(ResponseObj.fail(StatusCode.LOGIN_EXPIRE, "登录已过期，请您重新登录").toJSON());
        } catch (Exception e) {
            sessionLog.error("跳转失败", e);
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                }
            } catch (Exception e) {
                sessionLog.error("json关闭异常", e);
            }
		}
	}

	/** 
	* 登录过期
	* @author liu.weihao
	* @date 2016年12月10日 
	* @param request
	* @param response
	* @param handler 
	* @see com.dx.ss.data.rebate.interceptor.session.SessionInterceptor#loginExpire(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	*/
	@Override
	public void loginExpire(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// TODO Auto-generated method stub

	}

	/** 
	* 未授权
	* @author liu.weihao
	* @date 2016年12月10日 
	* @param request
	* @param response
	* @param handler 
	* @see com.dx.ss.data.rebate.interceptor.session.SessionInterceptor#notAuthorized(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	*/
	@Override
	public void notAuthorized(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// TODO Auto-generated method stub

	}

}
