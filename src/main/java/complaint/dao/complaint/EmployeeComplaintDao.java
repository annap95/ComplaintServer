package complaint.dao.complaint;

import complaint.model.complaint.EmployeeComplaint;

/**
 * Created by anna on 22.09.17.
 */
public interface EmployeeComplaintDao {

    EmployeeComplaint find(long id);
    void persist(EmployeeComplaint employeeComplaint);
    void update(EmployeeComplaint employeeComplaint);
    void delete(EmployeeComplaint employeeComplaint);
}
