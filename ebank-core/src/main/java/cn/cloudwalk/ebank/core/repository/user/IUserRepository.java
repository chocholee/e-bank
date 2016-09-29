package cn.cloudwalk.ebank.core.repository.user;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IUserRepository<T, ID> extends IHibernateRepository<T, ID> {

    UserEntity findById(String id);

    UserEntity findByUsername(String username);

}
