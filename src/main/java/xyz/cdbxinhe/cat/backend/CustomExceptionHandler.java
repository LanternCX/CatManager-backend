package xyz.cdbxinhe.cat.backend;

import com.alipay.api.AlipayApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import xyz.cdbxinhe.cat.backend.exception.*;

/**
 * 错误处理
 * package xyz.cdbxinhe.cat.backend
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response noHandlerFoundExceptionHandler() {
        return new Response(404, "请求到火星去啦!,对应路径不存在");
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response methodNotAllowedExceptionHandler() {
        return new Response(405, "这样不行!,请求方法不被允许");
    }

    @ExceptionHandler(value = AlreadyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response alreadyBorrowedExceptionHandler(AlreadyException alreadyException) {
        return new Response(400, alreadyException.getMessage());
    }

    @ExceptionHandler(value = InvalidAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response invalidAuthenticationExceptionHandler(InvalidAuthenticationException invalidAuthenticationException) {
        return new Response(401, invalidAuthenticationException.getMessage());
    }

    @ExceptionHandler(value = PayGatewayNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response payGatewayNotFoundExceptionExceptionHandler(PayGatewayNotFoundException payGatewayNotFoundException) {
        return new Response(400, payGatewayNotFoundException.getMessage());
    }

    @ExceptionHandler(value = NoPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Response noPermissionExceptionHandler(NoPermissionException noPermissionException) {
        return new Response(403, noPermissionException.getMessage());
    }

    @ExceptionHandler(value = AlipayApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response alipayApiExceptionExceptionHandler(AlipayApiException alipayApiException) {
        return new Response(500, alipayApiException.getMessage());
    }


    @ExceptionHandler(value = NetworkException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response networkExceptionHandler(NetworkException networkException) {
        return new Response(500, networkException.getMessage());
    }

    @ExceptionHandler(value = PayloadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response payloadErrorHandler(PayloadException payloadException) {
        return new Response(400, payloadException.getMessage());
    }
}
