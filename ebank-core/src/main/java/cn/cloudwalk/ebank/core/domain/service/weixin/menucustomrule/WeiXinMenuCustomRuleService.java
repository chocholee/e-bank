package cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.area.WeiXinAreaEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.wechat.WeiXinGroupWechatEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntityStatus;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.area.IWeiXinAreaService;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.command.WeiXinMenuCustomRuleCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.command.WeiXinMenuCustomRulePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.group.wechat.IWeiXinGroupWechatRepository;
import cn.cloudwalk.ebank.core.repository.weixin.menu.IWeiXinMenuRepository;
import cn.cloudwalk.ebank.core.repository.weixin.menucustom.IWeiXinMenuCustomRepository;
import cn.cloudwalk.ebank.core.repository.weixin.menucustomrule.IWeiXinMenuCustomRuleRepository;
import cn.cloudwalk.ebank.core.support.exception.WeiXinNotFoundException;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import com.arm4j.weixin.exception.WeiXinRequestException;
import com.arm4j.weixin.request.menu.WeiXinMenuAddConditionalRequest;
import com.arm4j.weixin.request.menu.WeiXinMenuDelConditionalRequest;
import com.arm4j.weixin.request.menu.entity.MenuButtonEntity;
import com.arm4j.weixin.request.menu.entity.MenuEntity;
import com.arm4j.weixin.request.menu.entity.MenuMatchRuleEntity;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by liwenhe on 16/11/25.
 */
@Service("weiXinMenuCustomRuleService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinMenuCustomRuleService implements IWeiXinMenuCustomRuleService {

    @Autowired
    private IWeiXinMenuCustomRuleRepository<WeiXinMenuCustomRuleEntity, String> weiXinMenuCustomRuleRepository;

    @Autowired
    private IWeiXinMenuCustomRepository<WeiXinMenuCustomEntity, String> weiXinMenuCustomRepository;

    @Autowired
    private IWeiXinGroupWechatRepository<WeiXinGroupWechatEntity, String> weiXinGroupWechatRepository;

    @Autowired
    private IWeiXinMenuRepository<WeiXinMenuEntity, String> weiXinMenuRepository;

    @Autowired
    private IWeiXinAreaService weiXinAreaService;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @Autowired
    private MessageSourceAccessor message;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinMenuCustomRuleEntity> pagination(WeiXinMenuCustomRulePaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

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

            return weiXinMenuCustomRuleRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinMenuCustomRuleEntity findById(String id) {
        return weiXinMenuCustomRuleRepository.getById(id);
    }

    @Override
    public WeiXinMenuCustomRuleEntity findByIdAndFetch(String id) {
        return weiXinMenuCustomRuleRepository.findByIdAndFetch(id);
    }

    @Override
    public WeiXinMenuCustomRuleEntity save(WeiXinMenuCustomRuleCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        String country = StringUtils.isEmpty(command.getCountry()) ? null : command.getCountry();
        String province = StringUtils.isEmpty(command.getProvince()) ? null : command.getProvince();
        String city = StringUtils.isEmpty(command.getCity()) ? null : command.getCity();
        String remark = StringUtils.isEmpty(command.getRemark()) ? null : command.getRemark();

        WeiXinMenuCustomEntity menuCustom = null;
        if (!StringUtils.isEmpty(command.getMenuCustomId())) {
            menuCustom = weiXinMenuCustomRepository.getById(command.getMenuCustomId());
        }

        WeiXinGroupWechatEntity groupWechat = null;
        if (!StringUtils.isEmpty(command.getGroupWechatId())) {
            groupWechat = weiXinGroupWechatRepository.getById(command.getGroupWechatId());
        }

        WeiXinMenuCustomRuleEntity entity = new WeiXinMenuCustomRuleEntity(
                command.getName(),
                null,
                country,
                province,
                city,
                remark,
                new Date(),
                command.getSex(),
                command.getPlatform(),
                command.getLanguage(),
                WeiXinMenuCustomRuleEntityStatus.NOT_SYNC,
                menuCustom,
                groupWechat,
                accountEntity.getId()
        );

        weiXinMenuCustomRuleRepository.save(entity);
        return entity;
    }

    @Override
    public WeiXinMenuCustomRuleEntity update(WeiXinMenuCustomRuleCommand command) {
        WeiXinMenuCustomRuleEntity entity = weiXinMenuCustomRuleRepository.findByIdAndFetch(command.getId());

        String country = StringUtils.isEmpty(command.getCountry()) ? null : command.getCountry();
        String province = StringUtils.isEmpty(command.getProvince()) ? null : command.getProvince();
        String city = StringUtils.isEmpty(command.getCity()) ? null : command.getCity();
        String remark = StringUtils.isEmpty(command.getRemark()) ? null : command.getRemark();

        entity.setCountry(country);
        entity.setProvince(province);
        entity.setCity(city);
        entity.setRemark(remark);

        if (!StringUtils.isEmpty(command.getMenuCustomId())
                &&
                (null != entity.getMenuCustom()
                        && !entity.getMenuCustom().getId().equals(command.getMenuCustomId())
                        || null == entity.getMenuCustom())) {
            WeiXinMenuCustomEntity menuCustom = weiXinMenuCustomRepository.getById(command.getMenuCustomId());
            entity.setMenuCustom(menuCustom);
        }

        if (!StringUtils.isEmpty(command.getGroupWechatId())
                &&
                (null != entity.getGroupWechat()
                        && !entity.getGroupWechat().getId().equals(command.getGroupWechatId())
                        || null == entity.getGroupWechat())) {
            WeiXinGroupWechatEntity groupWechat = weiXinGroupWechatRepository.getById(command.getGroupWechatId());
            entity.setGroupWechat(groupWechat);
        }

        if (entity.getStatus() == WeiXinMenuCustomRuleEntityStatus.SYNCED) {
            entity.setStatus(WeiXinMenuCustomRuleEntityStatus.EDITED_SYNC);
        }

        entity.setSex(command.getSex());
        entity.setPlatform(command.getPlatform());
        entity.setLanguage(command.getLanguage());

        weiXinMenuCustomRuleRepository.update(entity);
        return entity;
    }

    @Override
    public void delete(String id) throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        WeiXinMenuCustomRuleEntity entity = this.findById(id);
        if (entity.getStatus() == WeiXinMenuCustomRuleEntityStatus.EDITED_SYNC
                || entity.getStatus() == WeiXinMenuCustomRuleEntityStatus.SYNCED) {
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setMenuId(entity.getMenuId());
            WeiXinMenuDelConditionalRequest.request(weiXinAccountService.getAccessToken(accountEntity.getAppId()), menuEntity);
        }

        weiXinMenuCustomRuleRepository.delete(entity);
    }

    @Override
    public void sync(String id) throws WeiXinRequestException {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);
        if (null == accountEntity) {
            throw new WeiXinNotFoundException(message.getMessage("WeiXinAccountNotFoundException.message"));
        }

        // 查询当前数据
        WeiXinMenuCustomRuleEntity mcRule = weiXinMenuCustomRuleRepository.findByIdAndFetch(id);

        // 判断个性化菜单是否编辑过，编辑过则删除之前的个性化菜单并同步
        if (mcRule.getStatus() == WeiXinMenuCustomRuleEntityStatus.EDITED_SYNC) {
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setMenuId(mcRule.getMenuId());
            WeiXinMenuDelConditionalRequest.request(weiXinAccountService.getAccessToken(accountEntity.getAppId()), menuEntity);
        }

        // 判断个性化菜单是否已经同步过，如果同步过，return
        if (mcRule.getStatus() == WeiXinMenuCustomRuleEntityStatus.SYNCED) {
            return;
        }

        // 构造数据存储容器
        List<MenuButtonEntity> allMenuButtonEntities = new ArrayList<>();

        // 查询一级菜单
        DetachedCriteria firstDC = DetachedCriteria.forClass(WeiXinMenuEntity.class);
        firstDC.add(Restrictions.eq("menuCustom.id", mcRule.getMenuCustom().getId()))
                .createAlias("menuCustom", "menuCustom", JoinType.LEFT_OUTER_JOIN);
        List<WeiXinMenuEntity> weiXinMenuEntities = weiXinMenuRepository.findAll(firstDC);
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
            DetachedCriteria secondDC = DetachedCriteria.forClass(WeiXinMenuEntity.class);
            secondDC.add(Restrictions.eq("parent.id", parentIsNullMenu.getId()))
                    .createAlias("parent", "parent", JoinType.LEFT_OUTER_JOIN);
            List<WeiXinMenuEntity> menuEntities = weiXinMenuRepository.findAll(secondDC);
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

        // 构造个性化菜单规则
        MenuMatchRuleEntity matchRuleEntity = new MenuMatchRuleEntity();
        matchRuleEntity.setGroupId(String.valueOf(mcRule.getGroupWechat().getGroupId()));
        matchRuleEntity.setSex((null != mcRule.getSex()) ? String.valueOf(mcRule.getSex().ordinal()) : null);
        matchRuleEntity.setClientPlatformType((null != mcRule.getPlatform()) ? String.valueOf(mcRule.getPlatform().ordinal()) : null);
        matchRuleEntity.setLanguage((null != mcRule.getLanguage()) ? mcRule.getLanguage().name() : null);

        // 得到国家数据
        List<WeiXinAreaEntity> countries = weiXinAreaService.findCountries();
        for (WeiXinAreaEntity country : countries) {
            if (country.getCountryValue().equals(mcRule.getCountry())) {
                matchRuleEntity.setCountry(country.getCountryName());
            }
        }

        // 得到省份数据
        List<WeiXinAreaEntity> provinces = weiXinAreaService.findProvinces(mcRule.getCountry());
        for (WeiXinAreaEntity province : provinces) {
            if (province.getProvinceValue().equals(mcRule.getProvince())) {
                matchRuleEntity.setProvince(province.getProvinceName());
            }
        }

        // 得到城市数据
        List<WeiXinAreaEntity> cities = weiXinAreaService.findCities(mcRule.getProvince());
        for (WeiXinAreaEntity city : cities) {
            if (city.getCityValue().equals(mcRule.getCity())) {
                matchRuleEntity.setCity(city.getCityName());
            }
        }

        // 封装数据并同步到微信服务器
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setButtons(allMenuButtonEntities);
        menuEntity.setMenuMatchRuleEntity(matchRuleEntity);
        String menuId = WeiXinMenuAddConditionalRequest.request(weiXinAccountService.getAccessToken(accountEntity.getAppId()), menuEntity);

        // 更新数据
        mcRule.setMenuId(menuId);
        mcRule.setStatus(WeiXinMenuCustomRuleEntityStatus.SYNCED);
        weiXinMenuCustomRuleRepository.update(mcRule);
    }

}
