package cn.cloudwalk.ebank.core.domain.service.role;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IRoleService {

    List<RoleEntity> findAll();

}
