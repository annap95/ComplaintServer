package complaint.dao.complaint.impl;

import complaint.dao.complaint.ComplaintDao;
import complaint.model.complaint.Complaint;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ComplaintDaoImpl implements ComplaintDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(Complaint complaint) {
        entityManager.persist(complaint);
    }

    @Override
    public void update(Complaint complaint) {
        entityManager.merge(complaint);
    }

    @Override
    public void delete(Complaint complaint) {
        entityManager.remove(entityManager.merge(complaint));
    }

    @Override
    public Optional findById(long id) {
        return Optional.ofNullable(entityManager.find(Complaint.class, id));
    }

    @Override
    public List findAll() {
        return entityManager.createQuery("from Complaint").getResultList();
    }

    @Override
    public List findByCustomer(long customerId) {
        return entityManager.createQuery("from Complaint c where c.customer.customerId = :customerId")
                .setParameter("customerId", customerId)
                .getResultList();
    }

    @Override
    public List findByEmployee(long employeeId) {
        return entityManager.createQuery("from Complaint c where c.employee.employeeId = :employeeId")
                .setParameter("employeeId", employeeId)
                .getResultList();
    }
}
