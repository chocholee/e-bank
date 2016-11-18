package cn.cloudwalk.ebank.core.repository.weixin.scene;

import cn.cloudwalk.ebank.core.domain.model.weixin.scene.WeiXinSceneEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 16/11/17.
 */
@Repository("weiXinSceneRepository")
public class WeiXinSceneRepository extends AbstractHibernateRepository<WeiXinSceneEntity, String>
        implements IWeiXinSceneRepository<WeiXinSceneEntity, String> {
}
