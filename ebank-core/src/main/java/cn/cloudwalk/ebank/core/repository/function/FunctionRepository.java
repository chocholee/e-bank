package cn.cloudwalk.ebank.core.repository.function;

import cn.cloudwalk.ebank.core.domain.model.function.FunctionEntity;
import cn.cloudwalk.ebank.core.repository.AbstractHibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Repository("functionRepository")
public class FunctionRepository extends AbstractHibernateRepository<FunctionEntity, String>
        implements IFunctionRepository<FunctionEntity, String> {

    @Override
    public FunctionEntity findById(String id) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        criteria.add(Restrictions.eq("id", id))
                .setFetchMode("roleEntities", FetchMode.JOIN);
        return (FunctionEntity) criteria.uniqueResult();
    }

}
