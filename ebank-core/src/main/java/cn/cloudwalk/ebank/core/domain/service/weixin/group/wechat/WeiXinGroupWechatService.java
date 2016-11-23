package cn.cloudwalk.ebank.core.domain.service.weixin.group.wechat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liwenhe on 16/11/23.
 */
@Service("weiXinGroupWechatService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinGroupWechatService implements IWeiXinGroupWechatService {
}
