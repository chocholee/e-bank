package cn.cloudwalk.ebank.core.domain.model.weixin.area;

import cn.cloudwalk.ebank.core.support.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liwenhe on 16/11/25.
 */
@Entity
@Table(name = "weixin_area")
public class WeiXinAreaEntity extends AbstractEntity {

    private String countryName;

    private String countryValue;

    private String provinceName;

    private String provinceValue;

    private String cityName;

    private String cityValue;

    @Column(name = "country_name")
    public String getCountryName() {
        return countryName;
    }

    @Column(name = "country_value")
    public String getCountryValue() {
        return countryValue;
    }

    @Column(name = "province_name")
    public String getProvinceName() {
        return provinceName;
    }

    @Column(name = "province_value")
    public String getProvinceValue() {
        return provinceValue;
    }

    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    @Column(name = "city_value")
    public String getCityValue() {
        return cityValue;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryValue(String countryValue) {
        this.countryValue = countryValue;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceValue(String provinceValue) {
        this.provinceValue = provinceValue;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityValue(String cityValue) {
        this.cityValue = cityValue;
    }
}
