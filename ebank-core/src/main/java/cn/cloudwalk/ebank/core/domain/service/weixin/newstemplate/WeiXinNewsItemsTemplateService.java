package cn.cloudwalk.ebank.core.domain.service.weixin.newstemplate;

import cn.cloudwalk.ebank.core.domain.model.weixin.newstemplate.WeiXinNewsItemsTemplateEntity;
import cn.cloudwalk.ebank.core.repository.weixin.newstemplate.IWeiXinNewsItemsTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liwenhe on 2016/10/10.
 *
 * @author 李文禾
 */
@Service("weiXinNewsItemsTemplateService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinNewsItemsTemplateService implements IWeiXinNewsItemsTemplateService {

    @Autowired
    private IWeiXinNewsItemsTemplateRepository<WeiXinNewsItemsTemplateEntity, String> weiXinNewsItemsTemplateRepository;

    @Override
    public List<WeiXinNewsItemsTemplateEntity> findByNewsTemplateId(String newsTemplateId) {
        return weiXinNewsItemsTemplateRepository.findByNewsTemplateId(newsTemplateId);
    }

    @Override
    public WeiXinNewsItemsTemplateEntity findById(String id) {
        return weiXinNewsItemsTemplateRepository.getById(id);
    }
}
