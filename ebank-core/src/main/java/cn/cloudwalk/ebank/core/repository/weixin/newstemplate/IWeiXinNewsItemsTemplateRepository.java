package cn.cloudwalk.ebank.core.repository.weixin.newstemplate;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

import java.util.List;

/**
 * Created by liwenhe on 2016/10/10.
 *
 * @author 李文禾
 */
public interface IWeiXinNewsItemsTemplateRepository<T, ID> extends IHibernateRepository<T, ID> {

    List<T> findByNewsTemplateId(String newsTemplateId);

}
