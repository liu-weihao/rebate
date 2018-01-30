package com.dx.ss.data.rebate.interceptor.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.vo.ResponseObj;

public class AppExceptionInterceptor extends ExceptionInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null)	{
			super.afterCompletion(request, response, handler, ex);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.write(ResponseObj.fail(StatusCode.SYS_ERROR).toJSON());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
