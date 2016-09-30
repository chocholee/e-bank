package cn.cloudwalk.ebank.core.repository.function;

import cn.cloudwalk.ebank.core.repository.IHibernateRepository;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IFunctionRepository<T, ID> extends IHibernateRepository<T, ID> {

    T findById(String id);

}
