
package portal.repository;

import java.util.List;

import portal.business.Employees;

public interface EmployeesRepository {
    public Employees findByUsername(String username);
    public List<Employees> findAll();
}
