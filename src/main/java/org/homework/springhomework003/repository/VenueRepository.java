package org.homework.springhomework003.repository;


import org.apache.ibatis.annotations.*;
import org.homework.springhomework003.model.entity.Venue;
import org.homework.springhomework003.model.dto.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Select("SELECT * FROM venues WHERE venue_id = #{id}")
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    public Venue getVenueById(@Param("id") Integer id);

    @Select("UPDATE venues SET " +
            "venue_name = #{request.venueName}, " +
            "location = #{request.location} " +
            "WHERE venue_id = #{id} RETURNING *")
    @ResultMap("venueMapper")
    public Venue updateVenueById(@Param("id") Integer id, @Param("request") VenueRequest venueRequest);

    @Select("DELETE FROM venues WHERE venue_id = #{id} RETURNING *")
    @ResultMap("venueMapper")
    public Venue deleteVenueById(@Param("id") Integer id);

    @Select("SELECT * FROM venues OFFSET (#{page} - 1) * #{size} LIMIT #{size}")
    @ResultMap("venueMapper")
    List<Venue> getAllVenues(@Param("page") Integer page, @Param("size") Integer size);


    @Select("INSERT INTO venues (venue_name, location) VALUES (#{request.venueName}, #{request.location}) RETURNING *")
    @ResultMap("venueMapper")
    public Venue insertVenue(@Param("request") VenueRequest venueRequest);



}
