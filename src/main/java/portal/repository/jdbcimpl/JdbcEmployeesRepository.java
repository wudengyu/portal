package portal.repository.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import portal.business.Employees;
import portal.repository.EmployeesRepository;

@Repository
public class JdbcEmployeesRepository implements EmployeesRepository{
    
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Employees> findAll() {
        return jdbcOperations.query("select name,username from employees",
                    new RowMapper<Employees>(){
                    public Employees mapRow(ResultSet rs, int index) throws SQLException {
                        Employees employees = new Employees(rs.getString("name"),rs.getString("username"));
                        return employees;
                    }}
        );
    }

    @Override
    public Employees findByUsername(String username) {
        return 
            jdbcOperations.query("select name,username from employees where username=?",
            (rs)->{
                if(rs.next())
                    return new Employees(rs.getString("name"),rs.getString("username"));
                else
                    return null;},
            username);
    }
   
}
