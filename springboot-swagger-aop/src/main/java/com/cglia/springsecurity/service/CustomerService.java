package com.cglia.springsecurity.service;

import java.util.List;

import com.cglia.springsecurity.entity.Customer;

public interface CustomerService {
      public List<Customer> findAll();
      public Customer findById(int id);
      public void save(Customer customer);
      public Customer update(Customer customer, int id);
      public void deleteById(int id);
}
