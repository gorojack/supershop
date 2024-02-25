package top.gorojack.supershop.handler;

import top.gorojack.supershop.pojo.User;

public class UserInfoThreadHolder {

    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void setCurrentUser(User user) {
        threadLocal.set(user);
    }

    public static User getCurrentUser() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
