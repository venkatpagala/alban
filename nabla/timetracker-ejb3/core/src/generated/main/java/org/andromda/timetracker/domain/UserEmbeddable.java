// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by EntityEmbeddable.vsl in andromda-ejb3-cartridge on 04/16/2012 03:32:45.
//
package org.andromda.timetracker.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;

/**
 * Autogenerated POJO EJB mapped super class for User containing the
 * bulk of the entity implementation.
 *
 * This is a mapped super class and autogenerated by AndroMDA using the EJB3
 * cartridge.
 *
 * DO NOT MODIFY this class.
 *
 * 
 */
@MappedSuperclass
public abstract class UserEmbeddable implements Serializable
{
    private static final long serialVersionUID = -6177852626226267723L;

    // ----------- 9 Attribute Definitions ------------
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private Date creationDate;
    private String comment;
    private Long id;

    // --------- 1 Relationship Definitions -----------
    private Set<UserRole> roles = new TreeSet<UserRole>();

    // ---- Manageable Display Attributes (Transient) -----

    // -------- 9 Attribute Accessors ----------
    /**
     * Get the username property.
     * 
     * @return String The value of username
     */
    @Column(name = "USERNAME", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
    @NotNull(message = "You should enter a value.")
    @NotEmpty(message = "You should enter a value.")
    @Length(min = 5, max = 50)
    @Pattern(regexp = "^\\w*$", message = "not a valid field")
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Set the username property.
     * @param value the new value
     */
    public void setUsername(String value)
    {
        this.username = value;
    }

    /**
     * Get the password property.
     * 
     * @return String The value of password
     */
    @Column(name = "PASSWORD", nullable = false, insertable = true, updatable = true)
    @Length(min = 5)
    @NotNull(message = "You should enter a value.")
    @NotEmpty(message = "You should enter a value.")
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Set the password property.
     * @param value the new value
     */
    public void setPassword(String value)
    {
        this.password = value;
    }

    /**
     * Get the firstName property.
     * 
     * @return String The value of firstName
     */
    @Column(name = "FIRST_NAME", nullable = false, insertable = true, updatable = true)
    @NotNull(message = "You should enter a value.")
    @NotEmpty(message = "You should enter a value.")
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * Set the firstName property.
     * @param value the new value
     */
    public void setFirstName(String value)
    {
        this.firstName = value;
    }

    /**
     * Get the lastName property.
     * 
     * @return String The value of lastName
     */
    @Column(name = "LAST_NAME", nullable = false, insertable = true, updatable = true)
    @NotNull(message = "You should enter a value.")
    @NotEmpty(message = "You should enter a value.")
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * Set the lastName property.
     * @param value the new value
     */
    public void setLastName(String value)
    {
        this.lastName = value;
    }

    /**
     * Get the email property.
     * 
     * @return String The value of email
     */
    @Column(name = "EMAIL", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
    @NotNull(message = "You should enter a value.")
    @NotEmpty(message = "You should enter a value.")
    @Length(max = 50)
    @Email(message = "invalid e-mail invalid")
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Set the email property.
     * @param value the new value
     */
    public void setEmail(String value)
    {
        this.email = value;
    }

    /**
     * Get the isActive property.
     * 
     * @return boolean The value of isActive
     */
    @Column(name = "IS_ACTIVE", nullable = false, insertable = true, updatable = true)
    public boolean isIsActive()
    {
        return this.isActive;
    }

    /**
     * Set the isActive property.
     * @param value the new value
     */
    public void setIsActive(boolean value)
    {
        this.isActive = value;
    }

    /**
     * Get the creationDate property.
     * 
     * @return Date The value of creationDate
     */
    @Column(name = "CREATION_DATE", nullable = false, insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate()
    {
        return this.creationDate;
    }

    /**
     * Set the creationDate property.
     * @param value the new value
     */
    public void setCreationDate(Date value)
    {
        this.creationDate = value;
    }

    /**
     * Get the comment property.
     * 
     * @return String The value of comment
     */
    @Column(name = "COMMENT", insertable = true, updatable = true)
    public String getComment()
    {
        return this.comment;
    }

    /**
     * Set the comment property.
     * @param value the new value
     */
    public void setComment(String value)
    {
        this.comment = value;
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


    // ------------- 1 Relations ------------------
    /**
     * Get the roles Collection
     *
     * @return Set<UserRole>
     */
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinTable
    (
        name = "USER2ROLES",
        joinColumns = {@JoinColumn(name = "USER_ID_FK", referencedColumnName = "ID")},
        inverseJoinColumns = {@JoinColumn(name = "ROLES_ID_FK", referencedColumnName = "ID")}
    )
    public Set<UserRole> getRoles()
    {
        return this.roles;
    }

    /**
     * Set the roles
     *
     * @param rolesIn
     */
    public void setRoles (Set<UserRole> rolesIn)
    {
        this.roles = rolesIn;
    }

    // --------------- Constructors -----------------

    /**
     * Default empty constructor
     */
    public UserEmbeddable()
    {
        // Default empty constructor
    }

    /**
     * Implementation for the constructor with all POJO attributes except auto incremented identifiers.
     * This method sets all POJO fields defined in this class to the values provided by
     * the parameters.
     *
     * @param username Value for the username property
     * @param password Value for the password property
     * @param firstName Value for the firstName property
     * @param lastName Value for the lastName property
     * @param email Value for the email property
     * @param isActive Value for the isActive property
     * @param creationDate Value for the creationDate property
     * @param comment Value for the comment property
     */
    public UserEmbeddable(String username, String password, String firstName, String lastName, String email, boolean isActive, Date creationDate, String comment)
    {
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setIsActive(isActive);
        setCreationDate(creationDate);
        setComment(comment);
    }

    /**
     * Constructor with all POJO attribute values and CMR relations.
     *
     * @param username Value for the username property
     * @param password Value for the password property
     * @param firstName Value for the firstName property
     * @param lastName Value for the lastName property
     * @param email Value for the email property
     * @param isActive Value for the isActive property
     * @param creationDate Value for the creationDate property
     * @param comment Value for the comment property
     * @param roles Value for the roles relation
     */
    public UserEmbeddable(String username, String password, String firstName, String lastName, String email, boolean isActive, Date creationDate, String comment, Set<UserRole> roles)
    {
        setUsername(username);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setIsActive(isActive);
        setCreationDate(creationDate);
        setComment(comment);

        setRoles(roles);
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
        if (!(object instanceof UserEmbeddable))
        {
            return false;
        }
        final UserEmbeddable that = (UserEmbeddable)object;
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
        sb.append("UserEmbeddable(=");
        sb.append("username: ");
        sb.append(getUsername());
        sb.append(", password: ");
        sb.append(getPassword());
        sb.append(", firstName: ");
        sb.append(getFirstName());
        sb.append(", lastName: ");
        sb.append(getLastName());
        sb.append(", email: ");
        sb.append(getEmail());
        sb.append(", isActive: ");
        sb.append(isIsActive());
        sb.append(", creationDate: ");
        sb.append(getCreationDate());
        sb.append(", comment: ");
        sb.append(getComment());
        sb.append(", id: ");
        sb.append(getId());
        sb.append(")");
        return sb.toString();
    }

}