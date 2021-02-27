package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBeer(@RequestBody BeerDto beerDto) {

        BeerDto saved = beerService.create(beerDto);
        HttpHeaders http = new HttpHeaders();
        http.add("Location", "http://localhost:8080/api/v1/beer/" + saved.getId().toString());
        return new ResponseEntity(http, HttpStatus.CREATED);

    }

    @PutMapping({"/{beerId}"})
    public ResponseEntity putHandler(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {

        beerService.update(beerId, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)  // alternative to returning an entity with status
    public void deleteHandler(@PathVariable UUID beerId) {
        beerService.deleteById(beerId);
    }
}
