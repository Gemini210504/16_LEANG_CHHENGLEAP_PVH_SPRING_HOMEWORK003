package org.homework.springhomework003.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.entity.Venue;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private LocalDateTime eventDate;
    private Integer venueId;
    private List<Integer> attendeeId;
}
