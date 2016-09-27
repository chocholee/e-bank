package cn.cloudwalk.ebank.core.domain.service.resource;

import cn.cloudwalk.ebank.core.domain.model.resource.ResourceEntity;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IResourceService {

    List<ResourceEntity> findAll();

}
