package com.popular.running.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Basic(optional = false)
    @NotNull
    private long id;

    @Column( name = "METERS" )
    @Basic(optional = false)
    @NotNull
    private long meters;

    @Column( name = "DESCRIPTION" )
    @NotNull
    @Basic(optional = false)
    @Size(min=1, max=512)
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

    @OneToMany(mappedBy = "RunningEvent", cascade = CascadeType.ALL)
    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }
}
