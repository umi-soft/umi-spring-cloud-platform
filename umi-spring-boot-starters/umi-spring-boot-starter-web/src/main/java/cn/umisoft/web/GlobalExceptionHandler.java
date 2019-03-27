package cn.umisoft.web;

import cn.umisoft.util.exceptions.ControllerHandlerGlobalException;
import cn.umisoft.util.exceptions.DaoHandlerGlobalException;
import cn.umisoft.util.exceptions.ServiceHandlerGlobalException;
import cn.umisoft.web.util.ApiResult;
import cn.umisoft.web.util.ApiResultWrapper;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import io.lettuce.core.RedisCommandExecutionException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: Controller全局异常捕获器
 * @author: hujie@umisoft.cn
 * @date: 2019/1/15 2:25 PM
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public String getExceptionMessage(HttpServletResponse response,Exception exception) {
        exception.printStackTrace();
        response.setCharacterEncoding("UTF-8");
        return exception.getMessage();
    }

    @ExceptionHandler(ControllerHandlerGlobalException.class)
    public ApiResult controllerHandlerGlobalExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.controllerHandlerGlobalError(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(ServiceHandlerGlobalException.class)
    public ApiResult serviceHandlerGlobalExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.serviceHandlerGlobalError(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ApiResult springWebServletRequestBindingExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebServletRequestBindingException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(MultipartException.class)
    public ApiResult springWebMultipartExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebMultipartException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult springWebHttpRequestMethodNotSupportedExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebHttpRequestMethodNotSupportedException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ApiResult springWebHttpMediaTypeExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebHttpMediaTypeException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(HttpSessionRequiredException.class)
    public ApiResult springWebHttpSessionRequiredExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebHttpSessionRequiredException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ApiResult springWebMissingServletRequestPartExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebMissingServletRequestPartException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(ModelAndViewDefiningException.class)
    public ApiResult springWebModelAndViewDefiningExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebModelAndViewDefiningException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult springWebNoHandlerFoundExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebNoHandlerFoundException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(UnavailableException.class)
    public ApiResult springWebUnavailableExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.springWebUnavailableException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResult illegalArgumentExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.illegalArgumentError(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ApiResult jwtTokenExpiredExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.sessionExpired(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ApiResult jwtVerificationExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.sessionIllegal(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(DaoHandlerGlobalException.class)
    public ApiResult daoHandlerGlobalExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.daoHandlerGlobalError(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(RedisCommandExecutionException.class)
    public ApiResult redisCommandExecutionExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.redisCommandExecutionException(getExceptionMessage(response, exception));
    }

    @ExceptionHandler(Exception.class)
    public ApiResult defaultHandlerExceptionResolver(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        return ApiResultWrapper.unknown(getExceptionMessage(response, exception));
    }
}
