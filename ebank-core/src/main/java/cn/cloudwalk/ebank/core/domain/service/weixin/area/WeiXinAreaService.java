package cn.cloudwalk.ebank.core.domain.service.weixin.area;

import cn.cloudwalk.ebank.core.domain.model.weixin.area.WeiXinAreaEntity;
import cn.cloudwalk.ebank.core.repository.weixin.area.IWeiXinAreaRepository;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liwenhe on 16/11/25.
 */
@Service("weiXinAreaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WeiXinAreaService implements IWeiXinAreaService {

    @Autowired
    private IWeiXinAreaRepository<WeiXinAreaEntity, String> weiXinAreaRepository;

    @Override
    public List<WeiXinAreaEntity> findCountries() {
        DetachedCriteria dc = DetachedCriteria.forClass(WeiXinAreaEntity.class);
        dc.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("countryName").as("countryName"))
                .add(Projections.groupProperty("countryValue").as("countryValue")))
                .setResultTransformer(Transformers.aliasToBean(WeiXinAreaEntity.class));
        return weiXinAreaRepository.findAll(dc);
    }

    @Override
    public List<WeiXinAreaEntity> findProvinces(String countryValue) {
        DetachedCriteria dc = DetachedCriteria.forClass(WeiXinAreaEntity.class);
        dc.add(Restrictions.eq("countryValue", countryValue))
                .setProjection(Projections.projectionList()
                .add(Projections.groupProperty("provinceName").as("provinceName"))
                .add(Projections.groupProperty("provinceValue").as("provinceValue")))
                .setResultTransformer(Transformers.aliasToBean(WeiXinAreaEntity.class));
        return weiXinAreaRepository.findAll(dc);
    }

    @Override
    public List<WeiXinAreaEntity> findCities(String provinceValue) {
        DetachedCriteria dc = DetachedCriteria.forClass(WeiXinAreaEntity.class);
        dc.add(Restrictions.eq("provinceValue", provinceValue))
                .setProjection(Projections.projectionList()
                .add(Projections.groupProperty("cityName").as("cityName"))
                .add(Projections.groupProperty("cityValue").as("cityValue")))
                .setResultTransformer(Transformers.aliasToBean(WeiXinAreaEntity.class));
        return weiXinAreaRepository.findAll(dc);
    }
}
