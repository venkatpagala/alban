// license-header java merge-point
/* Autogenerated by AndroMDA (MessageDrivenBean.vsl) - do not edit */
package org.andromda.test.howto10.a;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.MessageListener;

/**
 * Autogenerated JMS message driven EJB class for the PaymentProcessorMDBBean bean.
 *
 * 
 */

// The MessageDriven annotation is now fully configured in ejb-jar.xml
// This allows to set the class name to the MDB implementation class
// The annotation is commented to avoid multiple registration with the
// container.
// @javax.ejb.MessageDriven(activationConfig =
// {
//   @javax.ejb.ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
//   @javax.ejb.ActivationConfigProperty(propertyName="destination", propertyValue="queue/howtomodel/howto10a")//
//}
//)


@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class PaymentProcessorMDBBean
    implements MessageListener
{
    // ------ Message Driven Context Injection ------

    @Resource
    protected MessageDrivenContext context;

    // ------ EJB Injection Definitions --------

    /**
     * Inject session EJB PaymentService;
     */
    @EJB
    protected PaymentServiceRemote paymentService;


    // --------- Default Constructor ----------

    public PaymentProcessorMDBBean()
    {
        // Empty implementation
    }


}