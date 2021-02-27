package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerById(UUID id);

    CustomerDto create(CustomerDto customerDto);

    void delete(UUID id);

    void update(UUID id, CustomerDto customerDto);
}
