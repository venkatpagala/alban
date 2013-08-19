// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by EntityEmbeddable.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto9.b;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * TODO: Model Documentation for org.andromda.test.howto9.b.Person
 *
 * Autogenerated POJO EJB class for Person containing the
 * bulk of the entity implementation.
 *
 * This is autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 */
@Entity
@Table(name="PERSON")
@NamedQueries
({
    @NamedQuery(name="Person.findAll", query="from Person as person"), 
    @NamedQuery(name="Person.findByName", query="from Person as person where person.name = :name and"), 
    @NamedQuery(name="Person.findByNameOrBirthDate", query="where person.name = :name or person.birthDate = :birthDate")
})
public class Person implements Serializable, Comparable<Person> {
    private static final long serialVersionUID = -7369490585991748846L;

    // ----------- 3 Attribute Definitions ------------
    private String name;
    private Date birthDate;
    private String serial;

    // --------- 1 Relationship Definitions -----------
    private Set<Car> cars = new HashSet<Car>();

    // ---- Manageable Display Attributes (Transient) -----

    // -------- 3 Attribute Accessors ----------
    /**
     * TODO: Model Documentation for org.andromda.test.howto9.b.Person.name
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
     * TODO: Model Documentation for org.andromda.test.howto9.b.Person.name
     * Set the name property.
     * @param value the new value
     */
    public void setName(String value)
    {
        this.name = value;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto9.b.Person.birthDate
     * Get the birthDate property.
     * @return Date The value of birthDate
     */
    @Column(name="BIRTH_DATE", unique=true, nullable=false, insertable=true, updatable=true)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="birthDate is required")
    public Date getBirthDate()
    {
        return this.birthDate;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto9.b.Person.birthDate
     * Set the birthDate property.
     * @param value the new value
     */
    public void setBirthDate(Date value)
    {
        this.birthDate = value;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto9.b.Person.serial
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
     * TODO: Model Documentation for org.andromda.test.howto9.b.Person.serial
     * Set the serial property.
     * @param value the new value
     */
    public void setSerial(String value)
    {
        this.serial = value;
    }


    // ------------- 1 Relations ------------------
    /**
     * TODO: Model Documentation for Car
     * Get the cars Collection
     * @return Set<Car>
     */
    @OneToMany(mappedBy="owner")
    public Set<Car> getCars()
    {
        return this.cars;
    }

    /**
     * TODO: Model Documentation for Car
     * Set the cars
     * @param carsIn
     */
    public void setCars (Set<Car> carsIn)
    {
        this.cars = carsIn;
    }

    // --------------- Constructors -----------------

    /**
     * Default empty no-arg constructor
     */
    public Person()
    {
        // Default empty constructor
    }

    /**
     * Constructor with all updatable Entity attributes except auto incremented identifiers.
     *
     * @param name String value for the name property true  1
     * @param birthDate Date value for the birthDate property true  1
     * @param serial String value for the serial property true  1
     */
    public Person(String name, Date birthDate, String serial)
    {
        setName(name);
        setBirthDate(birthDate);
        setSerial(serial);
    }


    /**
     * Constructor with all Entity attribute values and CMR relations.
     *
     * @param name String value for the name property
     * @param birthDate Date value for the birthDate property
     * @param serial String value for the serial property
     * @param cars Set<Car> value for the cars relation
     */
    public Person(String name, Date birthDate, String serial, Set<Car> cars)
    {
        // 3 updatableAttributes
        setName(name);
        setBirthDate(birthDate);
        setSerial(serial);

        // 1 relations
        setCars(cars);
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
        if (!(object instanceof Person))
        {
            return false;
        }
        final Person that = (Person)object;
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
        sb.append("Person(");
        sb.append(" name=").append(getName());
        sb.append(" birthDate=").append(getBirthDate());
        sb.append(" serial=").append(getSerial());
        sb.append(")");
        return sb.toString();
    }

    /**
     * @see Comparable#compareTo
     */
            @Override
    public int compareTo(Person o)
    {
        int cmp = 0;
        if (this.getSerial() != null)
        {
            cmp = this.getSerial().compareTo(o.getSerial());
        }
        else
        {
            if (this.getName() != null)
            {
                cmp = (cmp != 0 ? cmp : this.getName().compareTo(o.getName()));
            }
            if (this.getBirthDate() != null)
            {
                cmp = (cmp != 0 ? cmp : this.getBirthDate().compareTo(o.getBirthDate()));
            }
        }
        return cmp;
    }


}