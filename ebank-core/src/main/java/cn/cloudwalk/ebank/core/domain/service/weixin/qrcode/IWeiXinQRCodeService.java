package cn.cloudwalk.ebank.core.domain.service.weixin.qrcode;

import cn.cloudwalk.ebank.core.domain.model.weixin.qrcode.WeiXinQRCodeEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.command.WeiXinQRCodeCommand;
import cn.cloudwalk.ebank.core.domain.service.weixin.qrcode.command.WeiXinQRCodePaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;
import com.arm4j.weixin.exception.WeiXinRequestException;

/**
 * Created by liwenhe on 16/11/24.
 */
public interface IWeiXinQRCodeService {

    Pagination<WeiXinQRCodeEntity> pagination(WeiXinQRCodePaginationCommand command);

    WeiXinQRCodeEntity findById(String id);

    WeiXinQRCodeEntity findByIdAndFetch(String id);

    WeiXinQRCodeEntity save(WeiXinQRCodeCommand command) throws WeiXinRequestException;

    WeiXinQRCodeEntity update(WeiXinQRCodeCommand command);

    void delete(String id);

}
