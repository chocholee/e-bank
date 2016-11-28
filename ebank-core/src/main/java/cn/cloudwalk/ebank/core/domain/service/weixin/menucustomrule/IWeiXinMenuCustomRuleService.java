package cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustomrule.WeiXinMenuCustomRuleEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.command.WeiXinMenuCustomRuleCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustomrule.command.WeiXinMenuCustomRulePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import com.arm4j.weixin.exception.WeiXinRequestException;

/**
 * Created by liwenhe on 16/11/25.
 */
public interface IWeiXinMenuCustomRuleService {

    Pagination<WeiXinMenuCustomRuleEntity> pagination(WeiXinMenuCustomRulePaginationCommand command);

    WeiXinMenuCustomRuleEntity findById(String id);

    WeiXinMenuCustomRuleEntity findByIdAndFetch(String id);

    WeiXinMenuCustomRuleEntity save(WeiXinMenuCustomRuleCommand command);

    WeiXinMenuCustomRuleEntity update(WeiXinMenuCustomRuleCommand command);

    void delete(String id) throws WeiXinRequestException;

    void sync(String id) throws WeiXinRequestException;

}
