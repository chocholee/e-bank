package cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate.WeiXinNewsTemplateEntity;
import cn.cloudwalk.ebank.core.repository.weixin.newstemplate.IWeiXinNewsTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
@Service("weiXinNewsTemplateService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinNewsTemplateService implements IWeiXinNewsTemplateService {

    @Autowired
    private IWeiXinNewsTemplateRepository<WeiXinNewsTemplateEntity, String> weiXinNewsTemplateRepository;

    @Override
    public WeiXinNewsTemplateEntity findById(String id) {
        return weiXinNewsTemplateRepository.getById(id);
    }

}
