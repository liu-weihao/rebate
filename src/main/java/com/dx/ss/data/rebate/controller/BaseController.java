package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.bo.SessionUser;
import com.dx.ss.data.rebate.constants.ViewConstants;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.vo.ResponseObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * Description:
 * 公共Controller，所有Controller继承于此
 *
 * @Author Eric Lau
 * @Date 2017/8/28.
 */
@Slf4j
public class BaseController {

    /**
     * <p class="detail">
     * 功能：获取登录用户id
     * </p>
     * @author weihao.liu
     * @date 2016年12月26日
     * @param request
     * @return
     */
    public static String userId(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getUserId();
        }
        return StringUtils.EMPTY;
    }

    /**
     * <p class="detail">
     * 功能：获取username
     * </p>
     * @author weihao.liu
     * @date 2016年12月26日
     * @param request
     * @return
     */
    public static String username(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getUsername();
        }
        return StringUtils.EMPTY;
    }

    /**
     * <p class="detail">
     * 功能：获取用户上次登录时间
     * </p>
     * @author weihao.liu
     * @date 2016年12月26日
     * @param request
     * @return
     */
    public static Date loginTime(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getLoginTime();
        }
        return null;
    }

    /**
     * <p class="detail">
     * 功能：获取登录用户类型。
     * </p>
     * @param request
     * @return
     */
    public static Integer userType(HttpServletRequest request){
        SessionUser sessionUser = getSessionUser(request);
        if(sessionUser != null){
            return sessionUser.getType();
        }
        return null;
    }

    public static void destory(HttpServletRequest request){
        request.getSession().setAttribute(ViewConstants.LOGIN_TICKET_USER, null);
    }

    private static SessionUser getSessionUser(HttpServletRequest request){
        Object obj = request.getSession().getAttribute(ViewConstants.LOGIN_TICKET_USER);
        if(obj != null){	//已登录
            return (SessionUser) obj;
        }
        return null;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseObj exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        log.error("error: " + request.getRequestURI());
        log.error("捕获到异常：", e);
        return ResponseObj.fail(StatusCode.SYS_ERROR, StatusCode.SYS_ERROR.getDetail(), new HashMap<String, Object>(2){{
            put("error", e.toString());
            put("params", request.getParameterMap());
        }});
    }
}
