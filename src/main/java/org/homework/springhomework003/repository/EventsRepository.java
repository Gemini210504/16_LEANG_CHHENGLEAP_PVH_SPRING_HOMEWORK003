package org.homework.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.homework.springhomework003.model.dto.request.EventRequest;
import org.homework.springhomework003.model.entity.Event;

import java.util.List;

@Mapper
public interface EventsRepository {

    @Select("""
            SELECT * FROM events
            LIMIT #{size} OFFSET (#{page} - 1) * #{size} 
            """)
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.homework.springhomework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "org.homework.springhomework003.repository.AttendeeRepository.findAttendeesByEventId"))
    })
    List<Event> getAllEvents(@Param("page") Integer page, @Param("size") Integer size);

    @Select("""
            SELECT * FROM events WHERE event_id = #{id}
           """)
    @ResultMap("eventMapper")
    Event getEventById(@Param("id") Integer id);

    @Select("""
            UPDATE events SET
                event_name = #{request.eventName},
                event_date = #{request.eventDate},
                venue_id = #{request.venueId}
            WHERE event_id = #{id}
            RETURNING *
           """)
    @ResultMap("eventMapper")
    Event updateEventById(@Param("request") EventRequest eventRequest, @Param("id") Integer id);

    @Select("""
            DELETE FROM events WHERE event_id = #{id}
            RETURNING *
           """)
    @ResultMap("eventMapper")
    Event deleteEventById(Integer id);

    @Select("""
            INSERT INTO events (event_name, event_date, venue_id)
            VALUES (#{request.eventName}, #{request.eventDate}, #{request.venueId})
            RETURNING *
            """)
    @ResultMap("eventMapper")
    Event addEvent(@Param("request") EventRequest eventRequest);





}
