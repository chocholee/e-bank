package cn.cloudwalk.ebank.core.domain.service.weixin.keyword;

import cn.cloudwalk.ebank.core.domain.model.weixin.keyword.WeiXinKeywordEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.keyword.command.WeiXinKeywordPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

/**
 * Created by liwenhe on 2016/10/9.
 *
 * @author 李文禾
 */
public interface IWeiXinKeywordService {

    Pagination<WeiXinKeywordEntity> pagination(WeiXinKeywordPaginationCommand command);

    WeiXinKeywordEntity findByKeyword(String keyword);

//    WeiXinKeywordEntity save();

//    WeiXinKeywordEntity update();

    void delete(String id);

}
