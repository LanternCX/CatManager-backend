package xyz.cdbxinhe.cat.backend.exception;

/**
 * 错误-认证不通过
 * package xyz.cdbxinhe.cat.backend.exception
 * project backend
 * Created by @author CaoXin on date 2023/03/03
 */
public class InvalidAuthenticationException extends Exception {
    public InvalidAuthenticationException(String reason) {
        super(reason);
    }
}
