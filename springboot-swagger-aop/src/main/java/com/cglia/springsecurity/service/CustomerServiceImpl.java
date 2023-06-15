package com.cglia.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cglia.springsecurity.Repository.CustomerRepository;
import com.cglia.springsecurity.entity.Customer;
import com.cglia.springsecurity.exceptionhandling.GlobalNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
 
	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer findById(int id) {
		Optional<Customer> result = customerRepository.findById(id);
		Customer customer = null;
		if(result.isPresent()) {
			customer = result.get();
		}
		return customer;
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public void deleteById(int id) {
		Optional<Customer> result = customerRepository.findById(id);

		if(!result.isPresent()) {
            throw new GlobalNotFoundException("Customer ID : "+ id +" not found");
		}
		customerRepository.deleteById(id);
	}


	@Override
	public Customer update(Customer customer, int id) {
		Optional<Customer> result = customerRepository.findById(id);
		Customer oldCustomer = null;
		if(result.isPresent()) {
			oldCustomer = result.get();
			oldCustomer.setCustomerName(customer.getCustomerName());
			oldCustomer.setCustomerEmail(customer.getCustomerEmail());
			oldCustomer.setCustomerPassword(customer.getCustomerPassword());
			customerRepository.save(customer);
		}
		return customer;
	}
}
