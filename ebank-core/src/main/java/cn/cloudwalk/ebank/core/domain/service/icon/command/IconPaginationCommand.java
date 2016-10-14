package cn.cloudwalk.ebank.core.domain.service.icon.command;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntityType;
import cn.cloudwalk.ebank.core.support.command.AbstractPaginationCommand;

/**
 * Created by liwenhe on 2016/10/13.
 *
 * @author 李文禾
 */
public class IconPaginationCommand extends AbstractPaginationCommand {

    private String name;

    private IconEntityType type;

    public String getName() {
        return name;
    }

    public IconEntityType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(IconEntityType type) {
        this.type = type;
    }
}
