package cn.cloudwalk.ebank.core.domain.service.icon;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.repository.icon.IIconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Service("iconService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IconService implements IIconService {

    @Autowired
    private IIconRepository<IconEntity, String> iconRepository;

}
