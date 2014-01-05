// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SessionRemote.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto3.a;

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote component interface for the RentalServiceBean session bean.
 *
 * TODO: Model Documentation for org.andromda.test.howto3.a.RentalService
 *
 */
@Remote
public interface RentalServiceRemote extends RentalService
{

    // ------------ Business Methods  -----------------

    /**
     * Returns a list of all cars known by the system.
     * @return List
     */
    public List getAllCars();

    /**
     * Returns all customers with the specified name.
     * @param name
     * @return List
     */
    public List getCustomersByName(String name);

}