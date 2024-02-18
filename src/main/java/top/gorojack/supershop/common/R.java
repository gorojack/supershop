package top.gorojack.supershop.common;

import lombok.Data;
import top.gorojack.supershop.utils.Constant;

@Data
public class R {

    private Integer code;
    private String msg;
    private Object data;

    public static R fail() {
        return new R(2000, Constant.FAILED, null);
    }

    public static R fail(Object data) {
        return new R(2000, Constant.FAILED, data);
    }

    public static R fail(String msg) {
        return new R(2000, msg, null);
    }

    public static R fail(String msg, Object data) {
        return new R(2000, msg, data);
    }

    public static R fail(Integer code, String msg, Object data) {
        return new R(code, msg, data);
    }

    public static R ok() {
        return new R(1000, Constant.SUCCESS, null);
    }

    public static R ok(Object data) {
        return new R(1000, Constant.SUCCESS, data);
    }

    public static R ok(String msg) {
        return new R(1000, msg, null);
    }

    public static R ok(String msg, Object data) {
        return new R(1000, msg, data);
    }

    public static R ok(Integer code, String msg, Object data) {
        return new R(code, msg, data);
    }

    private R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
