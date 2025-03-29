package org.homework.springhomework003.controller;

import org.homework.springhomework003.exception.NotFoundException;
import org.homework.springhomework003.model.entity.Attendee;
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
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/{venue-id}")
    ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Integer id){
        Venue venue = venueService.getVenueById(id);
        if(venue == null){
            throw new NotFoundException("Venue id ["+id+"] not found");
        }
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Get venue by Id ["+id+"] success")
                .status(HttpStatus.OK)
                .payload(venueService.getVenueById(id))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{venue-id}")
    ResponseEntity<ApiResponse<Venue>> updateVenueById(@RequestBody VenueRequest venueRequest, @PathVariable("venue-id") Integer id){
        Venue venue = venueService.getVenueById(id);
        if(venue == null){
            throw new NotFoundException("Venue id ["+id+"] not found");
        }
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Update venue by Id success")
                .status(HttpStatus.OK)
                .payload(venueService.updateVenueById(venueRequest, id))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{venue-id}")
    ResponseEntity<ApiResponse<Venue>> deleteVenueById(@PathVariable("venue-id") Integer id){
        Venue venue = venueService.getVenueById(id);
        if(venue == null){
            throw new NotFoundException("Venue id "+id+" not found");
        }
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Delete venue by Id success")
                .status(HttpStatus.OK)
                .payload(venueService.deleteVenueById(id))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<Venue>>> getAllVenue(Integer page, Integer size){

        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .message("Get all venue success")
                .status(HttpStatus.OK)
                .payload(venueService.getAllVenue(page,size))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Venue>> addVenue(@RequestBody VenueRequest venueRequest){
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("Add event success")
                .status(HttpStatus.CREATED)
                .payload(venueService.addVenue(venueRequest))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
