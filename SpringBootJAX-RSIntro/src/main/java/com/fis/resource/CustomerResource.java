package com.fis.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;


import com.fis.entity.Customer;
import com.fis.exception.CustomerServiceException;
import com.fis.model.CustomerInfo;
import com.fis.model.LoginInfo;
import com.fis.service.CustomerService;

@Path("/customer")
public class CustomerResource {

	@Autowired
	private CustomerService customerService;
	
	/*
	 * To test this code from Postman, the URL will be:
	 * localhost:9090/api/customer/register
	 * And in the body of the request, you need to send
	 * the customer data in the json format, for ex:
	 	{
	    	"name" : "Majrul",
			"mobileNo" : 123456789,
			"email" : "majrul@gmail.com",
			"password" : "123456"
		}
	 */
	
	@POST
	@Path("/register")
	public String register(Customer customer) {
		try {
			customerService.registerv2(customer);
			return "Customer registered succesfully..";
		}
		catch(CustomerServiceException e) {
			return e.getMessage();
		}
		
	}
	
	//http://localhost:9090/api/customer/login
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomerInfo login(LoginInfo loginInfo){
		try {
			Customer customer = customerService.login(loginInfo.getEmail(), loginInfo.getPassword());
			
			CustomerInfo info = new CustomerInfo();
			info.setStatus(true);
			info.setId(customer.getId());
			info.setName(customer.getName());
			return info;
		}
		catch(CustomerServiceException e) {
			//return e.getMessage();
			CustomerInfo info = new CustomerInfo();
			info.setStatus(false);
			info.setErrorIfAny(e.getMessage());
			return info;
		}
	}
	
	//http://localhost:9090/api/customer/1
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer get(@PathParam("id") int id) {
		return customerService.get(id);
		
	}
	
	//http://localhost:9090/api/customer/update
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update(Customer customer) {
		customerService.update(customer);
		return "Customer details updated successfully!";
	}
	
	//http://localhost:9090/api/customer/delete/3
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@PathParam("id") int id) {
		customerService.delete(id);
		return "Customer deleted successfully!";
	}
	
}
