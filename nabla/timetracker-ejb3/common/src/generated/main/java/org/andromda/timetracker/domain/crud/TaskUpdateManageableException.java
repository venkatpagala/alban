// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!!
// Generated by crud/ManageableServiceUpdateException.vsl in andromda-ejb3-cartridge on 04/02/2012 13:10:05.
//
package org.andromda.timetracker.domain.crud;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * This class represents a task for which time allocations need to be tracked.
 */
public class TaskUpdateManageableException
    extends Exception
{
    /**
     * The default constructor.
     */
    public TaskUpdateManageableException()
    {}

    /**
     * Constructs a new instance of TaskUpdateManageableException
     *
     * @param throwable the parent Throwable
     */
    public TaskUpdateManageableException(Throwable throwable)
    {
        super(findRootCause(throwable));
    }

    /**
     * Constructs a new instance of TaskUpdateManageableException
     *
     * @param message the throwable message.
     */
    public TaskUpdateManageableException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new instance of TaskUpdateManageableException
     *
     * @param message the throwable message.
     * @param throwable the parent of this Throwable.
     */
    public TaskUpdateManageableException(String message, Throwable throwable)
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