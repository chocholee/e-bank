package cn.cloudwalk.ebank.core.repository.weixin.menucustomrule;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 16/11/25.
 */
public interface IWeiXinMenuCustomRuleRepository<T, ID> extends IHibernateRepository<T, ID> {

    T findByIdAndFetch(ID id);

}
