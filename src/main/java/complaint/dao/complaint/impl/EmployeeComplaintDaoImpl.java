package complaint.dao.complaint.impl;

import complaint.dao.complaint.EmployeeComplaintDao;
import complaint.model.complaint.EmployeeComplaint;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by anna on 22.09.17.
 */
@Repository
@Transactional
public class EmployeeComplaintDaoImpl implements EmployeeComplaintDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EmployeeComplaint find(long id) {
        return entityManager.find(EmployeeComplaint.class, id);
    }

    @Override
    public void persist(EmployeeComplaint employeeComplaint) {
        entityManager.persist(employeeComplaint);
    }

    @Override
    public void update(EmployeeComplaint employeeComplaint) {
        entityManager.merge(employeeComplaint);
    }

    @Override
    public void delete(EmployeeComplaint employeeComplaint) {
        entityManager.remove(entityManager.merge(employeeComplaint));
    }
}
