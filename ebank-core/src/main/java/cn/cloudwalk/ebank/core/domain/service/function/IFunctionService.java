package cn.cloudwalk.ebank.core.domain.service.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionCommand;
import cn.cloudwalk.ebank.core.domain.service.function.command.FunctionPaginationCommand;
import cn.cloudwalk.ebank.core.repository.Pagination;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IFunctionService {

    Pagination<FunctionEntity> pagination(FunctionPaginationCommand command);

    List<FunctionEntity> findAll();

    List<FunctionEntity> findForFirstMenu(boolean isFetchIcon, boolean isFetchRole);

    List<FunctionEntity> findByParentId(String parentId, boolean isFetchIcon, boolean isFetchRole);

    FunctionEntity findById(String id);

    FunctionEntity findByIdAndFetch(String id);

    FunctionEntity save(FunctionCommand command);

    FunctionEntity update(FunctionCommand command);

    void delete(String id);

}
