package org.homework.springhomework003.service;

import org.homework.springhomework003.model.entity.Venue;
import org.homework.springhomework003.model.dto.request.VenueRequest;

import java.util.List;

public interface VenueService {
    Venue getVenueById(Integer id);
    Venue updateVenueById(VenueRequest venueRequest, Integer id);
    Venue deleteVenueById(Integer id);
    List<Venue> getAllVenue(Integer page, Integer size);
    Venue addVenue(VenueRequest venueRequest);
}
