// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoLocal.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto7.a;

import java.util.Collection;
import java.util.List;

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
    public static final int TRANSFORM_NONE = 0;

    /**
     * Loads an instance of Car from the persistent store.
     * @param serial String identifier part of the composite PK of the entity to load.
     * @return Car
     * @throws CarDaoException
     */
    public Car load(String serial) throws CarDaoException;

    /**
     *   Does the same thing as {@link #load(String)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined in this class then the result <strong>WILL BE</strong> passed through an operation which can
     *   optionally transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform int transformation flag
     * @param serial String identifier part of the composite PK of the entity to load.
     * @return either the entity or the object transformed from the entity.
     * @throws CarDaoException
     */
    public Object load(int transform, String serial) throws CarDaoException;

    /**
     * Loads all entities of type {@link Car}.
     *
     * @return the loaded entities.
     * @throws CarDaoException
     */
    public Collection<Car> loadAll() throws CarDaoException;

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
     * @throws CarDaoException
     */
    @SuppressWarnings("rawtypes")
    public Collection loadAll(final int transform) throws CarDaoException;

    /**
     * Creates an instance of Car and adds it to the persistent store.
     * @param car
     * @return Car
     * @throws CarDaoException
     */
    public Car create(Car car) throws CarDaoException;

    /**
     *   Does the same thing as {@link #create(Car)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param car
     * @return Object
     * @throws CarDaoException
     */
    public Object create(int transform, Car car) throws CarDaoException;

    /**
     * Creates a new instance of Car and adds
     * from the passed in <code>entities</code> collection
     *
     * @param entities the collection of Car
     *        instances to create.
     * @return the created instances. Collection<Car>
     * @throws CarDaoException
     */
    public Collection<Car> create(Collection<Car> entities) throws CarDaoException;

    /**
     *   Does the same thing as {@link #create(Car)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entities (into value objects for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param entities
     * @return the created instances. Collection
     * @throws CarDaoException
     */
    @SuppressWarnings({"rawtypes"})
    public Collection create(int transform, Collection<Car> entities)
        throws CarDaoException;

    /**
     *   Creates a new <code>Car</code>
     *   instance from <strong>all</strong> attributes and adds it to
     *   the persistent store.
     * @param name
     * @param type
     * @return the created instances. Car
     * @throws CarDaoException
     */
    public Car create(        String name,
        CarType type)
 throws CarDaoException;

    /**
     *   Does the same thing as {@link #create(String, CarType)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param name
     * @param type
     * @return the created instances. Object
     * @throws CarDaoException
     */
    public Object create( int transform,        String name,
        CarType type)
 throws CarDaoException;

    /**
     *  Creates a new <code>Car</code>
     *  instance from only <strong>required</strong> properties (attributes
     *  and association ends) and adds it to the persistent store.
     *
     * @param name
     * @param owner
     * @param type
     * @return the created instance. Car
     * @throws CarDaoException
     */
    public Car create(        String name,
        Person owner,
        CarType type)
 throws CarDaoException;

    /**
     *   Does the same thing as {@link #create(String, CarType)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param name
     * @param owner
     * @param type
     * @return the created instance. Object
     * @throws CarDaoException
     */
    public Object create( int transform,        String name,
        Person owner,
        CarType type)
 throws CarDaoException;

    /**
     * Updates the <code>car</code> instance in the persistent store.
     * @param car
     * @throws CarDaoException
     */
    public void update(Car car) throws CarDaoException;

    /**
     * Updates all instances in the <code>entities</code> collection in the persistent store.
     * @param entities
     * @throws CarDaoException
     */
    public void update(Collection<Car> entities) throws CarDaoException;

    /**
     * Removes the instance of Car from the persistent store.
     * @param car
     * @throws CarDaoException
     */
    public void remove(Car car) throws CarDaoException;

    /**
     * Removes the instance of Car having the given
     * <code>identifier</code> from the persistent store.
     * @param serial
     * @throws CarDaoException
     */
    public void remove(String serial) throws CarDaoException;

    /**
     * Removes all entities in the given <code>entities<code> collection.
     * @param entities
     * @throws CarDaoException
     */
    public void remove(Collection<Car> entities) throws CarDaoException;

    /**
     * TODO: Model Documentation for Car.findByType
     * @param type
     * @return List
     * @throws CarDaoException
     */
    public List findByType(CarType type) throws CarDaoException;

    /**
     *   Does the same thing as {@link #findByType(CarType)} with an
     *   additional argument called <code>queryString</code>.  This <code>queryString</code>
     *   argument allows you to override the query string defined in {@link #findByType(CarType)}.
     * @param type
     * @return List
     * @throws CarDaoException
     */
    public List findByType(String queryString, CarType type) throws CarDaoException;

    /**
     *   Does the same thing as {@link #findByType(CarType)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   finder results will <strong>NOT</strong> be transformed during retrieval.
     *   If this flag is any of the other constants defined here
     *   then finder results <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entities (into value objects for example).  By default, transformation does
     *   not occur.
     * @param type
     * @return List
     * @throws CarDaoException
     */
    public List findByType(int transform, CarType type) throws CarDaoException;

    /**
     *   Does the same thing as {@link #findByType(boolean, CarType)} with an
     *   additional argument called <code>queryString</code>.  This <code>queryString</code>
     *   argument allows you to override the query string defined in {@link #findByType(int, CarType type)}.
     */
    public List findByType(int transform, String queryString, CarType type) throws CarDaoException;

    /**
     * Returns true if all cars are currently rented.
     * @return boolean
     */
    public boolean allCarsAreRented() throws CarDaoException;
    
}