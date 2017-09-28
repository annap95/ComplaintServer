package complaint.dao.user.impl;

import complaint.dao.user.CustomerDao;
import complaint.model.user.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

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
    public Customer findById(long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Optional findByUser(long userId) {
        return entityManager.createQuery("from Customer c where c.user.userId = :userId")
                .setParameter("userId", userId)
                .getResultList()
                .stream()
                .findFirst();
    }

}
