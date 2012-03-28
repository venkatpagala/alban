// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by DaoLocal.vsl overrided in andromda-ejb3-cartridge on 03/27/2012 13:19:39.
//
package org.andromda.timetracker.domain;

import java.util.Collection;
import org.andromda.timetracker.vo.TaskVO;

/**
 * @see Task
 */
public interface TaskDao
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
     * {@link TaskVO}.
     */
    public static final int TRANSFORM_TASKVO = 1;

    /**
     * Copies the fields of the specified entity to the target value object.  This method is similar to
     * toTaskVO(), but it does not handle any attributes in the target
     * value object that are "read-only" (as those do not have setter methods exposed).
     * @param sourceEntity
     * @param targetVO
     */
    public void toTaskVO(
        Task sourceEntity,
        TaskVO targetVO);

    /**
     * Converts this DAO's entity to an object of type {@link TaskVO}.
     * @param entity
     * @return TaskVO
     */
    public TaskVO toTaskVO(Task entity);

    /**
     * Converts this DAO's entity to a Collection of instances of type {@link TaskVO}.
     * @param entities
     */
    public void toTaskVOCollection(Collection entities);

    /**
     * Copies the fields of {@link TaskVO} to the specified entity.
     * @param sourceVO
     * @param targetEntity
     * @param copyIfNull If FALSE, the value object's field will not be copied to the entity if the value is NULL. If TRUE,
     *        it will be copied regardless of its value.
     */
    public void taskVOToEntity(
        TaskVO sourceVO,
        Task targetEntity,
        boolean copyIfNull);

    /**
     * Converts an instance of type {@link TaskVO} to this DAO's entity.
     * @param taskVO
     * @return Task
     */
    public Task taskVOToEntity(TaskVO taskVO);

    /**
     * Converts a Collection of instances of type {@link TaskVO} to this
     * DAO's entity.
     * @param instances
     */
    public void taskVOToEntityCollection(Collection instances);

    /**
     * Loads an instance of Task from the persistent store.
     * @param id the identifier of the entity to load.
     * @throws TaskDaoException
     */
    public Task load(Long id)
        throws TaskDaoException;

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
     * @throws TaskDaoException
     */
    public Object load(int transform, Long id)
        throws TaskDaoException;

    /**
     * Loads all entities of type {@link Task}.
     *
     * @return the loaded entities.
     * @throws TaskDaoException
     */
    public Collection<Task> loadAll()
        throws TaskDaoException;

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
     * @throws TaskDaoException
     */
    public Collection loadAll(final int transform)
        throws TaskDaoException;

    /**
     * Creates an instance of Task and adds it to the persistent store.
     * @param task
     * @return Task
     * @throws TaskDaoException
     */
    public Task create(Task task)
        throws TaskDaoException;

    /**
     *   Does the same thing as {@link #create(Task)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param task
     * @return Object
     * @throws TaskDaoException
     */
    public Object create(int transform, Task task)
        throws TaskDaoException;

    /**
     * Creates a new instance of Task and adds
     * from the passed in <code>entities</code> collection
     *
     * @param entities the collection of Task
     *        instances to create.
     * @return the created instances. Collection<Task>
     * @throws TaskDaoException
     */
    public Collection<Task> create(Collection<Task> entities)
        throws TaskDaoException;

    /**
     *   Does the same thing as {@link #create(Task)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entities (into value objects for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param entities
     * @return the created instances. Collection
     * @throws TaskDaoException
     */
    public Collection create(int transform, Collection<Task> entities)
        throws TaskDaoException;

    /**
     *   Creates a new <code>Task</code>
     *   instance from <strong>all</strong> attributes and adds it to
     *   the persistent store.
     * @param name
     * @return the created instances. Task
     * @throws TaskDaoException
     */
    public Task create(
        String name)
        throws TaskDaoException;

    /**
     *   Does the same thing as {@link #create(String)} with an
     *   additional flag called <code>transform</code>.  If this flag is set to <code>TRANSFORM_NONE</code> then
     *   the returned entity will <strong>NOT</strong> be transformed.  If this flag is any of the other constants
     *   defined here then the result <strong>WILL BE</strong> passed through an operation which can optionally
     *   transform the entity (into a value object for example).  By default, transformation does
     *   not occur.
     *
     * @param transform
     * @param name
     * @return the created instances. Object
     * @throws TaskDaoException
     */
    public Object create(
        int transform,
        String name)
        throws TaskDaoException;


    /**
     * Updates the <code>task</code> instance in the persistent store.
     * @param task
     * @throws TaskDaoException
     */
    public void update(Task task)
        throws TaskDaoException;

    /**
     * Updates all instances in the <code>entities</code> collection in the persistent store.
     * @param entities
     * @throws TaskDaoException
     */
    public void update(Collection<Task> entities)
        throws TaskDaoException;

    /**
     * Removes the instance of Task from the persistent store.
     * @param task
     * @throws TaskDaoException
     */
    public void remove(Task task)
        throws TaskDaoException;

    /**
     * Removes the instance of Task having the given
     * <code>identifier</code> from the persistent store.
     * @param id
     * @throws TaskDaoException
     */
    public void remove(Long id)
        throws TaskDaoException;

    /**
     * Removes all entities in the given <code>entities<code> collection.
     * @param entities
     * @throws TaskDaoException
     */
    public void remove(Collection<Task> entities)
        throws TaskDaoException;

}