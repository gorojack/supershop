package top.gorojack.supershop.handler.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.gorojack.supershop.common.R;
import top.gorojack.supershop.exception.AuthenticationException;
import top.gorojack.supershop.exception.LoginException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public R auth(AuthenticationException e) {
        return R.fail(2001, e.getMessage());
    }

    @ExceptionHandler(LoginException.class)
    public R login(LoginException e) {
        return R.fail(2002, e.getMessage());
    }
}
