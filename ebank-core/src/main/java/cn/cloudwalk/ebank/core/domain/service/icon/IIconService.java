package cn.cloudwalk.ebank.core.domain.service.icon;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconCommand;
import cn.cloudwalk.ebank.core.domain.service.icon.command.IconPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.io.IOException;
import java.util.List;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public interface IIconService {

    Pagination<IconEntity> pagination(IconPaginationCommand command);

    List<IconEntity> findAll();

    IconEntity findById(String id);

    IconEntity save(IconCommand command, String tempDir, String saveDir) throws IOException;

    IconEntity update(IconCommand command, String tempDir, String saveDir) throws IOException;

    void delete(String id, String saveDir) throws IOException;

}
