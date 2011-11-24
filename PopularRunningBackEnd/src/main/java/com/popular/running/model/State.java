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
 * State Entity
 * @author scastros
 */
@Entity
@Table( name = "STATE")
public class State implements Serializable
{
    /**
     * Serialization id
     */
    private static final long serialVersionUID = 1L;

    /**
     * The State's id attribute
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    @Basic(optional = false)
    @NotNull
    private long id;

    @Column( name = "DESCRIPTION" )
    @NotNull
    @Basic(optional = false)
    @Size(min=1, max=255)
    private String description;

    public State()
    {
    }

    /**
     * Constructor
     * 
     * @param description
     */
    public State( String description )
    {
        this.description = description;
    }

    /**
     * 
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * 
     * @param description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * 
     * @return id
     */
    @OneToMany(mappedBy = "City", cascade = CascadeType.ALL)
    public long getId()
    {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId( long id )
    {
        this.id = id;
    }
}