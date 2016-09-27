package cn.cloudwalk.ebank.core.domain.service.user;

import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserService implements IUserService {

    @Autowired
    private IUserRepository<UserEntity, String> userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity save(UserEntity entity) {
        userRepository.save(entity);
        return entity;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        userRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(UserEntity entity) {
        userRepository.delete(entity);
    }
}
