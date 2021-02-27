package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder().id(UUID.randomUUID()).name("Peter").build();
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        return CustomerDto.builder().id(UUID.randomUUID()).name("John").build();
    }

    @Override
    public void delete(UUID id) {
        //todo to implement
    }

    @Override
    public void update(UUID id, CustomerDto customerDto) {
        //todo to implement
    }
}
