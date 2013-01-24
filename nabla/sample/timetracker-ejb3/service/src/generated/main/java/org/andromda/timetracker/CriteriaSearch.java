// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: hibernate/HibernateSearch.vsl in andromda-spring-cartridge.
//
package org.andromda.timetracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 * Implements a generic search mechanism based on the Hibernate Criteria API. The
 * <code>CriteriaSearch</code> allows adding parameters which function as where clause. The
 * parameters are analyzed whether they should be considered or not. This depends both on the actual
 * value of the parameter and on the configuration.<br>
 * The <code>CriteriaSearch</code> is expected to be a general solution for a basic search with
 * parameters connected by logical <b>and</b>. This search does <b>not</b> provide grouping.
 *
 * @author Stefan Reichert
 * @author Peter Friese
 * @author Chad Brandon
 * @see Criteria
 * @see Restrictions
 */
public class CriteriaSearch
{
    private CriteriaSearchConfiguration configuration;
    private Criteria rootCriteria;
    private Map<String, Criteria> childCriteriaMap;
    private List<CriteriaSearchParameter> orderList;
    private Class resultType;

    private static final class ParameterComparator
        implements Comparator
    {
        public int compare(final Object object1, final Object object2)
        {
            final CriteriaSearchParameter parameter1 = (CriteriaSearchParameter)object1;
            final CriteriaSearchParameter parameter2 = (CriteriaSearchParameter)object2;

            final int relevance1 = parameter1.getOrderRelevance();
            final int relevance2 = parameter2.getOrderRelevance();
            int result = 0;
            if (relevance1 > relevance2)
            {
                result = 1;
            }
            else if (relevance1 < relevance2)
            {
                result = -1;
            }
            return result;
        }
    }

    /**
     * Exposes the root criteria to subclasses.
     * @return rootCriteria
     */
    protected Criteria getRootCriteria()
    {
        return this.rootCriteria;
    }

    /**
     * Constructor for CriteriaSearch. Creates a <code>CriteriaSearch</code> with a default
     * <code>CriteriaSearchConfiguration</code>.
     *
     * @param session The Hibernate session.
     * @param resultTypeIn The <code>Class</code> of the result.
     */
    public CriteriaSearch(Session session, Class resultTypeIn)
    {
        this.configuration = new CriteriaSearchConfiguration();
        this.resultType = resultTypeIn;
        this.rootCriteria = session.createCriteria(this.resultType);
        this.childCriteriaMap = new HashMap<String, Criteria>();
        this.orderList = new ArrayList<CriteriaSearchParameter>();
    }

    /**
     * Executes a <code>HibernateQuery</code> using the currently defined
     * <code>CriteriaSearchParameter</code>s, and returns a java.util.Set
     * containing the query results.
     *
     * @return result The result of the query.
     * @throws HibernateException
     */
    public Set executeAsSet()
        throws HibernateException
    {
        return new LinkedHashSet(this.executeAsList());
    }

    /**
     * Executes a <code>HibernateQuery</code> using the currently defined
     * <code>CriteriaSearchParameter</code>s, and returns a java.util.List
     * containing the query results.
     *
     * @return result The result of the query as a List
     * @throws HibernateException
     */
    public List executeAsList()
        throws HibernateException
    {
        // add ordering
        if (this.orderList.size() > 0)
        {
            Collections.sort(this.orderList, new ParameterComparator());
            for (final CriteriaSearchParameter parameter :  this.orderList)
            {
                int direction = parameter.getOrderDirection();
                if (direction == SearchParameter.ORDER_ASC)
                {
                    this.getParameterCriteria(parameter).addOrder(Order.asc(this.getParameterName(parameter)));
                }
                else
                {
                    this.getParameterCriteria(parameter).addOrder(Order.desc(this.getParameterName(parameter)));
                }
            }
        }

        // set the first result if configured
        if (this.configuration.getFirstResult() != null)
        {
            this.rootCriteria.setFirstResult(this.configuration.getFirstResult().intValue());
        }

        // set the fetch size if configured
        if (this.configuration.getFetchSize() != null)
        {
            this.rootCriteria.setFetchSize(this.configuration.getFetchSize().intValue());
        }

        // limit the maximum result if configured
        if (this.configuration.getMaximumResultSize() != null)
        {
            this.rootCriteria.setMaxResults(this.configuration.getMaximumResultSize().intValue());
        }
        return this.rootCriteria.list();
    }

    /**
     * Adds a <code>CriteriaSearchParameter</code> to this search. The parameter is connected to
     * the search by logical <b>and</b>. It is not considered if the value is <code>null</code>.
     * If the value is not <code>null</code> it is compared using the
     * <code>CriteriaSearchParameter.EQUALS_COMPARATOR</code>.
     *
     * @param parameterValue The value of the parameter.
     * @param parameterPattern The pattern of the parameter (dot-separated path e.g. person.address.street).
     * @throws HibernateException
     */
    public void addParameter(Object parameterValue, String parameterPattern)
        throws HibernateException
    {
        addParameter(new CriteriaSearchParameter(parameterValue, parameterPattern));
    }

    /**
     * Adds a <code>CriteriaSearchParameter</code> to this search. The parameter is connected to
     * the search by logical <b>and</b>. It is not considered if the value is <code>null</code> or
     * if the <code>String</code> empty. If the value is not <code>null</code> it is compared
     * using the <code>CriteriaSearchParameter.LIKE_COMPARATOR</code>.
     *
     * @param parameterValue The value of the parameter.
     * @param parameterPattern The pattern of the parameter (dot-separated path e.g. person.address.street).
     * @throws HibernateException
     */
    public void addParameter(String parameterValue, String parameterPattern)
        throws HibernateException
    {
        addParameter(new CriteriaSearchParameter(parameterValue, parameterPattern));
    }

    /**
     * Adds a <code>CriteriaSearchParameter</code> to this search. The parameter is connected to
     * the search by logical <b>and</b>.
     *
     * @param parameter The <code>CriteriaSearchParameter</code> to add.
     * @throws HibernateException
     */
    public void addParameter(CriteriaSearchParameter parameter)
        throws HibernateException
    {
        if (considerParameter(parameter))
        {
            final Criteria parameterCriteria = this.getParameterCriteria(parameter);
            final String parameterName = this.getParameterName(parameter);

            // check the type parameter value to add
            if(parameter.getParameterValue() instanceof Object[])
            {
                addExpression(
                    parameterCriteria,
                    parameterName,
                    (Object[])parameter.getParameterValue(),
                    parameter.getComparator(),
                    parameter.getMatchMode());
            }
            else
            {
                addExpression(
                    parameterCriteria,
                    parameterName,
                    parameter.getParameterValue(),
                    parameter.getComparator(),
                    parameter.getMatchMode());
            }
        }
        // if the parameter is to be ordered, add it to the order list
        if (parameter.getOrderDirection() != SearchParameter.ORDER_UNSET)
        {
            this.orderList.add(parameter);
        }
    }

    /**
     * Retrieves the appropriate criteria object for the given <code>parameter</code>.
     *
     * @param parameter the parameter for which to retrieve the criteria.
     * @return the retrieved criteria.
     */
    private Criteria getParameterCriteria(final CriteriaSearchParameter parameter)
        throws HibernateException
    {
        String[] path = CriteriaSearchParameter.PATTERN.split(parameter.getParameterPattern());
        Criteria parameterCriteria = this.rootCriteria;

        Criteria childEntityCriteria;
        if (path.length > 1)
        {
            // We have a parameter affecting an attribute of an inner childEntity object so we need
            // to traverse to get the right criteria object
            childEntityCriteria = this.rootCriteria;
            // Evaluating the proper criteria object for the defined parameter
            Class type = this.resultType;
            for (int index = 0; index < (path.length - 1); index++)
            {
                final String childEntityName = path[index];
                final Collection embeddedValues = CriteriaSearchProperties.getEmbeddedValues(type);
                if (embeddedValues != null && embeddedValues.contains(childEntityName))
                {
                    // - use the rest of the path as the parameter name
                    final int number = path.length - index;
                    final String[] restOfPath = new String[path.length - index];
                    System.arraycopy(path, index, restOfPath, 0, number);
                    break;
                }
                type = CriteriaSearchProperties.getNavigableAssociationEndType(type, childEntityName);
                childEntityCriteria = locateCriteria(childEntityName, childEntityCriteria);
            }
            if (childEntityCriteria != null)
            {
                // We now have the right criteria object
                parameterCriteria = childEntityCriteria;
            }
        }
        return parameterCriteria;
    }

    /**
     * Retrieves the appropriate nested parameter name for the given <code>parameter</code>.
     *
     * @param parameter the parameter for which to retrieve the parameter name.
     * @return the parameter name.
     */
    private String getParameterName(final CriteriaSearchParameter parameter)
    {
        // parsing the pattern of the parameter
        String[] path = CriteriaSearchParameter.PATTERN.split(parameter.getParameterPattern());
        String parameterName = path[path.length - 1];
        if (path.length > 1)
        {
            // We have a parameter affecting an attribute of an inner childEntity object so we need
            // to traverse to get the correct parameter name
            // Evaluating the proper criteria object for the defined parameter
            Class type = this.resultType;
            for (int index = 0; index < (path.length - 1); index++)
            {
                final String childEntityName = path[index];
                final Collection embeddedValues = CriteriaSearchProperties.getEmbeddedValues(type);
                if (embeddedValues != null && embeddedValues.contains(childEntityName))
                {
                    // - use the rest of the path as the parameter name
                    final int number = path.length - index;
                    final String[] restOfPath = new String[path.length - index];
                    System.arraycopy(path, index, restOfPath, 0, number);
                    parameterName = StringUtils.join(restOfPath, ".");
                    break;
                }
                type = CriteriaSearchProperties.getNavigableAssociationEndType(type, childEntityName);
            }
        }
        return parameterName;
    }

    /**
     * Decides whether a paramter is considered as a criteria for a search depending on the type and
     * value of the <code>parameterValue</code> and <code>searchIfIsNull</code>. A
     * <code>parameterValue</code> of the type <code>String</code> is considered
     * <code>null</code> if being a <code>NullPointer</code> or empty.
     *
     * @param parameter The parameter to check.
     */
    private boolean considerParameter(CriteriaSearchParameter parameter)
    {
        if (parameter.getParameterValue() instanceof String)
        {
            String stringParameterValue = (String) parameter.getParameterValue();
            return (parameter.isSearchIfNull()
                    || (stringParameterValue != null && stringParameterValue.length() > 0));
        }
        if (parameter.getParameterValue() instanceof Object[])
        {
            Object[] parameterValues = (Object[]) parameter.getParameterValue();
            return (parameter.isSearchIfNull()
                    || (parameterValues != null && parameterValues.length > 0));
        }
        final int comparator = parameter.getComparator();
        return (parameter.isSearchIfNull() ||
            SearchParameter.NOT_NULL_COMPARATOR == comparator ||
            SearchParameter.NULL_COMPARATOR == comparator ||
            SearchParameter.EMPTY_COMPARATOR == comparator ||
            SearchParameter.NOT_EMPTY_COMPARATOR == comparator) ||
            (parameter.getParameterValue() != null);
    }

    /**
     * Adds an <code>Restrictions</code> to a <code>Criteria</code>.
     *
     * @param criteria
     * @param parameterName
     * @param parameterValue
     * @param comparator
     * @param matchMode
     */
    private void addExpression(
        Criteria criteria,
        String parameterName,
        Object parameterValue,
        int comparator,
        MatchMode matchMode)
    {
        switch (comparator)
        {
            case SearchParameter.NOT_NULL_COMPARATOR:
            {
                criteria.add(Restrictions.isNotNull(parameterName));
                break;
            }
            case SearchParameter.NULL_COMPARATOR:
            {
                criteria.add(Restrictions.isNull(parameterName));
                break;
            }
            case SearchParameter.EMPTY_COMPARATOR:
            {
                criteria.add(Restrictions.isEmpty(parameterName));
                break;
            }
            case SearchParameter.NOT_EMPTY_COMPARATOR:
            {
                criteria.add(Restrictions.isNotEmpty(parameterName));
                break;
            }
            default:
            {
                if (parameterValue != null)
                {
                    switch (comparator)
                    {
                        case SearchParameter.LIKE_COMPARATOR:
                        {
                            if ((matchMode != null) && (parameterValue instanceof String))
                            {
                                criteria.add(Restrictions.like(
                                    parameterName,
                                    (String)parameterValue,
                                    matchMode));
                            }
                            else
                            {
                                criteria.add(Restrictions.like(parameterName, parameterValue));
                            }
                            break;
                        }
                        case SearchParameter.NOT_LIKE_COMPARATOR:
                        {
                            SimpleExpression expression;
                            if ((matchMode != null) && (parameterValue instanceof String))
                            {
                                expression = Restrictions.like(
                                    parameterName,
                                    (String)parameterValue,
                                    matchMode);
                            }
                            else
                            {
                                expression = Restrictions.like(parameterName, parameterValue);
                            }
                            criteria.add(Restrictions.not(expression));
                            break;
                        }
                        case SearchParameter.INSENSITIVE_LIKE_COMPARATOR:
                        {
                            if ((matchMode != null) && (parameterValue instanceof String))
                            {
                                criteria.add(Restrictions.ilike(
                                    parameterName,
                                    (String)parameterValue,
                                    matchMode));
                            }
                            else
                            {
                                criteria.add(Restrictions.ilike(parameterName, parameterValue));
                            }
                            break;
                        }
                        case SearchParameter.NOT_INSENSITIVE_LIKE_COMPARATOR:
                        {
                            Criterion criterion;
                            if ((matchMode != null) && (parameterValue instanceof String))
                            {
                                criterion = Restrictions.ilike(
                                    parameterName,
                                    (String)parameterValue,
                                    matchMode);
                            }
                            else
                            {
                                criterion = Restrictions.ilike(parameterName, parameterValue);
                            }
                            criteria.add(Restrictions.not(criterion));
                            break;
                        }
                        case SearchParameter.EQUAL_COMPARATOR:
                        {
                            criteria.add(Restrictions.eq(parameterName, parameterValue));
                            break;
                        }
                        case SearchParameter.GREATER_THAN_OR_EQUAL_COMPARATOR:
                        {
                            criteria.add(Restrictions.ge(parameterName, parameterValue));
                            break;
                        }
                        case SearchParameter.GREATER_THAN_COMPARATOR:
                        {
                            criteria.add(Restrictions.gt(parameterName, parameterValue));
                            break;
                        }
                        case SearchParameter.LESS_THAN_OR_EQUAL_COMPARATOR:
                        {
                            criteria.add(Restrictions.le(parameterName, parameterValue));
                            break;
                        }
                        case SearchParameter.LESS_THAN_COMPARATOR:
                        {
                            criteria.add(Restrictions.lt(parameterName, parameterValue));
                            break;
                        }
                        case SearchParameter.IN_COMPARATOR:
                        {
                            if (parameterValue instanceof Collection)
                            {
                                criteria.add(Restrictions.in(parameterName, (Collection)parameterValue));
                            }
                            break;
                        }
                        case SearchParameter.NOT_IN_COMPARATOR:
                        {
                            if (parameterValue instanceof Collection)
                            {
                                criteria.add(Restrictions.not(Restrictions.in(parameterName, (Collection)parameterValue)));
                            }
                            break;
                        }
                        case SearchParameter.NOT_EQUAL_COMPARATOR:
                        {
                            criteria.add(Restrictions.ne(parameterName, parameterValue));
                            break;
                        }
                    }
                }
                else
                {
                    criteria.add(Restrictions.isNull(parameterName));
                }
            }
        }
    }

    /**
     * Adds an <code>Restrictions</code> to a <code>Criteria</code>. The given <code>parameterValues</code>
     * represents either an array of <code>String</code> or another object. The different values in the
     * array are added to a disjunction or conjunction which is connected with logical and to the other criteria of the
     * search.
     *
     * @param criteria
     * @param parameterName
     * @param parameterValues
     * @param searchIfNull
     * @param comparator
     * @param matchMode
     */
    private void addExpression(
        Criteria criteria,
        String parameterName,
        Object[] parameterValues,
        int comparator,
        MatchMode matchMode)
    {
        if (parameterValues != null)
        {
            Disjunction disjunction = null;
            Conjunction conjunction = null;
            switch (comparator)
            {
                case SearchParameter.LIKE_COMPARATOR:
                {
                    disjunction = Restrictions.disjunction();
                    if ((matchMode != null) && (parameterValues instanceof String[]))
                    {
                        String[] stringParameterValues = (String[]) parameterValues;
                        for (int index = 0; index < parameterValues.length; index++)
                        {
                            if (stringParameterValues[index] != null)
                            {
                                disjunction.add(Restrictions.like(
                                    parameterName, stringParameterValues[index], matchMode));
                            }
                            else
                            {
                                disjunction.add(Restrictions
                                    .isNull(parameterName));
                            }
                        }
                    }
                    else
                    {
                        for (int index = 0; index < parameterValues.length; index++)
                        {
                            if (parameterValues[index] != null)
                            {
                                disjunction.add(Restrictions.like(
                                    parameterName, parameterValues[index]));
                            }
                            else
                            {
                                disjunction.add(Restrictions
                                        .isNull(parameterName));
                            }
                        }
                    }
                    break;
                }
                case SearchParameter.INSENSITIVE_LIKE_COMPARATOR:
                {
                    disjunction = Restrictions.disjunction();
                    if ((matchMode != null) && (parameterValues instanceof String[]))
                    {
                        String[] stringParameterValues = (String[]) parameterValues;
                        for (int index = 0; index < parameterValues.length; index++)
                        {
                            if (stringParameterValues[index] != null) {
                                disjunction.add(Restrictions.ilike(
                                    parameterName, stringParameterValues[index], matchMode));
                            }
                            else {
                                disjunction.add(Restrictions
                                    .isNull(parameterName));
                            }
                        }
                    }
                    else
                    {
                        for (int index = 0; index < parameterValues.length; index++)
                        {
                            if (parameterValues[index] != null)
                            {
                                disjunction.add(Restrictions.ilike(
                                    parameterName, parameterValues[index]));
                            }
                            else
                            {
                                disjunction.add(Restrictions
                                        .isNull(parameterName));
                            }
                        }
                    }
                    break;
                }
                case SearchParameter.EQUAL_COMPARATOR:
                {
                    disjunction = Restrictions.disjunction();
                    for (int index = 0; index < parameterValues.length; index++)
                    {
                        if (parameterValues[index] != null)
                        {
                            disjunction.add(Restrictions.eq(parameterName,
                                    parameterValues[index]));
                        }
                        else {
                            disjunction.add(Restrictions.isNull(parameterName));
                        }
                    }
                    break;
                }
                case SearchParameter.GREATER_THAN_OR_EQUAL_COMPARATOR:
                {
                    disjunction = Restrictions.disjunction();
                    for (int index = 0; index < parameterValues.length; index++)
                    {
                        if (parameterValues[index] != null)
                        {
                            disjunction.add(Restrictions.ge(parameterName,
                                    parameterValues[index]));
                        }
                        else
                        {
                            disjunction.add(Restrictions.isNull(parameterName));
                        }
                    }
                    break;
                }
                case SearchParameter.GREATER_THAN_COMPARATOR:
                {
                    disjunction = Restrictions.disjunction();
                    for (int index = 0; index < parameterValues.length; index++)
                    {
                        if (parameterValues[index] != null)
                        {
                            disjunction.add(Restrictions.gt(parameterName,
                                    parameterValues[index]));
                        }
                        else
                        {
                            disjunction.add(Restrictions.isNull(parameterName));
                        }
                    }
                    break;
                }
                case SearchParameter.LESS_THAN_OR_EQUAL_COMPARATOR:
                {
                    disjunction = Restrictions.disjunction();
                    for (int index = 0; index < parameterValues.length; index++)
                    {
                        if (parameterValues[index] != null)
                        {
                            disjunction.add(Restrictions.le(parameterName,
                                    parameterValues[index]));
                        }
                        else
                        {
                            disjunction.add(Restrictions.isNull(parameterName));
                        }
                    }
                    break;
                }
                case SearchParameter.LESS_THAN_COMPARATOR:
                {
                    disjunction = Restrictions.disjunction();
                    for (int index = 0; index < parameterValues.length; index++)
                    {
                        if (parameterValues[index] != null)
                        {
                            disjunction.add(Restrictions.lt(parameterName,
                                    parameterValues[index]));
                        }
                        else
                        {
                            disjunction.add(Restrictions.isNull(parameterName));
                        }
                    }
                    break;
                }
                case SearchParameter.IN_COMPARATOR:
                {
                    criteria.add(Restrictions.in(parameterName, parameterValues));
                    break;
                }
                case SearchParameter.NOT_IN_COMPARATOR:
                {
                    criteria.add(Restrictions.not(Restrictions.in(parameterName, parameterValues)));
                    break;
                }
                case SearchParameter.NOT_EQUAL_COMPARATOR:
                {
                    conjunction = Restrictions.conjunction();
                    for (int index = 0; index < parameterValues.length; index++)
                    {
                        if (parameterValues[index] != null)
                        {
                            conjunction.add(Restrictions.ne(parameterName,
                                    parameterValues[index]));
                        }
                        else
                        {
                            conjunction.add(Restrictions.isNotNull(parameterName));
                        }
                    }
                    break;
                }
            }

            if (disjunction != null)
            {
                criteria.add(disjunction);
            }
            if (conjunction != null)
            {
                criteria.add(conjunction);
            }
        }
        else
        {
            switch (comparator)
            {
                case SearchParameter.EMPTY_COMPARATOR:
                {
                    criteria.add(Restrictions.isEmpty(parameterName));
                    break;
                }
                case SearchParameter.NOT_EMPTY_COMPARATOR:
                {
                    criteria.add(Restrictions.isNotEmpty(parameterName));
                    break;
                }
                default:
                {
                    criteria.add(Restrictions.isNull(parameterName));
                }
            }
        }
    }

    /**
     * Locates a <code>Criteria</code> for a <code>childEntityName</code>. If a
     * <code>Criteria</code> exists for the <code>childEntityName</code>, it is returned. If
     * not, one is created and referenced in the <code>childCriteriaMap</code> under the
     * <code>childEntityName</code>.
     *
     * @param childEntityName
     * @param parentCriteria
     * @return criteria The Criteria for the childEntityName.
     * @throws HibernateException
     */
    private Criteria locateCriteria(String childEntityName, Criteria parentCriteria) throws HibernateException
    {
        if (this.childCriteriaMap.containsKey(childEntityName))
        {
            return (Criteria) this.childCriteriaMap.get(childEntityName);
        }
        Criteria childCriteria = parentCriteria.createCriteria(childEntityName);
        if (this.configuration.isForceEagerLoading())
        {
            parentCriteria.setFetchMode(childEntityName, FetchMode.JOIN);
        }

        // Hibernate does not support a 'unique' identifier. As a search may contain outer joins,
        // duplicates in the resultList are possible. We eliminate any duplicates here, creating a
        // distinctified resultSet (Suggestion from Hibernate itself; see www.hibernate.org's FAQ's).
        parentCriteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        this.childCriteriaMap.put(childEntityName, childCriteria);
        return childCriteria;
    }

    /**
     * Returns the configuration of this search.
     *
     * @return configuration
     */
    public CriteriaSearchConfiguration getConfiguration()
    {
        return this.configuration;
    }
}