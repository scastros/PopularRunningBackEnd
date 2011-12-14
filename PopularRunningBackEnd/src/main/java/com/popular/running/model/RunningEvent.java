package com.popular.running.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.wink.common.annotations.Asset;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author scastros
 */
@Asset
@Entity
@Table( name = "RUNNINGEVENT")
@SequenceGenerator(name="running_event_id", sequenceName="RUNNINGEVENT_SQ", allocationSize=1)
public class RunningEvent implements Serializable
{
    /**
     * Serialization id
     */
    private static final long serialVersionUID = 1L;

    /**
     * The RunningEvent's id attribute
     */
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "running_event_id" )
    @Column( name = "ID" )
    private long id;
    
    
    @Column( name = "DATE" )
    private long date;

    @Length(max=512)
    @Column( name = "SHORTNAME" )
    private String shortName;
    
    @Length(max=512)
    @Column( name = "PICTURE" )
    private String picture;
    
    @Column( name = "DISTANCE" )
    private long distance;
    
    @Column( name = "LOCATION" )
    private long location;
    
    @Length(max=2000)
    @Column( name = "DESCRIPTION" )
    private String description;
    
    @Length(max=512)
    @Column( name = "ENROLLMENT" )
    private String enrollment;
    
    @Length(max=512)
    @Column( name = "MAP" )
    private String map;
    
    @Length(max=512)
    @Column( name = "ELEVATION" )
    private String elevation;

	public RunningEvent(long date, String shortName, String picture,
			long distance, long location, String description,
			String enrollment, String map, String elevation) {
		super();
		this.date = date;
		this.shortName = shortName;
		this.picture = picture;
		this.distance = distance;
		this.location = location;
		this.description = description;
		this.enrollment = enrollment;
		this.map = map;
		this.elevation = elevation;
	}

	public RunningEvent() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
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

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public long getLocation() {
		return location;
	}

	public void setLocation(long location) {
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
		Long lId = new Long(id);
		result = prime * result + ((lId == null) ? 0 : lId.hashCode());
		return result;
	}
}
