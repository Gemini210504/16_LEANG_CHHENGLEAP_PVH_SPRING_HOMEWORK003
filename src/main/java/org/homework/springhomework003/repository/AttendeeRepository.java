package org.homework.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.homework.springhomework003.model.entity.Attendee;
import org.homework.springhomework003.model.dto.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("""
            SELECT * FROM attendees WHERE attendee_id = #{id}
           """)
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    Attendee getAttendeeById(@Param("id") Integer id);


    @Select("""
            DELETE FROM attendees WHERE attendee_id = #{id}
            RETURNING *
           """)
    @ResultMap("attendeeMapper")
    Attendee deleteAttendeeById(@Param("id") Integer id);

    @Select("""
            UPDATE attendees SET 
                attendee_name = #{request.attendeeName},
                email = #{request.email}
            WHERE attendee_id = #{id}
            RETURNING *
           """)
    @ResultMap("attendeeMapper")
    Attendee updateAttendeeById(@Param("request") AttendeeRequest attendeeRequest, @Param("id") Integer id);


    @Select("""
            SELECT * FROM attendees
            OFFSET (#{page} - 1) * #{size} LIMIT #{size}
           """)
    @ResultMap("attendeeMapper")
    List<Attendee> getAllAttendee(@Param("page") Integer page, @Param("size") Integer size);


    @Select("""
            INSERT INTO attendees (attendee_name,email)
                VALUES (#{request.attendeeName}, #{request.email})
            RETURNING *
            """)
    @ResultMap("attendeeMapper")
    Attendee insertAttendee(@Param("request") AttendeeRequest attendeeRequest);

    @Select("SELECT COUNT(*) FROM attendees WHERE email = #{email}")
    int countByEmail(@Param("email") String email);

    @Select("""
            SELECT a.attendee_id, a.attendee_name, a.email
            FROM attendees a
            INNER JOIN event_attendee ea ON ea.attendee_id = a.attendee_id
            WHERE ea.event_id = #{id};
           """)
    @ResultMap("attendeeMapper")
    List<Attendee> findAttendeesByEventId(@Param("id") Integer id);

    @Select("""
            INSERT INTO event_attendee (event_id, attendee_id)
            VALUES (#{eventId}, #{attendeeId})
            RETURNING *
            """)
    @ResultMap("attendeeMapper")
    void addAttendees(@Param("eventId") Integer eventId, @Param("attendeeId") Integer attendeeId);
}
