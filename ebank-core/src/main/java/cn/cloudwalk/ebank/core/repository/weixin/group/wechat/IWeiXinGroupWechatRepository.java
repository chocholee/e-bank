package cn.cloudwalk.ebank.core.repository.weixin.group.wechat;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 16/11/23.
 */
public interface IWeiXinGroupWechatRepository<T, ID> extends IHibernateRepository<T, ID> {

    T findByGroupId(Integer groupId, String accountId);

}
