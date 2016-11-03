package cn.cloudwalk.ebank.core.repository;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public interface IHibernateRepository<T, ID> {

    List<T> findAll();

    List<T> findAll(DetachedCriteria dc);

    List<T> findAll(List<Criterion> criterions, List<Order> orders, Map<String, FetchMode> fetchModeMap);

    T       getById(ID id);

    ID      save(T entity);

    void    delete(T entity);

    void    update(T entity);

    Pagination<T> pagination(int page, int pageSize);

    Pagination<T> pagination(int page, int pageSize, List<Criterion> criterions);

    Pagination<T> pagination(int page, int pageSize, List<Criterion> criterions, List<Order> orders);

    Pagination<T> pagination(int page, int pageSize, List<Criterion> criterions, List<Order> orders, Map<String, FetchMode> fetchModeMap);

    Pagination<T> pagination(int page, int pageSize,
                             List<Criterion> criterions,
                             List<Order> orders,
                             Map<String, FetchMode> fetchModeMap,
                             Map<String, String> aliasMap);

}
