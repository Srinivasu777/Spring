package com.fis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fis.entity.Customer;
import com.fis.repository.CustomerRepository;

@SpringBootTest
class SpringBootJaxRsIntroApplicationTests {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	void add() {
		Customer customer = new Customer();
		customer.setName("Prasanth");
		customer.setMobileNo(123456789);
		customer.setEmail("prasanth@gmail.com");
		customer.setPassword("456");
		
		customerRepository.save(customer);
	}
	
	@Test
	void checkIfCustomerExists() {
		int id = customerRepository.findIfCustomerExists("srinivasu@gmail.com");
		System.out.println(id);
	}
	

}
