package org.homework.springhomework003.service.implement;

import org.apache.ibatis.jdbc.Null;
import org.homework.springhomework003.exception.NotFoundException;
import org.homework.springhomework003.exception.WrongInputException;
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
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (id <= 0) {
            throw new WrongInputException("Attendee ID must be greater than 0");
        }
        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }

        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer id) {
        Attendee existingAttendee = attendeeRepository.getAttendeeById(id);
        if (id <= 0) {
            throw new WrongInputException("Attendee ID must be greater than 0");
        }
        if (existingAttendee == null) {
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }


        return attendeeRepository.updateAttendeeById(attendeeRequest, id);
    }

    @Override
    public Attendee deleteAttendeeById(Integer id) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (id <= 0) {
            throw new WrongInputException("Attendee ID must be greater than 0");
        }
        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }

        attendeeRepository.deleteAttendeeById(id);
        return attendee;
    }

    @Override
    public List<Attendee> getAllAttendee(Integer page, Integer size) {
        if (page < 1 || size < 1) {
            throw new WrongInputException("Page and size should be greater than 0");
        }
        List<Attendee> attendees = attendeeRepository.getAllAttendee(page, size);
        if (attendees == null || attendees.isEmpty()) {
            throw new NotFoundException("No attendees found");
        }

        return attendees;
    }


    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        String trimmedEmail = attendeeRequest.getEmail().trim();
        int existingEmailCount = attendeeRepository.countByEmail(trimmedEmail);
        if (existingEmailCount > 0) {
            throw new WrongInputException("Email is already in use");
        }
        Attendee attendee = new Attendee();
        attendee.setAttendeeName(attendeeRequest.getAttendeeName());
        attendee.setEmail(trimmedEmail);
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

}
