// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by ServiceDelegate.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto3.b;

import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import org.andromda.test.ServiceDelegateBase;
import org.andromda.test.ServiceLocator;

/**
 * Web service delegator for {@link org.andromda.test.howto3.b.RentalServiceBean}.
 *
 * @see org.andromda.test.howto3.b.RentalServiceBean
 */
public class RentalServiceDelegate
    extends ServiceDelegateBase
{
    /**
     * Default constructor
     */
    public RentalServiceDelegate()
    {
        super();
    }

    /**
     * Constructor setting the envirinment properties.
     *
     * @param properties
     */
    public RentalServiceDelegate(Properties properties)
    {
        super(properties);
    }

    /**
     * Gets an instance of {@link RentalServiceRemote}
     */
    private RentalServiceRemote getRentalServiceRemote()
        throws NamingException
    {
        return ServiceLocator.getInstance().get_org_andromda_test_howto3_b_RentalServiceBean_Remote(getProperties());
    }

    /**
     * Call the session bean operation using appropriate view type
     * @see org.andromda.test.howto3.b.RentalServiceBean#getAllCars()
     *
     * @return List
     */
    public List getAllCars()
    {
        try
        {
            return getRentalServiceRemote().getAllCars();
        }
        catch (NamingException ex)
        {
            throw new RentalServiceException(
                "Error performing 'org.andromda.test.howto3.b.RentalService.getAllCars()' --> " + ex, ex);
        }
    }

    /**
     * Call the session bean operation using appropriate view type
     * @see org.andromda.test.howto3.b.RentalServiceBean#getCustomersByName(String)
     *
     * @param name
     * @return List
     */
    public List getCustomersByName(String name)
    {
        try
        {
            return getRentalServiceRemote().getCustomersByName(name);
        }
        catch (NamingException ex)
        {
            throw new RentalServiceException(
                "Error performing 'org.andromda.test.howto3.b.RentalService.getCustomersByName(String name)' --> " + ex, ex);
        }
    }

}