package cn.cloudwalk.ebank.core.repository.weixin.keyword;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public interface IWeiXinKeywordRepository<T, ID> extends IHibernateRepository<T, ID> {

    T findByKeyword(String accountId, String keyword);

}
