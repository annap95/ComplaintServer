package complaint.dao.complaint.impl;

import complaint.dao.complaint.ComplaintDao;
import complaint.model.complaint.Complaint;
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
    public Complaint find(long id) {
        return entityManager.find(Complaint.class, id);
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
    public List<Complaint> getComplaints() {
        Query query = entityManager.createQuery("from Complaint");
        return query.getResultList();
    }
}
