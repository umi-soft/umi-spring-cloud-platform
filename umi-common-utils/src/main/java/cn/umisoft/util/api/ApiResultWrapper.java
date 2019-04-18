package cn.umisoft.util.api;

/**
 * @description: <p>请求返回结果集包装器,面向异常进行封装JSON结果，配合Controller，Service，Dao以及全局的异常捕获使用</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/14 12:35 AM
 */
public class ApiResultWrapper {

    public static ApiResult unknown(){
        return new ApiResult(ApiResultCode.UNKNOWN, "未知的错误");
    }

    /**
     * @description: <p>未知的错误</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:34 PM
     * @param: data
     * @return: ApiResult
     */
    public static ApiResult unknown(Object data){
        ApiResult apiResult = unknown();
        apiResult.setData(data);
        return apiResult;
    }
    /**
     * @description: <p>成功</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:34 PM
     * @return: ApiResult
     */
    public static ApiResult success(){
        return new ApiResult(ApiResultCode.SUCCESS, "操作成功");
    }
    /**
     * @description: <p>成功，附带相关结果</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:34 PM
     * @param: data
     * @return: ApiResult
     */
    public static ApiResult success(Object data){
        ApiResult apiResult = success();
        apiResult.setData(data);
        return apiResult;
    }
    /**
     * @description: <p>参数不完整</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult illegalArgumentError(){
        return new ApiResult(ApiResultCode.ILLEGAL_ARGUMENT_EXCEPTION, "参数不完整");
    }
    /**
     * @description: <p>参数不完整，附带相关结果</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult illegalArgumentError(Object data){
        ApiResult apiResult = illegalArgumentError();
        apiResult.setData(data);
        return apiResult;
    }
    /**
     * @description: <p>验证码过期</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult captchaExpired(){
        return new ApiResult(ApiResultCode.AUTHENTICATION_CAPTCHA_EXPIRE, "验证码过期");
    }
    /**
     * @description: <p>验证码过期，附带相关结果</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult captchaExpired(Object data){
        ApiResult apiResult = captchaExpired();
        apiResult.setData(data);
        return apiResult;
    }
    /**
     * @description: <p>用户不存在</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult noUser(){
        return new ApiResult(ApiResultCode.AUTHENTICATION_USERNAME_OR_PASSWORD_ERROR, "用户不存在");
    }
    /**
     * @description: <p>用户不存在，附带相关结果</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult noUser(Object data){
        ApiResult apiResult = noUser();
        apiResult.setData(data);
        return apiResult;
    }

    /**
     * @description: <p>回话过期</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult sessionExpired(){
        return new ApiResult(ApiResultCode.SESSION_EXPIRED, "回话过期");
    }
    /**
     * @description: <p>回话过期，附带相关结果</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/17 5:35 PM
     * @return: ApiResult
     */
    public static ApiResult sessionExpired(Object data){
        ApiResult apiResult = sessionExpired();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult sessionIllegal(){
        return new ApiResult(ApiResultCode.SESSION_ILLEGAL, "回话非法");
    }

    public static ApiResult sessionIllegal(Object data){
        ApiResult apiResult = sessionIllegal();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult permissionDenied(){
        return new ApiResult(ApiResultCode.PERMISSION_DENIED, "权限不足");
    }

    public static ApiResult permissionDenied(Object data){
        ApiResult apiResult = permissionDenied();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult controllerHandlerGlobalError(){
        return new ApiResult(ApiResultCode.CONTROLLER_HANDLER_GLOBAL_ERROR, "请求处理错误，请检查参数完整性、参数数据格式等方面是否满足接口标准");
    }

    public static ApiResult controllerHandlerGlobalError(Object data){
        ApiResult apiResult = controllerHandlerGlobalError();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult serviceHandlerGlobalError(){
        return new ApiResult(ApiResultCode.SERVICE_HANDLER_GLOBAL_ERROR, "业务逻辑处理错误");
    }

    public static ApiResult serviceHandlerGlobalError(Object data){
        ApiResult apiResult = serviceHandlerGlobalError();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebServletRequestBindingException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_SERVLET_REQUEST_BINDING_EXCEPTION, "接口调用不合符规范");
    }

    public static ApiResult springWebServletRequestBindingException(Object data){
        ApiResult apiResult = springWebServletRequestBindingException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebMultipartException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_MULTIPART_EXCEPTION, "文件上传错误");
    }

    public static ApiResult springWebMultipartException(Object data){
        ApiResult apiResult = springWebMultipartException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebHttpRequestMethodNotSupportedException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_HTTP_REQUEST_METHOD_NOT_SUPPORT_EXCEPTION, "接口不存在");
    }

    public static ApiResult springWebHttpRequestMethodNotSupportedException(Object data){
        ApiResult apiResult = springWebHttpRequestMethodNotSupportedException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebHttpMediaTypeException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_HTTP_MEDIA_TYPE_EXCEPTION, "媒体类型错误");
    }

    public static ApiResult springWebHttpMediaTypeException(Object data){
        ApiResult apiResult = springWebHttpMediaTypeException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebHttpSessionRequiredException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_HTTP_SESSION_REQUIRED_EXCEPTION, "Session不存在");
    }

    public static ApiResult springWebHttpSessionRequiredException(Object data){
        ApiResult apiResult = springWebHttpSessionRequiredException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebMissingServletRequestPartException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_MISSING_SERVLET_PART_EXCEPTION, "Request不完整");
    }

    public static ApiResult springWebMissingServletRequestPartException(Object data){
        ApiResult apiResult = springWebMissingServletRequestPartException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebModelAndViewDefiningException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_MODEL_AND_VIEW_DEFINING_EXCEPTION, "ModelAndView定义错误");
    }

    public static ApiResult springWebModelAndViewDefiningException(Object data){
        ApiResult apiResult = springWebModelAndViewDefiningException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebNoHandlerFoundException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_NO_HANDLER_FOUND_EXCEPTION, "找不到适配器");
    }

    public static ApiResult springWebNoHandlerFoundException(Object data){
        ApiResult apiResult = springWebNoHandlerFoundException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult springWebUnavailableException(){
        return new ApiResult(ApiResultCode.SPRING_WEB_UNAVAIABLE_EXCEPTION, "接口服务不可用");
    }

    public static ApiResult springWebUnavailableException(Object data){
        ApiResult apiResult = springWebUnavailableException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult daoHandlerGlobalError(){
        return new ApiResult(ApiResultCode.DAO_HANDLER_GLOBAL_ERROR, "数据库处理错误");
    }

    public static ApiResult daoHandlerGlobalError(Object data){
        ApiResult apiResult = daoHandlerGlobalError();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult redisCommandExecutionException(){
        return new ApiResult(ApiResultCode.REDIS_COMMAND_EXECUTION_EXCEPTION, "redis命令执行失败");
    }

    public static ApiResult redisCommandExecutionException(Object data){
        ApiResult apiResult = redisCommandExecutionException();
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult sentinelBlockException(){
        return new ApiResult(ApiResultCode.SENTINEL_BLOCK_EXCEPTION, "请求流量过大，服务保护机制已启动");
    }

    public static ApiResult sentinelBlockException(Object data){
        ApiResult apiResult = sentinelBlockException();
        apiResult.setData(data);
        return apiResult;
    }
}
