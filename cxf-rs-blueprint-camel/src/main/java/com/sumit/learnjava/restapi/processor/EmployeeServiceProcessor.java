package com.sumit.learnjava.restapi.processor;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;

import com.sumit.learnjava.restapi.dao.EmployeeServiceDao;
import com.sumit.learnjava.restapi.model.Employee;
import com.sumit.learnjava.restapi.model.EmployeeServiceRequest;

public class EmployeeServiceProcessor {
	
	private EmployeeServiceDao emplServiceDao;

	public void getAllEmployees(Exchange exchange) throws Exception {
		exchange.getOut().setBody(Response.ok().entity(emplServiceDao.getAllEmployees()).build());
	}

	public void getEmployeeById(Exchange exchange) throws Exception {
		EmployeeServiceRequest request = exchange.getIn().getBody(EmployeeServiceRequest.class);
		exchange.getOut().setBody(Response.ok().entity(emplServiceDao.getEmployeeById(Long.valueOf(request.getId()))).build());
	}

	public void addEmployee(Exchange exchange) throws Exception {
		Employee employee = exchange.getIn().getBody(Employee.class);
		exchange.getOut().setBody(Response.ok().entity(emplServiceDao.addEmployee(employee)).build());
	}

	public void updateEmployee(Exchange exchange) throws Exception {
		EmployeeServiceRequest request = exchange.getIn().getBody(EmployeeServiceRequest.class);
		long id = Long.valueOf(request.getId());
		Employee existingEmployee = emplServiceDao.getEmployeeById(id);
		if(existingEmployee!=null) {
			Employee updateEmployee = exchange.getIn().getBody(Employee.class);
			exchange.getOut().setBody(Response.ok().entity(emplServiceDao.updateEmployee(id, updateEmployee)).build());
		}
	}

	public void deleteEmployee(Exchange exchange) throws Exception {
		EmployeeServiceRequest request = exchange.getIn().getBody(EmployeeServiceRequest.class);
		long id = Long.valueOf(request.getId());
		emplServiceDao.deleteEmployee(id);
		exchange.getOut().setBody(Response.ok().build());
	}

	public EmployeeServiceDao getEmplServiceDao() {
		return emplServiceDao;
	}

	public void setEmplServiceDao(EmployeeServiceDao emplServiceDao) {
		this.emplServiceDao = emplServiceDao;
	}
	
	

}
