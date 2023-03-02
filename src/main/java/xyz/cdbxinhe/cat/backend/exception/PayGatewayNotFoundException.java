package xyz.cdbxinhe.cat.backend.exception;

/**
 * 错误-支付方式不存在
 * package xyz.cdbxinhe.cat.backend.exception
 * project backend
 * Created by @author CaoXin on date 2023/03/03
 */
public class PayGatewayNotFoundException extends Exception {
    public PayGatewayNotFoundException(String reason) {
        super(reason);
    }
}
