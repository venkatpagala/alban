// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand! hibernate4=false hibernateVersion=3
// Generated by hibernate/SpringHibernateDaoBase.vsl in andromda-spring-cartridge on 10/04/2013 00:03:56+0200.
//
package org.andromda.timetracker.domain;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.andromda.spring.PaginationResult;
import org.andromda.timetracker.PrincipalStore;
import org.andromda.timetracker.PropertySearch;
import org.andromda.timetracker.Search;
import org.andromda.timetracker.vo.TimecardSearchCriteriaVO;
import org.andromda.timetracker.vo.TimecardSummaryVO;
import org.andromda.timetracker.vo.TimecardVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <p>
 * Base Spring DAO Class: is able to create, update, remove, load, and find
 * objects of type <code>Timecard</code>.
 * </p>
 *
 * @see Timecard
 */
public abstract class TimecardDaoBase
    extends HibernateDaoSupport
    implements TimecardDao
{
    private TimeAllocationDao timeAllocationDao;

    /**
     * Sets the reference to <code>timeAllocationDao</code>.
     * @param timeAllocationDaoIn
     */
    public void setTimeAllocationDao(TimeAllocationDao timeAllocationDaoIn)
    {
        this.timeAllocationDao = timeAllocationDaoIn;
    }

    /**
     * Gets the reference to <code>timeAllocationDao</code>.
     * @return timeAllocationDao
     */
    protected TimeAllocationDao getTimeAllocationDao()
    {
        return this.timeAllocationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(final int transform, final Long id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException(
                "Timecard.get - 'id' can not be null");
        }
        final Object entity = this.getHibernateTemplate().get(TimecardImpl.class, id);
        return transformEntity(transform, (Timecard)entity);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Timecard get(Long id)
    {
        return (Timecard)this.get(TRANSFORM_NONE, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object load(final int transform, final Long id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException(
                "Timecard.load - 'id' can not be null");
        }
        final Object entity = this.getHibernateTemplate().get(TimecardImpl.class, id);
        return transformEntity(transform, (Timecard)entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timecard load(Long id)
    {
        return (Timecard)this.load(TRANSFORM_NONE, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Collection<Timecard> loadAll()
    {
        return (Collection<Timecard>) this.loadAll(TimecardDao.TRANSFORM_NONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<?> loadAll(final int transform)
    {
        return this.loadAll(transform, -1, -1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<?> loadAll(final int pageNumber, final int pageSize)
    {
        return this.loadAll(TimecardDao.TRANSFORM_NONE, pageNumber, pageSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<?> loadAll(final int transform, final int pageNumber, final int pageSize)
    {
        try
        {
            final Criteria criteria = this.getSession(false).createCriteria(TimecardImpl.class);
            if (pageNumber > 0 && pageSize > 0)
            {
                criteria.setFirstResult(this.calculateFirstResult(pageNumber, pageSize));
                criteria.setMaxResults(pageSize);
            }
            final Collection<?> results = criteria.list();
            this.transformEntities(transform, results);
            return results;
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    /**
     * firstResult = (pageNumber - 1) * pageSize
     * @param pageNumber
     * @param pageSize
     * @return firstResult
     */
    protected int calculateFirstResult(int pageNumber, int pageSize)
    {
        int firstResult = 0;
        if (pageNumber > 0)
        {
            firstResult = (pageNumber - 1) * pageSize;
        }
        return firstResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timecard create(Timecard timecard)
    {
        return (Timecard)this.create(TimecardDao.TRANSFORM_NONE, timecard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object create(final int transform, final Timecard timecard)
    {
        if (timecard == null)
        {
            throw new IllegalArgumentException(
                "Timecard.create - 'timecard' can not be null");
        }
        this.getHibernateTemplate().save(timecard);
        return this.transformEntity(transform, timecard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public Collection<Timecard> create(final Collection<Timecard> entities)
    {
        return (Collection<Timecard>) create(TimecardDao.TRANSFORM_NONE, entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<?> create(final int transform, final Collection<Timecard> entities)
    {
        if (entities == null)
        {
            throw new IllegalArgumentException(
                "Timecard.create - 'entities' can not be null");
        }
        this.getHibernateTemplate().executeWithNativeSession(
            new HibernateCallback()
            {
                public Object doInHibernate(Session session)
                    throws HibernateException
                {
                    for (Timecard entity : entities)
                    {
                        create(transform, entity);
                    }
                    return null;
                }
            });
        return entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timecard create(
        String comments,
        Date startDate,
        TimecardStatus status)
    {
        return (Timecard)this.create(TimecardDao.TRANSFORM_NONE, comments, startDate, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object create(
        final int transform,
        String comments,
        Date startDate,
        TimecardStatus status)
    {
        Timecard entity = new TimecardImpl();
        entity.setComments(comments);
        entity.setStartDate(startDate);
        entity.setStatus(status);
        return this.create(transform, entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timecard create(
        String comments,
        Date startDate,
        TimecardStatus status,
        User submitter)
    {
        return (Timecard)this.create(TimecardDao.TRANSFORM_NONE, comments, startDate, status, submitter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object create(
        final int transform,
        String comments,
        Date startDate,
        TimecardStatus status,
        User submitter)
    {
        Timecard entity = new TimecardImpl();
        entity.setComments(comments);
        entity.setStartDate(startDate);
        entity.setStatus(status);
        entity.setSubmitter(submitter);
        return this.create(transform, entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Timecard timecard)
    {
        if (timecard == null)
        {
            throw new IllegalArgumentException(
                "Timecard.update - 'timecard' can not be null");
        }
        this.getHibernateTemplate().update(timecard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Collection<Timecard> entities)
    {
        if (entities == null)
        {
            throw new IllegalArgumentException(
                "Timecard.update - 'entities' can not be null");
        }
        this.getHibernateTemplate().executeWithNativeSession(
            new HibernateCallback()
            {
                public Object doInHibernate(Session session)
                    throws HibernateException
                {
                    for (Timecard entity : entities)
                    {
                        update(entity);
                    }
                    return null;
                }
            });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Timecard timecard)
    {
        if (timecard == null)
        {
            throw new IllegalArgumentException(
                "Timecard.remove - 'timecard' can not be null");
        }
        this.getHibernateTemplate().delete(timecard);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Long id)
    {
        if (id == null)
        {
            throw new IllegalArgumentException(
                "Timecard.remove - 'id' can not be null");
        }
        Timecard entity = this.get(id);
        if (entity != null)
        {
            this.remove(entity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Collection<Timecard> entities)
    {
        if (entities == null)
        {
            throw new IllegalArgumentException(
                "Timecard.remove - 'entities' can not be null");
        }
        for (Timecard entity : entities)
        {
            this.getHibernateTemplate().delete(entity);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List findByCriteria(final TimecardSearchCriteriaVO criteria)
    {
        if (criteria == null)
        {
            throw new IllegalArgumentException(
                "org.andromda.timetracker.domain.TimecardDao.findByCriteria(TimecardSearchCriteriaVO criteria) - 'criteria' can not be null");
        }
        try
        {
            return this.handleFindByCriteria(criteria);
        }
        catch (Throwable th)
        {
            throw new RuntimeException(
            "Error performing 'TimecardDao.findByCriteria(TimecardSearchCriteriaVO criteria)' --> " + th,
            th);
        }
    }

    /**
     * Performs the core logic for {@link #findByCriteria(TimecardSearchCriteriaVO)}
     * @param criteria
     * @return List<?>
     * @throws Exception
     */
    protected abstract List<?> handleFindByCriteria(TimecardSearchCriteriaVO criteria)
        throws Exception;

    /**
     * Allows transformation of entities into value objects
     * (or something else for that matter), when the <code>transform</code>
     * flag is set to one of the constants defined in <code>TimecardDao</code>, please note
     * that the {@link #TRANSFORM_NONE} constant denotes no transformation, so the entity itself
     * will be returned.
     * <p>
     * This method will return instances of these types:
     * <ul>
     *   <li>{@link Timecard} - {@link #TRANSFORM_NONE}</li>
     *   <li>{@link TimecardSummaryVO} - {@link #TRANSFORM_TIMECARDSUMMARYVO}</li>
     *   <li>{@link TimecardVO} - {@link #TRANSFORM_TIMECARDVO}</li>
     * </ul>
     *
     * If the integer argument value is unknown {@link #TRANSFORM_NONE} is assumed.
     *
     * @param transform one of the constants declared in {@link TimecardDao}
     * @param entity an entity that was found
     * @return the transformed entity (i.e. new value object, etc)
     * @see TimecardDao#transformEntity(int, Timecard)
     */
    public Object transformEntity(final int transform, final Timecard entity)
    {
        Object target = null;
        if (entity != null)
        {
            switch (transform)
            {
                case TRANSFORM_TIMECARDSUMMARYVO :
                    target = toTimecardSummaryVO(entity);
                    break;
                case TRANSFORM_TIMECARDVO :
                    target = toTimecardVO(entity);
                    break;
                case TimecardDao.TRANSFORM_NONE : // fall-through
                default:
                    target = entity;
            }
        }
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transformEntities(final int transform, final Collection<?> entities)
    {
        switch (transform)
        {
            case TRANSFORM_TIMECARDSUMMARYVO :
                toTimecardSummaryVOCollection(entities);
                break;
            case TRANSFORM_TIMECARDVO :
                toTimecardVOCollection(entities);
                break;
            case TimecardDao.TRANSFORM_NONE : // fall-through
                default:
                // do nothing;
        }
    }

    /**
     * @see TimecardDao#toEntities(Collection)
     */
    public void toEntities(final Collection<?> results)
    {
        if (results != null)
        {
            CollectionUtils.transform(results, this.ENTITYTRANSFORMER);
        }
    }

    /**
     * This anonymous transformer is designed to transform report query results
     * (which result in an array of entities) to {@link Timecard}
     * using the Jakarta Commons-Collections Transformation API.
     */
    private Transformer ENTITYTRANSFORMER =
        new Transformer()
        {
            public Object transform(Object input)
            {
                Object result = null;
                if (input instanceof Object[])
                {
                    result = toEntity((Object[])input);
                }
                else if (input instanceof Timecard)
                {
                    result = input;
                }
                return result;
            }
        };

    /**
     * @param row
     * @return Timecard
     */
    protected Timecard toEntity(Object[] row)
    {
        Timecard target = null;
        if (row != null)
        {
            final int numberOfObjects = row.length;
            for (int ctr = 0; ctr < numberOfObjects; ctr++)
            {
                final Object object = row[ctr];
                if (object instanceof Timecard)
                {
                    target = (Timecard)object;
                    break;
                }
            }
        }
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public final Collection<TimecardSummaryVO> toTimecardSummaryVOCollection(Collection<?> entities)
    {
        Collection<TimecardSummaryVO> result = new ArrayList<TimecardSummaryVO>();
        if (entities != null)
        {
            CollectionUtils.transform(entities, this.TIMECARDSUMMARYVO_TRANSFORMER);
            result.addAll((Collection<? extends TimecardSummaryVO>) entities);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public final TimecardSummaryVO[] toTimecardSummaryVOArray(Collection<?> entities)
    {
        TimecardSummaryVO[] result = null;
        if (entities != null)
        {
            // Unchecked transformation: reuses entities Collection
            final Collection collection = new ArrayList(entities);
            this.toTimecardSummaryVOCollection(collection);
            result = (TimecardSummaryVO[]) collection.toArray(new TimecardSummaryVO[collection.size()]);
        }
        return result;
    }

    /**
     * Default implementation for transforming the results of a report query into a value object. This
     * implementation exists for convenience reasons only. It needs only be overridden in the
     * {@link TimecardDaoImpl} class if you intend to use reporting queries.
     * @param row
     * @return toTimecardSummaryVO(this.toEntity(row))
     * @see TimecardDao#toTimecardSummaryVO(Timecard)
     */
    protected TimecardSummaryVO toTimecardSummaryVO(Object[] row)
    {
        return this.toTimecardSummaryVO(this.toEntity(row));
    }

    /**
     * This anonymous transformer is designed to transform entities or report query results
     * (which result in an array of objects) to {@link TimecardSummaryVO}
     * using the Jakarta Commons-Collections Transformation API.
     */
    private Transformer TIMECARDSUMMARYVO_TRANSFORMER =
        new Transformer()
        {
            public Object transform(Object input)
            {
                Object result = null;
                if (input instanceof Timecard)
                {
                    result = toTimecardSummaryVO((Timecard)input);
                }
                else if (input instanceof Object[])
                {
                    result = toTimecardSummaryVO((Object[])input);
                }
                return result;
            }
        };

    /**
     * {@inheritDoc}
     */
    @Override
    public final void timecardSummaryVOToEntityCollection(Collection<?> instances)
    {
        if (instances != null)
        {
            for (final Iterator<?> iterator = instances.iterator(); iterator.hasNext();)
            {
                // - remove an objects that are null or not of the correct instance
                if (!(iterator.next() instanceof TimecardSummaryVO))
                {
                    iterator.remove();
                }
            }
            CollectionUtils.transform(instances, this.TimecardSummaryVOToEntityTransformer);
        }
    }

    private final Transformer TimecardSummaryVOToEntityTransformer =
        new Transformer()
        {
            public Object transform(Object input)
            {
                return timecardSummaryVOToEntity((TimecardSummaryVO)input);
            }
        };


    /**
     * {@inheritDoc}
     */
    @Override
    public void toTimecardSummaryVO(
        Timecard source,
        TimecardSummaryVO target)
    {
        target.setId(source.getId());
        target.setStatus(source.getStatus());
        target.setStartDate(source.getStartDate());
        target.setComments(source.getComments());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimecardSummaryVO toTimecardSummaryVO(final Timecard entity)
    {
        TimecardSummaryVO target = null;
        if (entity != null)
        {
            target =  new TimecardSummaryVO();
            this.toTimecardSummaryVO(entity, target);
        }
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timecardSummaryVOToEntity(
        TimecardSummaryVO source,
        Timecard target,
        boolean copyIfNull)
    {
        if (copyIfNull || source.getComments() != null)
        {
            target.setComments(source.getComments());
        }
        if (copyIfNull || source.getStartDate() != null)
        {
            target.setStartDate(source.getStartDate());
        }
        if (copyIfNull || source.getStatus() != null)
        {
            target.setStatus(source.getStatus());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public final Collection<TimecardVO> toTimecardVOCollection(Collection<?> entities)
    {
        Collection<TimecardVO> result = new ArrayList<TimecardVO>();
        if (entities != null)
        {
            CollectionUtils.transform(entities, this.TIMECARDVO_TRANSFORMER);
            result.addAll((Collection<? extends TimecardVO>) entities);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public final TimecardVO[] toTimecardVOArray(Collection<?> entities)
    {
        TimecardVO[] result = null;
        if (entities != null)
        {
            // Unchecked transformation: reuses entities Collection
            final Collection collection = new ArrayList(entities);
            this.toTimecardVOCollection(collection);
            result = (TimecardVO[]) collection.toArray(new TimecardVO[collection.size()]);
        }
        return result;
    }

    /**
     * Default implementation for transforming the results of a report query into a value object. This
     * implementation exists for convenience reasons only. It needs only be overridden in the
     * {@link TimecardDaoImpl} class if you intend to use reporting queries.
     * @param row
     * @return toTimecardVO(this.toEntity(row))
     * @see TimecardDao#toTimecardVO(Timecard)
     */
    protected TimecardVO toTimecardVO(Object[] row)
    {
        return this.toTimecardVO(this.toEntity(row));
    }

    /**
     * This anonymous transformer is designed to transform entities or report query results
     * (which result in an array of objects) to {@link TimecardVO}
     * using the Jakarta Commons-Collections Transformation API.
     */
    private Transformer TIMECARDVO_TRANSFORMER =
        new Transformer()
        {
            public Object transform(Object input)
            {
                Object result = null;
                if (input instanceof Timecard)
                {
                    result = toTimecardVO((Timecard)input);
                }
                else if (input instanceof Object[])
                {
                    result = toTimecardVO((Object[])input);
                }
                return result;
            }
        };

    /**
     * {@inheritDoc}
     */
    @Override
    public final void timecardVOToEntityCollection(Collection<?> instances)
    {
        if (instances != null)
        {
            for (final Iterator<?> iterator = instances.iterator(); iterator.hasNext();)
            {
                // - remove an objects that are null or not of the correct instance
                if (!(iterator.next() instanceof TimecardVO))
                {
                    iterator.remove();
                }
            }
            CollectionUtils.transform(instances, this.TimecardVOToEntityTransformer);
        }
    }

    private final Transformer TimecardVOToEntityTransformer =
        new Transformer()
        {
            public Object transform(Object input)
            {
                return timecardVOToEntity((TimecardVO)input);
            }
        };


    /**
     * {@inheritDoc}
     */
    @Override
    public void toTimecardVO(
        Timecard source,
        TimecardVO target)
    {
        target.setId(source.getId());
        target.setStatus(source.getStatus());
        target.setStartDate(source.getStartDate());
        target.setComments(source.getComments());
        // No conversion for target.allocations (can't convert source.getAllocations():org.andromda.timetracker.domain.TimeAllocation to org.andromda.timetracker.vo.TimeAllocationVO[])
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimecardVO toTimecardVO(final Timecard entity)
    {
        TimecardVO target = null;
        if (entity != null)
        {
            target =  new TimecardVO();
            this.toTimecardVO(entity, target);
        }
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timecardVOToEntity(
        TimecardVO source,
        Timecard target,
        boolean copyIfNull)
    {
        if (copyIfNull || source.getComments() != null)
        {
            target.setComments(source.getComments());
        }
        if (copyIfNull || source.getStartDate() != null)
        {
            target.setStartDate(source.getStartDate());
        }
        if (copyIfNull || source.getStatus() != null)
        {
            target.setStatus(source.getStatus());
        }
    }

    /**
     * Gets the current <code>principal</code> if one has been set,
     * otherwise returns <code>null</code>.
     *
     * @return the current principal
     */
    protected Principal getPrincipal()
    {
        return PrincipalStore.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public PaginationResult search(final int transform, final int pageNumber, final int pageSize, final Search search)
    {
        try
        {
            search.setPageNumber(pageNumber);
            search.setPageSize(pageSize);
            final PropertySearch propertySearch = new PropertySearch(
                this.getSession(false), TimecardImpl.class, search);
            final List results = propertySearch.executeAsList();
            this.transformEntities(transform, results);
            return new PaginationResult(results.toArray(new Object[results.size()]), propertySearch.getTotalCount());
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaginationResult search(final int pageNumber, final int pageSize, final Search search)
    {
        return this.search(TimecardDao.TRANSFORM_NONE, pageNumber, pageSize, search);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<?> search(final int transform, final Search search)
    {
        try
        {
            final PropertySearch propertySearch = new PropertySearch(
                this.getSession(false), TimecardImpl.class, search);
            final Set<?> results = propertySearch.executeAsSet();
            this.transformEntities(transform, results);
            return results;
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set<Timecard> search(final Search search)
    {
        return (Set<Timecard>) this.search(TimecardDao.TRANSFORM_NONE, search);
    }

    /**
     * Executes and returns the given Hibernate queryObject as a {@link PaginationResult} instance.
     * @param queryObject
     * @param transform
     * @param pageNumber
     * @param pageSize
     * @return PaginationResult
     */
    @SuppressWarnings({ "unchecked" })
    protected PaginationResult getPaginationResult(
        final Query queryObject,
        final int transform, int pageNumber, int pageSize)
    {
        try
        {
            final ScrollableResults scrollableResults = queryObject.scroll();
            scrollableResults.last();
            int totalCount = scrollableResults.getRowNumber();
            totalCount = totalCount >= 0 ? totalCount + 1 : 0;
            if (pageNumber > 0 && pageSize > 0)
            {
                queryObject.setFirstResult(this.calculateFirstResult(pageNumber, pageSize));
                queryObject.setMaxResults(pageSize);
            }
            // Unchecked transformation because Set object is reused, cannot be strongly typed.
            Set results = new LinkedHashSet(queryObject.list());
            transformEntities(transform, results);
            return new PaginationResult(results.toArray(new Object[results.size()]), totalCount);
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    // spring-hibernate-dao-base merge-point
}