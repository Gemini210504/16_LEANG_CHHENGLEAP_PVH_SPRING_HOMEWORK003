package org.homework.springhomework003.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.entity.Venue;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private String eventDate;
    private Venue venueId;
    private Attendee attendee;
}
