package cn.cloudwalk.ebank.core.repository.weixin.user;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 16/11/29.
 */
public interface IWeiXinUserRepository<T, ID> extends IHibernateRepository<T, ID> {

    T findByOpenId(String openId);

    T findByIdAndFetch(String id);

}
