package xyz.cdbxinhe.cat.backend.exception;

/**
 * 错误-权限不足
 * package xyz.cdbxinhe.cat.backend.exception
 * project backend
 * Created by @author CaoXin on date 2023/03/03
 */
public class NoPermissionException extends Exception {
    public NoPermissionException(String reason) {
        super(reason);
    }
}
