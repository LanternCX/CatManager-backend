package xyz.cdbxinhe.cat.backend.exception;

/**
 * 错误-状态已
 * package xyz.cdbxinhe.cat.backend.exception
 * project backend
 * Created by @author CaoXin on date 2023/03/03
 */
public class AlreadyException extends Exception {
    public AlreadyException(String reason) {
        super(reason);
    }
}
