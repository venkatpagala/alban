// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoBase.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto2.d2;

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
     * @see CarDao#load
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
                        final Car entity = this.entityManager.find(Car.class, serial);
            return transformEntity(transform, entity);
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#load( String)
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
    @SuppressWarnings({"unchecked"})
    public Collection<Car> loadAll() throws CarDaoException
    {
        return this.loadAll(TRANSFORM_NONE);
    }

    /**
     * @see CarDao#loadAll(int)
     */
    @SuppressWarnings("rawtypes")
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
     * Create Car with no VO transformation
     * @see CarDao#create(Car)
     */
    @Override
    public Car create(Car car) throws CarDaoException
    {
        return (Car)this.create(TRANSFORM_NONE, car);
    }

    /**
     * Create Car with VO transformation
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
     * Create a Collection of Car with no VO transformation
     * @see CarDao#create(Collection)
     */
    @Override
    //@SuppressWarnings({"unchecked"})
    public Collection<Car> create(final Collection<Car> entities) throws CarDaoException
    {
        return create(TRANSFORM_NONE, entities);
    }

    /**
     * Create a Collection of Car with VO transformation
     * @see CarDao#create(int, Collection)
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
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
     * Create Entity Car using instance attributes with no VO transformation
     * @see CarDao#create(String, String)
     */
    @Override
    public Car create(String name, String type) throws CarDaoException
    {
        return (Car)this.create(TRANSFORM_NONE, name, type);
    }

    /**
     * Create Entity Car using instance attributes with VO transformation
     * @see CarDao#create(int, String, String)
     * composite=false identifiers=1
     */
    @Override
    public Object create(final int transform, String name, String type) throws CarDaoException
    {
        Car entity = new Car();
        entity.setName(name);
        entity.setType(type);
        return this.create(transform, entity);
    }

    /**
     * Create Entity Car using required properties with no VO transformation
     * @see CarDao#create(String, Person, String)
     */
    @Override
    public Car create(String name, Person owner, String type) throws CarDaoException
    {
        return (Car)this.create(TRANSFORM_NONE, name, owner, type);
    }

    /**
     * Create Entity Car using required properties with VO transformation
     * @see CarDao#create(int, String, Person, String)
     */
    @Override
    public Object create(final int transform,String name, Person owner, String type) throws CarDaoException
    {
        Car entity = new Car();
        // name $propertyType.fullyQualifiedName identifier=$propertyType.identifier false
        entity.setName(name);
        // owner $propertyType.fullyQualifiedName identifier=$propertyType.identifier false
        entity.setOwner(owner);
        // type $propertyType.fullyQualifiedName identifier=$propertyType.identifier false
        entity.setType(type);
        return this.create(transform, entity);
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
     * @see CarDao#update(Collection)
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
     * @see CarDao#remove(Collection)
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
     * @see CarDao#allCarsAreRented()
     */
    @Override
    public boolean allCarsAreRented()    {
        try
        {
            return this.handleAllCarsAreRented();
        }
        catch (Throwable th)
        {
            CarDaoBase.logger.debug("Error performing 'CarDao.allCarsAreRented()' --> " + th);
            throw new RuntimeException("Error performing 'CarDao.allCarsAreRented()' --> " + th, th);
        }
    }

     /**
      * Performs the core logic for {@link #allCarsAreRented()}
      * @return boolean 
      * @throws Exception
      */
    protected abstract boolean handleAllCarsAreRented() throws Exception;

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