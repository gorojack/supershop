package top.gorojack.supershop.handler.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.gorojack.supershop.annotation.LoginRequired;
import top.gorojack.supershop.exception.LoginException;
import top.gorojack.supershop.handler.UserInfoThreadHolder;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.utils.JWTUtils;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod method)) return false;
        LoginRequired annotation = method.getMethodAnnotation(LoginRequired.class);
        if (null == annotation) return true;
        String token = request.getHeader("token");
        User user = JWTUtils.parseJWT(token);
        if (null == user) throw new LoginException();
        UserInfoThreadHolder.setCurrentUser(user);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        UserInfoThreadHolder.remove();
    }
}
