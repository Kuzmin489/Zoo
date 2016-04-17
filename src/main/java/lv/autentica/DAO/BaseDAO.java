package lv.autentica.DAO;


import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class BaseDAO {

    @Autowired
    protected SessionFactory sessionFactory;

    public <T> T save(final T o){
        return (T) sessionFactory.getCurrentSession().save(o);
    }

    public void delete(final Object object){
        sessionFactory.getCurrentSession().delete(object);
    }

    public <T> T get(final Class<T> type, final Long id){
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    public <T> T merge(final T o)   {
        return (T) sessionFactory.getCurrentSession().merge(o);
    }

    public <T> void saveOrUpdate(final T o){
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public <T> List<T> getAll(final Class<T> type) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(type).list();
    }

}
