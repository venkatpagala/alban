// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by ServiceDelegate.vsl in andromda-ejb3-cartridge on 03/27/2012 13:19:39.
//
package org.andromda.timetracker.service;

import java.util.Properties;
import javax.naming.NamingException;
import org.andromda.timetracker.ServiceDelegateBase;
import org.andromda.timetracker.ServiceLocator;
import org.andromda.timetracker.vo.UserDetailsVO;

/**
 * Web service delegator for {@link org.andromda.timetracker.service.SecurityServiceBean}.
 *
 * @see org.andromda.timetracker.service.SecurityServiceBean
 */
public class SecurityServiceDelegate
    extends ServiceDelegateBase
{
    /**
     * Default constructor
     */
    public SecurityServiceDelegate()
    {
        super();
    }

    /**
     * Constructor setting the envirinment properties.
     *
     * @param properties
     */
    public SecurityServiceDelegate(Properties properties)
    {
        super(properties);
    }

    /**
     * Gets an instance of {@link SecurityServiceRemote}
     */
    private SecurityServiceRemote getSecurityServiceRemote()
        throws NamingException
    {
        return ServiceLocator.getInstance().get_org_andromda_timetracker_service_SecurityServiceBean_Remote(getProperties());
    }

    /**
     * @see org.andromda.timetracker.service.SecurityServiceBean#getUserDetails(String)
     *
     * Call the session bean operation using appropriate view type
     */
    public UserDetailsVO getUserDetails(String username)
    {
        try
        {
            return getSecurityServiceRemote().getUserDetails(username);
        }
        catch (NamingException ex)
        {
            throw new SecurityServiceException(
                "Error performing 'org.andromda.timetracker.service.SecurityService.getUserDetails(String username)' --> " + ex, ex);
        }
    }

}