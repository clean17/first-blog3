package shop.mtcoding.blog2.handler.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import shop.mtcoding.blog2.model.User;


public class CustomInterceptor implements HandlerInterceptor {

    // @Override
    // public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    //         throws Exception {
    //     // System.out.println("테스트 : "+ request.getRequestURL().toString());
    //     if (request.getRequestURL().toString().equals("http://localhost:8080/")) {
    //         User principal = (User) request.getSession().getAttribute("principal");
    //         if (principal == null) {
    //             response.sendRedirect("/login");
    //             return false;
    //             // return true;
    //         }
    //     }
    //     return true;
    // }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            // HttpSession session = request.getSession();
            // User principal = (User) session.getAttribute("principal");
            User principal = (User) request.getSession().getAttribute("principal");
            if (principal == null) {
                response.setContentType("text/html; charset=utf-8");
                response.getWriter().println("잘못된 접근입니다.");
                return false;
            }else{
                return true;
            }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
    }
}
