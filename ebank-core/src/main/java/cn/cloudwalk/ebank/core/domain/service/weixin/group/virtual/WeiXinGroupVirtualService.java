package cn.cloudwalk.ebank.core.domain.service.weixin.group.virtual;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.group.virtual.WeiXinGroupVirtualEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.group.command.WeiXinGroupPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.account.IWeiXinAccountRepository;
import cn.cloudwalk.ebank.core.repository.weixin.group.virtual.IWeiXinGroupVirtualRepository;
import cn.cloudwalk.ebank.core.support.utils.CustomSecurityContextHolderUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liwenhe on 16/11/23.
 */
@Service("weiXinGroupVirtualService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinGroupVirtualService implements IWeiXinGroupVirtualService {

    @Autowired
    private IWeiXinGroupVirtualRepository<WeiXinGroupVirtualEntity, String> weiXinGroupVirtualRepository;

    @Autowired
    private IWeiXinAccountRepository<WeiXinAccountEntity, String> weiXinAccountRepository;

    @Override
    @SuppressWarnings("unchecked")
    public Pagination<WeiXinGroupVirtualEntity> pagination(WeiXinGroupPaginationCommand command) {
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

            return weiXinGroupVirtualRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

}
