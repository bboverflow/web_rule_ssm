package cn.trusteye.ssm.interceptor;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Rayman on 2017/3/2.
 */
public class HandlerInterceptor implements org.springframework.web.servlet.HandlerInterceptor {
    //身份认证、身份授权
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求url
        String url = request.getRequestURI();
        if(url.indexOf("login.action")>=0){
            return true;
        }
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null){
            //身份存在,放行
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return true;
    }

    //将公用的模型数据（比如菜单导航）传到视图，也可以在这里统一指定视图
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptor1...postHandle");
    }

    //统一的日志处理和异常处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("HandlerInterceptor1...afterCompletion");
    }
}
