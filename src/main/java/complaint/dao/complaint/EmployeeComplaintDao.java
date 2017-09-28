package complaint.dao.complaint;

import complaint.model.complaint.EmployeeComplaint;

public interface EmployeeComplaintDao {

    void persist(EmployeeComplaint employeeComplaint);
    void update(EmployeeComplaint employeeComplaint);
    void delete(EmployeeComplaint employeeComplaint);

    EmployeeComplaint findById(long id);
}
