// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto4.a;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * TODO: Model Documentation for org.andromda.test.howto4.a.Car
 *
 * Autogenerated POJO EJB mapped super class for Car containing the
 * bulk of the entity implementation.
 *
 * This is a mapped super class and autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 */
@MappedSuperclass
public abstract class CarEmbeddable implements Serializable{
    private static final long serialVersionUID = -6793461983486533447L;
    private CarPK pk;

    // ----------- 3 Attribute Definitions ------------
    private String type;

    // --------- 1 Relationship Definitions -----------
    private Person owner;

    // ---- Manageable Display Attributes (Transient) -----

    // ----- Composite PK Accessors ------
    /**
     * Return the composite primary key for Car
     * @return The composite primary key
     */
    @EmbeddedId
    @JoinColumns({
        @JoinColumn(name="SERIAL", nullable=false),
        @JoinColumn(name="NAME", nullable=false)
    })
    public CarPK getPk()
    {
        return this.pk;
    }

    /**
     * Set the composite primary key for Car
     * @param pkIn The composite primary key
     */
    public void setPk(CarPK pkIn)
    {
        this.pk = pkIn;
    }

    // -------- 3 Attribute Accessors ----------
    /**
     * TODO: Model Documentation for org.andromda.test.howto4.a.Car.type
     * Get the type property.
     * @return String The value of type
     */
    @Column(name="TYPE", unique=true, nullable=false, insertable=true, updatable=true)
    @NotNull(message="type is required")
    public String getType()
    {
        return this.type;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto4.a.Car.type
     * Set the type property.
     * @param value the new value
     */
    public void setType(String value)
    {
        this.type = value;
    }


    // ------------- 1 Relations ------------------
    /**
     * TODO: Model Documentation for Person
     * Get the owner
     * @return Person
     */
    @ManyToOne(optional=false)
    @JoinColumn(name="OWNER", insertable=true, updatable=true)
    public Person getOwner()
    {
        return this.owner;
    }

    /**
     * TODO: Model Documentation for Person
     * Set the owner
     * @param ownerIn
     */
    public void setOwner(Person ownerIn)
    {
        this.owner = ownerIn;
    }

    // --------------- Constructors -----------------

    /**
     * Default empty no-arg constructor
     */
    public CarEmbeddable()
    {
        // Default empty constructor
    }

    /**
     * Constructor with all updatable Entity attributes except auto incremented identifiers.
     *
     * @param pk CarPK The composite primary key class
     * @param type String value for the type property true  1
     */
    public CarEmbeddable(CarPK pk, String type)
    {
        setPk(pk);
        setType(type);
    }


    /**
     * Constructor with all Entity attribute values and CMR relations.
     *
     * @param pk CarPK The composite primary key class
     * @param type String value for the type property
     * @param owner Person value for the owner relation
     */
    public CarEmbeddable(CarPK pk, String type, Person owner)
    {
        setPk(pk);
        // 1 updatableAttributes
        setType(type);

        // 1 relations
        setOwner(owner);
    }

    /**
     * Constructor with composite primary key arg only.
     *
     * @param pkIn CarPK Composite primary key
     */
    public CarEmbeddable(CarPK pkIn)
    {
        this.pk = pkIn;
    }

    // -------- Common Methods -----------
    /**
     * Indicates if the argument is of the same type and all values are equal.
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object)
    {
        if (null == object)
        {
            return false;
        }
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof CarEmbeddable))
        {
            return false;
        }
        final CarEmbeddable that = (CarEmbeddable)object;
        if (this.getPk().getSerial() == null || that.getPk().getSerial() == null || !this.getPk().getSerial().equals(that.getPk().getSerial()))
        {
            return false;
        }
        if (this.getPk().getName() == null || that.getPk().getName() == null || !this.getPk().getName().equals(that.getPk().getName()))
        {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code value for the object
     * @return int The hash code value
     * @see Object#hashCode
     */
    @Override
    public int hashCode()
    {
        int hashCode = 0;
        hashCode = 29 * hashCode + (getPk().getSerial() == null ? 0 : getPk().getSerial().hashCode());
        hashCode = 29 * hashCode + (getPk().getName() == null ? 0 : getPk().getName().hashCode());

        return hashCode;
    }

    /**
     * Returns a String representation of the object
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     * @see Object#toString
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CarEmbeddable(");
        sb.append(this.pk);
        sb.append(" type=").append(getType());
        sb.append(" owner=").append(getOwner());
        sb.append(")");
        return sb.toString();
    }


}