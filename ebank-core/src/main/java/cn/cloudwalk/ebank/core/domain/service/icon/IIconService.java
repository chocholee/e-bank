package cn.cloudwalk.ebank.core.domain.service.icon;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconCommand;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public interface IIconService {

    Pagination<IconEntity> pagination(IconPaginationCommand command);

    IconEntity findById(String id);

    IconEntity save(IconCommand command);

    IconEntity update(IconCommand command);

    void delete(String id);

}
