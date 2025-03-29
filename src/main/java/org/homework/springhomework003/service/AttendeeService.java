package org.homework.springhomework003.service;

import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.dto.request.AttendeeRequest;

import java.util.List;


public interface AttendeeService {
    Attendee getAttendeeById(Integer id);
    Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer id);
    Attendee deleteAttendeeById(Integer id);
    List<Attendee> getAllAttendee(Integer page, Integer size);
    Attendee addAttendee(AttendeeRequest attendeeRequest);


}
