package cn.cloudwalk.ebank.core.repository.weixinaccount;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 2016/9/30.
 *
 * @author 李文禾
 */
public interface IWeiXinAccountRepository<T, ID> extends IHibernateRepository<T, ID> {

    T findById(String id);

    T findByAppId(String appId);

}
