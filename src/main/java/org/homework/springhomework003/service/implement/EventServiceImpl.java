package org.homework.springhomework003.service.implement;

import org.homework.springhomework003.exception.NotFoundException;
import org.homework.springhomework003.exception.WrongInputException;
import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.entity.Event;
import org.homework.springhomework003.model.dto.request.EventRequest;
import org.homework.springhomework003.repository.EventsRepository;
import org.homework.springhomework003.repository.AttendeeRepository;
import org.homework.springhomework003.repository.VenueRepository; // Add Venue repository
import org.homework.springhomework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventsRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final VenueRepository venueRepository;

    public EventServiceImpl(EventsRepository eventRepository, AttendeeRepository attendeeRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public Event getEventById(Integer id) {
        Event event = eventRepository.getEventById(id);
        if (id <= 0) {
            throw new WrongInputException("Event ID must be greater than 0");
        }
        if (event == null) {
            throw new NotFoundException("Event id [" + id + "] not found");
        }

        return event;
    }

    @Override
    public Event updateEventById(EventRequest eventRequest, Integer id) {
        if (id <= 0) {
            throw new WrongInputException("Event ID must be greater than 0");
        }
        if (venueRepository.getVenueById(eventRequest.getVenueId()) == null) {
            throw new NotFoundException("Venue id [" + eventRequest.getVenueId() + "] not found");
        }


        if (eventRequest.getAttendeeId() != null) {
            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                if (attendeeRepository.getAttendeeById(attendeeId) == null) {
                    throw new NotFoundException("Attendee id [" + attendeeId + "] not found");
                }
            }
        }

        return eventRepository.updateEventById(eventRequest, id);
    }

    @Override
    public Event deleteEventById(Integer id) {
        Event event = eventRepository.getEventById(id);
        if (id <= 0) {
            throw new WrongInputException("Event ID must be greater than 0");
        }
        if (event == null) {
            throw new NotFoundException("Event id [" + id + "] not found");
        }

        return eventRepository.deleteEventById(id);
    }

    @Override
    public List<Event> getAllEvent(Integer page, Integer size) {
        if (page < 1 || size < 1) {
            throw new WrongInputException("Page and size should be greater than 0");
        }
        List<Event> events = eventRepository.getAllEvents(page, size);
        if (events == null || events.isEmpty()) {
            throw new NotFoundException("No event found");
        }
        return events;
    }

    @Override
    public Event addEvent(EventRequest eventRequest) {
        if (venueRepository.getVenueById(eventRequest.getVenueId()) == null) {
            throw new NotFoundException("Venue id [" + eventRequest.getVenueId() + "] not found");
        }

        Event event = eventRepository.addEvent(eventRequest);

        if (eventRequest.getAttendeeId() != null && !eventRequest.getAttendeeId().isEmpty()) {
            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                if (attendeeRepository.getAttendeeById(attendeeId) == null) {
                    throw new NotFoundException("Attendee id [" + attendeeId + "] not found");
                }
                attendeeRepository.addAttendees(event.getEventId(), attendeeId);
            }
        }
        return event;
    }
}
