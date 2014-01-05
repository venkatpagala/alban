// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoBase.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto6.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
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
     * @see CarDao#create(String, CarType)
     */
    @Override
    public Car create(String name, CarType type) throws CarDaoException
    {
        return (Car)this.create(TRANSFORM_NONE, name, type);
    }

    /**
     * Create Entity Car using instance attributes with VO transformation
     * @see CarDao#create(int, String, CarType)
     * composite=false identifiers=1
     */
    @Override
    public Object create(final int transform, String name, CarType type) throws CarDaoException
    {
        Car entity = new Car();
        entity.setName(name);
        entity.setType(type);
        return this.create(transform, entity);
    }

    /**
     * Create Entity Car using required properties with no VO transformation
     * @see CarDao#create(String, Person, CarType)
     */
    @Override
    public Car create(String name, Person owner, CarType type) throws CarDaoException
    {
        return (Car)this.create(TRANSFORM_NONE, name, owner, type);
    }

    /**
     * Create Entity Car using required properties with VO transformation
     * @see CarDao#create(int, String, Person, CarType)
     */
    @Override
    public Object create(final int transform,String name, Person owner, CarType type) throws CarDaoException
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
     * @see CarDao#findByType(CarType)
     */
    @Override
    public List findByType(CarType type) throws CarDaoException
    {
        return this.findByType(TRANSFORM_NONE, type);
    }

    /**
     * @see CarDao#findByType(String, CarType)
     */
    @Override
    public List findByType(final String queryString, final CarType type) throws CarDaoException
    {
        return this.findByType(TRANSFORM_NONE, queryString, type);
    }

    /**
     * @see CarDao#findByType(int, CarType)
     */
    @Override
    public List findByType(final int transform, final CarType type) throws CarDaoException
    {
        try
        {
            Query queryObject = entityManager.createNamedQuery("Car.findByType");
            queryObject.setParameter("type", type);
            List results = queryObject.getResultList();
            transformEntities(transform, results);
            return results;
        }
        catch (Exception ex)
        {
            throw new CarDaoException(ex);
        }
    }

    /**
     * @see CarDao#findByType(int, String, CarType)
     */
    @Override
    public List findByType(final int transform, final String queryString, final CarType type) throws CarDaoException
    {
        try
        {
            Query queryObject = entityManager.createQuery(queryString);
            queryObject.setParameter("type", type);
            List results = queryObject.getResultList();
            transformEntities(transform, results);
            return results;
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
     * <p/>
     * This method will return instances of these types:
     * <ul>
     *   <li>{@link Car} - {@link #TRANSFORM_NONE}</li>
     *   <li>{@link CarDetails} - {@link #TRANSFORM_CARDETAILS}</li>
     *   <li>{@link CarListItem} - {@link #TRANSFORM_CARLISTITEM}</li>
     * </ul>
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
                case CarDao.TRANSFORM_CARDETAILS :
                    target = toCarDetails(entity);
                    break;
                case CarDao.TRANSFORM_CARLISTITEM :
                    target = toCarListItem(entity);
                    break;
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
            case CarDao.TRANSFORM_CARDETAILS :
                toCarDetailsCollection(entities);
                break;
            case CarDao.TRANSFORM_CARLISTITEM :
                toCarListItemCollection(entities);
                break;
            case TRANSFORM_NONE : // fall-through
                default:
                // do nothing;
        }
    }

    /**
     * @see CarDao#toCarDetailsCollection(Collection)
     */
    @Override
    public final void toCarDetailsCollection(Collection entities)
    {
        if (entities != null)
        {
            CollectionUtils.transform(entities, this.CARDETAILS_TRANSFORMER);
        }
    }

    /**
     * Default implementation for transforming the results of a report query into a value object. This
     * implementation exists for convenience reasons only. It needs only be overridden in the
     * {@link CarDaoImpl} class if you intend to use reporting queries.
     * @param row Object[] Array of Car to transform
     * @return target CarDetails
     * @see CarDao#toCarDetails(Car)
     */
    protected CarDetails toCarDetails(Object[] row)
    {
        CarDetails target = null;
        if (row != null)
        {
            final int numberOfObjects = row.length;
            for (int ctr = 0; ctr < numberOfObjects; ctr++)
            {
                final Object object = row[ctr];
                if (object instanceof Car)
                {
                    target = this.toCarDetails((Car)object);
                    break;
                }
            }
        }
        return target;
    }

    /**
     * This anonymous transformer is designed to transform entities or report query results
     * (which result in an array of objects) to {@link CarDetails}
     * using the Jakarta Commons-Collections Transformation API.
     */
    private Transformer CARDETAILS_TRANSFORMER =
        new Transformer()
        {
            @Override
            public Object transform(Object input)
            {
                Object result = null;
                if (input instanceof Car)
                {
                    result = toCarDetails((Car)input);
                }
                else if (input instanceof Object[])
                {
                    result = toCarDetails((Object[])input);
                }
                return result;
            }
        };

    /**
     * @see CarDao#carDetailsToEntityCollection(Collection)
     */
    @Override
    public final void carDetailsToEntityCollection(Collection instances)
    {
        if (instances != null)
        {
            for (final Iterator iterator = instances.iterator(); iterator.hasNext();)
            {
                // - remove objects that are null or not of the correct instance
                if (!(iterator.next() instanceof CarDetails))
                {
                    iterator.remove();
                }
            }
            CollectionUtils.transform(instances, this.CarDetailsToEntityTransformer);
        }
    }

    private final Transformer CarDetailsToEntityTransformer =
        new Transformer()
        {
            @Override
            public Object transform(Object input)
            {
                return carDetailsToEntity((CarDetails)input);
            }
        };

    /**
     * @see CarDao#toCarDetails(Car, CarDetails)
     */
    @Override
    public void toCarDetails( Car source, CarDetails target)
    {
        target.setName(source.getName());
        target.setSerial(source.getSerial());
    }

    /**
     * @see CarDao#toCarDetails(Car)
     */
    @Override
    public CarDetails toCarDetails(final Car entity)
    {
        final CarDetails target = new CarDetails();
        this.toCarDetails(entity, target);
        return target;
    }

    /**
     * @see CarDao#carDetailsToEntity(CarDetails, Car, boolean)
     */
    @Override
    public void carDetailsToEntity( CarDetails source, Car target, boolean copyIfNull)
    {
        if (copyIfNull || source.getName() != null)
        {
            target.setName(source.getName());
        }
    }

    /**
     * @see CarDao#toCarListItemCollection(Collection)
     */
    @Override
    public final void toCarListItemCollection(Collection entities)
    {
        if (entities != null)
        {
            CollectionUtils.transform(entities, this.CARLISTITEM_TRANSFORMER);
        }
    }

    /**
     * Default implementation for transforming the results of a report query into a value object. This
     * implementation exists for convenience reasons only. It needs only be overridden in the
     * {@link CarDaoImpl} class if you intend to use reporting queries.
     * @param row Object[] Array of Car to transform
     * @return target CarListItem
     * @see CarDao#toCarListItem(Car)
     */
    protected CarListItem toCarListItem(Object[] row)
    {
        CarListItem target = null;
        if (row != null)
        {
            final int numberOfObjects = row.length;
            for (int ctr = 0; ctr < numberOfObjects; ctr++)
            {
                final Object object = row[ctr];
                if (object instanceof Car)
                {
                    target = this.toCarListItem((Car)object);
                    break;
                }
            }
        }
        return target;
    }

    /**
     * This anonymous transformer is designed to transform entities or report query results
     * (which result in an array of objects) to {@link CarListItem}
     * using the Jakarta Commons-Collections Transformation API.
     */
    private Transformer CARLISTITEM_TRANSFORMER =
        new Transformer()
        {
            @Override
            public Object transform(Object input)
            {
                Object result = null;
                if (input instanceof Car)
                {
                    result = toCarListItem((Car)input);
                }
                else if (input instanceof Object[])
                {
                    result = toCarListItem((Object[])input);
                }
                return result;
            }
        };

    /**
     * @see CarDao#carListItemToEntityCollection(Collection)
     */
    @Override
    public final void carListItemToEntityCollection(Collection instances)
    {
        if (instances != null)
        {
            for (final Iterator iterator = instances.iterator(); iterator.hasNext();)
            {
                // - remove objects that are null or not of the correct instance
                if (!(iterator.next() instanceof CarListItem))
                {
                    iterator.remove();
                }
            }
            CollectionUtils.transform(instances, this.CarListItemToEntityTransformer);
        }
    }

    private final Transformer CarListItemToEntityTransformer =
        new Transformer()
        {
            @Override
            public Object transform(Object input)
            {
                return carListItemToEntity((CarListItem)input);
            }
        };

    /**
     * @see CarDao#toCarListItem(Car, CarListItem)
     */
    @Override
    public void toCarListItem( Car source, CarListItem target)
    {
        target.setName(source.getName());
        target.setSerial(source.getSerial());
    }

    /**
     * @see CarDao#toCarListItem(Car)
     */
    @Override
    public CarListItem toCarListItem(final Car entity)
    {
        final CarListItem target = new CarListItem();
        this.toCarListItem(entity, target);
        return target;
    }

    /**
     * @see CarDao#carListItemToEntity(CarListItem, Car, boolean)
     */
    @Override
    public void carListItemToEntity( CarListItem source, Car target, boolean copyIfNull)
    {
        if (copyIfNull || source.getName() != null)
        {
            target.setName(source.getName());
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