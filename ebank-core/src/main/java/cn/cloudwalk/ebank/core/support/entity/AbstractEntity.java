package cn.cloudwalk.ebank.core.support.entity;

import java.io.Serializable;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public abstract class AbstractEntity implements Serializable {

    private String id;

    private Integer version;

    public String getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
