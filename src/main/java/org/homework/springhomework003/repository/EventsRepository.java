package org.homework.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.homework.springhomework003.model.entity.Event;

import java.util.List;

@Mapper
public interface EventsRepository {
    @Select ("""
            Select * FROM events
            """)
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.homework.springhomework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "org.homework.springhomework003.repository.AttendeeRepository.selectAttendeeByTableCenter"))



    })
    List<Event> getAllEvents(Integer page, Integer size);


}

