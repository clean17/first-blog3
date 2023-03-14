package shop.mtcoding.blog2.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import shop.mtcoding.blog2.model.User;

@Aspect
@Component
public class LoginAdvice {
    
    @Around("execution(* shop.mtcoding.aopstudy.controller..*.*(..))") // 강사님이 알려주신
    public Object loginUserAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object result = jp.proceed();
        Object[] args = jp.getArgs();
        Object[] param = new Object[1];
        for (Object arg : args) {
            if (arg instanceof User) {
                System.out.println("테스트 : 유저 있음");
                HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
                HttpSession session = req.getSession();
                User principal = (User) session.getAttribute("principal");
                param[0] = principal;
                result = jp.proceed(param);
            }
        }
        return result;
    }
}
