// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!!
// Generated by: ApplicationException.vsl in andromda-java-cartridge.
//
package org.andromda.test.howto11.a;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 */
public class RentalException
    extends Exception
{
    /** The serial version UID of this class. Throwable implements Serializable so declaration not needed. */
    private static final long serialVersionUID = 9117003909212316826L;

    /**
     * The default constructor.
     */
    public RentalException()
    {
        super();
        // Avoid compiler warning from uncommented empty method
    }

    /**
     * Constructs a new instance of RentalException
     *
     * @param throwable the parent Throwable
     */
    public RentalException(Throwable throwable)
    {
        super(findRootCause(throwable));
    }

    /**
     * Constructs a new instance of RentalException
     *
     * @param messageIn the throwable message.
     */
    public RentalException(String messageIn)
    {
        super(messageIn);
    }

    /**
     * Constructs a new instance of RentalException
     *
     * @param messageIn the throwable message.
     * @param throwable the parent of this Throwable.
     */
    public RentalException(String messageIn, Throwable throwable)
    {
        super(messageIn, findRootCause(throwable));
    }

    private Object[] messageArguments;

    /**
     * Gets the message arguments that can be used by message resources (in
     * something like the presentation tier)
     *
     * @return messageArguments
     */
    public Object[] getMessageArguments()
    {
        return this.messageArguments;
    }

    /**
     * Sets the message arguments that can be used by message resources (in
     * something like the presentation tier)
     *
     * @param messageArgumentsIn
     */
    public void setMessageArguments(Object[] messageArgumentsIn)
    {
        this.messageArguments = messageArgumentsIn;
    }

    /**
     * Finds the root cause of the parent exception
     * by traveling up the exception tree. Performs printStackTrace if
     * an exception is thrown.
     * @param th Throwable to find the cause from
     * @return targetException Throwable cause
     */
    private static Throwable findRootCause(Throwable th)
    {
        if (th != null)
        {
            // Reflectively get any JMX or EJB exception causes.
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