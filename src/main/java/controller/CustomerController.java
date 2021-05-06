package controller;

import dto.ResponseMessenger;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.ICustomerService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Customer> customers = (List<Customer>) customerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (!customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createCustomer(@Validated @RequestBody Customer customer) {
        if(customer.getFirstName().equals("")){
            return new ResponseEntity<>(new ResponseMessenger("the firstname is requited!!!"),HttpStatus.OK);
        }
        if(customer.getLastName().equals("")){
            return new ResponseEntity<>(new ResponseMessenger("the lastname is requited!!!"),HttpStatus.OK);
        }
        customerService.save(customer);
        return new ResponseEntity<>(customer,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCustomer(@PathVariable Long id, @Validated @RequestBody Customer customer) {
        Optional<Customer> editCustomer=customerService.findById(id);
        if(!editCustomer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(customer.getFirstName().equals("")){
            return new ResponseEntity<>(new ResponseMessenger("the firstname is requited!!!"),HttpStatus.OK);
        }
        if(customer.getLastName().equals("")){
            return new ResponseEntity<>(new ResponseMessenger("the lastname is requited!!!"),HttpStatus.OK);
        }
        customer.setId(editCustomer.get().getId());
        customerService.save(customer);
        return new ResponseEntity<>(new ResponseMessenger("edit susscefully"),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Customer> deleteCustomer=customerService.findById(id);
        if(!deleteCustomer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessenger("delete susscefully"),HttpStatus.OK);
    }


}
