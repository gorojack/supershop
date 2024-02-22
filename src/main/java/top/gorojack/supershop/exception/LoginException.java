package top.gorojack.supershop.exception;

import top.gorojack.supershop.utils.Constant;

public class LoginException extends Exception {

    public LoginException() {
        super(Constant.NO_LOGIN);
    }
}
