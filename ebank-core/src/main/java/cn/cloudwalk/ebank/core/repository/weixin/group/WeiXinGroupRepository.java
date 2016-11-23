package cn.cloudwalk.ebank.core.repository.weixin.group;

import cn.cloudwalk.ebank.core.domain.model.weixin.group.WeiXinGroupEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/23.
 */
@Repository("weiXinGroupRepository")
public class WeiXinGroupRepository extends AbstractHibernateRepository<WeiXinGroupEntity, String>
        implements IWeiXinGroupRepository<WeiXinGroupEntity, String> {
}
