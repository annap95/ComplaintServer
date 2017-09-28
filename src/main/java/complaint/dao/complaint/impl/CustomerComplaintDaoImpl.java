package complaint.dao.complaint.impl;

import complaint.dao.complaint.CustomerComplaintDao;
import complaint.model.complaint.CustomerComplaint;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class CustomerComplaintDaoImpl implements CustomerComplaintDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(CustomerComplaint customerComplaint) {
        entityManager.persist(customerComplaint);
    }

    @Override
    public void update(CustomerComplaint customerComplaint) {
        entityManager.merge(customerComplaint);
    }

    @Override
    public void delete(CustomerComplaint customerComplaint) {
        entityManager.remove(entityManager.merge(customerComplaint));
    }

    @Override
    public CustomerComplaint findById(long id) {
        return entityManager.find(CustomerComplaint.class, id);
    }

}
