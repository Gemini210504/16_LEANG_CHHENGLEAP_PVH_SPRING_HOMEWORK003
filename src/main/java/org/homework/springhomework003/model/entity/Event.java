package org.homework.springhomework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private int eventId;
    private String eventName;
    private String eventDate;
    private Venue venue;
    private List<Attendee> attendees;
}
