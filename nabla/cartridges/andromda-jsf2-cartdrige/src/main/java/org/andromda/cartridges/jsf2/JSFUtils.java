package org.andromda.cartridges.jsf2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.andromda.cartridges.jsf2.metafacades.JSFAttribute;
import org.andromda.cartridges.jsf2.metafacades.JSFManageableEntityAttribute;
import org.andromda.cartridges.jsf2.metafacades.JSFParameter;
import org.andromda.metafacades.uml.AttributeFacade;
import org.andromda.metafacades.uml.ClassifierFacade;
import org.andromda.metafacades.uml.FrontEndAction;
import org.andromda.metafacades.uml.FrontEndParameter;
import org.andromda.metafacades.uml.ModelElementFacade;
import org.andromda.metafacades.uml.OperationFacade;
import org.andromda.metafacades.uml.ParameterFacade;
import org.andromda.metafacades.uml.UMLMetafacadeUtils;
import org.andromda.utils.StringUtilsHelper;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;


/**
 * Utilties for use within the JSF cartridge.
 *
 * @author Chad Brandon
 */
public class JSFUtils
{
    /**
     * Converts the argument into a web resource name, this means: all lowercase
     * characters and words are separated with dashes.
     *
     * @param string any string
     * @return the string converted to a value that would be well-suited for a
     *         web file name
     */
    public static String toWebResourceName(final String string)
    {
        return StringUtilsHelper.separate(
            string,
            "-").toLowerCase();
    }

    private static final Pattern VALIDATOR_TAGGEDVALUE_PATTERN =
        Pattern.compile("\\w+(\\(\\w+=[^,)]*(,\\w+=[^,)]*)*\\))?");

    /**
     * Reads the validator arguments from the the given tagged value.
     *
     * @return returns a list of String instances or an empty list
     * @throws IllegalArgumentException when the input string does not match the required pattern
     */
    public static List<String> parseValidatorArgs(String validatorTaggedValue)
    {
        if (validatorTaggedValue == null)
        {
            throw new IllegalArgumentException("Validator tagged value cannot be null");
        }

        // check if the input tagged value matches the required pattern
        if (!VALIDATOR_TAGGEDVALUE_PATTERN.matcher(validatorTaggedValue).matches())
        {
            throw new IllegalArgumentException(
                "Illegal validator tagged value (this tag is used to specify custom validators " +
                "and might look like myValidator(myVar=myArg,myVar2=myArg2), perhaps you wanted to use " +
                "@andromda.presentation.view.field.format?): " + validatorTaggedValue);
        }

        final List<String> validatorArgs = new ArrayList<String>();

        // only keep what is between parentheses (if any)
        int left = validatorTaggedValue.indexOf('(');
        if (left > -1)
        {
            final int right = validatorTaggedValue.indexOf(')');
            validatorTaggedValue = validatorTaggedValue.substring(
                    left + 1,
                    right);

            final String[] pairs = validatorTaggedValue.split(",");
            for (int i = 0; i < pairs.length; i++)
            {
                final String pair = pairs[i];
                final int equalsIndex = pair.indexOf('=');

                // it's possible the argument is the empty string
                if (equalsIndex < pair.length() - 1)
                {
                    validatorArgs.add(pair.substring(equalsIndex + 1));
                }
                else
                {
                    validatorArgs.add("");
                }
            }
        }
        return validatorArgs;
    }

    /**
     * Reads the validator variable names from the the given tagged value.
     *
     * @return never null, returns a list of String instances
     * @throws IllegalArgumentException when the input string does not match the required pattern
     */
    public static List parseValidatorVars(String validatorTaggedValue)
    {
        if (validatorTaggedValue == null)
        {
            throw new IllegalArgumentException("Validator tagged value cannot be null");
        }

        // check if the input tagged value matches the required pattern
        if (!VALIDATOR_TAGGEDVALUE_PATTERN.matcher(validatorTaggedValue).matches())
        {
            throw new IllegalArgumentException("Illegal validator tagged value: " + validatorTaggedValue);
        }

        final List<String> validatorVars = new ArrayList<String>();

        // only keep what is between parentheses (if any)
        int left = validatorTaggedValue.indexOf('(');
        if (left > -1)
        {
            int right = validatorTaggedValue.indexOf(')');
            validatorTaggedValue = validatorTaggedValue.substring(
                    left + 1,
                    right);

            final String[] pairs = validatorTaggedValue.split(",");
            for (int i = 0; i < pairs.length; i++)
            {
                final String pair = pairs[i];
                final int equalsIndex = pair.indexOf('=');
                validatorVars.add(pair.substring(
                        0,
                        equalsIndex));
            }
        }
        return validatorVars;
    }

    /**
     * Parses the validator name for a tagged value.
     *
     * @throws IllegalArgumentException when the input string does not match the required pattern
     */
    public static String parseValidatorName(final String validatorTaggedValue)
    {
        if (validatorTaggedValue == null)
        {
            throw new IllegalArgumentException("Validator tagged value cannot be null");
        }

        // check if the input tagged value matches the required pattern
        if (!VALIDATOR_TAGGEDVALUE_PATTERN.matcher(validatorTaggedValue).matches())
        {
            throw new IllegalArgumentException("Illegal validator tagged value: " + validatorTaggedValue);
        }

        final int leftParen = validatorTaggedValue.indexOf('(');
        return (leftParen == -1) ? validatorTaggedValue : validatorTaggedValue.substring(
            0,
            leftParen);
    }

    /**
     * Constructs a string representing an array initialization in Java.
     *
     * @param name the name to give the array.
     * @param count the number of items to give the array.
     * @return A String representing Java code for the initialization of an array.
     */
    public static String constructDummyArrayDeclaration(
        final String name,
        final int count)
    {
        final StringBuffer array = new StringBuffer("new Object[] {");
        for (int ctr = 1; ctr <= count; ctr++)
        {
            array.append("\"" + name + "-" + ctr + "\"");
            if (ctr != count)
            {
                array.append(", ");
            }
        }
        array.append("}");
        return array.toString();
    }

    /**
     * @return this field's date format
     */
    public static String getDateFormat(String format)
    {
        format = StringUtils.trimToEmpty(format);
        return format.endsWith(STRICT) ? getToken(
            format,
            1,
            2) : getToken(
            format,
            0,
            1);
    }

    private static final String STRICT = "strict";

    /**
     * @return <code>true</code> if this field's value needs to conform to a strict date format, <code>false</code> otherwise
     */
    public static boolean isStrictDateFormat(String format)
    {
        return strictDateTimeFormat ? strictDateTimeFormat : STRICT.equalsIgnoreCase(getToken(
                format,
                0,
                2));
    }

    /**
     * Indicates if the given <code>format</code> is an email format.
     *
     * @return <code>true</code> if this field is to be formatted as an email address, <code>false</code> otherwise
     */
    public static boolean isEmailFormat(String format)
    {
        return "email".equalsIgnoreCase(JSFUtils.getToken(
                format,
                0,
                2));
    }

    /**
     * Indicates if the given <code>format</code> is an equal format.
     *
     * @return <code>true</code> if this field is to be formatted as an email address, <code>false</code> otherwise
     */
    public static boolean isEqualFormat(String format)
    {
        return "equal".equalsIgnoreCase(JSFUtils.getToken(
                format,
                0,
                2));
    }

    /**
     * Indicates if the given <code>format</code> is a creditcard format.
     *
     * @return <code>true</code> if this field is to be formatted as a credit card, <code>false</code> otherwise
     */
    public static boolean isCreditCardFormat(final String format)
    {
        return "creditcard".equalsIgnoreCase(JSFUtils.getToken(
                format,
                0,
                2));
    }

    /**
     * Indicates if the given <code>format</code> is a pattern format.
     *
     * @return <code>true</code> if this field's value needs to respect a certain pattern, <code>false</code> otherwise
     */
    public static boolean isPatternFormat(final String format)
    {
        return "pattern".equalsIgnoreCase(JSFUtils.getToken(
                format,
                0,
                2));
    }

    /**
     * Indicates if the given <code>format</code> is a minlength format.
     *
     * @return <code>true</code> if this field's value needs to consist of at least a certain
     *         number of characters, <code>false</code> otherwise
     */
    public static boolean isMinLengthFormat(final String format)
    {
        return "minlength".equalsIgnoreCase(JSFUtils.getToken(
                format,
                0,
                2));
    }

    /**
     * Indicates if the given <code>format</code> is a maxlength format.
     *
     * @return <code>true</code> if this field's value needs to consist of at maximum a certain
     *         number of characters, <code>false</code> otherwise
     */
    public static boolean isMaxLengthFormat(String format)
    {
        return "maxlength".equalsIgnoreCase(JSFUtils.getToken(
                format,
                0,
                2));
    }

    /**
     * @return the i-th space delimited token read from the argument String, where i does not exceed the specified limit
     */
    public static String getToken(
        String string,
        int index,
        int limit)
    {
        String token = null;
        if (string != null && string.length() > 0)
        {
            final String[] tokens = string.split(
                    "[\\s]+",
                    limit);
            token = index >= tokens.length ? null : tokens[index];
        }
        return token;
    }

    /**
     * Retrieves the input format (if one is defined), for the given
     * <code>element</code>.
     * @param element the model element for which to retrieve the input format.
     * @return the input format.
     */
    public static String getInputFormat(final ModelElementFacade element)
    {
        final Object value = element.findTaggedValue(JSFProfile.TAGGEDVALUE_INPUT_FORMAT);
        final String format = value == null ? null : String.valueOf(value);
        return format == null ? null : format.trim();
    }

    /**
     * Indicates if the given <code>format</code> is a range format.
     *
     * @return <code>true</code> if this field's value needs to be in a specific range, <code>false</code> otherwise
     */
    public static boolean isRangeFormat(final String format)
    {
        return "range".equalsIgnoreCase(JSFUtils.getToken(
                format,
                0,
                2));
    }

    /**
     * @return <code>true</code> if the type of this field is a byte, <code>false</code> otherwise
     */
    public static boolean isByte(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.BYTE_TYPE_NAME);
    }

    /**
     * @return <code>true</code> if the type of this field is a short, <code>false</code> otherwise
     */
    public static boolean isShort(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.SHORT_TYPE_NAME);
    }

    /**
     * @return <code>true</code> if the type of this field is an integer, <code>false</code> otherwise
     */
    public static boolean isInteger(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.INTEGER_TYPE_NAME);
    }

    /**
     * @return <code>true</code> if the type of this field is a long integer, <code>false</code> otherwise
     */
    public static boolean isLong(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.LONG_TYPE_NAME);
    }

    /**
     * @return <code>true</code> if the type of this field is a floating point, <code>false</code> otherwise
     */
    public static boolean isFloat(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.FLOAT_TYPE_NAME);
    }

    /**
     * @return <code>true</code> if the type of this field is a double precision floating point, <code>false</code> otherwise
     */
    public static boolean isDouble(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.DOUBLE_TYPE_NAME);
    }


    /**
     * @return <code>true</code> if the type of this field is a date, <code>false</code> otherwise
     */
    public static boolean isDate(final ClassifierFacade type)
    {
        return type != null && type.isDateType();
    }

    /**
     * @return <code>true</code> if the type of this field is a time, <code>false</code> otherwise
     */
    public static boolean isTime(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.TIME_TYPE_NAME);
    }

    /**
     * @return <code>true</code> if the type of this field is a URL, <code>false</code> otherwise
     */
    public static boolean isUrl(final ClassifierFacade type)
    {
        return isType(
            type,
            JSFProfile.URL_TYPE_NAME);
    }


    private static boolean isType(final ClassifierFacade type, String typeName)
    {
        boolean isType = UMLMetafacadeUtils.isType(
            type,
            typeName);
        if (!isType)
        {
            // - handle abstract types that are mapped to java types
            if (type.getLanguageMappings() != null)
            {
                final String javaTypeName = type.getLanguageMappings().getTo(type.getFullyQualifiedName(true));
                if (javaTypeName != null)
                {
                    isType = javaTypeName.replaceAll(".*\\.", "").equalsIgnoreCase(type.getLanguageMappings().getTo(typeName));
                }
            }
        }
        return isType;
    }

    /**
     * @return <code>true</code> if the type of this field is a String, <code>false</code> otherwise
     */
    public static boolean isString(final ClassifierFacade type)
    {
        return type != null && type.isStringType();
    }

    /**
     * Indicates if the given element is read-only or not.
     *
     * @param element the element to check.
     * @return true/false
     */
    public static boolean isReadOnly(final ModelElementFacade element)
    {
        boolean readOnly = false;
        if (element != null)
        {
            final Object value = element.findTaggedValue(JSFProfile.TAGGEDVALUE_INPUT_READONLY);
            readOnly = Boolean.valueOf(ObjectUtils.toString(value)).booleanValue();
        }
        return readOnly;
    }

    /**
     * Retrieves the "equal" value from the given element (if one is present).
     *
     * @param element the element from which to retrieve the equal value.
     * @return the "equal" value.
     */
    public static java.lang.String getEqual(final ModelElementFacade element)
    {
        String equal = null;
        if (element != null)
        {
            final Object value = element.findTaggedValue(JSFProfile.TAGGEDVALUE_INPUT_EQUAL);
            equal = value == null ? null : value.toString();
        }
        return equal;
    }

    /**
     * Retrieves the "equal" value from the given element (if one is present).
     *
     * @param element the element from which to retrieve the equal value.
     * @param ownerParameter the optional owner parameter (specified if the element is an attribute).
     * @return the "equal" value.
     */
    public static java.lang.String getEqual(final ModelElementFacade element, final ParameterFacade ownerParameter)
    {
        String equal = null;
        if (element != null)
        {
            final Object value = element.findTaggedValue(JSFProfile.TAGGEDVALUE_INPUT_EQUAL);
            equal = value == null ? null : value.toString();
            if (StringUtils.isNotBlank(equal) && ownerParameter != null)
            {
                equal = ownerParameter.getName() + StringUtilsHelper.upperCamelCaseName(equal);
            }
        }
        return equal;
    }

    /**
     * Retrieves the "validwhen" value from the given element (if one is present).
     *
     * @param element the element from which to retrieve the validwhen value.
     * @return the "validwhen" value.
     */
    public static java.lang.String getValidWhen(final ModelElementFacade element)
    {
        String validWhen = null;
        if (element != null)
        {
            final Object value = element.findTaggedValue(JSFProfile.TAGGEDVALUE_INPUT_VALIDWHEN);
            validWhen = value == null ? null : '(' + value.toString() + ')';
        }
        return validWhen;
    }

    /**
     * @return the lower limit for this field's value's range
     */
    public static String getRangeStart(final String format)
    {
        return JSFUtils.getToken(
            format,
            1,
            3);
    }

    /**
     * @return the upper limit for this field's value's range
     */
    public static String getRangeEnd(final String format)
    {
        return JSFUtils.getToken(
            format,
            2,
            3);
    }

    /**
     * @return the minimum number of characters this field's value must consist of
     */
    public static String getMinLengthValue(final String format)
    {
        return JSFUtils.getToken(
            format,
            1,
            2);
    }

    /**
     * @return the maximum number of characters this field's value must consist of
     */
    public static String getMaxLengthValue(final String format)
    {
        return JSFUtils.getToken(
            format,
            1,
            2);
    }

    /**
     * @return the pattern this field's value must respect
     */
    public static String getPatternValue(final String format)
    {
        return '^' + JSFUtils.getToken(
            format,
            1,
            2) + '$';
    }

    /**
     * Retrieves the validator types as a collection from the given
     * <code>element</code> (if any can be retrieved).
     *
     * @param element the element from which to retrieve the types.
     * @param type the type of the element.
     * @return the collection of validator types.
     */
    public static java.util.Collection getValidatorTypes(
        final ModelElementFacade element,
        final ClassifierFacade type)
    {
        final Collection<String> validatorTypesList = new ArrayList<String>();
        if (element != null && type != null)
        {
            final String format = JSFUtils.getInputFormat(element);
            final boolean isRangeFormat = format != null && isRangeFormat(format);
            if (element instanceof AttributeFacade)
            {
                if (((AttributeFacade)element).isRequired())
                {
                    validatorTypesList.add("required");
                }
            }
            else if (element instanceof JSFParameter)
            {
                if (((JSFParameter)element).isRequired())
                {
                    validatorTypesList.add("required");
                }
            }
            if (JSFUtils.isByte(type))
            {
                validatorTypesList.add("byte");
            }
            else if (JSFUtils.isShort(type))
            {
                validatorTypesList.add("short");
            }
            else if (JSFUtils.isInteger(type))
            {
                validatorTypesList.add("integer");
            }
            else if (JSFUtils.isLong(type))
            {
                validatorTypesList.add("long");
            }
            else if (JSFUtils.isFloat(type))
            {
                validatorTypesList.add("float");
            }
            else if (JSFUtils.isDouble(type))
            {
                validatorTypesList.add("double");
            }
            else if (JSFUtils.isDate(type))
            {
                validatorTypesList.add("date");
            }
            else if (JSFUtils.isTime(type))
            {
                validatorTypesList.add("time");
            }
            else if (JSFUtils.isUrl(type))
            {
                validatorTypesList.add("url");
            }

            if (isRangeFormat)
            {
                if (JSFUtils.isInteger(type) || JSFUtils.isShort(type) || JSFUtils.isLong(type))
                {
                    validatorTypesList.add("intRange");
                }
                if (JSFUtils.isFloat(type))
                {
                    validatorTypesList.add("floatRange");
                }
                if (JSFUtils.isDouble(type))
                {
                    validatorTypesList.add("doubleRange");
                }
            }

            if (format != null)
            {
                if (JSFUtils.isString(type) && JSFUtils.isEmailFormat(format))
                {
                    validatorTypesList.add("email");
                }
                else if (JSFUtils.isString(type) && JSFUtils.isCreditCardFormat(format))
                {
                    validatorTypesList.add("creditCard");
                }
                else
                {
                    Collection formats = element.findTaggedValues(JSFProfile.TAGGEDVALUE_INPUT_FORMAT);
                    for (final Iterator formatIterator = formats.iterator(); formatIterator.hasNext();)
                    {
                        String additionalFormat = String.valueOf(formatIterator.next());
                        if (JSFUtils.isMinLengthFormat(additionalFormat))
                        {
                            validatorTypesList.add("minlength");
                        }
                        else if (JSFUtils.isMaxLengthFormat(additionalFormat))
                        {
                            validatorTypesList.add("maxlength");
                        }
                        else if (JSFUtils.isPatternFormat(additionalFormat))
                        {
                            validatorTypesList.add("mask");
                        }
                    }
                }
            }

            if (JSFUtils.getValidWhen(element) != null)
            {
                validatorTypesList.add("validwhen");
            }
            if (JSFUtils.getEqual(element) != null)
            {
                validatorTypesList.add("equal");
            }
        }

        // - custom (paramterized) validators are allowed here
        final Collection taggedValues = element.findTaggedValues(JSFProfile.TAGGEDVALUE_INPUT_VALIDATORS);
        for (final Iterator iterator = taggedValues.iterator(); iterator.hasNext();)
        {
            String validator = String.valueOf(iterator.next());
            validatorTypesList.add(JSFUtils.parseValidatorName(validator));
        }
        return validatorTypesList;
    }

    /**
     * Gets the validator variables for the given <code>element</code> (if they can
     * be retrieved).
     *
     * @param element the element from which to retrieve the variables
     * @param type the type of the element.
     * @param ownerParameter the optional owner parameter (if the element is an attribute for example).
     * @return the collection of validator variables.
     */
    public static java.util.Collection getValidatorVars(
        final ModelElementFacade element,
        final ClassifierFacade type,
        final ParameterFacade ownerParameter)
    {
        final Map<String, Object> vars = new LinkedHashMap<String, Object>();
        if (element != null && type != null)
        {
            final String format = JSFUtils.getInputFormat(element);
            if (format != null)
            {
                final boolean isRangeFormat = JSFUtils.isRangeFormat(format);

                if (isRangeFormat)
                {
                    final String min = "min";
                    final String max = "max";
                    vars.put(
                        min,
                        Arrays.asList(new Object[] {min, JSFUtils.getRangeStart(format)}));
                    vars.put(
                        max,
                        Arrays.asList(new Object[] {max, JSFUtils.getRangeEnd(format)}));
                }
                else
                {
                    final Collection formats = element.findTaggedValues(JSFProfile.TAGGEDVALUE_INPUT_FORMAT);
                    for (final Iterator formatIterator = formats.iterator(); formatIterator.hasNext();)
                    {
                        final String additionalFormat = String.valueOf(formatIterator.next());
                        final String minlength = "minlength";
                        final String maxlength = "maxlength";
                        final String mask = "mask";
                        if (JSFUtils.isMinLengthFormat(additionalFormat))
                        {
                            vars.put(
                                minlength,
                                Arrays.asList(new Object[] {minlength, JSFUtils.getMinLengthValue(additionalFormat)}));
                        }
                        else if (JSFUtils.isMaxLengthFormat(additionalFormat))
                        {
                            vars.put(
                                maxlength,
                                Arrays.asList(new Object[] {maxlength, JSFUtils.getMaxLengthValue(additionalFormat)}));
                        }
                        else if (JSFUtils.isPatternFormat(additionalFormat))
                        {
                            vars.put(
                                mask,
                                Arrays.asList(new Object[] {mask, JSFUtils.getPatternValue(additionalFormat)}));
                        }
                    }
                }
            }
            String inputFormat;
            if (element instanceof JSFAttribute)
            {
                inputFormat = ((JSFAttribute)element).getFormat();
            }
            else if (element instanceof JSFParameter)
            {
                inputFormat = ((JSFParameter)element).getFormat();
            }
            else if (element instanceof JSFManageableEntityAttribute)
            {
                inputFormat = ((JSFManageableEntityAttribute)element).getFormat();
            }
            else
            {
                throw new RuntimeException("'element' is an invalid type, it must be either an instance of '" +
                    JSFAttribute.class.getName() + "' or '" + JSFParameter.class.getName() + "'");
            }
            if (JSFUtils.isDate(type))
            {
                final String datePatternStrict = "datePatternStrict";
                if (format != null && JSFUtils.isStrictDateFormat(format))
                {
                    vars.put(
                        datePatternStrict,
                        Arrays.asList(new Object[] {datePatternStrict, inputFormat}));
                }
                else
                {
                    final String datePattern = "datePattern";
                    vars.put(
                        datePattern,
                        Arrays.asList(new Object[] {datePattern, inputFormat}));
                }
            }
            if (JSFUtils.isTime(type))
            {
                final String timePattern = "timePattern";
                vars.put(
                    timePattern,
                    Arrays.asList(new Object[] {timePattern, inputFormat}));
            }

            final String validWhen = JSFUtils.getValidWhen(element);
            if (validWhen != null)
            {
                final String test = "test";
                vars.put(
                    test,
                    Arrays.asList(new Object[] {test, validWhen}));
            }

            final String equal = JSFUtils.getEqual(element, ownerParameter);
            if (equal != null)
            {
                final String fieldName = "fieldName";
                vars.put(
                    fieldName,
                    Arrays.asList(new Object[] {fieldName, equal}));
            }
        }

        // - custom (parameterized) validators are allowed here
        //   in this case we will reuse the validator arg values
        final Collection taggedValues = element.findTaggedValues(JSFProfile.TAGGEDVALUE_INPUT_VALIDATORS);
        for (final Iterator iterator = taggedValues.iterator(); iterator.hasNext();)
        {
            final String validator = String.valueOf(iterator.next());

            // - guaranteed to be of the same length
            final List validatorVars = JSFUtils.parseValidatorVars(validator);
            final List validatorArgs = JSFUtils.parseValidatorArgs(validator);

            for (int ctr = 0; ctr < validatorVars.size(); ctr++)
            {
                final String validatorVar = (String)validatorVars.get(ctr);
                final String validatorArg = (String)validatorArgs.get(ctr);
                vars.put(
                    validatorVar,
                    Arrays.asList(new Object[] {validatorVar, validatorArg}));
            }
        }
        return vars.values();
    }

    /**
     * Gets the validator args for the <code>element</code> and the given <code>validatorType</code>.
     *
     * @param element the element for which to retrieve the arguments.
     * @param validatorType the validator type name.
     * @return the validator args as a collection.
     */
    public static java.util.Collection getValidatorArgs(
        final ModelElementFacade element,
        final java.lang.String validatorType)
    {
        final Collection<Object> args = new ArrayList<Object>();
        if ("intRange".equals(validatorType) || "floatRange".equals(validatorType) ||
            "doubleRange".equals(validatorType))
        {
            args.add("${var:min}");
            args.add("${var:max}");
        }
        else if ("minlength".equals(validatorType))
        {
            args.add("${var:minlength}");
        }
        else if ("maxlength".equals(validatorType))
        {
            args.add("${var:maxlength}");
        }
        else if ("date".equals(validatorType))
        {
            final String validatorFormat = JSFUtils.getInputFormat(element);
            if (JSFUtils.isStrictDateFormat(validatorFormat))
            {
                args.add("${var:datePatternStrict}");
            }
            else
            {
                args.add("${var:datePattern}");
            }
        }
        else if ("time".equals(validatorType))
        {
            args.add("${var:timePattern}");
        }
        else if ("equal".equals(validatorType))
        {
            ModelElementFacade equalParameter = null;
            final String equal = JSFUtils.getEqual(element);
            if (element instanceof ParameterFacade)
            {
                final FrontEndParameter parameter = (FrontEndParameter)element;
                final OperationFacade operation = parameter.getOperation();
                if (operation != null)
                {
                    equalParameter = operation.findParameter(equal);
                }
                if (equalParameter == null)
                {
                    final FrontEndAction action = parameter.getAction();
                    if (action != null)
                    {
                        equalParameter = action.findParameter(equal);
                    }
                }
            }
            else if (element instanceof AttributeFacade)
            {
                final AttributeFacade attribute = (AttributeFacade)element;
                final ClassifierFacade owner = attribute.getOwner();
                if (owner != null)
                {
                    equalParameter = owner.findAttribute(equal);
                }
            }
            args.add(equalParameter);
            args.add("${var:fieldName}");
        }

        // custom (paramterized) validators are allowed here
        final Collection taggedValues = element.findTaggedValues(JSFProfile.TAGGEDVALUE_INPUT_VALIDATORS);
        for (final Iterator iterator = taggedValues.iterator(); iterator.hasNext();)
        {
            final String validator = String.valueOf(iterator.next());
            if (validatorType.equals(JSFUtils.parseValidatorName(validator)))
            {
                args.addAll(JSFUtils.parseValidatorArgs(validator));
            }
        }
        return args;
    }

    /**
     * Whether or not date patterns should be treated as strict.
     */
    private static boolean strictDateTimeFormat;

    /**
     * Sets whether or not the dattern patterns should be treated as strict.
     *
     * @param strictDateTimeFormat
     */
    public void setStrictDateTimeFormat(final boolean strictDateTimeFormat)
    {
        JSFUtils.strictDateTimeFormat = strictDateTimeFormat;
    }

    /**
     * Indicates whether or not the format for this element is a strict date
     * format.
     * @return true/false
     */
    public static boolean isStrictDateFormat(final ModelElementFacade element)
    {
        final String format = JSFUtils.getInputFormat(element);
        return JSFUtils.isStrictDateFormat(format);
    }

    /**
     * Gets the format string for the given <code>element</code>.
     *
     * @param element the element for which to retrieve the format.
     * @param type the type of the element.
     * @return the format string (if one is present otherwise null).
     */
    public static String getFormat(
        final ModelElementFacade element,
        final ClassifierFacade type,
        final String defaultDateFormat,
        final String defaultTimeFormat)
    {
        String format = null;
        if (element != null && type != null)
        {
            format = JSFUtils.getInputFormat(element);
            if (format == null)
            {
                if(type.isDateType() && type.isTimeType())
                {
                    format = defaultDateFormat+" "+defaultTimeFormat;
                }
                else if (type.isTimeType())
                {
                    format = defaultTimeFormat;
                }
                else if (type.isDateType())
                {
                    format = defaultDateFormat;
                }
            }
            else if (type.isDateType())
            {
                format = JSFUtils.getDateFormat(format);
            }
        }
        return format;
    }

    /**
     * The XHTML extension.
     */
    private static final String EXTENSION_XHTML = "xhtml";

    /**
     * Gets the extension for the view type.
     *
     * @return the view type extension.
     */
    public String getViewExtension()
    {
        return EXTENSION_XHTML;
    }

    private String portletContainer;

    public void setPortletContainer(String portletContainer)
    {
        this.portletContainer = portletContainer;
    }

    private boolean isPortlet()
    {
        return StringUtils.isNotBlank(this.portletContainer);
    }

    public String getRequestClassName()
    {
        final String className;
        if (this.isPortlet())
        {
            className = "javax.portlet.PortletRequest";
        }
        else
        {
            className = "javax.servlet.http.HttpServletRequest";
        }
        return className;
    }

    public String getResponseClassName()
    {
        final String className;
        if (this.isPortlet())
        {
            className = "javax.portlet.PortletResponse";
        }
        else
        {
            className = "javax.servlet.http.HttpServletResponse";
        }
        return className;
    }

    public String getSessionClassName()
    {
        final String className;
        if (this.isPortlet())
        {
            className = "javax.portlet.PortletSession";
        }
        else
        {
            className = "javax.servlet.http.HttpSession";
        }
        return className;
    }

    /**
     * @return the calculated SerialVersionUID
     */
    public static String calcSerialVersionUID(StringBuffer buffer){
        final String signature = buffer.toString();
        String serialVersionUID = String.valueOf(0L);
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] hashBytes = md.digest(signature.getBytes());

            long hash = 0;
            for (int ctr = Math.min(
                        hashBytes.length,
                        8) - 1; ctr >= 0; ctr--)
            {
                hash = (hash << 8) | (hashBytes[ctr] & 0xFF);
            }
            serialVersionUID = String.valueOf(hash);
        }
        catch (final NoSuchAlgorithmException exception)
        {
            throw new RuntimeException("Error performing JSFAction.getFormSerialVersionUID",exception);
        }
        
        return serialVersionUID;
    }
    
    public int calculateIcefacesTimeout(String string)
    {
        return string != null ? Integer.valueOf(string) * 6000 : 0;
    }
}
