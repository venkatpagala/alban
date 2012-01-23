// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: SpringServiceBase.vsl in andromda-spring-cartridge.
//
/**
 * TEMPLATE:    SpringServiceBase.vsl in andromda-spring cartridge
 * MODEL CLASS: Data::timetracker::org.andromda.timetracker::service::TimeTrackingService
 * STEREOTYPE:  Service
 */
package org.andromda.timetracker.service;

import java.security.Principal;
import java.util.Locale;
import org.andromda.timetracker.BeanLocator;
import org.andromda.timetracker.PrincipalStore;
import org.andromda.timetracker.domain.TimecardDao;
import org.andromda.timetracker.vo.TimecardSearchCriteriaVO;
import org.andromda.timetracker.vo.TimecardSummaryVO;
import org.springframework.context.MessageSource;

/**
 * <p>
 * Spring Service base class for <code>TimeTrackingService</code>,
 * provides access to all services and entities referenced by this service.
 * </p>
 *
 * @see TimeTrackingService
 */
public abstract class TimeTrackingServiceBase
    implements TimeTrackingService
{
    private TimecardDao timecardDao;

    /**
     * Sets the reference to <code>timecard</code>'s DAO.
     * @param timecardDaoIn
     */
    public void setTimecardDao(TimecardDao timecardDaoIn)
    {
        this.timecardDao = timecardDaoIn;
    }

    /**
     * Gets the reference to <code>timecard</code>'s DAO.
     * @return timecardDao
     */
    protected TimecardDao getTimecardDao()
    {
        return this.timecardDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimecardSummaryVO[] findTimecards(final TimecardSearchCriteriaVO criteria)
    {
        if (criteria == null)
        {
            throw new IllegalArgumentException(
                "org.andromda.timetracker.service.TimeTrackingService.findTimecards(TimecardSearchCriteriaVO criteria) - 'criteria' can not be null");
        }
        try
        {
            return this.handleFindTimecards(criteria);
        }
        catch (Throwable th)
        {
            throw new TimeTrackingServiceException(
                "Error performing 'TimeTrackingService.findTimecards(TimecardSearchCriteriaVO criteria)' --> " + th,
                th);
        }
    }

    /**
     * Performs the core logic for {@link #findTimecards(TimecardSearchCriteriaVO)}
     * @param criteria TimecardSearchCriteriaVO 
     * @return TimecardSummaryVO[]
     * @throws Exception
     */
    protected abstract TimecardSummaryVO[] handleFindTimecards(TimecardSearchCriteriaVO criteria)
        throws Exception;

    /**
     * Gets the current <code>principal</code> if one has been set,
     * otherwise returns <code>null</code>.
     *
     * @return the current principal
     */
    protected Principal getPrincipal()
    {
        return PrincipalStore.get();
    }

    /**
     * Gets the message source available to this service.
     * @return MessageSource
     */
    protected MessageSource getMessages()
    {
        return (MessageSource)
            BeanLocator.instance().getBean("messageSource");
    }

    /**
     * Gets the message having the given <code>key</code> in the underlying message bundle.
     *
     * @param key the key of the message in the messages.properties message bundle.
     * @return String
     */
    protected String getMessage(final String key)
    {
        return this.getMessages().getMessage(key, null, null);
    }

    /**
     * Gets the message having the given <code>key</code> and <code>arguments</code> in the
     * underlying message bundle.
     *
     * @param key the key of the message in the messages.properties message bundle.
     * @param arguments any arguments to substitute when resolving the message.
     * @return String
     */
    protected String getMessage(final String key, final Object[] arguments)
    {
        return this.getMessages().getMessage(key, arguments, null);
    }

    /**
     * Gets the message having the given <code>key</code> using the given <code>arguments</code>
     * for the given <code>locale</code>.
     *
     * @param key the key of the message in the messages.properties message bundle.
     * @param arguments any arguments to substitute when resolving the message.
     * @param locale the locale of the messages to retrieve.
     * @return String
     */
    protected String getMessage(
        final String key, final Object[] arguments,
        final Locale locale)
    {
        return this.getMessages().getMessage(key, arguments, locale);
    }

}