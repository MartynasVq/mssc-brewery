package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

    @Override
    public BeerDto create(BeerDto beerDto) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Dog")
                .beerStyle("Ale")
                .build();
    }

    @Override
    public void update(UUID beerId, BeerDto beerDto) {
        //todo implement update
    }

    @Override
    public void deleteById(UUID beerId) {
        //todo implement delete
    }
}
