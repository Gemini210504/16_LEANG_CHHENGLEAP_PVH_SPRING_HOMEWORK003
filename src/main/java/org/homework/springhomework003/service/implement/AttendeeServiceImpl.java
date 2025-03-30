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
        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }
        return attendee;
    }

    @Override
    public Attendee updateAttendeeById(AttendeeRequest attendeeRequest, Integer id) {
        Attendee existingAttendee = attendeeRepository.getAttendeeById(id);
        if (existingAttendee == null) {
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }

        return attendeeRepository.updateAttendeeById(attendeeRequest, id);
    }

    @Override
    public Attendee deleteAttendeeById(Integer id) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new NotFoundException("Attendee with ID " + id + " not found");
        }
        attendeeRepository.deleteAttendeeById(id);
        return attendee;
    }

    @Override
    public List<Attendee> getAllAttendee(Integer page, Integer size) {
        return attendeeRepository.getAllAttendee(page, size);
    }

    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        if (attendeeRequest.getEmail() == null || attendeeRequest.getEmail().trim().equals("")) {
            throw new WrongInputException("Email is required");
        }

        if (attendeeRequest.getAttendeeName()== null || attendeeRequest.getAttendeeName().trim().equals("")) {
            throw new WrongInputException("Name is required");
        }
        String trimmedEmail = attendeeRequest.getEmail().trim();
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!trimmedEmail.matches(emailRegex)) {
            throw new WrongInputException("Email is in wrong format");
        }

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
