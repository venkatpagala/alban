// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto2.d;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * TODO: Model Documentation for org.andromda.test.howto2.d.Car
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
public abstract class CarEmbeddable implements Serializable {
    private static final long serialVersionUID = -6793461983486533447L;

    // ----------- 3 Attribute Definitions ------------
    private String serial;
    private String name;
    private String type;

    // --------- 1 Relationship Definitions -----------
    private Person owner;

    // ---- Manageable Display Attributes (Transient) -----

    // -------- 3 Attribute Accessors ----------
    /**
     * TODO: Model Documentation for org.andromda.test.howto2.d.Car.serial
     * Get the serial property.
     * @return String The value of serial
     */
    @Id
    @Column(name="SERIAL", unique=true, nullable=false, insertable=true, updatable=true)
    @NotNull(message="serial is required")
    public String getSerial()
    {
        return this.serial;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto2.d.Car.serial
     * Set the serial property.
     * @param value the new value
     */
    public void setSerial(String value)
    {
        this.serial = value;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto2.d.Car.name
     * Get the name property.
     * @return String The value of name
     */
    @Column(name="NAME", unique=true, nullable=false, insertable=true, updatable=true)
    @NotNull(message="name is required")
    public String getName()
    {
        return this.name;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto2.d.Car.name
     * Set the name property.
     * @param value the new value
     */
    public void setName(String value)
    {
        this.name = value;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto2.d.Car.type
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
     * TODO: Model Documentation for org.andromda.test.howto2.d.Car.type
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
    @JoinColumn(name="OWNER")
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
     * @param serial String value for the serial property true  1
     * @param name String value for the name property true  1
     * @param type String value for the type property true  1
     */
    public CarEmbeddable(String serial, String name, String type)
    {
        setSerial(serial);
        setName(name);
        setType(type);
    }


    /**
     * Constructor with all Entity attribute values and CMR relations.
     *
     * @param serial String value for the serial property
     * @param name String value for the name property
     * @param type String value for the type property
     * @param owner Person value for the owner relation
     */
    public CarEmbeddable(String serial, String name, String type, Person owner)
    {
        // 3 updatableAttributes
        setSerial(serial);
        setName(name);
        setType(type);

        // 1 relations
        setOwner(owner);
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
        if (this.getSerial() == null || that.getSerial() == null || !this.getSerial().equals(that.getSerial()))
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
        hashCode = 29 * hashCode + (getSerial() == null ? 0 : getSerial().hashCode());

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
        sb.append(" serial=").append(getSerial());
        sb.append(" name=").append(getName());
        sb.append(" type=").append(getType());
        sb.append(" owner=").append(getOwner());
        sb.append(")");
        return sb.toString();
    }


}