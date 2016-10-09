package cn.cloudwalk.ebank.core.support.exception;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinAccountNotFoundException extends RuntimeException {

    public WeiXinAccountNotFoundException() {
    }

    public WeiXinAccountNotFoundException(String message) {
        super(message);
    }

    public WeiXinAccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
