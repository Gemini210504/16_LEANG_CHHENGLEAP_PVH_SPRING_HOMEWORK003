package org.homework.springhomework003.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.homework.springhomework003.model.entity.Venue;
import org.homework.springhomework003.model.dto.request.VenueRequest;
import org.homework.springhomework003.model.dto.response.ApiResponse;
import org.homework.springhomework003.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@Valid @PathVariable("venue-id") Integer id) {
        Venue venue = venueService.getVenueById(id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Get venue by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(venue)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@Valid @RequestBody VenueRequest venueRequest,
                                                              @PathVariable("venue-id") Integer id) {
        Venue updatedVenue = venueService.updateVenueById(venueRequest, id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Update venue by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(updatedVenue)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(@Valid @PathVariable("venue-id") Integer id) {
        Venue deletedVenue = venueService.deleteVenueById(id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Delete venue by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(deletedVenue)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer size) {
        List<Venue> venues = venueService.getAllVenue(page, size);
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .message("Get all venues success")
                .status(HttpStatus.OK)
                .payload(venues)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> addVenue(@Valid @RequestBody VenueRequest venueRequest) {
        Venue newVenue = venueService.addVenue(venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Add venue success")
                .status(HttpStatus.CREATED)
                .payload(newVenue)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
