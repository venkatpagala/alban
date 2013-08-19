// license-header java merge-point
/* Autogenerated by AndroMDA EntityCompositePK.vsl in andromda-ejb3-cartridge - do not edit */
package org.andromda.test.howto4.b;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Composite primary key class for Person entity bean.
 * TODO: Model Documentation for org.andromda.test.howto4.b.Person
 */
@Embeddable
public class PersonPK
    implements Serializable, Comparable<PersonPK>
{
    private static final long serialVersionUID = 1L;
    private long id;
    private String tag;

    /**
     * Get the id property.
     * TODO: Model Documentation for org.andromda.test.howto4.b.Person.id
     * @return long The value of id
     * owning=$property.otherEnd.owning optional=$property.optional $property.otherEnd.fulyQualifiedName many2One=$property.many2One target=$property.otherEnd.many2One
     */
    @Column(name="ID", nullable=false)
    public long getId()
    {
        return this.id;
    }

    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(long value)
    {
        this.id = value;
    }

    /**
     * Get the tag property.
     * TODO: Model Documentation for org.andromda.test.howto4.b.Person.tag
     * @return String The value of tag
     * owning=$property.otherEnd.owning optional=$property.optional $property.otherEnd.fulyQualifiedName many2One=$property.many2One target=$property.otherEnd.many2One
     */
    @Column(name="TAG", nullable=false)
    public String getTag()
    {
        return this.tag;
    }

    /**
     * Set the tag property.
     * @param value the new value
     */
    public void setTag(String value)
    {
        this.tag = value;
    }

    /**
     * Default empty constructor
     */
    public PersonPK()
    {
        // default empty constructor
    }

    /**
     * Implementation for the main constructor with all POJO identifier properties.
     *
     * @param id Value for the id property true
     * @param tag Value for the tag property false
     */
    public PersonPK(long id, String tag)
    {
        setId(id);
        setTag(tag);
    }

    /**
     * Returns a hash code value for the object
     * @return int The hash code value
     * @see Object#hashCode
     */
    @Override
    public int hashCode()
    {
        int result = 0;
        result = 37*result + (int)this.id;
        result = 37*result + ((this.tag != null) ? this.tag.hashCode() : 0);
        return result;
    }

    /**
     * Indicates if the argument is of the same type and all values are equal.
     * @param object The target object to compare with
     * @return boolean True if both objects a 'equal'
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object)
    {
        if (object == null)
        {
            return false;
        }
        if (object == this)
        {
            return true;
        }
        if (!(object instanceof PersonPK))
        {
            return false;
        }
        PersonPK pk = (PersonPK)object;
        return
             pk.id == this.id
            && pk.tag.equals(this.tag);
    }

    /**
     * @see Comparable#compareTo
     */
    public int compareTo(PersonPK o)
    {
        int cmp = 0;
        if (this.getTag() != null)
        {
            cmp = (cmp != 0 ? cmp : this.getTag().compareTo(o.getTag()));
        }
        return cmp;
    }

    /**
     * Returns a String representation of the composite key object
     * @return String Textual representation of the object displaying name/value pairs for all properties
     * @see Object#toString
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("PersonPK(");
        sb.append(" id=").append(getId());
        sb.append(" tag=").append(getTag());
        sb.append(")");
        return sb.toString();
    }
}