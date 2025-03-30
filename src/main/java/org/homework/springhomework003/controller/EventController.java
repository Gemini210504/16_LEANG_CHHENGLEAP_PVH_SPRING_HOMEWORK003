package org.homework.springhomework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.homework.springhomework003.model.dto.request.EventRequest;
import org.homework.springhomework003.model.dto.response.ApiResponse;
import org.homework.springhomework003.model.entity.Event;
import org.homework.springhomework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@Valid @PathVariable("event-id") Integer id) {
        Event event = eventService.getEventById(id);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get event by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(event)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById(@RequestBody @Valid EventRequest eventRequest,  @PathVariable("event-id") Integer id) {
        Event updatedEvent = eventService.updateEventById(eventRequest, id);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Update event by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(updatedEvent)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(@Valid @PathVariable("event-id") Integer id) {
        Event deletedEvent = eventService.deleteEventById(id);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Delete event by Id [" + id + "] success")
                .status(HttpStatus.OK)
                .payload(deletedEvent)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer size) {
        List<Event> events = eventService.getAllEvent(page, size);
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("Get all events success")
                .status(HttpStatus.OK)
                .payload(events)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> addEvent(@Valid @RequestBody EventRequest eventRequest) {
        Event newEvent = eventService.addEvent(eventRequest);
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Event added successfully")
                .status(HttpStatus.CREATED)
                .payload(newEvent)
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
