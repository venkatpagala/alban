// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SessionBeanBase.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto18.a;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Autogenerated EJB3 session bean base class UserEndPointServiceBean which contains
 * method level annotations for the session bean.  All method level annotations
 * are inherited by the extending session bean class.
 * 
 */
public abstract class UserEndPointServiceBase implements UserEndPointService
{

    // ------ Session Context Injection ------

    @Resource
    protected SessionContext context;

    // ------ Persistence Context Definitions --------
    /**
     * Inject persistence context howtomodel
     */
    @PersistenceContext(unitName = "howtomodel")
    protected EntityManager emanager;


    // ------ DAO Injection Definitions --------

    /**
     * Inject DAO UserDao
     */
    @EJB    
    private UserDao userDao;

    // --------------- Constructors ---------------

    public UserEndPointServiceBase()
    {
        super();
    }

    // ------ DAO Getters --------

    /**
     * Get the injected DAO UserDao
     */
    protected UserDao getUserDao()
    {
        return this.userDao;
    }

    // -------- Business Methods  --------------

    /**
     * 
     */
    public void addUser(String name) throws UserException
    {
        if (name == null)
        {
            throw new IllegalArgumentException("org.andromda.test.howto18.a.UserEndPointServiceBean.addUser(String name) - 'name' can not be null");
        }
        try
        {
            this.handleAddUser(name);
        }
        catch (UserException ex)
        {
            throw ex;
        }
        catch (Throwable th)
        {
            throw new UserEndPointServiceException("Error performing 'UserEndPointService.addUser(String name)' --> " + th, th);
        }
    }

    /**
     * Performs the core logic for {@link #addUser(String)}
     */
    protected abstract void handleAddUser(String name) throws Exception;

    /**
     * 
     */
    public User getUser(Long id) throws UserException
    {
        try
        {
            return this.handleGetUser(id);
        }
        catch (UserException ex)
        {
            throw ex;
        }
        catch (Throwable th)
        {
            throw new UserEndPointServiceException("Error performing 'UserEndPointService.getUser(Long id)' --> " + th, th);
        }
    }

    /**
     * Performs the core logic for {@link #getUser(Long)}
     */
    protected abstract User handleGetUser(Long id) throws Exception;


    // -------- Lifecycle Callbacks --------------

}