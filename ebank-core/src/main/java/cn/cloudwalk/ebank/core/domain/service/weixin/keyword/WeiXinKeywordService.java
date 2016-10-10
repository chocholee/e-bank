package cn.cloudwalk.ebank.core.domain.service.weixin.keyword;

import cn.cloudwalk.ebank.core.domain.model.weixin.account.WeiXinAccountEntity;
import cn.cloudwalk.ebank.core.domain.model.weixin.keyword.WeiXinKeywordEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.account.IWeiXinAccountService;
import cn.cloudwalk.ebank.core.domain.service.weixin.keyword.command.WeiXinKeywordPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import cn.cloudwalk.ebank.core.repository.weixin.keyword.IWeiXinKeywordRepository;
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
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
@Service("weiXinKeywordService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinKeywordService implements IWeiXinKeywordService {

    @Autowired
    private IWeiXinKeywordRepository<WeiXinKeywordEntity, String> weiXinKeywordRepository;

    @Autowired
    private IWeiXinAccountService weiXinAccountService;

    @Override
    public Pagination<WeiXinKeywordEntity> pagination(WeiXinKeywordPaginationCommand command) {
        String username = CustomSecurityContextHolderUtil.getUsername();
        WeiXinAccountEntity accountEntity = weiXinAccountService.findByUsername(username);

        if (null != accountEntity) {
            // 添加查询条件
            List<Criterion> criterions = new ArrayList<>();
            if (!StringUtils.isEmpty(command.getKeyword())) {
                criterions.add(Restrictions.like("keyword", command.getKeyword(), MatchMode.ANYWHERE));
            }
            if (!CustomSecurityContextHolderUtil.hasRole("administrator"))
                criterions.add(Restrictions.eq("accountEntity.id", accountEntity.getId()));

            // 添加排序条件
            List<Order> orders = new ArrayList<>();
            orders.add(Order.desc("createdDate"));

            return weiXinKeywordRepository.pagination(command.getPage(), command.getPageSize(), criterions, orders);
        } else {
            return new Pagination<WeiXinKeywordEntity>(command.getPage(), command.getPageSize(), 0, Collections.EMPTY_LIST);
        }
    }

    @Override
    public WeiXinKeywordEntity findByKeyword(String accountId, String keyword) {
        return weiXinKeywordRepository.findByKeyword(accountId, keyword);
    }

    @Override
    public void delete(String id) {
        WeiXinKeywordEntity entity = weiXinKeywordRepository.getById(id);
        weiXinKeywordRepository.delete(entity);
    }
}
