package complaint.dao.complaint;

import complaint.model.complaint.EmployeeComplaint;

import java.util.Optional;

public interface EmployeeComplaintDao {

    void persist(EmployeeComplaint employeeComplaint);
    void update(EmployeeComplaint employeeComplaint);
    void delete(EmployeeComplaint employeeComplaint);

    Optional<EmployeeComplaint> findById(long id);
}
