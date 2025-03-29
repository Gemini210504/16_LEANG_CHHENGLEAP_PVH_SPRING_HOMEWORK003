package org.homework.springhomework003.controller;


import org.homework.springhomework003.exception.NotFoundException;
import org.homework.springhomework003.model.entity.Event;
import org.homework.springhomework003.model.dto.request.EventRequest;
import org.homework.springhomework003.model.dto.response.ApiResponse;
import org.homework.springhomework003.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/{event-id}")
    ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") Integer id){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Get event by Id success")
                .status(HttpStatus.OK)
//                .payload()
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{event-id}")
    ResponseEntity<ApiResponse<Event>> updateEventById(@RequestBody EventRequest eventRequest, @PathVariable("event-id") Integer id){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Update event by Id success")
                .status(HttpStatus.OK)
//                .payload()
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{event-id}")
    ResponseEntity<ApiResponse<Event>> deleteEventById(@PathVariable("event-id") Integer id){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Delete event by Id success")
                .status(HttpStatus.OK)
//                .payload()
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<Event>>> getAllEvent(Integer page, Integer size){
        List<Event> events = eventService.getAllEvent(page, size);

        if (events == null || events.isEmpty()) {
            throw new NotFoundException("Don't have any event");
        }

        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .message("Get all event success")
                .status(HttpStatus.OK)
                .payload(eventService.getAllEvent(page, size))
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    ResponseEntity<ApiResponse<Event>> addEvent(@RequestBody EventRequest eventRequest){
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .message("Add event success")
                .status(HttpStatus.CREATED)
//                .payload()
                .success(true)
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
