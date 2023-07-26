package com.example.api.service;


import com.example.api.domain.Address;
import com.example.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private AddressRepository repository;
    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> findAll() {
        return (List<Address>) repository.findAll();
    }
    public Address save(Address address) {
        return repository.save(address);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
