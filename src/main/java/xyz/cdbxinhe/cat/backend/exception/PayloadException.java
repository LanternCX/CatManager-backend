package xyz.cdbxinhe.cat.backend.exception;

/**
 * 错误-参数不合法
 * package xyz.cdbxinhe.cat.backend.exception
 * project backend
 * Created by @author CaoXin on date 2023/03/03
 */
public class PayloadException extends Exception {
    public PayloadException(String reason) {
        super(reason);
    }
}