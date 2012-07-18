// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SessionBeanBase.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto5.a;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Autogenerated EJB3 session bean base class RentalServiceBean which contains
 * method level annotations for the session bean.  All method level annotations
 * are inherited by the extending session bean class.
 * 
 */
public abstract class RentalServiceBase implements RentalService
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
     * Inject DAO CarDao
     */
    @EJB    
    private CarDao carDao;

    /**
     * Inject DAO PersonDao
     */
    @EJB    
    private PersonDao personDao;

    // --------------- Constructors ---------------

    public RentalServiceBase()
    {
        super();
    }

    // ------ DAO Getters --------

    /**
     * Get the injected DAO CarDao
     */
    protected CarDao getCarDao()
    {
        return this.carDao;
    }

    /**
     * Get the injected DAO PersonDao
     */
    protected PersonDao getPersonDao()
    {
        return this.personDao;
    }

    // -------- Business Methods  --------------

    /**
     * Returns a list of all cars known by the system.
     */
    public List getAllCars()    {
        try
        {
            return this.handleGetAllCars();
        }
        catch (Throwable th)
        {
            throw new RentalServiceException("Error performing 'RentalService.getAllCars()' --> " + th, th);
        }
    }

    /**
     * Performs the core logic for {@link #getAllCars()}
     */
    protected abstract List handleGetAllCars() throws Exception;

    /**
     * Returns all customers with the specified name.
     */
    public List getCustomersByName(String name)    {
        if (name == null)
        {
            throw new IllegalArgumentException("org.andromda.test.howto5.a.RentalServiceBean.getCustomersByName(String name) - 'name' can not be null");
        }
        try
        {
            return this.handleGetCustomersByName(name);
        }
        catch (Throwable th)
        {
            throw new RentalServiceException("Error performing 'RentalService.getCustomersByName(String name)' --> " + th, th);
        }
    }

    /**
     * Performs the core logic for {@link #getCustomersByName(String)}
     */
    protected abstract List handleGetCustomersByName(String name) throws Exception;


    // -------- Lifecycle Callbacks --------------

}