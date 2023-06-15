package com.cglia.springsecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cglia.springsecurity.entity.Customer;
import com.cglia.springsecurity.exceptionhandling.GlobalNotFoundException;
import com.cglia.springsecurity.service.CustomerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@ApiOperation(value="Returns a List of Customers details")
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllEmployees(){
		List<Customer> customers = customerService.findAll();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Return a single customer")
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getSingleCustomer(@PathVariable int id){
		Customer customer = customerService.findById(id);
		if(customer == null) {
			throw new GlobalNotFoundException("Customer details with id : "+ id +" not found");
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
		customer.setId(0);
		customerService.save(customer);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer customer){
		customerService.update(customer, id);
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable int id){
		customerService.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
