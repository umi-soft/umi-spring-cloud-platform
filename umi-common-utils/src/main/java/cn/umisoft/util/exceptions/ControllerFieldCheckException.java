package cn.umisoft.util.exceptions;

/**
 * @description: Controller层处理过程中主动检测到相关问题时，可选择抛出该异常，配合全局异常捕获使用
 * @author: hujie@umisoft.cn
 * @date: 2019/1/14 11:30 AM
 */
public class ControllerFieldCheckException extends RuntimeException {

    public ControllerFieldCheckException() {
        super();
    }

    public ControllerFieldCheckException(String message) {
        super(message);
    }

    public ControllerFieldCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerFieldCheckException(Throwable cause) {
        super(cause);
    }

    protected ControllerFieldCheckException(String message, Throwable cause,
                                            boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
