package cn.cloudwalk.ebank.core.domain.service.weixin.menu;

import cn.cloudwalk.ebank.core.domain.model.weixin.menu.WeiXinMenuEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.command.WeiXinMenuCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.menu.command.WeiXinMenuPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import com.arm4j.weixin.exception.WeiXinRequestException;

import java.util.List;

/**
 * Created by liwenhe on 2016/10/8.
 *
 * @author 李文禾
 */
public interface IWeiXinMenuService {

    Pagination<WeiXinMenuEntity> pagination(WeiXinMenuPaginationCommand command);

    List<WeiXinMenuEntity> findAll();

    List<WeiXinMenuEntity> findByParentIsNull();

    List<WeiXinMenuEntity> findByParentId(String parentId);

    WeiXinMenuEntity save(WeiXinMenuCommand command);

//    WeiXinMenuEntity update();

    void delete(String id);

    boolean sync() throws WeiXinRequestException;

}
