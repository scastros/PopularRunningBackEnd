package com.popular.running.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author scastros
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table( name = "CITY")
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
    @Basic(optional = false)
    @NotNull
    private long id;
    
    @NotNull
    @JoinColumn(name = "STATE", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private State state;

    @Column( name = "DESCRIPTION" )
    @NotNull
    @Basic(optional = false)
    @Size(min=1, max=255)
    private String description;

    public City()
    {
    }

    /**
     * Constructor
     * @param state
     * @param description
     */
    public City( State state,
                 String description )
    {
        this.state = state;
        this.description = description;
    }

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="STATE")
    public State getState()
    {
        return state;
    }

    public void setState( State state )
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
