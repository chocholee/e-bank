package cn.cloudwalk.ebank.core.repository.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Repository("functionRepository")
public class FunctionRepository extends AbstractHibernateRepository<FunctionEntity, String>
        implements IFunctionRepository<FunctionEntity, String> {
}
