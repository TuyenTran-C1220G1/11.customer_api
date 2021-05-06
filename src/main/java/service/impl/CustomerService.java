package service.impl;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ICustomerRepository;
import service.ICustomerService;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }


    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
