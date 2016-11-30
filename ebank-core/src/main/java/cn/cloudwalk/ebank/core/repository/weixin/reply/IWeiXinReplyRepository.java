package cn.cloudwalk.ebank.core.repository.weixin.reply;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

import java.util.List;

/**
 * Created by liwenhe on 16/11/21.
 */
public interface IWeiXinReplyRepository<T, ID> extends IHibernateRepository<T, ID> {

    List<T> findBySceneId(String sceneId);

    T findByKeyword(String keyword, String accountId);

}
