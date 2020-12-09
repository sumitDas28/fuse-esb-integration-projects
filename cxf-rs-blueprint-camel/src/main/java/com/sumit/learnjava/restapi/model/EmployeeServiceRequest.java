package com.sumit.learnjava.restapi.model;

import javax.ws.rs.PathParam;

import org.apache.camel.Body;

public class EmployeeServiceRequest {
	
	@PathParam("id")
	private String id;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
