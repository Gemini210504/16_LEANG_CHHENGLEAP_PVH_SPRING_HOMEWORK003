package org.homework.springhomework003.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Attendee's name cannot be blank")
    private String attendeeName;

    @NotBlank(message = "Attendee's email cannot be blank")
    @Email(message = "Email wrong format, please Input again !!!")
    private String email;
}
