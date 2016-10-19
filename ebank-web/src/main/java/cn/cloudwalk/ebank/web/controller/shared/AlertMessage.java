package cn.cloudwalk.ebank.web.controller.shared;

import java.io.Serializable;

/**
 * Created by liwenhe on 2016/10/19.
 *
 * @author 李文禾
 */
public class AlertMessage implements Serializable {

    private final static String ALERT_MESSAGE_KEY = "message";

    private Type type;

    private String message;

    private Object data;

    public AlertMessage(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public AlertMessage(Type type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public enum Type {
        INFO("信息"),
        WARNING("警告"),
        ERROR("错误"),
        SUCCESS("成功");

        private String name;

        Type(String name) {
            this.name = name;
        }

        public final String getName() {
            return name;
        }
    }
}
