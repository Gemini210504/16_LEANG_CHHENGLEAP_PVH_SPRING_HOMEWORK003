package org.homework.springhomework003.service;

import org.homework.springhomework003.model.entity.Event;
import org.homework.springhomework003.model.dto.request.EventRequest;

import java.util.List;

public interface EventService {
    Event getEventById(Integer id);
    Event updateEventById(EventRequest eventRequest, Integer id);
    Event deleteEventById(Integer id);
    List<Event> getAllEvent(Integer page, Integer size);
    Event addEvent(EventRequest eventRequest);
}
