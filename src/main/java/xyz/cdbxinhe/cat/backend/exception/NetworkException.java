package xyz.cdbxinhe.cat.backend.exception;

/**
 * 错误-服务器自身请求错误
 * package xyz.cdbxinhe.cat.backend.exception
 * project backend
 * Created by @author CaoXin on date 2023/03/03
 */
public class NetworkException extends Exception {
    public NetworkException(String reason) {
        super(reason);
    }
}