package com.example.api.web.rest;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Customer> findAll(Pageable pageable) {
        return (Page<Customer>) service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    @PostMapping(path = "/save")
    public Customer Save(@RequestBody @Valid Customer customer) throws Exception {

        List<Address> addresses1 = customer.getAddresses();
        List<Address> addresses2 = new ArrayList<Address>();

        for (Address address1 : addresses1) {
            String url = "https://viacep.com.br/ws/" + address1.getCep() + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            Address address2 = restTemplate.getForObject(url, Address.class);
            address2.setNumero(address1.getNumero());
            address2.setComplemento(address1.getComplemento());
            address2.setCustomer(customer);
            addresses2.add(address2);
            System.out.println(address1);
        }
        customer.setAddresses(addresses2);
        return service.save(customer);
    }

    @PutMapping(path = "/update")
    public Customer Update( @RequestBody @Valid Customer customer) throws Exception {

        List<Address> addresses1 = customer.getAddresses();
        List<Address> addresses2 = new ArrayList<Address>();

        for (Address address1 : addresses1) {
            String url = "https://viacep.com.br/ws/" + address1.getCep() + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            Address address2 = restTemplate.getForObject(url, Address.class);
            address2.setNumero(address1.getNumero());
            address2.setComplemento(address1.getComplemento());
            address2.setCustomer(customer);
            addresses2.add(address2);
            System.out.println(address1);
        }
        customer.setAddresses(addresses2);
        return service.save(customer);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void Delete(@PathVariable Long id) {
        service.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
