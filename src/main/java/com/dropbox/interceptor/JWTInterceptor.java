package com.dropbox.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.dropbox.utils.JWTUtil;
import com.dropbox.vo.ResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        try{
            DecodedJWT verify = JWTUtil.verify(token);
            String id = verify.getClaim("id").asString();
            String username = verify.getClaim("username").asString();
            request.setAttribute("id", id);
            request.setAttribute("username", username);
            return true;
        }catch (Exception e){
            ResponseVO responseVO = ResponseVO.Failure("auth failed");
            String json = new ObjectMapper().writeValueAsString(responseVO);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
