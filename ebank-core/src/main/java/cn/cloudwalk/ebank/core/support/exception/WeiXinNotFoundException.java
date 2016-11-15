package cn.cloudwalk.ebank.core.support.exception;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public class WeiXinNotFoundException extends RuntimeException {

    public WeiXinNotFoundException() {
    }

    public WeiXinNotFoundException(String message) {
        super(message);
    }

    public WeiXinNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
