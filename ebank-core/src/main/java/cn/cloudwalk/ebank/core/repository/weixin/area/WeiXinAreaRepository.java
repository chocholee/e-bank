package cn.cloudwalk.ebank.core.repository.weixin.area;

import cn.cloudwalk.ebank.core.domain.model.weixin.area.WeiXinAreaEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/25.
 */
@Repository("weiXinAreaRepository")
public class WeiXinAreaRepository extends AbstractHibernateRepository<WeiXinAreaEntity, String>
        implements IWeiXinAreaRepository<WeiXinAreaEntity, String> {
}
