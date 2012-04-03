// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoLocal.vsl overrided in andromda-ejb3-cartridge on 04/03/2012 12:18:27.
//
package org.andromda.timetracker.domain;

import java.util.Collection;
import java.util.Date;
import org.andromda.timetracker.vo.UserDetailsVO;
import org.andromda.timetracker.vo.UserVO;

/**
 * @see User
 */
public interface UserDao
{
    /**
     * This constant is used as a transformation flag; entities can be converted automatically into value objects
     * or other types, different methods in a class implementing this interface support this feature: look for
     * an <code>int</code> parameter called <code>transform</code>.
     * <p/>
     * This specific flag denotes no transformation will occur.
     */
    public static final int TRANSFORM_NONE = 0;

    /**
     * This constant is used as a transformation flag; entities can be converted automatically into value objects
     * or other types, different methods in a class implementing this interface support this feature: look for
     * an <code>int</code> parameter called <code>transform</code>.
     * <p/>
     * This specific flag denotes entities must be transformed into objects of type
     * {@link UserVO}.
     */
    public static final int TRANSFORM_USERVO = 1;

    /**
     * Copies the fields of the specified entity to the target value object.  This method is similar to
     * toUserVO(), but it does not handle any attributes in the target
     * value object that are "read-only" (as those do not have setter methods exposed).
     * @param sourceEntity
     * @param targetVO
     */
    public void toUserVO(
        User sourceEntity,
        UserVO targetVO);

    /**
     * Converts this DAO's entity to an object of type {@link UserVO}.
     * @param entity
     * @return UserVO
     */
    public UserVO toUserVO(User entity);

    /**
     * Converts this DAO's entity to a Collection of instances of type {@link UserVO}.
     * @param entities
     */
    public void toUserVOCollection(Collection entities);

    /**
     * Copies the fields of {@link UserVO} to the specified entity.
     * @param sourceVO
     * @param targetEntity
     * @param copyIfNull If FALSE, the value object's field will not be copied to the entity if the value is NULL. If TRUE,
     *        it will be copied regardless of its value.
     */
    public void userVOToEntity(
        UserVO sourceVO,
        User targetEntity,
        boolean copyIfNull);

    /**
     * Converts an instance of type {@link UserVO} to this DAO's entity.
     * @param userVO
     * @return User
     */
    public User userVOToEntity(UserVO userVO);

    /**
     * Converts a Collection of instances of type {@link UserVO} to this
     * DAO's entity.
     * @param instances
     */
    public void userVOToEntityCollection(Collection instances);

    /**
     * This constant is used as a transformation flag; entities can be converted automatically into value objects
     * or other types, different methods in a class implementing this interface support this feature: look for
     * an <code>int</code> parameter called <code>transform</code>.
     * <p/>
     * This specific flag denotes entities must be transformed into objects of type
     * {@link UserDetailsVO}.
     */
    public static final int TRANSFORM_USERDETAILSVO = 2;

    /**
     * Copies the fields of the specified entity to the target value object.  This method is similar to
     * toUserDetailsVO(), but it does not handle any attributes in the target
     * value object that are "read-only" (as those do not have setter methods exposed).
     * @param sourceEntity
     * @param targetVO
     */
    public void toUserDetailsVO(
        User sourceEntity,
        UserDetailsVO targetVO);

    /**
     * Converts this DAO's entity to an object of type {@link UserDetailsVO}.
     * @param entity
     * @return UserDetailsVO
     */
    public UserDetailsVO toUserDetailsVO(User entity);

    /**
     * Converts this DAO's entity to a Collection of instances of type {@link UserDetailsVO}.
     * @param entities
     */
    public void toUserDetailsVOCollection(Collection entities);

    /**
     * Copies the fields of {@link UserDetailsVO} to the specified entity.
     * @param sourceVO
     * @param targetEntity
     * @param copyIfNull If FALSE, the value object's field will not be copied to the entity if the value is NULL. If TRUE,
     *        it will be copied regardless of its value.
     */
    public void userDetailsVOToEntity(
        UserDetailsVO sourceVO,
        User targetEntity,
        boolean copyIfNull);

    /**
     * Converts an instance of type {@link UserDetailsVO} to this DAO's entity.
     * @param userDetailsVO
     * @return User
     */
    public User userDetailsVOToEntity(UserDetailsVO userDetailsVO);

    /**
     * Converts a Collection of instances of type {@link UserDetailsVO} to this
     * DAO's entity.
     * @param instances
     */
    public void userDetailsVOToEntityCollection(Collection instances);

    /**
     * Loads an instance of User from the persistent store.
     * @param id the identifier of the entity to load.
     * @throws UserDaoException
     */
    public User load(Long id)
        throws UserDaoException;

    /**
     *   Does the same thing as {@link #load(Long)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined in this class then the result <strong>WILL BE</strong> passed through an operation which can
     *   optionally transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param id the identifier of the entity to load.
     * @return either the entity or the object transformed from the entity.
     * @throws UserDaoException
     */
    public Object load(int transform, Long id)
        throws UserDaoException;

    /**
     * Loads all entities of type {@link User}.
     *
     * @return the loaded entities.
     * @throws UserDaoException
     */
    public Collection<User> loadAll()
        throws UserDaoException;

    /**
     *   Does the same thing as {@link #loadAll()} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform the flag indicating what transformation to use.
     * @return Collection of the loaded entities.
     * @throws UserDaoException
     */
    public Collection loadAll(final int transform)
        throws UserDaoException;

    /**
     * Creates an instance of User and adds it to the persistent store.
     * @param user
     * @return User
     * @throws UserDaoException
     */
    public User create(User user)
        throws UserDaoException;

    /**
     *   Does the same thing as {@link #create(User)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param user
     * @return Object
     * @throws UserDaoException
     */
    public Object create(int transform, User user)
        throws UserDaoException;

    /**
     * Creates a new instance of User and adds
     * from the passed in <code>entities</code> collection
     *
     * @param entities the collection of User
     *        instances to create.
     * @return the created instances. Collection<User>
     * @throws UserDaoException
     */
    public Collection<User> create(Collection<User> entities)
        throws UserDaoException;

    /**
     *   Does the same thing as {@link #create(User)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entities (into value objects for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param entities
     * @return the created instances. Collection
     * @throws UserDaoException
     */
    public Collection create(int transform, Collection<User> entities)
        throws UserDaoException;

    /**
     *   Creates a new <code>User</code>
     *   instance from <strong>all</strong> attributes and adds it to
     *   the persistent store.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param isActive
     * @param creationDate
     * @param comment
     * @return the created instances. User
     * @throws UserDaoException
     */
    public User create(
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        boolean isActive,
        Date creationDate,
        String comment)
        throws UserDaoException;

    /**
     *   Does the same thing as {@link #create(String, String, String, String, String, boolean, Date, String)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param isActive
     * @param creationDate
     * @param comment
     * @return the created instances. Object
     * @throws UserDaoException
     */
    public Object create(
        int transform,
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        boolean isActive,
        Date creationDate,
        String comment)
        throws UserDaoException;


    /**
     * Updates the <code>user</code> instance in the persistent store.
     * @param user
     * @throws UserDaoException
     */
    public void update(User user)
        throws UserDaoException;

    /**
     * Updates all instances in the <code>entities</code> collection in the persistent store.
     * @param entities
     * @throws UserDaoException
     */
    public void update(Collection<User> entities)
        throws UserDaoException;

    /**
     * Removes the instance of User from the persistent store.
     * @param user
     * @throws UserDaoException
     */
    public void remove(User user)
        throws UserDaoException;

    /**
     * Removes the instance of User having the given
     * <code>identifier</code> from the persistent store.
     * @param id
     * @throws UserDaoException
     */
    public void remove(Long id)
        throws UserDaoException;

    /**
     * Removes all entities in the given <code>entities<code> collection.
     * @param entities
     * @throws UserDaoException
     */
    public void remove(Collection<User> entities)
        throws UserDaoException;

    /**
     * 
     * @param username
     * @return User
     */
    public User getUserDetails(String username)     
        throws UserDaoException;
    
}