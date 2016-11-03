package cn.cloudwalk.ebank.core.domain.service.user;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserAddCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserEditCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IUserService {

    Pagination<UserEntity> pagination(UserPaginationCommand command);

    Pagination<UserEntity> paginationWithoutSelf(UserPaginationCommand command);

    List<UserEntity> findAll();

    UserEntity findById(String id);

    UserEntity findByUsername(String username);

    UserEntity save(UserAddCommand command);

    UserEntity update(UserEditCommand command);

    UserEntity increaseLoginCount(String id);

    void resetLoginCount(String id);

    void delete(String id);

    void lock(String id);

    void authorize(String id, String[] roleIds);
}
