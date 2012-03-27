// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by ServiceDelegate.vsl in andromda-ejb3-cartridge on 03/26/2012 15:24:03.
//
package org.andromda.timetracker.service;

import java.util.Properties;
import javax.naming.NamingException;
import org.andromda.timetracker.ServiceDelegateBase;
import org.andromda.timetracker.ServiceLocator;
import org.andromda.timetracker.vo.UserDetailsVO;
import org.andromda.timetracker.vo.UserVO;

/**
 * Web service delegator for {@link org.andromda.timetracker.service.UserServiceBean}.
 *
 * @see org.andromda.timetracker.service.UserServiceBean
 */
public class UserServiceDelegate
    extends ServiceDelegateBase
{
    /**
     * Default constructor
     */
    public UserServiceDelegate()
    {
        super();
    }

    /**
     * Constructor setting the envirinment properties.
     *
     * @param properties
     */
    public UserServiceDelegate(Properties properties)
    {
        super(properties);
    }

    /**
     * Gets an instance of {@link UserServiceRemote}
     */
    private UserServiceRemote getUserServiceRemote()
        throws NamingException
    {
        return ServiceLocator.getInstance().get_org_andromda_timetracker_service_UserServiceBean_Remote(getProperties());
    }

    /**
     * @see org.andromda.timetracker.service.UserServiceBean#getAllUsers()
     *
     * Call the session bean operation using appropriate view type
     */
    public UserVO[] getAllUsers()
        throws UserDoesNotExistException
    {
        try
        {
            return getUserServiceRemote().getAllUsers();
        }
        catch (UserDoesNotExistException ex)
        {
            throw ex;
        }
        catch (NamingException ex)
        {
            throw new UserServiceException(
                "Error performing 'org.andromda.timetracker.service.UserService.getAllUsers()' --> " + ex, ex);
        }
    }

    /**
     * @see org.andromda.timetracker.service.UserServiceBean#getUser(String)
     *
     * Call the session bean operation using appropriate view type
     */
    public UserVO getUser(String username)
        throws UserDoesNotExistException
    {
        try
        {
            return getUserServiceRemote().getUser(username);
        }
        catch (UserDoesNotExistException ex)
        {
            throw ex;
        }
        catch (NamingException ex)
        {
            throw new UserServiceException(
                "Error performing 'org.andromda.timetracker.service.UserService.getUser(String username)' --> " + ex, ex);
        }
    }

    /**
     * @see org.andromda.timetracker.service.UserServiceBean#registerUser(UserDetailsVO)
     *
     * Call the session bean operation using appropriate view type
     */
    public UserDetailsVO registerUser(UserDetailsVO userDetailVO)
        throws UserDoesNotExistException
    {
        try
        {
            return getUserServiceRemote().registerUser(userDetailVO);
        }
        catch (UserDoesNotExistException ex)
        {
            throw ex;
        }
        catch (NamingException ex)
        {
            throw new UserServiceException(
                "Error performing 'org.andromda.timetracker.service.UserService.registerUser(UserDetailsVO userDetailVO)' --> " + ex, ex);
        }
    }

    /**
     * @see org.andromda.timetracker.service.UserServiceBean#removeUser(UserVO)
     *
     * Call the session bean operation using appropriate view type
     */
    public void removeUser(UserVO userVO)
        throws UserDoesNotExistException
    {
        try
        {
            getUserServiceRemote().removeUser(userVO);
        }
        catch (UserDoesNotExistException ex)
        {
            throw ex;
        }
        catch (NamingException ex)
        {
            throw new UserServiceException(
                "Error performing 'org.andromda.timetracker.service.UserService.removeUser(UserVO userVO)' --> " + ex, ex);
        }
    }

}