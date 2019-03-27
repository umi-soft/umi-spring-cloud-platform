package cn.umisoft.util.exceptions;

/**
 * @description: Service业务逻辑处理过程中主动检测到到相关问题时，可选择抛出该异常，配合全局异常捕获使用
 * @author: hujie@umisoft.cn
 * @date: 2019/1/14 11:30 AM
 */
public class ServiceHandlerGlobalException extends RuntimeException {
    public ServiceHandlerGlobalException() {
        super();
    }

    public ServiceHandlerGlobalException(String message) {
        super(message);
    }

    public ServiceHandlerGlobalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceHandlerGlobalException(Throwable cause) {
        super(cause);
    }

    protected ServiceHandlerGlobalException(String message, Throwable cause,
                                               boolean enableSuppression,
                                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
