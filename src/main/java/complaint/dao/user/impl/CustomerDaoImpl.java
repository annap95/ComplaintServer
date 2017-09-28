package complaint.dao.user.impl;

import complaint.dao.user.CustomerDao;
import complaint.model.user.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by anna on 22.09.17.
 */
@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Customer find(long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public void persist(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void delete(Customer customer) {
        entityManager.remove(entityManager.merge(customer));
    }

    @Override
    public Customer findCustomerByUserId(long userId) {
        Query query = entityManager.createQuery("from Customer c where c.user.userId = :userId");
        query.setParameter("userId", userId);
        List list = query.getResultList();
        if(list == null || list.isEmpty())
            return null;
        return (Customer) list.get(0);
    }

}
