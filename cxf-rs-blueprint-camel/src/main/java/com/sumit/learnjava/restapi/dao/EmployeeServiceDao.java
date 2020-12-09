package com.sumit.learnjava.restapi.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sumit.learnjava.restapi.model.Employee;

public class EmployeeServiceDao {

	private JdbcTemplate jdbcTemplate;

	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> empList = jdbcTemplate.query("select id, first_name, last_name, email from employee", new EmployeeRowMapper());
		return empList;
	}

	public Employee getEmployeeById(long id) throws Exception {
		StringBuffer query = new StringBuffer();
		query.append("select id, first_name, last_name, email from employee where id = ");
		query.append(id);
		List<Employee> employeeList = jdbcTemplate.query(query.toString(), new EmployeeRowMapper());
		if(employeeList.isEmpty()) {
			return null;
		}
		return employeeList.get(0);
	}

	public Employee addEmployee(Employee employee) throws Exception {
		String insertQuery = "insert into employee (id, first_name, last_name, email) values (?,?,?,?)";
		jdbcTemplate.update(insertQuery, employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
		
		StringBuffer selectQuery = new StringBuffer();
		selectQuery.append("select id, first_name, last_name, email from employee where id = ");
		selectQuery.append(employee.getId());
		List<Employee> employeeList = jdbcTemplate.query(selectQuery.toString(), new EmployeeRowMapper());
		if(employeeList.isEmpty()) {
			return null;
		}
		return employeeList.get(0);

	}

	public Employee updateEmployee(long id, Employee employee) throws Exception {
		String updateQuery = "update employee set first_name=?, last_name=?, email=? where id = ?";
		jdbcTemplate.update(updateQuery, employee.getFirstName(), employee.getLastName(), employee.getEmail(), id);
		StringBuffer selectQuery = new StringBuffer();
		selectQuery.append("select id, first_name, last_name, email from employee where id = ");
		selectQuery.append(id);
		List<Employee> employeeList = jdbcTemplate.query(selectQuery.toString(), new EmployeeRowMapper());
		if(employeeList.isEmpty()) {
			return null;
		}
		return employeeList.get(0);
	}

	public void deleteEmployee(long id) throws Exception {
		String deleteQuery = "delete from employee where id=?";
		jdbcTemplate.update(deleteQuery, id);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
