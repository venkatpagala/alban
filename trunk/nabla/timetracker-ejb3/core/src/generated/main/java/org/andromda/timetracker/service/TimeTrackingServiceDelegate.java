// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by ServiceDelegate.vsl in andromda-ejb3-cartridge on 03/30/2012 03:34:20.
//
package org.andromda.timetracker.service;

import java.util.Properties;
import javax.naming.NamingException;
import org.andromda.timetracker.ServiceDelegateBase;
import org.andromda.timetracker.ServiceLocator;
import org.andromda.timetracker.vo.TimecardSearchCriteriaVO;
import org.andromda.timetracker.vo.TimecardSummaryVO;

/**
 * Web service delegator for {@link org.andromda.timetracker.service.TimeTrackingServiceBean}.
 *
 * @see org.andromda.timetracker.service.TimeTrackingServiceBean
 */
public class TimeTrackingServiceDelegate
    extends ServiceDelegateBase
{
    /**
     * Default constructor
     */
    public TimeTrackingServiceDelegate()
    {
        super();
    }

    /**
     * Constructor setting the envirinment properties.
     *
     * @param properties
     */
    public TimeTrackingServiceDelegate(Properties properties)
    {
        super(properties);
    }

    /**
     * Gets an instance of {@link TimeTrackingServiceRemote}
     */
    private TimeTrackingServiceRemote getTimeTrackingServiceRemote()
        throws NamingException
    {
        return ServiceLocator.getInstance().get_org_andromda_timetracker_service_TimeTrackingServiceBean_Remote(getProperties());
    }

    /**
     * @see org.andromda.timetracker.service.TimeTrackingServiceBean#findTimecards(TimecardSearchCriteriaVO)
     *
     * Call the session bean operation using appropriate view type
     */
    public TimecardSummaryVO[] findTimecards(TimecardSearchCriteriaVO criteria)
    {
        try
        {
            return getTimeTrackingServiceRemote().findTimecards(criteria);
        }
        catch (NamingException ex)
        {
            throw new TimeTrackingServiceException(
                "Error performing 'org.andromda.timetracker.service.TimeTrackingService.findTimecards(TimecardSearchCriteriaVO criteria)' --> " + ex, ex);
        }
    }

}