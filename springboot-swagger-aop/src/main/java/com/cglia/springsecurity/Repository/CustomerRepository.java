package com.cglia.springsecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cglia.springsecurity.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
   // thats it no coding required
}
