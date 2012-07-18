// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by ServiceDelegate.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto18.a;

import java.util.Collection;
import java.util.Properties;
import javax.naming.NamingException;
import org.andromda.test.ServiceDelegateBase;
import org.andromda.test.ServiceLocator;

/**
 * Web service delegator for {@link org.andromda.test.howto18.a.UserServiceBean}.
 *
 * @see org.andromda.test.howto18.a.UserServiceBean
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
        return ServiceLocator.getInstance().get_org_andromda_test_howto18_a_UserServiceBean_Remote(getProperties());
    }

    /**
     * @see org.andromda.test.howto18.a.UserServiceBean#addUser(User)
     *
     * Call the session bean operation using appropriate view type
     */
    public void addUser(User user)
        throws UserException
    {
        try
        {
            getUserServiceRemote().addUser(user);
        }
        catch (UserException ex)
        {
            throw ex;
        }
        catch (NamingException ex)
        {
            throw new UserServiceException(
                "Error performing 'org.andromda.test.howto18.a.UserService.addUser(User user)' --> " + ex, ex);
        }
    }

    /**
     * @see org.andromda.test.howto18.a.UserServiceBean#getUser(Long)
     *
     * Call the session bean operation using appropriate view type
     */
    public User getUser(Long id)
        throws UserException
    {
        try
        {
            return getUserServiceRemote().getUser(id);
        }
        catch (UserException ex)
        {
            throw ex;
        }
        catch (NamingException ex)
        {
            throw new UserServiceException(
                "Error performing 'org.andromda.test.howto18.a.UserService.getUser(Long id)' --> " + ex, ex);
        }
    }

    /**
     * @see org.andromda.test.howto18.a.UserServiceBean#getAllUsers()
     *
     * Call the session bean operation using appropriate view type
     */
    public Collection getAllUsers()
        throws UserException
    {
        try
        {
            return getUserServiceRemote().getAllUsers();
        }
        catch (UserException ex)
        {
            throw ex;
        }
        catch (NamingException ex)
        {
            throw new UserServiceException(
                "Error performing 'org.andromda.test.howto18.a.UserService.getAllUsers()' --> " + ex, ex);
        }
    }

}