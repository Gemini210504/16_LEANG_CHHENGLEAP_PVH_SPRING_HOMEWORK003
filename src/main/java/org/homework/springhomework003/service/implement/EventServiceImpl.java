package org.homework.springhomework003.service.implement;

import org.homework.springhomework003.model.entity.Event;
import org.homework.springhomework003.model.dto.request.EventRequest;
import org.homework.springhomework003.repository.EventsRepository;
import org.homework.springhomework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventsRepository eventRepository;

    public EventServiceImpl(EventsRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public Event getEventById(Integer id) {
        return null;
    }

    @Override
    public Event updateEventById(EventRequest eventRequest, Integer id) {
        return null;
    }

    @Override
    public Event deleteEventById(Integer id) {
        return null;
    }

    @Override
    public List<Event> getAllEvent(Integer page, Integer size) {
        return eventRepository.getAllEvents(page, size);
    }

    @Override
    public Event addEvent(EventRequest eventRequest) {
        return null;
    }
}
