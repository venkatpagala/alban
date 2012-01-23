// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.andromda.timetracker.service;

import org.andromda.timetracker.domain.User;
import org.andromda.timetracker.vo.UserDetailsVO;

/**
 * @see org.andromda.timetracker.service.SecurityService
 */
public class SecurityServiceImpl
    extends SecurityServiceBase
{
    /**
     * @see org.andromda.timetracker.service.SecurityService#getUserDetails(java.lang.String)
     */
    @Override
    protected UserDetailsVO handleGetUserDetails(String username)
        throws Exception
    {
        UserDetailsVO userDetailsVO = null;
        User user = getUserDao().getUserDetails(username);
        if (user != null)
        {
            userDetailsVO = getUserDao().toUserDetailsVO(user);
        }
        return userDetailsVO;
    }
}
