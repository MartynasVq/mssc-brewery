package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//Method validation @Validated on the class and then validator on the method parameter

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerService;


    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBeer(@Valid @RequestBody BeerDtoV2 beerDto) {

        BeerDtoV2 saved = beerService.create(beerDto);
        HttpHeaders http = new HttpHeaders();
        http.add("Location", "http://localhost:8080/api/v1/beer/" + saved.getId().toString());
        return new ResponseEntity(http, HttpStatus.CREATED);

    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity putHandler(@PathVariable UUID beerId, @Valid @RequestBody BeerDtoV2 beerDto) {

        beerService.update(beerId, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)  // alternative to returning an entity with status
    public void deleteHandler(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
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
