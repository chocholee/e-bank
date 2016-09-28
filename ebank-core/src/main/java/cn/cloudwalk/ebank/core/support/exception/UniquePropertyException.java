package cn.cloudwalk.ebank.core.support.exception;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public class UniquePropertyException extends RuntimeException {

    public UniquePropertyException() {
    }

    public UniquePropertyException(String message) {
        super(message);
    }

    public UniquePropertyException(String message, Throwable cause) {
        super(message, cause);
    }
}
