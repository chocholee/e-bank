package cn.cloudwalk.ebank.core.support.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by liwenhe on 2016/9/22.
 *
 * @author 李文禾
 */
public class InvalidCaptchaException extends AuthenticationException {
    /**
     * Constructs a {@code InvalidCaptchaException} with the specified message
     * and root cause.
     *
     * @param msg the detail message
     * @param t the root cause
     */
    public InvalidCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an {@code InvalidCaptchaException} with the specified message
     * and no root cause.
     *
     * @param msg the detail message
     */
    public InvalidCaptchaException(String msg) {
        super(msg);
    }
}
