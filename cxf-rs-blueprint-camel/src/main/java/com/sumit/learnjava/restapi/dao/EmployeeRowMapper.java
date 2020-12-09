package com.sumit.learnjava.restapi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sumit.learnjava.restapi.model.Employee;

public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Employee(rs.getLong("id"),rs.getString("first_name"),rs.getString("last_name"),rs.getString("email"));
	}

}
