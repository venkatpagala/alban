// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoBase.vsl in andromda-ejb3-cartridge.
//
package org.andromda.timetracker.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

/**
 * <p>
 * Base EJB3 DAO Class: is able to create, update, remove, load, and find
 * objects of type <code>Movie</code>.
 * </p>
 *
 * @see MovieDao
 */
//@javax.ejb.TransactionAttribute(javax.ejb.TransactionAttributeType.REQUIRED)
//@javax.ejb.Local({MovieDao.class})
public abstract class MovieDaoBase implements MovieDao
{

    private static final Logger logger = Logger.getLogger(MovieDaoBase.class);
    
    /** Session Context Injection */
    @Resource
    protected SessionContext context;

    /**
     * Inject persistence context timetracker-ejb3     */    
    @PersistenceContext(unitName = "timetracker-ejb3")    protected EntityManager entityManager;

    /**
     * @see MovieDao#load
     */
    @Override
    public Object load(final int transform, final Long id) throws MovieDaoException
    {
        if (id == null)
        {
            throw new IllegalArgumentException("Movie.load - 'id' can not be null");
        }
        try
        {
                        final Movie entity = this.entityManager.find(Movie.class, id);
            return transformEntity(transform, entity);
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * @see MovieDao#load( Long)
     */
    @Override
        public Movie load( final Long id) throws MovieDaoException
    {
        return (Movie)this.load(TRANSFORM_NONE, id);
    }

    /**
     * @see MovieDao#loadAll()
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Collection<Movie> loadAll() throws MovieDaoException
    {
        return this.loadAll(TRANSFORM_NONE);
    }

    /**
     * @see MovieDao#loadAll(int)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Collection loadAll(final int transform) throws MovieDaoException
    {
        try
        {
            TypedQuery<Movie> query = this.entityManager.createNamedQuery("Movie.findAll", Movie.class);

            List<Movie> results = query.getResultList();
            this.transformEntities(transform, results);
            return results;
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * Create Movie with no VO transformation
     * @see MovieDao#create(Movie)
     */
    @Override
    public Movie create(Movie movie) throws MovieDaoException
    {
        return (Movie)this.create(TRANSFORM_NONE, movie);
    }

    /**
     * Create Movie with VO transformation
     * @see MovieDao#create(int, Movie)
     */
    @Override
    public Object create(final int transform, final Movie movie) throws MovieDaoException
    {
        if (movie == null)
        {
            throw new IllegalArgumentException("Movie.create - 'movie' can not be null");
        }

        try
        {
            this.entityManager.persist(movie);
            this.entityManager.flush();
            return this.transformEntity(transform, movie);
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * Create a Collection of Movie with no VO transformation
     * @see MovieDao#create(Collection)
     */
    @Override
    //@SuppressWarnings({"unchecked"})
    public Collection<Movie> create(final Collection<Movie> entities) throws MovieDaoException
    {
        return create(TRANSFORM_NONE, entities);
    }

    /**
     * Create a Collection of Movie with VO transformation
     * @see MovieDao#create(int, Collection)
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection create(final int transform, final Collection<Movie> entities) throws MovieDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Movie.create - 'entities' can not be null");
        }
        Collection results = new ArrayList();
        try
        {
            for (final Movie entity : entities)
            {
                results.add(create(transform, entity));
            }
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
        return results;
    }

    /**
     * Create Entity Movie using instance attributes with no VO transformation
     * @see MovieDao#create(String, Integer, Integer, String, String)
     */
    @Override
    public Movie create(String title, Integer year, Integer version, String director, String plot) throws MovieDaoException
    {
        return (Movie)this.create(TRANSFORM_NONE, title, year, version, director, plot);
    }

    /**
     * Create Entity Movie using instance attributes with VO transformation
     * @see MovieDao#create(int, String, Integer, Integer, String, String)
     * composite=false identifiers=1
     */
    @Override
    public Object create(final int transform, String title, Integer year, Integer version, String director, String plot) throws MovieDaoException
    {
        Movie entity = new Movie();
        entity.setTitle(title);
        entity.setYear(year);
        entity.setDirector(director);
        entity.setPlot(plot);
        return this.create(transform, entity);
    }

    /**
     * @see MovieDao#update(Movie)
     */
    @Override
    public void update(Movie movie) throws MovieDaoException
    {
        if (movie == null)
        {
            throw new IllegalArgumentException("Movie.update - 'movie' can not be null");
        }
        try
        {
            this.entityManager.merge(movie);
            this.entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * @see MovieDao#update(Collection)
     */
    @Override
    public void update(final Collection<Movie> entities) throws MovieDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Movie.update - 'entities' can not be null");
        }
        try
        {
            for (final Movie entity : entities)
            {
                update(entity);
            }
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * @see MovieDao#remove(Movie)
     */
    @Override
    public void remove(Movie movie) throws MovieDaoException
    {
        if (movie == null)
        {
            throw new IllegalArgumentException("Movie.remove - 'movie' can not be null");
        }
        try
        {
            this.entityManager.remove(movie);
            this.entityManager.flush();
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * @see MovieDao#remove(Long)
     */
    @Override
    public void remove(Long id) throws MovieDaoException
    {
        if (id == null)
        {
            throw new IllegalArgumentException("Movie.remove - 'id' can not be null");
        }
        try
        {
            final Movie entity = this.load(id);
            if (entity != null)
            {
                this.remove(entity);
            }
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * @see MovieDao#remove(Collection)
     */
    @Override
    public void remove(Collection<Movie> entities) throws MovieDaoException
    {
        if (entities == null)
        {
            throw new IllegalArgumentException("Movie.remove - 'entities' can not be null");
        }
        try
        {
            for (final Movie entity : entities)
            {
                remove(entity);
            }
        }
        catch (Exception ex)
        {
            throw new MovieDaoException(ex);
        }
    }

    /**
     * Allows transformation of entities into value objects
     * (or something else for that matter), when the <code>transform</code>
     * flag is set to one of the constants defined in <code>MovieDao</code>, please note
     * that the {@link #TRANSFORM_NONE} constant denotes no transformation, so the entity itself
     * will be returned.
     *
     * If the integer argument value is unknown {@link #TRANSFORM_NONE} is assumed.
     *
     * @param transform one of the constants declared in {@link MovieDao}
     * @param entity an entity that was found
     * @return the transformed entity (i.e. new value object, etc)
     * @see #transformEntities(int,Collection)
     */
    protected Object transformEntity(final int transform, final Movie entity)
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
     * {@link #transformEntity(int, Movie)}
     * method. This method does not instantiate a new collection.
     * <p/>
     * Transforms into the same collection as the argument, but this time containing the transformed entities
     * This method is to be used internally only.
     *
     * @param transform one of the constants declared in <code>MovieDao</code>
     * @param entities the collection of entities to transform
     * @see #transformEntity(int, Movie)
     */
    protected void transformEntities(final int transform, final Collection<Movie> entities)
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

    /**
     * @return the hibernateSession
     */   
    public Session getHibernateSession()
    {
        if (this.entityManager.getDelegate() instanceof HibernateEntityManager)
        {
            return ((HibernateEntityManager) this.entityManager.getDelegate()).getSession();
        } else
        {
            return (Session) this.entityManager.getDelegate();
        }
    }    

}