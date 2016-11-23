package cn.cloudwalk.ebank.core.domain.service.weixin.menucustom;

import cn.cloudwalk.ebank.core.domain.model.weixin.menucustom.WeiXinMenuCustomEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.command.WeiXinMenuCustomCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menucustom.command.WeiXinMenuCustomPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 16/11/22.
 */
public interface IWeiXinMenuCustomService {

    List<Map<String, Object>> menuPackageMenuDataSet(String id);

    Pagination<WeiXinMenuCustomEntity> pagination(WeiXinMenuCustomPaginationCommand command);

    WeiXinMenuCustomEntity findById(String id);

    WeiXinMenuCustomEntity save(WeiXinMenuCustomCommand command);

    WeiXinMenuCustomEntity update(WeiXinMenuCustomCommand command);

    void delete(String id);

}
