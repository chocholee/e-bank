package cn.cloudwalk.ebank.core.domain.service.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IFunctionService {

    List<FunctionEntity> findAll();

}
