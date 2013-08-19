// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoBase.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto2.c;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 * <p>
 * Base EJB3 DAO Class: is able to create, update, remove, load, and find
 * objects of type <code>Person</code>.
 * </p>
 *
 * @see PersonDao
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Local({PersonDao.class})
public abstract class PersonDaoBase implements PersonDao
{

    private static final Logger logger = Logger.getLogger(PersonDaoBase.class);
    
    /** Session Context Injection */
    @Resource
    protected SessionContext context;

    /**
     * Inject persistence context howtomodel     */    
    @PersistenceContext(unitName = "howtomodel")
    protected EntityManager entityManager;

    /**
     * @see PersonDao#load
     */
    @Override
    public Object load(final int transform, final String serial) throws PersonDaoException
    {
        if (serial == null)
        {
            throw new IllegalArgumentException("Person.load - 'serial' can not be null");
        }
        try
        {
                        final Person entity = this.entityManager.find(Person.class, serial);
            return transformEntity(transform, entity);
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * @see PersonDao#load( String)
     */
    @Override
        public Person load( final String serial) throws PersonDaoException
    {
        return (Person)this.load(TRANSFORM_NONE, serial);
    }

    /**
     * @see PersonDao#loadAll()
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Collection<Person> loadAll() throws PersonDaoException
    {
        return this.loadAll(TRANSFORM_NONE);
    }

    /**
     * @see PersonDao#loadAll(int)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Collection loadAll(final int transform) throws PersonDaoException
    {
        try
        {
                        TypedQuery<Person> query = this.entityManager.createNamedQuery("Person.findAll", Person.class);
            List<Person> results = query.getResultList();
            this.transformEntities(transform, results);
            return results;
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * Create Person with no VO transformation
     * @see PersonDao#create(Person)
     */
    @Override
    public Person create(Person person) throws PersonDaoException
    {
        return (Person)this.create(TRANSFORM_NONE, person);
    }

    /**
     * Create Person with VO transformation
     * @see PersonDao#create(int, Person)
     */
    @Override
    public Object create(final int transform, final Person person) throws PersonDaoException
    {
        if (person == null)
        {
            throw new IllegalArgumentException("Person.create - 'person' can not be null");
        }

        try
        {
            this.entityManager.persist(person);
            this.entityManager.flush();
            return this.transformEntity(transform, person);
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * Create a Collection of Person with no VO transformation
     * @see PersonDao#create(Collection)
     */
    @Override
    //@SuppressWarnings({"unchecked"})
    public Collection<Person> create(final Collection<Person> entities) throws PersonDaoException
    {
        return create(TRANSFORM_NONE, entities);
    }

    /**
     * Create a Collection of Person with VO transformation
     * @see PersonDao#create(int, Collection)
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection create(final int transform, final Collection<Person> entities) throws PersonDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Person.create - 'entities' can not be null");
        }
        Collection results = new ArrayList();
        try
        {
            for (final Person entity : entities)
            {
                results.add(create(transform, entity));
            }
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
        return results;
    }

    /**
     * Create Entity Person using instance attributes with no VO transformation
     * @see PersonDao#create(String, Date)
     */
    @Override
    public Person create(String name, Date birthDate) throws PersonDaoException
    {
        return (Person)this.create(TRANSFORM_NONE, name, birthDate);
    }

    /**
     * Create Entity Person using instance attributes with VO transformation
     * @see PersonDao#create(int, String, Date)
     * composite=false identifiers=1
     */
    @Override
    public Object create(final int transform, String name, Date birthDate) throws PersonDaoException
    {
        Person entity = new Person();
        entity.setName(name);
        entity.setBirthDate(birthDate);
        return this.create(transform, entity);
    }

    /**
     * @see PersonDao#update(Person)
     */
    @Override
    public void update(Person person) throws PersonDaoException
    {
        if (person == null)
        {
            throw new IllegalArgumentException("Person.update - 'person' can not be null");
        }
        try
        {
            this.entityManager.merge(person);
            this.entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * @see PersonDao#update(Collection)
     */
    @Override
    public void update(final Collection<Person> entities) throws PersonDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Person.update - 'entities' can not be null");
        }
        try
        {
            for (final Person entity : entities)
            {
                update(entity);
            }
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * @see PersonDao#remove(Person)
     */
    @Override
    public void remove(Person person) throws PersonDaoException
    {
        if (person == null)
        {
            throw new IllegalArgumentException("Person.remove - 'person' can not be null");
        }
        try
        {
            this.entityManager.remove(person);
            this.entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * @see PersonDao#remove(String)
     */
    @Override
    public void remove(String serial) throws PersonDaoException
    {
        if (serial == null)
        {
            throw new IllegalArgumentException("Person.remove - 'serial' can not be null");
        }
        try
        {
            final Person entity = this.load(serial);
            if (entity != null)
            {
                this.remove(entity);
            }
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * @see PersonDao#remove(Collection)
     */
    @Override
    public void remove(Collection<Person> entities) throws PersonDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Person.remove - 'entities' can not be null");
        }
        try
        {
            for (final Person entity : entities)
            {
                remove(entity);
            }
        }
        catch (Exception ex)
        {
            throw new PersonDaoException(ex);
        }
    }

    /**
     * Allows transformation of entities into value objects
     * (or something else for that matter), when the <code>transform</code>
     * flag is set to one of the constants defined in <code>PersonDao</code>, please note
     * that the {@link #TRANSFORM_NONE} constant denotes no transformation, so the entity itself
     * will be returned.
     *
     * If the integer argument value is unknown {@link #TRANSFORM_NONE} is assumed.
     *
     * @param transform one of the constants declared in {@link PersonDao}
     * @param entity an entity that was found
     * @return the transformed entity (i.e. new value object, etc)
     * @see #transformEntities(int,Collection)
     */
    protected Object transformEntity(final int transform, final Person entity)
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
     * {@link #transformEntity(int, Person)}
     * method. This method does not instantiate a new collection.
     * <p/>
     * Transforms into the same collection as the argument, but this time containing the transformed entities
     * This method is to be used internally only.
     *
     * @param transform one of the constants declared in <code>PersonDao</code>
     * @param entities the collection of entities to transform
     * @see #transformEntity(int, Person)
     */
    protected void transformEntities(final int transform, final Collection<Person> entities)
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