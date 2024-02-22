package top.gorojack.supershop.exception;

import top.gorojack.supershop.utils.Constant;

public class AuthenticationException extends Exception {

    public AuthenticationException() {
        super(Constant.AUTHENTICATION_FAILED);
    }
}
