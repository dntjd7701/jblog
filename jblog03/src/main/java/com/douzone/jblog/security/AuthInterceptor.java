package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		// 1. handler 종류 확인 
		if(handler instanceof HandlerMethod == false) {
			
			// DefaultServletHandler가 처리하는 경우 (정적 자원)
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth 가 없으면 Type에 붙어있는지 확인.
		// 메소드 혹은 클래스. 
		
		if(auth == null) {
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		if(auth == null) {
			return true;
		}
		
		// 5. Auth가 붙어있음. 
		HttpSession session  = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/auth");
			return false;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/auth");
			return false;
		}
		
		String []getId = request.getRequestURI().toString().split("/");
		String checkId = getId[4];
		
		if(checkId.equals(authUser.getId()) == false){
			response.sendRedirect(request.getContextPath() + "/user/auth");
			return false;
		}
		return true;
	}
	

}
