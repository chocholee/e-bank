package cn.cloudwalk.ebank.core.domain.service.user;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IUserService {

    List<UserEntity> findAll();

    UserEntity findById(String id);

    UserEntity findByUsername(String username);

    UserEntity save(UserEntity entity);

    UserEntity update(UserEntity entity);

    void delete(UserEntity entity);
}
