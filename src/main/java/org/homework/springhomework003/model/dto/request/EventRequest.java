package org.homework.springhomework003.model.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Event name cannot be blank")
    private String eventName;

    private LocalDateTime eventDate;

    @NotNull(message = "Venue ID is required")
    private Integer venueId;

    @NotNull(message = "Attendee ID list cannot be null")
    @NotEmpty(message = "Attendee ID list cannot be empty")
    private List<Integer> attendeeId;
}
