// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SessionRemote.vsl in andromda-ejb3-cartridge on 03/30/2012 03:34:20.
//
package org.andromda.timetracker.service;

import javax.ejb.Remote;
import org.andromda.timetracker.vo.UserDetailsVO;

/**
 * Remote component interface for the SecurityServiceBean session bean.
 * 
 */
@Remote
public interface SecurityServiceRemote
    extends SecurityService
{

    // ------------ Business Methods  -----------------

    /**
     * 
     */
    public UserDetailsVO getUserDetails(String username);

}