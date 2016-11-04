package cn.cloudwalk.ebank.core.support.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    protected String id;

    @Id
    @GenericGenerator(name = "entity_generator", strategy = "uuid")
    @GeneratedValue(generator = "entity_generator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
