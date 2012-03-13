package org.andromda.cartridges.jsf2.metafacades;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.andromda.cartridges.jsf2.JSFGlobals;
import org.andromda.cartridges.jsf2.JSFProfile;
import org.andromda.cartridges.jsf2.JSFUtils;
import org.andromda.metafacades.uml.StateVertexFacade;
import org.andromda.utils.StringUtilsHelper;


/**
 * MetafacadeLogic implementation for org.andromda.cartridges.jsf2.metafacades.JSFForward.
 *
 * @see org.andromda.cartridges.jsf2.metafacades.JSFForward
 */
public class JSFForwardLogicImpl
    extends JSFForwardLogic
{

    public JSFForwardLogicImpl (Object metaObject, String context)
    {
        super (metaObject, context);
    }

    /**
     * @see org.andromda.metafacades.uml.ModelElementFacade#getName()
     */
    public String getName()
    {
        StringBuffer name = new StringBuffer(super.getName());
        final Object target = this.getTarget();
        if (target instanceof JSFFinalState)
        {
            name.append(JSFGlobals.USECASE_FORWARD_NAME_SUFFIX);
        }
        else
        {
            name.insert(0, this.getUseCase().getName() + "-");
        }
        return JSFUtils.toWebResourceName(name.toString());
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFForward#getPath()
     */
    protected String handleGetPath()
    {
        String forwardPath = null;
        final StateVertexFacade target = getTarget();
        if (this.isEnteringView())
        {
            forwardPath = ((JSFView)target).getPath();
        }
        else if (this.isEnteringFinalState())
        {
            forwardPath = ((JSFFinalState)target).getPath();
        }

        return forwardPath;
    }

    protected boolean handleIsSuccessMessagesPresent()
    {
        return !this.getSuccessMessages().isEmpty();
    }

    protected boolean handleIsWarningMessagesPresent()
    {
        return !this.getWarningMessages().isEmpty();
    }

    /**
     * Collects specific messages in a map.
     *
     * @param taggedValue the tagged value from which to read the message
     * @return maps message keys to message values, but only those that match the arguments
     *         will have been recorded
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> getMessages(String taggedValue)
    {
        Map<String, String> messages;

        final Collection taggedValues = this.findTaggedValues(taggedValue);
        if (taggedValues.isEmpty())
        {
            messages = Collections.EMPTY_MAP;
        }
        else
        {
            messages = new LinkedHashMap<String, String>(); // we want to keep the order

            for (final Iterator iterator = taggedValues.iterator(); iterator.hasNext();)
            {
                final String value = (String)iterator.next();
                messages.put(StringUtilsHelper.toResourceMessageKey(value), value);
            }
        }

        return messages;
    }

    protected Map handleGetSuccessMessages()
    {
        return this.getMessages(JSFProfile.TAGGEDVALUE_ACTION_SUCCESS_MESSAGE);
    }

    protected Map handleGetWarningMessages()
    {
        return this.getMessages(JSFProfile.TAGGEDVALUE_ACTION_WARNING_MESSAGE);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFForward#isFinalStateTarget()
     */
    protected boolean handleIsFinalStateTarget()
    {
        return this.getTarget() instanceof JSFFinalState;
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFForward#getFromOutcome()
     */
    protected String handleGetFromOutcome()
    {
        return this.getName();
    }
}