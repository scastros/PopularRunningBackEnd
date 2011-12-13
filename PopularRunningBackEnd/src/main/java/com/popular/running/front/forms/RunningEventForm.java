package com.popular.running.front.forms;

import java.io.Serializable;
import java.util.Date;

import org.apache.wink.common.annotations.Asset;
import org.hibernate.validator.constraints.Length;

import com.popular.running.model.City;
import com.popular.running.model.Distance;
import com.popular.running.model.State;

/**
 *
 * @author scastros
 */
@Asset
public class RunningEventForm implements Serializable
{
    /**
     * Serialization id
     */
    private static final long serialVersionUID = 1L;

    private Date date;

    @Length(max=512)
    private String shortName;
    
    @Length(max=512)
    private String picture;
    
    private Distance distance;
    
    private City location;
    
    private State state;    
    
    @Length(max=2000)
    private String description;
    
    @Length(max=512)
    private String enrollment;
    
    @Length(max=512)
    private String map;
    
    @Length(max=512)
    private String elevation;

	public RunningEventForm(Date date, String shortName, String picture,
			Distance distance, State state, City location, String description,
			String enrollment, String map, String elevation) {
		super();
		this.date = date;
		this.shortName = shortName;
		this.picture = picture;
		this.distance = distance;
		this.state = state;
		this.location = location;
		this.description = description;
		this.enrollment = enrollment;
		this.map = map;
		this.elevation = elevation;
	}

	public RunningEventForm() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public City getLocation() {
		return location;
	}

	public void setLocation(City location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RunningEvent [")
			   .append("date=").append(date)
			   .append(", shortName=").append(shortName)
			   .append(", picture=").append(picture)
			   .append(", distance=").append(distance)
			   .append(", state=").append(state)
			   .append(", location=").append(location)
			   .append(", description=").append(description)
			   .append(", enrollment=").append(enrollment)
			   .append(", map=").append(map)
			   .append(", elevation=").append(elevation)				
			   .append("]");
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Date lId = date;
		result = prime * result + ((lId == null) ? 0 : lId.hashCode());
		return result;
	}
}
