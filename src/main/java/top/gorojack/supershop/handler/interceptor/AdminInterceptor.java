package top.gorojack.supershop.handler.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.gorojack.supershop.annotation.AdminRequired;
import top.gorojack.supershop.exception.AuthenticationException;
import top.gorojack.supershop.pojo.User;
import top.gorojack.supershop.utils.JWTUtils;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod method)) return false;
        AdminRequired annotation = method.getMethodAnnotation(AdminRequired.class);
        if (null == annotation) return true;
        String token = request.getHeader("token");
        User user = JWTUtils.parseJWT(token);
        if (null == user || !"ADMIN".equals(user.getRole())) throw new AuthenticationException();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
