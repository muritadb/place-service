package com.murita.place_service.web;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murita.place_service.api.PlaceRequest;
import com.murita.place_service.api.PlaceResponse;
import com.murita.place_service.domain.Place;
import com.murita.place_service.domain.PlaceService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {

  private PlaceService placeService;

  public PlaceController(PlaceService placeService) {
    this.placeService = placeService;
  }

  @PostMapping
  public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request) {
    var placeResponse = placeService.create(request).map(PlaceMapper::fromPlaceToResponse);

    return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
  }

  @GetMapping
  public ResponseEntity<Flux<Place>> getAllPlaces() {
    Flux<Place> places = placeService.getAllPlaces();

    return ResponseEntity.ok(places);
  }
}
