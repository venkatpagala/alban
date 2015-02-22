// license-header java merge-point
// Generated by: crud/SpringCrudDaoBase.vsl overrided in andromda-spring-cartridge.
package org.andromda.timetracker.domain.crud;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.andromda.timetracker.domain.Timecard;
import org.andromda.timetracker.domain.TimecardImpl;
import org.andromda.timetracker.domain.User;
import org.andromda.timetracker.domain.UserDao;
import org.andromda.timetracker.domain.UserImpl;
import org.andromda.timetracker.domain.UserRole;
import org.andromda.timetracker.domain.UserRoleDao;
import org.andromda.timetracker.domain.UserRoleImpl;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * TODO: Model Documentation for User
 */
public final class UserManageableDaoBase
    extends HibernateDaoSupport
    implements UserManageableDao
{
    private UserDao dao;

    /**
     * @param daoIn
     */
    public void setDao(UserDao daoIn)
    {
        this.dao = daoIn;
    }

    /**
     * @return dao
     */
    protected UserDao getDao()
    {
        return this.dao;
    }

    private UserRoleDao rolesDao = null;

    /**
     * @param rolesDaoIn
     */
    public void setRolesDao(UserRoleDao rolesDaoIn)
    {
        this.rolesDao = rolesDaoIn;
    }

    /**
     * @return dao UserRoleDao
     */
    protected UserRoleDao getRolesDao()
    {
        return this.rolesDao;
    }

    @SuppressWarnings({"unchecked"})
    private Set<UserRole> findUserRoleByIds(Long[] ids)
    {
        final Session session = this.getSession(false);
        try
        {
            final Criteria criteria = session.createCriteria(UserRoleImpl.class);
            criteria.add(Restrictions.in("id", ids));
            return new HashSet<UserRole>(criteria.list());
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    @SuppressWarnings({"unchecked"})
    private Set<Timecard> findTimecardByIds(Long[] ids)
    {
        final Session session = this.getSession(false);
        try
        {
            final Criteria criteria = session.createCriteria(TimecardImpl.class);
            criteria.add(Restrictions.in("id", ids));
            return new HashSet<Timecard>(criteria.list());
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    /**
     * @param username TODO: Model Documentation for User.username
     * @param password TODO: Model Documentation for User.password
     * @param firstName TODO: Model Documentation for User.firstName
     * @param lastName TODO: Model Documentation for User.lastName
     * @param email TODO: Model Documentation for User.email
     * @param isEnable TODO: Model Documentation for User.isEnable
     * @param creationDate TODO: Model Documentation for User.creationDate
     * @param comment TODO: Model Documentation for User.comment
     * @param id TODO: Model Documentation for User.id
     * @param roles TODO: Model Documentation for UserRole
     * @return entity
     * @see UserDao#create(User)
     */
    @SuppressWarnings("cast")
    public User create(String username, String password, String firstName, String lastName, String email, boolean isEnable, Date creationDate, String comment, Long id, Long[] roles)
    {
        final User entity = new UserImpl();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setEmail(email);
        entity.setIsEnable(isEnable);
        entity.setCreationDate(creationDate);
        entity.setComment(comment);
        entity.setId(id);
        @SuppressWarnings("unchecked")
        final Set<UserRole> rolesEntities = (roles != null && roles.length > 0)
            ? this.findUserRoleByIds(roles)
            : Collections.EMPTY_SET;

        entity.setRoles(rolesEntities);


        return (User)this.getDao().create(entity);
    }

    /**
     * @param id
     * @return entity
     * @see UserDao#load(Long)
     */
    public User readById(Long id)
    {
        return getDao().load(id);
    }

    /**
     * @param username TODO: Model Documentation for User.username
     * @param password TODO: Model Documentation for User.password
     * @param firstName TODO: Model Documentation for User.firstName
     * @param lastName TODO: Model Documentation for User.lastName
     * @param email TODO: Model Documentation for User.email
     * @param isEnable TODO: Model Documentation for User.isEnable
     * @param creationDate TODO: Model Documentation for User.creationDate
     * @param comment TODO: Model Documentation for User.comment
     * @param id TODO: Model Documentation for User.id
     * @param roles TODO: Model Documentation for UserRole
     * @return List<User>
     */
    @SuppressWarnings("unchecked")
    public List<User> read(String username, String password, String firstName, String lastName, String email, Boolean isEnable, Date creationDate, String comment, Long id, Long[] roles)
    {
        final Session session = getSession(false);

        try
        {
            final Criteria criteria = session.createCriteria(UserImpl.class);

            if (username != null)
            {
                criteria.add(Restrictions.ilike("username", username, MatchMode.START));
            }
            if (password != null)
            {
                criteria.add(Restrictions.ilike("password", password, MatchMode.START));
            }
            if (firstName != null)
            {
                criteria.add(Restrictions.ilike("firstName", firstName, MatchMode.START));
            }
            if (lastName != null)
            {
                criteria.add(Restrictions.ilike("lastName", lastName, MatchMode.START));
            }
            if (email != null)
            {
                criteria.add(Restrictions.ilike("email", email, MatchMode.START));
            }
            if (isEnable != null)
            {
                criteria.add(Restrictions.eq("isEnable", isEnable.booleanValue()));
            }
            if (creationDate != null)
            {
                // we check whether or not the user supplied time information within this particular date argument
                // if he/she didn't we assume he/she wishes to search in the scope of the entire day
                final Calendar calendar = new GregorianCalendar();
                calendar.setTime(creationDate);
                if ( calendar.get(Calendar.HOUR) != 0
                     || calendar.get(Calendar.MINUTE) != 0
                     || calendar.get(Calendar.SECOND) != 0
                     || calendar.get(Calendar.MILLISECOND) != 0 )
                {
                    criteria.add(Restrictions.eq("creationDate", creationDate));
                }
                else
                {
                    calendar.add(Calendar.DATE, 1);
                    calendar.add(Calendar.MILLISECOND, -1);
                    criteria.add(Restrictions.between("creationDate", creationDate, calendar.getTime()));
                }
            }
            if (comment != null)
            {
                criteria.add(Restrictions.ilike("comment", comment, MatchMode.START));
            }
            if (id != null)
            {
                criteria.add(Restrictions.eq("id", id));
            }
            if (roles != null && roles.length > 0)
            {
                criteria.createCriteria("roles").add(Restrictions.in("id", roles));
            }
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(250);

            return criteria.list();
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    /**
     * @return criteria.list()
     */
    @SuppressWarnings("unchecked")
    public List<User> readAll()
    {
        final Session session = getSession(false);

        try
        {
            final Criteria criteria = session.createCriteria(UserImpl.class);
            criteria.setMaxResults(250);
            return criteria.list();
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }

    /**
     * @return backingLists Map<String,Object>
     */
    public Map<String,Object> readBackingLists()
    {
        final Map<String,Object> lists = new HashMap<String,Object>();
        final Session session = this.getSession();

        try
        {
            // Put the manageableAssociationEnds in the session query
            lists.put("roles", session.createQuery("select item.id, item.id from UserRoleImpl item order by item.id").list());
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
        return lists;
    }

    /**
     * @param username TODO: Model Documentation for User.username
     * @param password TODO: Model Documentation for User.password
     * @param firstName TODO: Model Documentation for User.firstName
     * @param lastName TODO: Model Documentation for User.lastName
     * @param email TODO: Model Documentation for User.email
     * @param isEnable TODO: Model Documentation for User.isEnable
     * @param creationDate TODO: Model Documentation for User.creationDate
     * @param comment TODO: Model Documentation for User.comment
     * @param id TODO: Model Documentation for User.id
     * @param roles TODO: Model Documentation for UserRole
     * @return getDao().update(entity)
     * @see UserDao#update(User)
     */
    public User update(String username, String password, String firstName, String lastName, String email, boolean isEnable, Date creationDate, String comment, Long id, Long[] roles)
    {
        final User entity = this.getDao().load(id);

        entity.setUsername(username);
        entity.setPassword(password);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setEmail(email);
        entity.setIsEnable(isEnable);
        entity.setCreationDate(creationDate);
        entity.setComment(comment);
        @SuppressWarnings("unchecked")
        final Set<UserRole> rolesEntities = (roles != null && roles.length > 0)
            ? this.findUserRoleByIds(roles)
            : Collections.EMPTY_SET;

        entity.getRoles().clear();
        entity.getRoles().addAll(rolesEntities);


        this.getDao().update(entity);
        return entity;
    }


    /**
     * @param ids
     */
    public void delete(Long[] ids)
    {
        final Session session = getSession(false);
        try
        {
            final Query queryObject =
                session.createQuery("delete UserImpl where id in (:ids)");
            queryObject.setParameterList("ids", ids);
            queryObject.executeUpdate();
        }
        catch (HibernateException ex)
        {
            throw super.convertHibernateAccessException(ex);
        }
    }
}