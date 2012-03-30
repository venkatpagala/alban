// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by EntityEmbeddable.vsl in andromda-ejb3-cartridge on 03/30/2012 03:34:19.
//
package org.andromda.timetracker.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Autogenerated POJO EJB class for UserRole containing the
 * bulk of the entity implementation.
 *
 * This is autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 *
 * 
 */
@Entity
@Table(name = "USER_ROLE")
@NamedQuery(name = "UserRole.findAll", query = "select userRole from UserRole AS userRole")
public class UserRole
    implements Serializable, Comparable<UserRole>
{
    private static final long serialVersionUID = 7844086459073233566L;

    // ----------- 2 Attribute Definitions ------------
    private Role role;
    private Long id;

    // -------- 2 Attribute Accessors ----------
    /**
     * Get the role property.
     * 
     * @return Role The value of role
     */
    @Column(name = "ROLE", nullable = false, insertable = true, updatable = true, columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    public Role getRole()
    {
        return this.role;
    }

    /**
     * Set the role property.
     * @param value the new value
     */
    public void setRole(Role value)
    {
        this.role = value;
    }

    /**
     * Get the id property.
     * 
     * @return Long The value of id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true)
    public Long getId()
    {
        return this.id;
    }

    /**
     * Set the id property.
     * @param value the new value
     */
    public void setId(Long value)
    {
        this.id = value;
    }


    // ------------- 0 Relations ------------------
    // --------------- Constructors -----------------

    /**
     * Default empty constructor
     */
    public UserRole()
    {
        // Default empty constructor
    }

    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param role Value for the role property
     */
    public UserRole(Role role)
    {
        setRole(role);
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
        if (!(object instanceof UserRole))
        {
            return false;
        }
        final UserRole that = (UserRole)object;
        if (this.getId() == null || that.getId() == null || !this.getId().equals(that.getId()))
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
        hashCode = 29 * hashCode + (getId() == null ? 0 : getId().hashCode());

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
        sb.append("UserRole(=");
        sb.append("role: ");
        sb.append(getRole());
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }

    /**
     * @see Comparable#compareTo(T)
     */
    public int compareTo(UserRole o)
    {
        int cmp = 0;
        if (this.getId() != null)
        {
            cmp = this.getId().compareTo(o.getId());
        }
        else
        {
            if (this.getRole() != null)
            {
                cmp = (cmp != 0 ? cmp : this.getRole().compareTo(o.getRole()));
            }
        }
        return cmp;
    }
}