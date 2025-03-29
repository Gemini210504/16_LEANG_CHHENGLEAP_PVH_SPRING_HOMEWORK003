package org.homework.springhomework003.service.implement;

import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.dto.request.AttendeeRequest;
import org.homework.springhomework003.repository.AttendeeRepository;
import org.homework.springhomework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public Attendee getAttendeeById(Integer id) {
        return attendeeRepository.getAttendeeById(id);
    }

    @Override
    public Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer id) {
        return attendeeRepository.updateAttendeeById(attendeeRequest,id);
    }

    @Override
    public Attendee deleteAttendeeById(Integer id) {
        return attendeeRepository.deleteAttendeeById(id);
    }

    @Override
    public List<Attendee> getAllAttendee(Integer page, Integer size) {
        return attendeeRepository.getAllAttendee(page, size);
    }

    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }
}
