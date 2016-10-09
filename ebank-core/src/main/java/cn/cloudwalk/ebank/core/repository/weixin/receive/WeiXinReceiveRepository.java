package cn.cloudwalk.ebank.core.repository.weixin.receive;

import cn.cloudwalk.ebank.core.domain.model.weixin.receive.WeiXinReceiveEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
@Repository("weiXinReceiveRepository")
public class WeiXinReceiveRepository extends AbstractHibernateRepository<WeiXinReceiveEntity, String>
        implements IWeiXinReceiveRepository<WeiXinReceiveEntity, String> {
}
