package cn.cloudwalk.ebank.web.controller.weixin.area;

import cn.cloudwalk.ebank.core.domain.model.weixin.area.WeiXinAreaEntity;
import cn.cloudwalk.ebank.core.domain.service.weixin.area.IWeiXinAreaService;
import cn.cloudwalk.ebank.web.controller.shared.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liwenhe on 16/11/25.
 */
@Controller
@RequestMapping("/weixin/area")
public class WeiXinAreaController extends BaseController {

    @Autowired
    private IWeiXinAreaService weiXinAreaService;

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    @ResponseBody
    public List<WeiXinAreaEntity> countries() {
        return weiXinAreaService.findCountries();
    }

    @RequestMapping(value = "/provinces/{country}", method = RequestMethod.GET)
    @ResponseBody
    public List<WeiXinAreaEntity> provinces(@PathVariable String country) {
        return weiXinAreaService.findProvinces(country);
    }

    @RequestMapping(value = "/cities/{province}", method = RequestMethod.GET)
    @ResponseBody
    public List<WeiXinAreaEntity> cities(@PathVariable String province) {
        return weiXinAreaService.findCities(province);
    }

}
