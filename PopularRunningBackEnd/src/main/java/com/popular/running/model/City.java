package com.popular.running.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author scastros
 */
@Entity
@Table( name = "CITY" )
public class City implements Serializable
{
    /**
     * Serialization id
     */
    private static final long serialVersionUID = 1L;

    /**
     * The City's id attribute
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private long id;

    @Column( name = "STATE" )
    private long state;

    @Column( name = "DESCRIPTION" )
    private String description;

    public City()
    {
    }

    public City( long state,
                 String description )
    {
        this.state = state;
        this.description = description;
    }

    public long getState()
    {
        return state;
    }

    public void setState( long state )
    {
        this.state = state;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }
}
