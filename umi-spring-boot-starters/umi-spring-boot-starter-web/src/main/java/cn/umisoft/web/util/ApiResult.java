package cn.umisoft.web.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 统一的API返回结果封装
 * @author: hujie@umisoft.cn
 * @date: 2019/1/14 9:43 AM
 */
@Getter
@Setter
public class ApiResult {

    private ApiResultCode code;

    private String message;

    private Object data;

    public ApiResult() {}

    public ApiResult(ApiResultCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(ApiResultCode code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
