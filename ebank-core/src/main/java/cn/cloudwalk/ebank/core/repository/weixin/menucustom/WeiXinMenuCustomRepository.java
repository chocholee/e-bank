package cn.cloudwalk.ebank.core.repository.weixin.menucustom;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/22.
 */
@Repository("weiXinMenuCustomRepository")
public class WeiXinMenuCustomRepository extends AbstractHibernateRepository<WeiXinMenuCustomEntity, String>
        implements IWeiXinMenuCustomRepository<WeiXinMenuCustomEntity, String> {
}
