// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: hibernate/search/PropertySearch.java.vsl in andromda-spring-cartridge.
//
package org.andromda.timetracker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;

/**
 * Provides the ability to search by properties.
 */
@SuppressWarnings({"unchecked"})
public class PropertySearch
    extends CriteriaSearch
{
    private Search search;

    /**
     * Constructor for PropertySearch. Creates a <code>PropertySearch</code> instance
     * from the given arguments.
     *
     * @param session The Hibernate session.
     * @param entityType The <code>Class</code> of the result.
     * @param searchIn the object that specifies the search criteria.
     */
    public PropertySearch(
        final Session session,
        final Class entityType,
        final Search searchIn)
    {
        super(session, entityType);
        this.search = searchIn;
        try
        {
            this.initializeConverters();
            this.getConfiguration().setForceEagerLoading(searchIn.isEagerFetching());
            final SearchParameter[] parameters = searchIn.getParameters();
            if (parameters != null)
            {
                for (int ctr = 0; ctr < parameters.length; ctr++)
                {
                    final SearchParameter searchParameter = parameters[ctr];

                    Object value;
                    switch (searchParameter.getComparator())
                    {
                        case SearchParameter.IN_COMPARATOR :
                            value = searchParameter.getValue();
                            break;
                        case SearchParameter.NOT_IN_COMPARATOR :
                            value = searchParameter.getValue();
                            break;
                        default :
                            value = this.getValue(entityType, searchParameter);
                            break;
                    }

                    // - now add the parameter.
                    final CriteriaSearchParameter parameter =
                        new CriteriaSearchParameter(value,
                            searchParameter.getName(),
                            searchParameter.getComparator());
                    parameter.setOrderDirection(searchParameter.getOrder());
                    parameter.setSearchIfNull(searchParameter.isSearchIfNull());
                    switch (searchParameter.getMatch())
                    {
                        case SearchParameter.MATCH_ANYWHERE :
                            parameter.setMatchMode(MatchMode.ANYWHERE);
                            break;
                        case SearchParameter.MATCH_START :
                            parameter.setMatchMode(MatchMode.START);
                            break;
                        case SearchParameter.MATCH_END :
                            parameter.setMatchMode(MatchMode.END);
                            break;
                        default:
                            parameter.setMatchMode(MatchMode.EXACT);
                            break;
                    }
                    this.addParameter(parameter);
                    if (searchIn.isUseSqlLimiting())
                    {
                        if (searchIn.getPageNumber() > 0 && searchIn.getPageSize() > 0)
                        {
                            // - set the first result as part of pagination support
                            this.getConfiguration().setFirstResult(new Integer(this.calculateFirstResult(searchIn.getPageNumber(), searchIn.getPageSize())));
                            this.getConfiguration().setMaximumResultSize(new Integer(searchIn.getPageSize()));
                        }
                    }
                }
            }
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Stores the total count when sql limiting isn't used.
     */
    private int totalCount;

    /**
     * Gets the total possible count of objects returned in this search.
     * @return totalCount
     */
    public int getTotalCount()
    {
        int count;
        if (this.search.isUseSqlLimiting())
        {
            // Remove first result requirement
            this.getConfiguration().setFirstResult(new Integer(0));
            this.getRootCriteria().setProjection(
                Projections.projectionList().add(
                    Projections.rowCount()));
            count = ((Integer)this.executeAsList().iterator().next()).intValue();
        }
        else
        {
            count = this.totalCount;
        }
        return count;
    }

    /**
     * @see CriteriaSearch#executeAsList()
     */
    @Override
    public List executeAsList()
        throws HibernateException
    {
        List results = super.executeAsList();
        if (!this.search.isUseSqlLimiting() && this.search.getPageNumber() > 0 && this.search.getPageSize() > 0)
        {
            int start = this.calculateFirstResult(this.search.getPageNumber(), this.search.getPageSize());
            int end = this.calculateLastResult(this.search.getPageNumber(), this.search.getPageSize());
            this.totalCount = results.size();
            if (this.totalCount < start)
            {
                start = this.totalCount;
            }
            if (this.totalCount < end)
            {
                end = this.totalCount;
            }
            results = results.subList(start, end);
        }
        return results;
    }

    /**
     * Calculates the first result based upon page size and current
     * desired page.
     *
     * @param pageNumber the page number to retrieve.
     * @param pageSize the page size to retrieve.
     *
     * @return the calculated first result.
     */
    private int calculateFirstResult(int pageNumber, int pageSize)
    {
        int firstResult = 0;
        if (pageNumber > 0 && pageSize > 0)
        {
            firstResult = (pageNumber - 1) * pageSize;
        }
        return firstResult;
    }

    /**
     * Calculates the last result based upon page size and current
     * desired page.
     *
     * @param pageNumber the page number to retrieve.
     * @param pageSize the page size to retrieve.
     *
     * @return the calculated first result.
     */
    private int calculateLastResult(int pageNumber, int pageSize)
    {
        int lastResult = 0;
        if (pageNumber > 0 && pageSize > 0)
        {
            lastResult = pageNumber * pageSize;
        }
        return lastResult;
    }

    private static final String PERIOD = ".";

    /**
     * Initializes the converters to behave the way we want when converting values (we don't
     * want empty strings converted to zeros, like beanutils does by default)
     */
    private void initializeConverters()
    {
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new CalendarConverter(), Calendar.class);
        ConvertUtils.register(new DateConverter(), Date.class);
    }

    /**
     * <p>Convert the value to an object of the specified class (if
     * possible).</p>
     *
     * @param value Value to be converted (may be null)
     * @param type Class of the value to be converted to
     * @return The converted value
     *
     * @exception ConversionException if thrown by an underlying Converter
     */
    protected Object convert(Object value, Class type) {
        Converter converter = ConvertUtils.lookup(type);
        Object result;
        if (converter != null)
        {
            result = converter.convert(type, value);
        }
        else
        {
            result = value;
        }
        return result;
    }

    /**
     * Converts the value contained within the parameter to the type which Hibernate expects.
     *
     * @param entityType the class of the entity for which the search is being performed.
     * @param parameter the parameter from which to get the value.
     * @return the appropriate value.
     */
    private Object getValue(Class type, final SearchParameter parameter)
    {
        try
        {
            Object value = parameter.getValue();
            // - don't try to convert null values or classes
            if (value != null && !value.getClass().equals(Class.class))
            {
                Class propertyType = type;
                for (final StringTokenizer tokenizer = new StringTokenizer(parameter.getName(), PERIOD); tokenizer.hasMoreTokens();)
                {
                    final String token = tokenizer.nextToken().trim();
                    Class lastType = type;
                    type = CriteriaSearchProperties.getPropertyType(type, token);
                    if (!tokenizer.hasMoreTokens())
                    {
                        break;
                    }
                    if (type == null)
                    {
                        throw new RuntimeException("No accessible property named '" + token + "', exists on: " + lastType.getName());
                    }
                    propertyType = type;
                }
                final String name = parameter.getName().replaceAll(".*\\" + PERIOD, "");
                try{
                    value = convert(value,propertyType.getMethod("get"+StringUtils.capitalize(name)).getReturnType());
                }
                catch (final NoSuchMethodException noSuchMethodException)
                {
                    throw new RuntimeException("No accessible property named '" + name + "', exists on: " + propertyType.getName());
                }
            }
            return value;
        }
        catch (final Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }

    /**
     * A beanutils converter that converts Calendar objects to Date instances.
     */
    private static final class DateConverter
        implements Converter
    {
        public DateConverter()
        {
            // Empty block comment
        }

        /**
         * @see org.apache.commons.beanutils.Converter#convert(Class, Object)
         */
        public Object convert(
            Class type,
            Object value)
            throws ConversionException
        {
            if (value != null)
            {
                try
                {
                    if (value instanceof Calendar)
                    {
                        value = ((Calendar)value).getTime();
                    }
                }
                catch (Exception ex)
                {
                    throw new ConversionException(ex);
                }
            }
            return value;
        }
    }

    /**
     * A beanutils converter that converts Date objects to Calendar instances.
     */
    private static final class CalendarConverter
        implements Converter
    {
        public CalendarConverter()
        {
            // Empty block comment
        }

        /**
         * @see org.apache.commons.beanutils.Converter#convert(Class, Object)
         */
        public Object convert(
            Class type,
            Object value)
            throws ConversionException
        {
            if (value != null)
            {
                try
                {
                    if (value instanceof Date)
                    {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime((Date)value);
                        value = calendar;
                    }
                }
                catch (Exception ex)
                {
                    throw new ConversionException(ex);
                }
            }
            return value;
        }
    }
}