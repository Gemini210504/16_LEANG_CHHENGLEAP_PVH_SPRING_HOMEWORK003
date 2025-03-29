package org.homework.springhomework003.service.implement;

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
        return venueRepository.getVenueById(id);
    }

    @Override
    public Venue updateVenueById(VenueRequest venueRequest, Integer id) {
        return venueRepository.updateVenueById(id, venueRequest);
    }

    @Override
    public Venue deleteVenueById(Integer id) {
        return venueRepository.deleteVenueById(id);
    }

    @Override
    public List<Venue> getAllVenue(Integer page, Integer size) {
        return venueRepository.getAllVenues(page, size);
    }


    @Override
    public Venue addVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }
}
