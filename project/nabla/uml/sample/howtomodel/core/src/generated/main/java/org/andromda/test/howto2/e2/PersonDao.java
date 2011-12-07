// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: SessionDaoLocal.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto2.e2;

import java.util.Collection;
import java.util.Date;

/**
 * @see Person
 */
public interface PersonDao
{
    /**
     * This constant is used as a transformation flag; entities can be converted automatically into value objects
     * or other types, different methods in a class implementing this interface support this feature: look for
     * an <code>int</code> parameter called <code>transform</code>.
     * <p/>
     * This specific flag denotes no transformation will occur.
     */
    public final static int TRANSFORM_NONE = 0;

    /**
     * Loads an instance of Person from the persistent store.
     * @param serial the identifier of the entity to load.
     * @throws PersonDaoException
     */
    public Person load(String serial)
        throws PersonDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #load(String)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined in this class then the result <strong>WILL BE</strong> passed through an operation which can
     *   optionally transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @param serial the identifier of the entity to load.
     * @return either the entity or the object transformed from the entity.
     * @throws PersonDaoException
     */
    public Object load(int transform, String serial)
        throws PersonDaoException;

    /**
     * Loads all entities of type {@link Person}.
     *
     * @return the loaded entities.
     * @throws PersonDaoException
     */
    public Collection<Person> loadAll()
        throws PersonDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #loadAll()} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @param transform the flag indicating what transformation to use.
     * @return the loaded entities.
     * @throws PersonDaoException
     */
    public Collection loadAll(final int transform)
        throws PersonDaoException;

    /**
     * Creates an instance of Person and adds it to the persistent store.
     * @throws PersonDaoException
     */
    public Person create(Person person)
        throws PersonDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #create(Person)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @throws PersonDaoException
     */
    public Object create(int transform, Person person)
        throws PersonDaoException;

    /**
     * Creates a new instance of Person and adds
     * from the passed in <code>entities</code> collection
     *
     * @param entities the collection of Person
     *        instances to create.
     *
     * @return the created instances.
     * @throws PersonDaoException
     */
    public Collection<Person> create(Collection<Person> entities)
        throws PersonDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #create(Person)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entities (into value objects for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @throws PersonDaoException
     */
    public Collection create(int transform, Collection<Person> entities)
        throws PersonDaoException;

    /**
     * <p>
     *   Creates a new <code>Person</code>
     *   instance from <strong>all</strong> attributes and adds it to
     *   the persistent store.
     * </p>
     */
    public Person create(
        String name,
        Date birthDate)
        throws PersonDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #create(String, Date)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @throws PersonDaoException
     */
    public Object create(
        int transform,
        String name,
        Date birthDate)
        throws PersonDaoException;


    /**
     * Updates the <code>person</code> instance in the persistent store.
     * @throws PersonDaoException
     */
    public void update(Person person)
        throws PersonDaoException;

    /**
     * Updates all instances in the <code>entities</code> collection in the persistent store.
     * @throws PersonDaoException
     */
    public void update(Collection<Person> entities)
        throws PersonDaoException;

    /**
     * Removes the instance of Person from the persistent store.
     * @throws PersonDaoException
     */
    public void remove(Person person)
        throws PersonDaoException;

    /**
     * Removes the instance of Person having the given
     * <code>identifier</code> from the persistent store.
     * @throws PersonDaoException
     */
    public void remove(String serial)
        throws PersonDaoException;

    /**
     * Removes all entities in the given <code>entities<code> collection.
     * @throws PersonDaoException
     */
    public void remove(Collection<Person> entities)
        throws PersonDaoException;

}