package ua.omenyuk.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.omenyuk.spring.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import java.util.List;

/**
 * @author Vycheslav Omenyuk
 */
@Component
public class EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;
    private static int EMPLOYEE_COUNT;

    @Autowired
    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> index() {
        return jdbcTemplate.query("SELECT * FROM Employee", new EmployeeMapper());
    }

    public Employee show(int employee_id) {
        return jdbcTemplate.query("SELECT * FROM Employee WHERE employee_id=?", new Object[]{employee_id}, new BeanPropertyRowMapper<>(Employee.class))
                .stream().findAny().orElse(null);
    }


    // todo
    public void save(Employee employee) {
        String sql = "INSERT INTO Employee (employee_id,name, position, salary) values((SELECT max(employee_id)+1 from Employee),?,?,?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getPosition(), employee.getSalary());
    }

    public void update(int employee_id, Employee updatedEmployee) {
        jdbcTemplate.update("UPDATE Employee SET name=?, position=?, salary=? WHERE employee_id=?", updatedEmployee.getName(),
                updatedEmployee.getPosition(), updatedEmployee.getSalary(), employee_id);
    }

    public void delete(int employee_id) {
        jdbcTemplate.update("DELETE FROM Employee WHERE employee_id=?", employee_id);
    }
}