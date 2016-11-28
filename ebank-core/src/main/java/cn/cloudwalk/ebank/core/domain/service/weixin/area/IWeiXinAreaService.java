package cn.cloudwalk.ebank.core.domain.service.weixin.area;

import cn.cloudwalk.ebank.core.domain.model.weixin.area.WeiXinAreaEntity;

import java.util.List;

/**
 * Created by liwenhe on 16/11/25.
 */
public interface IWeiXinAreaService {

    List<WeiXinAreaEntity> findCountries();

    List<WeiXinAreaEntity> findProvinces(String countryValue);

    List<WeiXinAreaEntity> findCities(String provinceValue);

}
