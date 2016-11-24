package cn.cloudwalk.ebank.core.repository.weixin.qrcode;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 16/11/24.
 */
public interface IWeiXinQRCodeRepository<T, ID> extends IHibernateRepository<T, ID> {

    T findByIdAndFetch(ID id);

}
