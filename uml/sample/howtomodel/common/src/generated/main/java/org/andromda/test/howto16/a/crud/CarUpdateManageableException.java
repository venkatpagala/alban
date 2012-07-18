// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!!
// Generated by crud/ManageableServiceUpdateException.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto16.a.crud;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 */
public class CarUpdateManageableException
    extends Exception
{
    /**
     * The default constructor.
     */
    public CarUpdateManageableException()
    {}

    /**
     * Constructs a new instance of CarUpdateManageableException
     *
     * @param throwable the parent Throwable
     */
    public CarUpdateManageableException(Throwable throwable)
    {
        super(findRootCause(throwable));
    }

    /**
     * Constructs a new instance of CarUpdateManageableException
     *
     * @param message the throwable message.
     */
    public CarUpdateManageableException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new instance of CarUpdateManageableException
     *
     * @param message the throwable message.
     * @param throwable the parent of this Throwable.
     */
    public CarUpdateManageableException(String message, Throwable throwable)
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