package cn.cloudwalk.ebank.core.domain.service.role;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.service.role.command.RoleCommand;
import cn.cloudwalk.ebank.core.domain.service.role.command.RolePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IRoleService {

    Pagination<RoleEntity> pagination(RolePaginationCommand command);

    List<RoleEntity> findAll();

    RoleEntity findById(String id);

    RoleEntity save(RoleCommand command);

    RoleEntity update(RoleCommand command);

    void delete(String id);

    void authorize(String id, String[] functionIds);

}
