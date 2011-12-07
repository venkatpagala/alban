// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto7.a;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Autogenerated POJO EJB mapped super class for Car containing the
 * bulk of the entity implementation.
 *
 * This is a mapped super class and autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 *
 * 
 *
 */
@MappedSuperclass
public abstract class CarEmbeddable
    implements Serializable
{

    private static final long serialVersionUID = -7616604201737209315L;

    // ----------- Attribute Definitions ------------

    private String serial;
    private String name;
    private CarType type;


    // --------- Relationship Definitions -----------

    private Person owner;

    // ---- Manageable Display Attributes (Transient) -----


    // --------------- Constructors -----------------

    /**
     * Default empty constructor
     */
    public CarEmbeddable() {}

    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param serial Value for the serial property
     * @param name Value for the name property
     * @param type Value for the type property
     */
    public CarEmbeddable(String serial, String name, CarType type)
    {
        setSerial(serial);
        setName(name);
        setType(type);
    }

    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param serial Value for the serial property
     * @param name Value for the name property
     * @param type Value for the type property
     * @param owner Value for the owner relation
     */
    public CarEmbeddable(String serial, String name, CarType type, Person owner)
    {
        setSerial(serial);
        setName(name);
        setType(type);

        setOwner(owner);
    }


    // -------- Attribute Accessors ----------

    /**
     * Get the serial property.
     * 
     * @return String The value of serial
     */
    @Id
    @Column(name = "SERIAL", unique = true, nullable = false, insertable = true, updatable = true)
    public String getSerial()
    {
        return serial;
    }

    /**
     * Set the serial property.
     * @param value the new value
     */
    public void setSerial(String value)
    {
        this.serial = value;
    }

    /**
     * Get the name property.
     * 
     * @return String The value of name
     */
    @Column(name = "NAME", unique = true, nullable = false, insertable = true, updatable = true)
    public String getName()
    {
        return name;
    }

    /**
     * Set the name property.
     * @param value the new value
     */
    public void setName(String value)
    {
        this.name = value;
    }

    /**
     * Get the type property.
     * 
     * @return CarType The value of type
     */
    @Column(name = "TYPE", unique = true, nullable = false, insertable = true, updatable = true, columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    public CarType getType()
    {
        return type;
    }

    /**
     * Set the type property.
     * @param value the new value
     */
    public void setType(CarType value)
    {
        this.type = value;
    }


    // ------------- Relations ------------------

    /**
     * Get the owner
     *
     * @return Person
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "OWNER_FK")
    public Person getOwner()
    {
        return this.owner;
    }

   /**
    * Set the owner
    *
    * @param owner
    */
    public void setOwner(Person owner)
    {
        this.owner = owner;
    }

    // -------- Common Methods -----------

    /**
     * Indicates if the argument is of the same type and all values are equal.
     *
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     */
    public boolean equals(Object object)
    {
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
     *
     * @return int The hash code value
     */
    public int hashCode()
    {
        int hashCode = 0;
        hashCode = 29 * hashCode + (getSerial() == null ? 0 : getSerial().hashCode());

        return hashCode;
    }

    /**
     * Returns a String representation of the object
     *
     * @return String Textual representation of the object displaying name/value pairs for all attributes
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("CarEmbeddable(=");
        sb.append("serial: ");
        sb.append(getSerial());
        sb.append(", name: ");
        sb.append(getName());
        sb.append(", type: ");
        sb.append(getType());
        sb.append(")");
        return sb.toString();
    }

}