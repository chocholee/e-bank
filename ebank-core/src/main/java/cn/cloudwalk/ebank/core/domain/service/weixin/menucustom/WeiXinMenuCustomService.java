package cn.cloudwalk.ebank.core.domain.service.weixin.menucustom;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.IWeiXinMenuService;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.command.WeiXinMenuCustomCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.command.WeiXinMenuCustomPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.menucustom.IWeiXinMenuCustomRepository;
import cn.cloudwalk.ebank.core.repository.weixin.menucustomrule.IWeiXinMenuCustomRuleRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import org.hibernate.FetchMode;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by liwenhe on 16/11/22.
 */
@Service("weiXinMenuCustomService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinMenuCustomService implements IWeiXinMenuCustomService {

    @Autowired
    private IWeiXinMenuCustomRepository<WeiXinMenuCustomEntity, String> weiXinMenuCustomRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Autowired
    private IWeiXinMenuCustomRuleRepository<WeiXinMenuCustomRuleEntity, String> weiXinMenuCustomRuleRepository;

    @Autowired
    private IWeiXinMenuService weiXinMenuService;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    public List<Map<String, Object>> menuPackageMenuDataSet(String id) {
        // 菜单包数据条件
        List<Criterion> criterions = new ArrayList<>();
        criterions.add(Restrictions.isNull("parent"));
        criterions.add(Restrictions.eq("menuCustom.id", id));

        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("order"));

        Map<String, FetchMode> fetchModeMap = new HashMap<>();
        fetchModeMap.put("menuCustom", FetchMode.JOIN);

        List<WeiXinMenuEntity> parents = weiXinMenuService.findAll(criterions, orders, fetchModeMap);

        // 拼装数据
        List<Map<String, Object>> dataSet = new ArrayList<>();
        for (WeiXinMenuEntity parent : parents) {
            Map<String, Object> firstMenuMap = new HashMap<>();
            firstMenuMap.put("id", parent.getId());
            firstMenuMap.put("name", parent.getName());
            firstMenuMap.put("order", parent.getOrder());
            firstMenuMap.put("type", parent.getType().getName());
            firstMenuMap.put("flag", true);

            // 查找二级菜单
            List<Map<String, Object>> secondMenuList = new ArrayList<>();
            List<WeiXinMenuEntity> secondMenus = weiXinMenuService.findByParentId(parent.getId());
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
            dataSet.add(firstMenuMap);
        }

        return dataSet;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinMenuCustomEntity> pagination(WeiXinMenuCustomPaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getName())) {
                criterions.add(Restrictions.like("name", command.getName(), MatchMode.ANYWHERE));
            }

            criterions.add(Restrictions.eq("accountId", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinMenuCustomRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinMenuCustomEntity findById(String id) {
        return weiXinMenuCustomRepository.getById(id);
    }

    @Override
    public WeiXinMenuCustomEntity save(WeiXinMenuCustomCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountRepository.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinMenuCustomEntity entity = new WeiXinMenuCustomEntity(
                command.getName(),
                command.getRemark(),
                new Date(),
                accountEntity.getId());

        weiXinMenuCustomRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinMenuCustomEntity update(WeiXinMenuCustomCommand command) {
        WeiXinMenuCustomEntity entity = this.findById(command.getId());
        entity.setName(command.getName());
        entity.setRemark(command.getRemark());
        weiXinMenuCustomRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        // 查询个性化菜单
        WeiXinMenuCustomEntity entity = this.findById(id);

        // 查询与个性化菜单关联的菜单数据
        List<WeiXinMenuEntity> menuEntities = weiXinMenuService.findByParentIsNullAndMenuCustom(entity.getId());
        for (WeiXinMenuEntity menu : menuEntities) {
            weiXinMenuService.delete(menu.getId());
        }

        // 删除与个性化规则的外键关系
        DetachedCriteria menuCustomRuleDc = DetachedCriteria.forClass(WeiXinMenuCustomRuleEntity.class);
        menuCustomRuleDc.add(Restrictions.eq("menuCustom.id", id))
                .createAlias("menuCustom", "menuCustom", JoinType.LEFT_OUTER_JOIN);
        List<WeiXinMenuCustomRuleEntity> mcRuleList = weiXinMenuCustomRuleRepository.findAll(menuCustomRuleDc);
        for (WeiXinMenuCustomRuleEntity mcRule : mcRuleList) {
            weiXinMenuCustomRuleRepository.delete(mcRule);
        }

        // 删除个性化菜单
        weiXinMenuCustomRepository.delete(entity);
    }
}
