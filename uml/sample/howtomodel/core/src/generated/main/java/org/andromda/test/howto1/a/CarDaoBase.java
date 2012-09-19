// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoBase.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto1.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;

/**
 * <p>
 * Base EJB3 DAO Class: is able to create, update, remove, load, and find
 * objects of type <code>Car</code>.
 * </p>
 *
 * @see CarDao
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Local({CarDao.class})
public abstract class CarDaoBase implements CarDao
{

    private static final Logger logger = Logger.getLogger(CarDaoBase.class);

    /** Session Context Injection */
    @Resource
    protected SessionContext context;

    /**
     * Inject persistence context howtomodel     */
    @PersistenceContext(unitName = "howtomodel")
    protected EntityManager entityManager;

    /**
     * @see CarDao#load(int,)
     */
    @Override
    public Object load(final int transform, final String serial) throws CarDaoException
    {
        if (serial == null)
        {
            throw new IllegalArgumentException("Car.load - 'serial' can not be null");
        }
        try
        {
            final Object entity = (Car)this.entityManager.find(Car.class, serial);
            return transformEntity(transform, (Car)entity);
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#load()
     */
    @Override
    public Car load( final String serial) throws CarDaoException
    {
        return (Car)this.load(TRANSFORM_NONE, serial);
    }

    /**
     * @see CarDao#loadAll()
     */
    @Override
    //@SuppressWarnings({"unchecked"})
    public Collection<Car> loadAll() throws CarDaoException
    {
        return(Collection<Car>) this.loadAll(TRANSFORM_NONE);
    }

    /**
     * @see CarDao#loadAll(int)
     */
    @Override
    public Collection loadAll(final int transform) throws CarDaoException
    {
        try
        {
            Query query = entityManager.createNamedQuery("Car.findAll");
            List<Car> results = query.getResultList();
            this.transformEntities(transform, results);
            return results;
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#create(Car)
     */
    @Override
    public Car create(Car car) throws CarDaoException
    {
        return (Car)this.create(TRANSFORM_NONE, car);
    }

    /**
     * @see CarDao#create(int, Car)
     */
    @Override
    public Object create(final int transform, final Car car) throws CarDaoException
    {
        if (car == null)
        {
            throw new IllegalArgumentException("Car.create - 'car' can not be null");
        }

        try
        {
            this.entityManager.persist(car);
            this.entityManager.flush();
            return this.transformEntity(transform, car);
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#create(Collection<Car>)
     */
    @Override
    //@SuppressWarnings({"unchecked"})
    public Collection<Car> create(final Collection<Car> entities) throws CarDaoException
    {
        return create(TRANSFORM_NONE, entities);
    }

    /**
     * @see CarDao#create(int, Collection)
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Collection create(final int transform, final Collection<Car> entities) throws CarDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Car.create - 'entities' can not be null");
        }
        Collection results = new ArrayList();
        try
        {
            for (final Car entity : entities)
            {
                results.add(create(transform, entity));
            }
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
        return results;
    }

    /**
     * @see CarDao#update(Car)
     */
    @Override
    public void update(Car car) throws CarDaoException
    {
        if (car == null)
        {
            throw new IllegalArgumentException("Car.update - 'car' can not be null");
        }
        try
        {
            this.entityManager.merge(car);
            this.entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#update(Collection<Car>)
     */
    @Override
    public void update(final Collection<Car> entities) throws CarDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Car.update - 'entities' can not be null");
        }
        try
        {
            for (final Car entity : entities)
            {
                update(entity);
            }
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#remove(Car)
     */
    @Override
    public void remove(Car car) throws CarDaoException
    {
        if (car == null)
        {
            throw new IllegalArgumentException("Car.remove - 'car' can not be null");
        }
        try
        {
            this.entityManager.remove(car);
            this.entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#remove(String)
     */
    @Override
    public void remove(String serial) throws CarDaoException
    {
        if (serial == null)
        {
            throw new IllegalArgumentException("Car.remove - 'serial' can not be null");
        }
        try
        {
            final Car entity = this.load(serial);
            if (entity != null)
            {
                this.remove(entity);
            }
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#remove(Collection<Car>)
     */
    @Override
    public void remove(Collection<Car> entities) throws CarDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Car.remove - 'entities' can not be null");
        }
        try
        {
            for (final Car entity : entities)
            {
                remove(entity);
            }
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * Allows transformation of entities into value objects
     * (or something else for that matter), when the <code>transform</code>
     * flag is set to one of the constants defined in <code>CarDao</code>, please note
     * that the {@link #TRANSFORM_NONE} constant denotes no transformation, so the entity itself
     * will be returned.
     *
     * If the integer argument value is unknown {@link #TRANSFORM_NONE} is assumed.
     *
     * @param transform one of the constants declared in {@link CarDao}
     * @param entity an entity that was found
     * @return the transformed entity (i.e. new value object, etc)
     * @see #transformEntities(int,Collection)
     */
    protected Object transformEntity(final int transform, final Car entity)
    {
        Object target = null;
        if (entity != null)
        {
            switch (transform)
            {
                case TRANSFORM_NONE : // fall-through
                default:
                    target = entity;
            }
        }
        return target;
    }

    /**
     * Transforms a collection of entities using the
     * {@link #transformEntity(int, Car)}
     * method. This method does not instantiate a new collection.
     * <p/>
     * Transforms into the same collection as the argument, but this time containing the transformed entities
     * This method is to be used internally only.
     *
     * @param transform one of the constants declared in <code>CarDao</code>
     * @param entities the collection of entities to transform
     * @see #transformEntity(int, Car)
     */
    protected void transformEntities(final int transform, final Collection<Car> entities)
    {
        switch (transform)
        {
            case TRANSFORM_NONE : // fall-through
                default:
                // do nothing;
        }
    }


    // For unit testing outside of container - persistence context not injected
    /**
     * @return the context
     */
    public SessionContext getContext()
    {
        return this.context;
    }

    /**
     * @param contextIn the context to set
     */
    public void setContext(SessionContext contextIn)
    {
        this.context = contextIn;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager()
    {
        return this.entityManager;
    }

    /**
     * @param entityManagerIn the entityManager to set
     */
    public void setEntityManager(EntityManager entityManagerIn)
    {
        this.entityManager = entityManagerIn;
    }
}