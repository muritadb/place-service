package com.murita.place_service.domain;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.github.slugify.Slugify;
import com.murita.place_service.api.PlaceRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlaceService {
  private PlaceRepository placeRepository;
  private Slugify slg;

  public PlaceService(PlaceRepository placeRepository) {
    this.placeRepository = placeRepository;
    this.slg = Slugify.builder().build();
  }

  public Mono<Place> create(PlaceRequest placeRequest) {
    var place = new Place(null, placeRequest.name(),
        slg.slugify(placeRequest.name()), placeRequest.state(),
        null, null);
    return placeRepository.save(place);
  }

  public Flux<Place> getAllPlaces() {
    return placeRepository.findAll();
  }

}
