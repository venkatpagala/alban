// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: SessionDaoLocal.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto4.a;

import java.util.Collection;

/**
 * @see Car
 */
public interface CarDao
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
     * Loads an instance of Car from the persistent store.
     * @throws CarDaoException
     */
    public Car load(String serial, String name)
        throws CarDaoException;

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
     * @return either the entity or the object transformed from the entity.
     * @throws CarDaoException
     */
    public Object load(int transform, String serial, String name)
        throws CarDaoException;

    /**
     * Loads all entities of type {@link Car}.
     *
     * @return the loaded entities.
     * @throws CarDaoException
     */
    public Collection<Car> loadAll()
        throws CarDaoException;

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
     * @throws CarDaoException
     */
    public Collection loadAll(final int transform)
        throws CarDaoException;

    /**
     * Creates an instance of Car and adds it to the persistent store.
     * @throws CarDaoException
     */
    public Car create(Car car)
        throws CarDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #create(Car)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @throws CarDaoException
     */
    public Object create(int transform, Car car)
        throws CarDaoException;

    /**
     * Creates a new instance of Car and adds
     * from the passed in <code>entities</code> collection
     *
     * @param entities the collection of Car
     *        instances to create.
     *
     * @return the created instances.
     * @throws CarDaoException
     */
    public Collection<Car> create(Collection<Car> entities)
        throws CarDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #create(Car)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entities (into value objects for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @throws CarDaoException
     */
    public Collection create(int transform, Collection<Car> entities)
        throws CarDaoException;

    /**
     * <p>
     *   Creates a new <code>Car</code>
     *   instance from <strong>all</strong> attributes and adds it to
     *   the persistent store.
     * </p>
     */
    public Car create(
        String type)
        throws CarDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #create(String)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @throws CarDaoException
     */
    public Object create(
        int transform,
        String type)
        throws CarDaoException;

    /**
     * <p>
     *  Creates a new <code>Car</code>
     *  instance from only <strong>required</strong> properties (attributes
     *  and association ends) and adds it to the persistent store.
     * </p>
     *
     * @throws CarDaoException
     */
    public Car create(
        Person owner,
        String type)
        throws CarDaoException;

    /**
     * <p>
     *   Does the same thing as {@link #create(String)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     * </p>
     *
     * @throws CarDaoException
     */
    public Object create(
        int transform,
        Person owner,
        String type)
        throws CarDaoException;

    /**
     * Updates the <code>car</code> instance in the persistent store.
     * @throws CarDaoException
     */
    public void update(Car car)
        throws CarDaoException;

    /**
     * Updates all instances in the <code>entities</code> collection in the persistent store.
     * @throws CarDaoException
     */
    public void update(Collection<Car> entities)
        throws CarDaoException;

    /**
     * Removes the instance of Car from the persistent store.
     * @throws CarDaoException
     */
    public void remove(Car car)
        throws CarDaoException;

    /**
     * Removes the instance of Car having the given
     * <code>identifier</code> from the persistent store.
     * @throws CarDaoException
     */
    public void remove(String serial, String name)
        throws CarDaoException;

    /**
     * Removes all entities in the given <code>entities<code> collection.
     * @throws CarDaoException
     */
    public void remove(Collection<Car> entities)
        throws CarDaoException;

    /**
     * Returns true if all cars are currently rented.
     */
    public boolean allCarsAreRented();

}