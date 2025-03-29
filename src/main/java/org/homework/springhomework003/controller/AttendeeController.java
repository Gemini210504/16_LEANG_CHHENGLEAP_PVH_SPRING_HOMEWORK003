package org.homework.springhomework003.controller;

import org.homework.springhomework003.exception.NotFoundException;
import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.dto.request.AttendeeRequest;
import org.homework.springhomework003.model.dto.response.ApiResponse;
import org.homework.springhomework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;

    }

    @GetMapping("/{attendee-id}")
    ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Integer id){
        Attendee attendee = attendeeService.getAttendeeById(id);
        if(attendee == null){
            throw new NotFoundException("Attendee id "+id+" not found");
        }
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Get attendee by Id ["+id+"] success")
                .status(HttpStatus.OK)
                .payload(attendeeService.getAttendeeById(id))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{attendee-id}")
    ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@RequestBody AttendeeRequest attendeeRequest, @PathVariable("attendee-id") Integer id){
        Attendee attendee = attendeeService.getAttendeeById(id);
        if(attendee == null){
            throw new NotFoundException("Attendee id "+id+" not found");
        }
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Update attendee by Id["+id+"] success")
                .status(HttpStatus.OK)
                .payload(attendeeService.updateAttendeeById(attendeeRequest, id))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{attendee-id}")
    ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(@PathVariable("attendee-id") Integer id){
        Attendee attendee = attendeeService.getAttendeeById(id);
        if(attendee == null){
            throw new NotFoundException("Attendee id "+id+" not found");
        }
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Delete attendee by Id["+id+"] success")
                .status(HttpStatus.OK)
                .payload(attendeeService.deleteAttendeeById(id))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendee(Integer page, Integer size){
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .message("Get all attendee success")
                .status(HttpStatus.OK)
                .payload(attendeeService.getAllAttendee(page, size))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Attendee>> addAttendee(@RequestBody AttendeeRequest attendeeRequest){
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Add attendee success")
                .status(HttpStatus.CREATED)
                .payload(attendeeService.addAttendee(attendeeRequest))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
