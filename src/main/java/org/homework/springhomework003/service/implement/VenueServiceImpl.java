package org.homework.springhomework003.service.implement;

import org.homework.springhomework003.exception.NotFoundException;
import org.homework.springhomework003.exception.WrongInputException;
import org.homework.springhomework003.model.entity.Venue;
import org.homework.springhomework003.model.dto.request.VenueRequest;
import org.homework.springhomework003.repository.VenueRepository;
import org.homework.springhomework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public Venue getVenueById(Integer id) {
        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new NotFoundException("Venue id [" + id + "] not found");
        }
        return venue;
    }

    @Override
    public Venue updateVenueById(VenueRequest venueRequest, Integer id) {
        Venue existingVenue = venueRepository.getVenueById(id);
        if (existingVenue == null) {
            throw new NotFoundException("Venue id [" + id + "] not found");
        }
        return venueRepository.updateVenueById(id, venueRequest);
    }

    @Override
    public Venue deleteVenueById(Integer id) {
        Venue existingVenue = venueRepository.getVenueById(id);
        if (existingVenue == null) {
            throw new NotFoundException("Venue id [" + id + "] not found");
        }
        return venueRepository.deleteVenueById(id);
    }

    @Override
    public List<Venue> getAllVenue(Integer page, Integer size) {
        if (page < 1 || size < 1) {
            throw new WrongInputException("Page and size should be greater than 0");
        }
        return venueRepository.getAllVenues(page, size);
    }

    @Override
    public Venue addVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }
}
