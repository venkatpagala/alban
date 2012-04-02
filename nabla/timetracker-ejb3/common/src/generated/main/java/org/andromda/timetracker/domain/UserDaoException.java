// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!!
// Generated by DaoDefaultException.vsl in andromda-ejb3-cartridge on 04/02/2012 13:10:05.
//
package org.andromda.timetracker.domain;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 */
public class UserDaoException
    extends Exception
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = -3876530241628970605L;

    /**
     * The default constructor.
     */
    public UserDaoException()
    {}

    /**
     * Constructs a new instance of UserDaoException
     *
     * @param throwable the parent Throwable
     */
    public UserDaoException(Throwable throwable)
    {
        super(findRootCause(throwable));
    }

    /**
     * Constructs a new instance of UserDaoException
     *
     * @param message the throwable message.
     */
    public UserDaoException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new instance of UserDaoException
     *
     * @param message the throwable message.
     * @param throwable the parent of this Throwable.
     */
    public UserDaoException(String message, Throwable throwable)
    {
        super(message, findRootCause(throwable));
    }

    /**
     * Finds the root cause of the parent exception
     * by traveling up the exception tree
     */
    private static Throwable findRootCause(Throwable th)
    {
        if (th != null)
        {
            // Lets reflectively get any JMX or EJB exception causes.
            try
            {
                Throwable targetException = null;
                // java.lang.reflect.InvocationTargetException
                // or javax.management.ReflectionException
                String exceptionProperty = "targetException";
                if (PropertyUtils.isReadable(th, exceptionProperty))
                {
                    targetException = (Throwable)PropertyUtils.getProperty(th, exceptionProperty);
                }
                else
                {
                    exceptionProperty = "causedByException";
                    //javax.ejb.EJBException
                    if (PropertyUtils.isReadable(th, exceptionProperty))
                    {
                        targetException = (Throwable)PropertyUtils.getProperty(th, exceptionProperty);
                    }
                }
                if (targetException != null)
                {
                    th = targetException;
                }
            }
            catch (Exception ex)
            {
                // just print the exception and continue
                ex.printStackTrace();
            }

            if (th.getCause() != null)
            {
                th = th.getCause();
                th = findRootCause(th);
            }
        }
        return th;
    }
}