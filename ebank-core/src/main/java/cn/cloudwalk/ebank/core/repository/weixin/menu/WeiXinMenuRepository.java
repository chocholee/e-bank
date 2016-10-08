package cn.cloudwalk.ebank.core.repository.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Repository("weiXinMenuRepository")
public class WeiXinMenuRepository extends AbstractHibernateRepository<WeiXinMenuEntity, String>
        implements IWeiXinMenuRepository<WeiXinMenuEntity, String> {
}
