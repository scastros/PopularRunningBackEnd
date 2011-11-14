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
@Table( name = "DISTANCE")
public class Distance implements Serializable
{
    /**
     * Serialization id
     */
    private static final long serialVersionUID = 1L;

    /**
     * The Distance's id attribute
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private long id;

    @Column( name = "METERS" )
    private long meters;

    @Column( name = "DESCRIPTION" )
    private String description;

    public Distance()
    {
    }

    public Distance( long meters,
                     String description )
    {
        this.meters = meters;
        this.description = description;
    }

    public long getMeters()
    {
        return meters;
    }

    public void setMeters( long meters )
    {
        this.meters = meters;
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
