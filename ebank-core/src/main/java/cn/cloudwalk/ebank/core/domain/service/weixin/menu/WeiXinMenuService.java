package cn.cloudwalk.ebank.core.domain.service.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.command.WeiXinMenuCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.IWeiXinMenuCustomService;
import cn.cloudwalk.ebank.core.repository.weixin.menu.IWeiXinMenuRepository;
import cn.cloudwalk.ebank.core.repository.weixin.menucustom.IWeiXinMenuCustomRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import com.arm4j.weixin.exception.WeiXinRequestException;
import com.arm4j.weixin.request.menu.WeiXinMenuCreateRequest;
import com.arm4j.weixin.request.menu.entity.MenuButtonEntity;
import com.arm4j.weixin.request.menu.entity.MenuEntity;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Service("weiXinMenuService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinMenuService implements IWeiXinMenuService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWeiXinMenuRepository<WeiXinMenuEntity, String> weiXinMenuRepository;

    @Autowired
    private IWeiXinMenuCustomRepository<WeiXinMenuCustomEntity, String> weiXinMenuCustomRepository;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    public List<Map<String, Object>> dataset() {
        List<Map<String, Object>> dataset = new ArrayList<>();

        // 查找一级菜单
        List<WeiXinMenuEntity> firstMenus = this.findByParentAndMenuCustomIsNull();
        for (WeiXinMenuEntity first : firstMenus) {
            Map<String, Object> firstMenuMap = new HashMap<>();
            firstMenuMap.put("id", first.getId());
            firstMenuMap.put("name", first.getName());
            firstMenuMap.put("order", first.getOrder());
            firstMenuMap.put("type", first.getType().getName());
            firstMenuMap.put("flag", true);

            // 查找二级菜单
            List<Map<String, Object>> secondMenuList = new ArrayList<>();
            List<WeiXinMenuEntity> secondMenus = this.findByParentId(first.getId());
            for (WeiXinMenuEntity second : secondMenus) {
                Map<String, Object> secondMenuMap = new HashMap<>();
                secondMenuMap.put("id", second.getId());
                secondMenuMap.put("name", second.getName());
                secondMenuMap.put("order", second.getOrder());
                secondMenuMap.put("type", second.getType().getName());
                secondMenuList.add(secondMenuMap);
            }

            // 组装二级菜单数据至一级菜单中
            firstMenuMap.put("children", secondMenuList);
            dataset.add(firstMenuMap);
        }

        return dataset;
    }

    @Override
    public List<WeiXinMenuEntity> findAll() {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.asc("order"));

            return weiXinMenuRepository.findAll(criterions, orders, null);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<WeiXinMenuEntity> findAll(List<Criterion> criterions, List<Order> orders, Map<String, FetchMode> fetchModeMap) {
        return weiXinMenuRepository.findAll(criterions, orders, fetchModeMap);
    }

    @Override
    public List<WeiXinMenuEntity> findByParentIsNullAndMenuCustom(String menuCustomId) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));
            criterions.add(Restrictions.eq("menuCustom.id", menuCustomId));
            criterions.add(Restrictions.isNull("parent"));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.asc("order"));

            // 添加fetchMode
            Map<String, FetchMode> fetchModeMap = new HashMap<>();
            fetchModeMap.put("menuCustom", FetchMode.JOIN);

            return weiXinMenuRepository.findAll(criterions, orders, fetchModeMap);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<WeiXinMenuEntity> findByParentAndMenuCustomIsNull() {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));
            criterions.add(Restrictions.isNull("parent"));
            criterions.add(Restrictions.isNull("menuCustom"));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.asc("order"));

            return weiXinMenuRepository.findAll(criterions, orders, null);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<WeiXinMenuEntity> findByParentId(String parentId) {
        // 添加查询条件
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.eq("parent.id", parentId));

        // 添加排序条件
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));

        // 添加fetch
        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("parent", FetchMode.JOIN);

        return weiXinMenuRepository.findAll(criterions, orders, fetchModeMap);
    }

    @Override
    public WeiXinMenuEntity findByIdAndFetch(String id) {
        return weiXinMenuRepository.findByIdAndFetch(id);
    }

    @Override
    public WeiXinMenuEntity save(WeiXinMenuCommand command) {
        // 查询公众号
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 查询父类
        WeiXinMenuEntity parent = null;
        if (!StringUtils.isEmpty(command.getParent())) {
            parent = weiXinMenuRepository.getById(command.getParent());
        }

        // 查询个性化菜单
        WeiXinMenuCustomEntity menuCustomEntity = null;
        if (!StringUtils.isEmpty(command.getMenuCustom())) {
            menuCustomEntity = weiXinMenuCustomRepository.getById(command.getMenuCustom());
        }

        // 新增数据
        WeiXinMenuEntity entity = new WeiXinMenuEntity(
                command.getName(),
                command.getKey(),
                command.getUrl(),
                command.getMediaId(),
                command.getOrder(),
                command.getType(),
                parent,
                menuCustomEntity,
                accountEntity.getId()
        );

        weiXinMenuRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinMenuEntity update(WeiXinMenuCommand command) {
        // 查询父类
        WeiXinMenuEntity parent = null;
        if (!StringUtils.isEmpty(command.getParent())) {
            parent = weiXinMenuRepository.getById(command.getParent());
        }

        // 查询个性化菜单
        WeiXinMenuCustomEntity menuCustomEntity = null;
        if (!StringUtils.isEmpty(command.getMenuCustom())) {
            menuCustomEntity = weiXinMenuCustomRepository.getById(command.getMenuCustom());
        }

        // 更新当前数据
        WeiXinMenuEntity entity = weiXinMenuRepository.getById(command.getId());
        entity.setName(command.getName());
        entity.setKey(command.getKey());
        entity.setUrl(command.getUrl());
        entity.setOrder(command.getOrder());
        entity.setType(command.getType());
        entity.setParent(parent);
        entity.setMenuCustom(menuCustomEntity);

        weiXinMenuRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        List<WeiXinMenuEntity> children = this.findByParentId(id);
        for (WeiXinMenuEntity child : children) {
            this.delete(child.getId());
        }
        WeiXinMenuEntity entity = weiXinMenuRepository.getById(id);
        weiXinMenuRepository.delete(entity);
    }

    @Override
    public void sync() throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        List<MenuButtonEntity> allMenuButtonEntities = new ArrayList<>();
        List<WeiXinMenuEntity> weiXinMenuEntities = this.findByParentAndMenuCustomIsNull();
        for (WeiXinMenuEntity parentIsNullMenu : weiXinMenuEntities) {
            // 第一级菜单
            MenuButtonEntity firstMenuButtonEntity = new MenuButtonEntity();
            firstMenuButtonEntity.setName(parentIsNullMenu.getName());
            firstMenuButtonEntity.setKey(parentIsNullMenu.getKey());
            firstMenuButtonEntity.setType(parentIsNullMenu.getType().name().toLowerCase());
            switch (parentIsNullMenu.getType()) {
                case VIEW:
                    firstMenuButtonEntity.setUrl(parentIsNullMenu.getUrl());
                    break;
                case VIEW_LIMITED:
                    firstMenuButtonEntity.setMediaId(parentIsNullMenu.getMediaId());
                    break;
            }

            // 第二级菜单
            List<MenuButtonEntity> secondMenuButtonEntities = new ArrayList<>();
            List<WeiXinMenuEntity> menuEntities = this.findByParentId(parentIsNullMenu.getId());
            for (WeiXinMenuEntity entity : menuEntities) {
                MenuButtonEntity menuButtonEntity = new MenuButtonEntity();
                menuButtonEntity.setName(entity.getName());
                menuButtonEntity.setKey(entity.getKey());
                menuButtonEntity.setType(entity.getType().name().toLowerCase());
                switch (entity.getType()) {
                    case VIEW:
                        menuButtonEntity.setUrl(entity.getUrl());
                        break;
                    case VIEW_LIMITED:
                        menuButtonEntity.setMediaId(entity.getMediaId());
                        break;
                }

                secondMenuButtonEntities.add(menuButtonEntity);
            }

            if (!menuEntities.isEmpty()) {
                // 第二级菜单添加至第一级菜单
                firstMenuButtonEntity.setSubButton(secondMenuButtonEntities);
            }

            // 第一级菜单添加至容器中
            allMenuButtonEntities.add(firstMenuButtonEntity);
        }

        // 同步至微信中
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setButtons(allMenuButtonEntities);
        WeiXinMenuCreateRequest.request(weiXinAccountService.getAccessToken(accountEntity.getAppId()), menuEntity);
    }
}
