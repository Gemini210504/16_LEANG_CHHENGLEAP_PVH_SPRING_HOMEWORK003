package org.homework.springhomework003.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.dto.request.AttendeeRequest;
import org.homework.springhomework003.model.dto.response.ApiResponse;
import org.homework.springhomework003.service.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Integer id) {
        Attendee attendee = attendeeService.getAttendeeById(id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Get attendee by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(attendee)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@Valid @RequestBody AttendeeRequest attendeeRequest,
                                                                    @PathVariable("attendee-id") Integer id) {
        Attendee updatedAttendee = attendeeService.updateAttendeeById(attendeeRequest, id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Update attendee by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(updatedAttendee)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Void>> deleteAttendeeById(@PathVariable("attendee-id") Integer id) {
        attendeeService.deleteAttendeeById(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Delete attendee by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size) {
        List<Attendee> attendees = attendeeService.getAllAttendee(page, size);
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .message("Get all attendees success")
                .status(HttpStatus.OK)
                .payload(attendees)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> addAttendee(@Valid @RequestBody AttendeeRequest attendeeRequest) {
        Attendee newAttendee = attendeeService.addAttendee(attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("Attendee added successfully")
                .status(HttpStatus.CREATED)
                .payload(newAttendee)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
