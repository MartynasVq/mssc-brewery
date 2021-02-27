package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import guru.springframework.msscbrewery.web.model.v2.BeerStyle;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class BeerServiceImplV2 implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.LAGER)
                .build();
    }

    @Override
    public BeerDtoV2 create(BeerDtoV2 beerDto) {
        return BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName("Galaxy Dog")
                .beerStyle(BeerStyle.IPA)
                .build();
    }

    @Override
    public void update(UUID beerId, BeerDtoV2 beerDto) {
//todo implement update
    }

    @Override
    public void deleteById(UUID beerId) {
//todo implement update
    }
}
