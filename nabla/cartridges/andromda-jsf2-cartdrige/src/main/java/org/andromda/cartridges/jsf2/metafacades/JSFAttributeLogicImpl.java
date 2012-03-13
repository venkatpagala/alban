package org.andromda.cartridges.jsf2.metafacades;

import java.util.Collection;
import java.util.Iterator;

import org.andromda.cartridges.jsf2.JSFGlobals;
import org.andromda.cartridges.jsf2.JSFProfile;
import org.andromda.cartridges.jsf2.JSFUtils;
import org.andromda.metafacades.uml.ClassifierFacade;
import org.andromda.metafacades.uml.FrontEndParameter;
import org.andromda.metafacades.uml.FrontEndView;
import org.andromda.metafacades.uml.ModelElementFacade;
import org.andromda.metafacades.uml.ParameterFacade;
import org.andromda.utils.StringUtilsHelper;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;


/**
 * MetafacadeLogic implementation for org.andromda.cartridges.jsf2.metafacades.JSFAttribute.
 *
 * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute
 */
public class JSFAttributeLogicImpl
    extends JSFAttributeLogic
{
    public JSFAttributeLogicImpl(
        Object metaObject,
        String context)
    {
        super(metaObject, context);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getMessageKey()
     */
    protected java.lang.String handleGetMessageKey()
    {
        final StringBuffer messageKey = new StringBuffer();
        if (!this.isNormalizeMessages())
        {
            final ClassifierFacade owner = this.getOwner();
            if (owner != null)
            {
                messageKey.append(StringUtilsHelper.toResourceMessageKey(owner.getName()));
                messageKey.append('.');
            }
        }
        final String name = this.getName();
        if (name != null && name.trim().length() > 0)
        {
            messageKey.append(StringUtilsHelper.toResourceMessageKey(name));
        }
        return messageKey.toString();
    }

    /**
     * Indicates whether or not we should normalize messages.
     *
     * @return true/false
     */
    private final boolean isNormalizeMessages()
    {
        final String normalizeMessages =
            ObjectUtils.toString(this.getConfiguredProperty(JSFGlobals.NORMALIZE_MESSAGES));
        return Boolean.valueOf(normalizeMessages).booleanValue();
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getMessageValue()
     */
    protected java.lang.String handleGetMessageValue()
    {
        return StringUtilsHelper.toPhrase(super.getName());
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getFormat()
     */
    protected String handleGetFormat()
    {
        return JSFUtils.getFormat(
            (ModelElementFacade)this.THIS(),
            this.getType(),
            this.getDefaultDateFormat(),
            this.getDefaultTimeFormat());
    }

    /**
     * @return the default time format pattern as defined using the configured property
     */
    private String getDefaultTimeFormat()
    {
        return (String)this.getConfiguredProperty(JSFGlobals.PROPERTY_DEFAULT_TIMEFORMAT);
    }

    /**
     * @return the default date format pattern as defined using the configured property
     */
    private String getDefaultDateFormat()
    {
        return (String)this.getConfiguredProperty(JSFGlobals.PROPERTY_DEFAULT_DATEFORMAT);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getDummyValue()
     */
    protected String handleGetDummyValue()
    {
        final ClassifierFacade type = this.getType();
        if (type != null)
        {
            final String typeName = type.getFullyQualifiedName();
            final String name = this.getName();
            if ("java.lang.String".equals(typeName))
            {
                return "\"" + name + "-test" + "\"";
            }
            if ("java.util.Date".equals(typeName))
            {
                return "new java.util.Date()";
            }
            if ("java.sql.Date".equals(typeName))
            {
                return "new java.sql.Date(new java.util.Date().getTime())";
            }
            if ("java.sql.Timestamp".equals(typeName))
            {
                return "new java.sql.Timestamp(new Date().getTime())";
            }
            if ("java.util.Calendar".equals(typeName))
            {
                return "java.util.Calendar.getInstance()";
            }
            if ("int".equals(typeName))
            {
                return "(int)" + name.hashCode();
            }
            if ("boolean".equals(typeName))
            {
                return "false";
            }
            if ("long".equals(typeName))
            {
                return "(long)" + name.hashCode();
            }
            if ("char".equals(typeName))
            {
                return "(char)" + name.hashCode();
            }
            if ("float".equals(typeName))
            {
                return "(float)" + name.hashCode() / hashCode();
            }
            if ("double".equals(typeName))
            {
                return "(double)" + name.hashCode() / hashCode();
            }
            if ("short".equals(typeName))
            {
                return "(short)" + name.hashCode();
            }
            if ("byte".equals(typeName))
            {
                return "(byte)" + name.hashCode();
            }
            if ("java.lang.Integer".equals(typeName))
            {
                return "new Integer((int)" + name.hashCode() + ")";
            }
            if ("java.lang.Boolean".equals(typeName))
            {
                return "Boolean.FALSE";
            }
            if ("java.lang.Long".equals(typeName))
            {
                return "new Long((long)" + name.hashCode() + ")";
            }
            if ("java.lang.Character".equals(typeName))
            {
                return "new Character(char)" + name.hashCode() + ")";
            }
            if ("java.lang.Float".equals(typeName))
            {
                return "new Float((float)" + name.hashCode() / hashCode() + ")";
            }
            if ("java.lang.Double".equals(typeName))
            {
                return "new Double((double)" + name.hashCode() / hashCode() + ")";
            }
            if ("java.lang.Short".equals(typeName))
            {
                return "new Short((short)" + name.hashCode() + ")";
            }
            if ("java.lang.Byte".equals(typeName))
            {
                return "new Byte((byte)" + name.hashCode() + ")";
            }

            //if (type.isArrayType()) return constructDummyArray();
            if (type.isSetType())
            {
                return "new java.util.HashSet(java.util.Arrays.asList(" + constructDummyArray() + "))";
            }
            if (type.isCollectionType())
            {
                return "java.util.Arrays.asList(" + constructDummyArray() + ")";
            }

            // maps and others types will simply not be treated
        }
        return "null";
    }

    /**
     * Constructs a string representing an array initialization in Java.
     *
     * @return A String representing Java code for the initialization of an array.
     */
    private final String constructDummyArray()
    {
        return JSFUtils.constructDummyArrayDeclaration(
            this.getName(),
            JSFGlobals.DUMMY_ARRAY_COUNT);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getFormPropertyName(org.andromda.metafacades.uml.ParameterFacade)
     */
    protected String handleGetFormPropertyName(final ParameterFacade ownerParameter)
    {
        final StringBuffer propertyName = new StringBuffer();
        if (ownerParameter != null)
        {
            propertyName.append(ownerParameter.getName());
            propertyName.append('.');
        }
        final String name = this.getName();
        if (name != null && name.trim().length() > 0)
        {
            propertyName.append(name);
        }
        return propertyName.toString();
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getBackingListName(org.andromda.metafacades.uml.ParameterFacade)
     */
    protected String handleGetBackingListName(final ParameterFacade ownerParameter)
    {
        final String backingListName =
            StringUtils.replace(
                ObjectUtils.toString(this.getConfiguredProperty(JSFGlobals.BACKING_LIST_PATTERN)),
                "{0}",
                this.getFormPropertyId(ownerParameter));
        return org.andromda.utils.StringUtilsHelper.lowerCamelCaseName(backingListName);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getBackingValueName(org.andromda.metafacades.uml.ParameterFacade)
     */
    protected String handleGetBackingValueName(final ParameterFacade ownerParameter)
    {
        final String backingListName =
            StringUtils.replace(
                ObjectUtils.toString(this.getConfiguredProperty(JSFGlobals.BACKING_VALUE_PATTERN)),
                "{0}",
                this.getFormPropertyId(ownerParameter));
        return org.andromda.utils.StringUtilsHelper.lowerCamelCaseName(backingListName);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getLabelListName(org.andromda.metafacades.uml.ParameterFacade)
     */
    protected String handleGetLabelListName(final ParameterFacade ownerParameter)
    {
        return StringUtils.replace(
            ObjectUtils.toString(this.getConfiguredProperty(JSFGlobals.LABEL_LIST_PATTERN)),
            "{0}",
            this.getFormPropertyId(ownerParameter));
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getValueListName(org.andromda.metafacades.uml.ParameterFacade)
     */
    protected String handleGetValueListName(final ParameterFacade ownerParameter)
    {
        return StringUtils.replace(
            ObjectUtils.toString(this.getConfiguredProperty(JSFGlobals.VALUE_LIST_PATTERN)),
            "{0}",
            this.getFormPropertyId(ownerParameter));
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getFormPropertyId(java.lang.String)
     */
    protected String handleGetFormPropertyId(final ParameterFacade ownerParameter)
    {
        return StringUtilsHelper.lowerCamelCaseName(this.getFormPropertyName(ownerParameter));
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isSelectable(org.andromda.metafacades.uml.FrontEndParameter)
     */
    protected boolean handleIsSelectable(final FrontEndParameter ownerParameter)
    {
        boolean selectable = false;
        if (ownerParameter != null)
        {
            if (ownerParameter.isActionParameter())
            {
                selectable = this.isInputMultibox() || this.isInputSelect() || this.isInputRadio();
                final ClassifierFacade type = this.getType();

                if (!selectable && type != null)
                {
                    final String name = this.getName();
                    final String typeName = type.getFullyQualifiedName();

                    // - if the parameter is not selectable but on a targetting page it IS selectable we must
                    //   allow the user to set the backing list too
                    final Collection views = ownerParameter.getAction().getTargetViews();
                    for (final Iterator iterator = views.iterator(); iterator.hasNext() && !selectable;)
                    {
                        final FrontEndView view = (FrontEndView)iterator.next();
                        final Collection parameters = view.getAllActionParameters();
                        for (final Iterator parameterIterator = parameters.iterator();
                            parameterIterator.hasNext() && !selectable;)
                        {
                            final Object object = parameterIterator.next();
                            if (object instanceof JSFParameter)
                            {
                                final JSFParameter parameter = (JSFParameter)object;
                                final String parameterName = parameter.getName();
                                final ClassifierFacade parameterType = parameter.getType();
                                if (parameterType != null)
                                {
                                    final String parameterTypeName = parameterType.getFullyQualifiedName();
                                    if (name.equals(parameterName) && typeName.equals(parameterTypeName))
                                    {
                                        selectable =
                                            parameter.isInputMultibox() || parameter.isInputSelect() ||
                                            parameter.isInputRadio();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (ownerParameter.isControllerOperationArgument())
            {
                final String name = this.getName();
                final Collection actions = ownerParameter.getControllerOperation().getDeferringActions();
                for (final Iterator actionIterator = actions.iterator(); actionIterator.hasNext();)
                {
                    final JSFAction action = (JSFAction)actionIterator.next();
                    final Collection formFields = action.getFormFields();
                    for (final Iterator fieldIterator = formFields.iterator(); fieldIterator.hasNext() && !selectable;)
                    {
                        final Object object = fieldIterator.next();
                        if (object instanceof JSFParameter)
                        {
                            final JSFParameter parameter = (JSFParameter)object;
                            if (name.equals(parameter.getName()))
                            {
                                selectable = parameter.isSelectable();
                            }
                        }
                    }
                }
            }
        }
        return selectable;
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isValidationRequired()
     */
    protected boolean handleIsValidationRequired()
    {
        return !this.getValidatorTypes().isEmpty();
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getValidatorTypes()
     */
    protected java.util.Collection handleGetValidatorTypes()
    {
        return JSFUtils.getValidatorTypes(
            (ModelElementFacade)this.THIS(),
            this.getType());
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getValidatorVars(JSFParameter)
     */
    protected Collection handleGetValidatorVars(JSFParameter ownerParameter)
    {
        return JSFUtils.getValidatorVars(
            ((ModelElementFacade)this.THIS()),
            this.getType(),
            ownerParameter);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getValidWhen()
     */
    protected java.lang.String handleGetValidWhen()
    {
        return JSFUtils.getValidWhen(this);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputTextarea()
     */
    protected boolean handleIsInputTextarea()
    {
        return this.isInputType(JSFGlobals.INPUT_TEXTAREA);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputSelect()
     */
    protected boolean handleIsInputSelect()
    {
        return this.isInputType(JSFGlobals.INPUT_SELECT);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputSecret()
     */
    protected boolean handleIsInputSecret()
    {
        return this.isInputType(JSFGlobals.INPUT_PASSWORD);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputHidden()
     */
    protected boolean handleIsInputHidden()
    {
        return this.isInputType(JSFGlobals.INPUT_HIDDEN);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isPlaintext()
     */
    protected boolean handleIsPlaintext()
    {
        return this.isInputType(JSFGlobals.PLAIN_TEXT);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputRadio()
     */
    protected boolean handleIsInputRadio()
    {
        return this.isInputType(JSFGlobals.INPUT_RADIO);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputText()
     */
    protected boolean handleIsInputText()
    {
        return this.isInputType(JSFGlobals.INPUT_TEXT);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputMultibox()
     */
    protected boolean handleIsInputMultibox()
    {
        return this.isInputType(JSFGlobals.INPUT_MULTIBOX);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputTable()
     */
    protected boolean handleIsInputTable()
    {
        return this.getInputTableIdentifierColumns().length() > 0 || this.isInputType(JSFGlobals.INPUT_TABLE);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputCheckbox()
     */
    protected boolean handleIsInputCheckbox()
    {
        boolean checkbox = this.isInputType(JSFGlobals.INPUT_CHECKBOX);
        if (!checkbox && this.getInputType().length() == 0)
        {
            final ClassifierFacade type = this.getType();
            checkbox = type != null ? type.isBooleanType() : false;
        }
        return checkbox;
    }

    /**
     * Gets the current value of the specified input type (or an empty string
     * if one isn't specified).
     *
     * @return the input type name.
     */
    private final String getInputType()
    {
        return ObjectUtils.toString(this.findTaggedValue(JSFProfile.TAGGEDVALUE_INPUT_TYPE)).trim();
    }

    /**
     * Indicates whether or not this parameter is of the given input type.
     *
     * @param inputType the name of the input type to check for.
     * @return true/false
     */
    private final boolean isInputType(final String inputType)
    {
        return inputType.equalsIgnoreCase(this.getInputType());
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputFile()
     */
    protected boolean handleIsInputFile()
    {
        boolean file = false;
        ClassifierFacade type = getType();
        if (type != null)
        {
            file = type.isFileType();
        }
        return file;
    }

    /**
     * Overridden to provide consistent behavior with {@link JSFParameter#isReadOnly()}.
     *
     * @see org.andromda.metafacades.uml.AttributeFacade#isReadOnly()
     */
    public boolean isReadOnly()
    {
        return JSFUtils.isReadOnly(this);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getValueListDummyValue()
     */
    protected String handleGetValueListDummyValue()
    {
        return this.constructDummyArray();
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getValidatorArgs(java.lang.String)
     */
    protected Collection handleGetValidatorArgs(final String validatorType)
    {
        return JSFUtils.getValidatorArgs(
            (ModelElementFacade)this.THIS(),
            validatorType);
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isStrictDateFormat()
     */
    protected boolean handleIsStrictDateFormat()
    {
        return JSFUtils.isStrictDateFormat((ModelElementFacade)this.THIS());
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getDateFormatter(org.andromda.cartridges.jsf2.metafacades.JSFParameter)
     */
    protected String handleGetDateFormatter(final JSFParameter ownerParameter)
    {
        final ClassifierFacade type = this.getType();
        return type != null && type.isDateType() ? this.getFormPropertyId(ownerParameter) + "DateFormatter" : null;
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#getTimeFormatter(org.andromda.cartridges.jsf2.metafacades.JSFParameter)
     */
    protected String handleGetTimeFormatter(final JSFParameter ownerParameter)
    {
        final ClassifierFacade type = this.getType();
        return type != null && type.isTimeType() ? this.getFormPropertyId(ownerParameter) + "TimeFormatter" : null;
    }

    /**
     * Overridden to provide quotes around string types.
     *
     * @see org.andromda.metafacades.uml.AttributeFacade#getDefaultValue()
     */
    public String getDefaultValue()
    {
        String defaultValue = super.getDefaultValue();
        if (StringUtils.isNotBlank(defaultValue))
        {
            final ClassifierFacade type = this.getType();
            if (type != null && type.isStringType())
            {
                defaultValue = "\"" + defaultValue + "\"";
            }
        }
        return defaultValue;
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isEqualValidator()
     */
    protected boolean handleIsEqualValidator()
    {
        final String equal = JSFUtils.getEqual((ModelElementFacade)this.THIS());
        return equal != null && equal.trim().length() > 0;
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isBackingValueRequired(org.andromda.metafacades.uml.FrontEndParameter)
     */
    protected boolean handleIsBackingValueRequired(final FrontEndParameter ownerParameter)
    {
        boolean required = false;
        if (ownerParameter != null)
        {
            if (ownerParameter.isActionParameter())
            {
                required = this.isInputTable();
                final ClassifierFacade type = this.getType();

                if (!required && type != null)
                {
                    final String name = this.getName();
                    final String typeName = type.getFullyQualifiedName();

                    // - if the parameter is not selectable but on a targetting page it IS selectable we must
                    //   allow the user to set the backing list too
                    final Collection views = ownerParameter.getAction().getTargetViews();
                    for (final Iterator iterator = views.iterator(); iterator.hasNext() && !required;)
                    {
                        final FrontEndView view = (FrontEndView)iterator.next();
                        final Collection parameters = view.getAllActionParameters();
                        for (final Iterator parameterIterator = parameters.iterator();
                            parameterIterator.hasNext() && !required;)
                        {
                            final Object object = parameterIterator.next();
                            if (object instanceof JSFParameter)
                            {
                                final JSFParameter parameter = (JSFParameter)object;
                                final String parameterName = parameter.getName();
                                final ClassifierFacade parameterType = parameter.getType();
                                if (parameterType != null)
                                {
                                    final String parameterTypeName = parameterType.getFullyQualifiedName();
                                    if (name.equals(parameterName) && typeName.equals(parameterTypeName))
                                    {
                                        required = parameter.isInputTable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (ownerParameter.isControllerOperationArgument())
            {
                final String name = this.getName();
                final Collection actions = ownerParameter.getControllerOperation().getDeferringActions();
                for (final Iterator actionIterator = actions.iterator(); actionIterator.hasNext();)
                {
                    final JSFAction action = (JSFAction)actionIterator.next();
                    final Collection formFields = action.getFormFields();
                    for (final Iterator fieldIterator = formFields.iterator(); fieldIterator.hasNext() && !required;)
                    {
                        final Object object = fieldIterator.next();
                        if (object instanceof JSFParameter)
                        {
                            final JSFParameter parameter = (JSFParameter)object;
                            if (name.equals(parameter.getName()))
                            {
                                required = parameter.isBackingValueRequired();
                            }
                        }
                    }
                }
            }
        }
        return required;
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttribute#isInputTypePresent()
     */
    protected boolean handleIsInputTypePresent()
    {
        boolean present = false;
        final ClassifierFacade type = this.getType();
        if (type != null)
        {
            present =
                (StringUtils.isNotBlank(this.getInputType()) || type.isDateType() || type.isBooleanType()) &&
                !this.isPlaintext();
        }
        return present;
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttributer#getInputTableIdentifierColumns()
     */
    protected String handleGetInputTableIdentifierColumns()
    {
        return ObjectUtils.toString(this.findTaggedValue(JSFProfile.TAGGEDVALUE_INPUT_TABLE_IDENTIFIER_COLUMNS)).trim();
    }

    /**
     * @see org.andromda.cartridges.jsf2.metafacades.JSFAttributer#getMaxLength()
     */
    protected String handleGetMaxLength()
    {
        final Collection vars=getValidatorVars(null);
        if(vars == null)
        {
            return null;
        }
        else
        {
            for(Iterator it=vars.iterator(); it.hasNext(); )
            {
                final Object[] values=((Collection)it.next()).toArray();
                if(values[0].equals("maxlength"))
                {
                    return values[1].toString();
                }
            }
            return null;
        }
     }
}