package cn.cloudwalk.ebank.core.repository;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
@Repository
@SuppressWarnings("unchecked")
public class AbstractHibernateRepository<T, ID extends Serializable> implements IHibernateRepository<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getPersistenceClass() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) (parameterizedType.getActualTypeArguments()[0]);
    }

    @Override
    public List<T> findAll() {
        return getSession().createCriteria(getPersistenceClass()).list();
    }

    @Override
    public List<T> findAll(List<Criterion> criterions, List<Order> orders, Map<String, FetchMode> fetchModeMap) {
        Criteria criteria = getSession().createCriteria(getPersistenceClass());
        if (null != criterions) {
            for (Criterion criterion : criterions) {
                criteria.add(criterion);
            }
        }

        if (null != orders) {
            for (Order order : orders) {
                criteria.addOrder(order);
            }
        }

        if (null != fetchModeMap) {
            for (String key : fetchModeMap.keySet()) {
                criteria.setFetchMode(key, fetchModeMap.get(key));
            }
        }

        return criteria.list();
    }

    @Override
    public T getById(ID id) {
        return getSession().get(getPersistenceClass(), id);
    }

    @Override
    public ID save(T entity) {
        return (ID) getSession().save(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public Pagination<T> pagination(int page, int pageSize) {
        return pagination(page, pageSize, null);
    }

    @Override
    public Pagination<T> pagination(int page, int pageSize, List<Criterion> criterions) {
        return pagination(page, pageSize, criterions, null);
    }

    @Override
    public Pagination<T> pagination(int page, int pageSize, List<Criterion> criterions, List<Order> orders) {
        return pagination(page, pageSize, criterions, orders, null);
    }

    @Override
    public Pagination<T> pagination(int page, int pageSize, List<Criterion> criterions, List<Order> orders, Map<String, FetchMode> fetchModeMap) {
        return pagination(page, pageSize, criterions, orders, fetchModeMap, null);
    }

    @Override
    public Pagination<T> pagination(int page, int pageSize,
                                    List<Criterion> criterions,
                                    List<Order> orders,
                                    Map<String, FetchMode> fetchModeMap,
                                    Map<String, String> aliasMap) {
        Criteria queryCount = getSession().createCriteria(getPersistenceClass());
        Criteria query = getSession().createCriteria(getPersistenceClass());

        if (null != criterions) {
            for (Criterion criterion : criterions) {
                query.add(criterion);
                queryCount.add(criterion);
            }
        }

        if (null != orders) {
            for (Order order : orders) {
                query.addOrder(order);
                queryCount.addOrder(order);
            }
        }

        if (null != fetchModeMap) {
            for (String key : fetchModeMap.keySet()) {
                query.setFetchMode(key, fetchModeMap.get(key));
                queryCount.setFetchMode(key, fetchModeMap.get(key));
            }
        }

        if (null != aliasMap) {
            for (String key : aliasMap.keySet()) {
                query.createAlias(key, aliasMap.get(key));
                queryCount.createAlias(key, aliasMap.get(key));
            }
        }

        int count = queryCount.list().size();
        List<T> list = query.setFirstResult((page - 1) * pageSize)
                .setFetchSize(pageSize)
                .list();

        return new Pagination(page, pageSize, count, list);
    }
}
