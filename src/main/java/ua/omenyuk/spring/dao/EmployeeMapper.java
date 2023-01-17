package ua.omenyuk.spring.dao;
import org.springframework.jdbc.core.RowMapper;
import ua.omenyuk.spring.models.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vycheslav Omenyuk
 */
public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeId(resultSet.getInt("employee_id"));
        employee.setName(resultSet.getString("name"));
        employee.setPosition(resultSet.getString("position"));
        employee.setSalary(resultSet.getInt("salary"));

        return employee;

    }
}
