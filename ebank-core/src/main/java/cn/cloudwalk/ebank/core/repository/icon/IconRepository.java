package cn.cloudwalk.ebank.core.repository.icon;

import cn.cloudwalk.ebank.core.domain.model.icon.IconEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
@Repository("IconRepository")
public class IconRepository extends AbstractHibernateRepository<IconEntity, String>
        implements IIconRepository<IconEntity, String> {
}
