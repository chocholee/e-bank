package cn.cloudwalk.ebank.core.domain.service.user;

import cn.cloudwalk.ebank.core.domain.model.role.RoleEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntity;
import cn.cloudwalk.ebank.core.domain.model.user.UserEntityStatus;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserAddCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserEditCommand;
import cn.cloudwalk.ebank.core.domain.service.user.command.UserPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.role.IRoleRepository;
import cn.cloudwalk.ebank.core.repository.user.IUserRepository;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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

    @Autowired
    private IRoleRepository<RoleEntity, String> roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Pagination<UserEntity> pagination(UserPaginationCommand command) {
        // 添加查寻条件
        List<Criterion> criterions = new ArrayList<>();
        if (!StringUtils.isEmpty(command.getUsername())) {
            criterions.add(Restrictions.like("username", command.getUsername(), MatchMode.ANYWHERE));
        }

        // 添加排序
        List<Order> orders = new ArrayList<>();
        orders.add(Order.desc("createdDate"));

        return userRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserEntity save(UserAddCommand command) {
        // 生成盐并进行加密
        String salt     = UUID.randomUUID().toString().replaceAll("-", "");
        String password = passwordEncoder.encodePassword(command.getPassword(), salt);

        // 重新赋值盐和密码并保存
        UserEntity entity = new UserEntity();
        entity.setUsername(command.getUsername());
        entity.setSalt(salt);
        entity.setPassword(password);
        entity.setRemark(command.getRemark());
        entity.setRealname(command.getRealname());
        entity.setPhone(command.getPhone());
        entity.setEmail(command.getEmail());
        entity.setStatus(UserEntityStatus.ENABLE);
        entity.setCreatedDate(new Date());
        userRepository.save(entity);
        return entity;
    }

    @Override
    public UserEntity update(UserEditCommand command) {
        // 查找用户信息并更新
        UserEntity entity = this.findById(command.getId());
        entity.setRealname(command.getRealname());
        entity.setPhone(command.getPhone());
        entity.setEmail(command.getEmail());
        entity.setRemark(command.getRemark());
        entity.setUpdatedDate(new Date());
        userRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        UserEntity entity = this.findById(id);
        userRepository.delete(entity);
    }

    @Override
    public void lock(String id) {
        UserEntity entity = this.findById(id);
        entity.setStatus(
                (entity.getStatus() == UserEntityStatus.ENABLE)
                        ? UserEntityStatus.DISABLE
                        : UserEntityStatus.ENABLE);
        entity.setUpdatedDate(new Date());
        userRepository.update(entity);
    }

    @Override
    public void authorize(String id, String[] roleIds) {
        Set<RoleEntity> roleEntities = new HashSet<>();
        if (null != roleIds) {
            for (String roleId : roleIds) {
                RoleEntity roleEntity = roleRepository.getById(roleId);
                roleEntities.add(roleEntity);
            }
        }
        UserEntity entity = this.findById(id);
        entity.setRoleEntities(roleEntities);
        entity.setUpdatedDate(new Date());
        userRepository.update(entity);
    }
}
