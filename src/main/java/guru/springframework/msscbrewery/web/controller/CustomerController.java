package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("{id}")
    private ResponseEntity<CustomerDto> getCustomerByUUID(@PathVariable UUID id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity createCustomer(@Valid @RequestBody CustomerDto customerDto) {

        CustomerDto customer = customerService.create(customerDto);
        HttpHeaders http = new HttpHeaders();
        http.add("Location", "http://127.0.0.1:8080/api/v1/customer/" + customer.getId().toString());

        return new ResponseEntity(http, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable UUID id) {
        customerService.delete(id);
    }

    @PutMapping("{id}")
    public ResponseEntity updateCustomer(@PathVariable UUID id, @Valid @RequestBody CustomerDto customerDto) {

        customerService.update(id, customerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();

        e.getConstraintViolations().forEach(v -> {
            errors.add(v.getPropertyPath() + " " + v.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
