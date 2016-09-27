package cn.cloudwalk.ebank.core.repository.resource;

import cn.cloudwalk.ebank.core.domain.model.resource.ResourceEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Repository("resourceRepository")
public class ResourceRepository extends AbstractHibernateRepository<ResourceEntity, String>
        implements IResourceRepository<ResourceEntity, String> {
}
