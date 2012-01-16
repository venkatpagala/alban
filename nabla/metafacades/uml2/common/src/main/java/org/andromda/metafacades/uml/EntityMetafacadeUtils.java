package org.andromda.metafacades.uml;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.andromda.core.common.ExceptionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Utilities for dealing with entity metafacades
 *
 * @author Chad Brandon
 * @author Bob Fields
 */
public class EntityMetafacadeUtils
{
    /**
     * <p/> Converts a string following the Java naming conventions to a
     * database attribute name. For example convert customerName to
     * CUSTOMER_NAME.
     * </p>
     *
     * @param modelElementName the string to convert
     * @param separator character used to separate words
     * @return string converted to database attribute format
     */
    public static String toSqlName(
        String modelElementName,
        Object separator)
    {
        ExceptionUtils.checkEmpty(
            "modelElementName",
            modelElementName);

        StringBuilder sqlName = new StringBuilder();
        StringCharacterIterator iterator = new StringCharacterIterator(StringUtils.uncapitalize(modelElementName));

        for (char character = iterator.first(); character != CharacterIterator.DONE; character = iterator.next())
        {
            if (Character.isUpperCase(character))
            {
                sqlName.append(separator);
            }
            character = Character.toUpperCase(character);
            sqlName.append(character);
        }
        return StringEscapeUtils.escapeSql(sqlName.toString());
    }

    /**
     * Gets the SQL name. (i.e. column name, table name, etc.). If it can't find
     * the corresponding tagged value with the specified <code>name</code>,
     * then it uses the element name by default and just returns that.
     *
     * @param prefix the optional prefix to add to the sql name (i.e. table name
     *        prefix, etc.).
     * @param element from which to retrieve the SQL name.
     * @param name the name of the tagged value.
     * @param nameMaxLength if this is not null, then the name returned will be
     *        trimmed to this length (if it happens to be longer).
     * @param separator character used to separate words
     * @return the SQL name as a String.
     */
    public static String getSqlNameFromTaggedValue(
        String prefix,
        ModelElementFacade element,
        String name,
        Short nameMaxLength,
        Object separator)
    {
        return getSqlNameFromTaggedValue(
            prefix,
            element,
            name,
            nameMaxLength,
            null,
            separator);
    }

    /**
     * Gets the SQL name. (i.e. column name, table name, etc.). If it can't find
     * the corresponding tagged value with the specified <code>name</code>,
     * then it uses the element name by default and just returns that.
     *
     * @param element from which to retrieve the SQL name.
     * @param name the name of the tagged value.
     * @param nameMaxLength if this is not null, then the name returned will be
     *        trimmed to this length (if it happens to be longer).
     * @param suffix the optional suffix to add to the sql name (i.e. foreign
     *        key suffix, etc.)
     * @param separator character used to separate words
     * @return the SQL name as a String.
     */
    public static String getSqlNameFromTaggedValue(
        ModelElementFacade element,
        String name,
        Short nameMaxLength,
        String suffix,
        Object separator)
    {
        return getSqlNameFromTaggedValue(
            null,
            element,
            name,
            nameMaxLength,
            suffix,
            separator);
    }

    /**
     * Gets the SQL name. (i.e. column name, table name, etc.). If it can't find
     * the corresponding tagged value with the specified <code>name</code>,
     * then it uses the element name by default and just returns that.
     *
     * @param element from which to retrieve the SQL name.
     * @param name the name of the tagged value.
     * @param nameMaxLength if this is not null, then the name returned will be
     *        trimmed to this length (if it happens to be longer).
     * @param separator character used to separate words
     * @return the SQL name as a String.
     */
    public static String getSqlNameFromTaggedValue(
        ModelElementFacade element,
        String name,
        Short nameMaxLength,
        Object separator)
    {
        return getSqlNameFromTaggedValue(
            null,
            element,
            name,
            nameMaxLength,
            null,
            separator);
    }

    /**
     * Gets the SQL name. (i.e. column name, table name, etc.). If it can't find
     * the corresponding tagged value with the specified <code>name</code>,
     * then it uses the element name by default and just returns that.
     *
     * @param prefix the optional prefix to add to the sql name (i.e. table name
     *        prefix, etc.).
     * @param element from which to retrieve the SQL name.
     * @param name the name of the tagged value.
     * @param nameMaxLength if this is not null, then the name returned will be
     *        trimmed to this length (if it happens to be longer).
     * @param suffix the optional suffix to add to the sql name (i.e. foreign
     *        key suffix, etc.)
     * @param separator character used to separate words
     * @return the SQL name as a String.
     */
    public static String getSqlNameFromTaggedValue(
        String prefix,
        final ModelElementFacade element,
        String name,
        final Short nameMaxLength,
        String suffix,
        final Object separator)
    {
        if (element != null)
        {
            Object value = element.findTaggedValue(name);
            StringBuilder buffer = new StringBuilder(StringUtils.trimToEmpty((String)value));
            if (StringUtils.isEmpty(buffer.toString()))
            {
                // if we can't find the tagValue then use the
                // element name for the name
                buffer = new StringBuilder(toSqlName(
                            element.getName(),
                            separator));
                suffix = StringUtils.trimToEmpty(suffix);
                prefix = StringUtils.trimToEmpty(prefix);
                if (nameMaxLength != null)
                {
                    final short maxLength = (short)(nameMaxLength.shortValue() - suffix.length() - prefix.length());
                    buffer =
                        new StringBuilder(
                            EntityMetafacadeUtils.ensureMaximumNameLength(
                                buffer.toString(),
                                Short.valueOf(maxLength)));
                }
                if (StringUtils.isNotBlank(prefix))
                {
                    buffer.insert(
                        0,
                        prefix);
                }
                if (StringUtils.isNotBlank(suffix))
                {
                    buffer.append(suffix);
                }
            }
            name = buffer.toString();
        }
        return name;
    }

    /**
     * Puts non-abstract entities in order based on associations. To be used in constructors and
     * tests so that entities may be created and deleted in the proper order, without
     * violating key constraints in the database
     *
     * @param entities the entity list to be sorted.
     * @param ascending true for entities with no associations first.
     * @return the sorted entity list.
     */
    public static List<Entity> sortEntities(
        final Collection<Entity> entities,
        boolean ascending)
    {
        // Initially holds entities with no outgoing relations. Add related entities to the end
        List<Entity> sorted = new ArrayList<Entity>();
        // Holds entities with relations after first pass. Second pass sorts the entities
        List<Entity> unsorted = new ArrayList<Entity>();
        if (entities != null)
        {
            for (Entity entity : entities)
            {
                // We won't be testing or creating abstract entities
                if (!entity.isAbstract())
                {
                    Collection<ClassifierFacade> ends = entity.getNavigableConnectingEnds();
                    // Put the entities with no associations first in the sorted list
                    if (ends.size()==0)
                    {
                        sorted.add(entity);
                        //System.out.println(entity.getName() + " No associations");
                    }
                    else
                    {
                        unsorted.add(entity);
                    }
                }
            }
            for (Entity entity : unsorted)
            {
                if (!entity.isAbstract())
                {
                    Collection<AssociationEndFacade> ends = entity.getAssociationEnds();
                    // Test each association to see if incoming or outgoing. sort outgoing before incoming.
                    //for (AssociationEndFacade end : ends)
                    for (AssociationEndFacade end : ends)
                    {
                        AssociationEndFacade otherEnd = end.getOtherEnd();
                        if (end.isNavigable() && end.getType() instanceof Entity)
                        {
                            // otherEnd is actually the association/type on this entity
                            Entity entityEnd = (Entity)end.getType();
                            // Incoming relations are sorted after other entities
                            // Aggregation and Composition always owns the end
                            // One to Many association many end comes last
                            int thisPosition = unsorted.lastIndexOf(entity);
                            int referencedPosition = unsorted.lastIndexOf(entityEnd);
                            // Special case: 1to1 with one end marked as primary. Hibernate cartridge specific.
                            boolean primary = BooleanUtils.toBoolean(
                                ObjectUtils.toString(end.findTaggedValue("andromda_persistence_associationEnd_primary")));
                            //System.out.println(entity.getName() + "=" + thisPosition + " " + entityEnd.getName() + "=" + referencedPosition + " prop=" + end.getName() + " primary=" + primary + " Many=" + end.isMany() + " One2One=" + end.isOne2One() + " end=" + end + " other=" + otherEnd);
                            //System.out.println(entity.getName() + "=" + thisPosition + " " + entityEnd.getName() + "=" + referencedPosition + " prop=" + end.getName() + " AggE=" + end.isAggregation() + " CompE=" + end.isComposition() + " Agg=" + otherEnd.isAggregation() + " Comp=" + otherEnd.isComposition() + " Many=" + end.isMany() + " One2One=" + end.isOne2One() + " end=" + end + " other=" + otherEnd);
                            if (!otherEnd.isAggregation() && !otherEnd.isComposition() &&
                                (!end.isMany() || end.isOne2One()))
                            {
                                if (!end.isOne2One() || !primary)
                                {
                                    if (thisPosition > -1 && referencedPosition > -1 && referencedPosition > thisPosition)
                                    {
                                        // Swap the locations of the two List entries if referenced entity is higher in the list
                                        unsorted.set(referencedPosition, entity);
                                        unsorted.set(thisPosition, entityEnd);
                                        //System.out.println(entity.getName() + " swapped with " + entityEnd.getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sorted.addAll(unsorted);
        }
        if (!ascending)
        {
            Collections.reverse(sorted);
        }
        return sorted;
    }

    /**
     * <p/> Trims the passed in value to the maximum name length.
     * </p>
     * If no maximum length has been set then this method does nothing.
     *
     * @param name the name length to check and trim if necessary
     * @param nameMaxLength if this is not null, then the name returned will be
     *        trimmed to this length (if it happens to be longer).
     * @return String the string to be used as SQL type
     */
    public static String ensureMaximumNameLength(
        String name,
        Short nameMaxLength)
    {
        if (StringUtils.isNotBlank(name) && nameMaxLength != null)
        {
            short max = nameMaxLength.shortValue();
            if (name.length() > max)
            {
                name = name.substring(
                        0,
                        max);
            }
        }
        return name;
    }

    /**
     * Gets all identifiers for an entity. If 'follow' is true, and if
     * no identifiers can be found on the entity, a search up the
     * inheritance chain will be performed, and the identifiers from
     * the first super class having them will be used.   If no
     * identifiers exist, a default identifier will be created if the
     * allowDefaultIdentifiers property is set to true.
     *
     * @param entity the entity for which to retrieve the identifiers
     * @param follow a flag indicating whether or not the inheritance hierarchy
     *        should be followed
     * @return the collection of entity identifier attributes.
     */
    public static Collection<EntityAttribute> getIdentifiers(
        final Entity entity,
        final boolean follow)
    {
        // TODO Entity.getAttributes returns List<? extends AttributeFacade>, currently unchecked conversion
        final Collection attributes = new ArrayList(entity.getAttributes());
        MetafacadeUtils.filterByStereotype(
            attributes,
            UMLProfile.STEREOTYPE_IDENTIFIER);
        final Collection<EntityAttribute> identifiers = new ArrayList<EntityAttribute>();
        identifiers.addAll(attributes);
        /*final Collection<AssociationEndFacade> associations = new ArrayList<AssociationEndFacade>(entity.getNavigableConnectingEnds(follow));
        MetafacadeUtils.filterByStereotype(
                associations,
                UMLProfile.STEREOTYPE_IDENTIFIER);
        identifiers.addAll(associations);*/

        if (identifiers.isEmpty() && follow && entity.getGeneralization() instanceof Entity)
        {
            identifiers.addAll(getIdentifiers((Entity)entity.getGeneralization(), follow));
        }
        return identifiers;
    }

    /**
     * Constructs a sql type name from the given <code>mappedName</code> and
     * <code>columnLength</code>.
     *
     * @param typeName the actual type name (usually retrieved from a mappings
     *        file, i.e. NUMBER(19).
     * @param columnLength the length of the column.
     * @return the new name construct
     */
    public static String constructSqlTypeName(
        final String typeName,
        final String columnLength)
    {
        String value = typeName;
        if (StringUtils.isNotBlank(typeName))
        {
            final char beginChar = '(';
            final char endChar = ')';
            final int beginIndex = value.indexOf(beginChar);
            final int endIndex = value.indexOf(endChar);
            if (beginIndex != -1 && endIndex != -1 && endIndex > beginIndex)
            {
                String replacement = value.substring(
                        beginIndex,
                        endIndex) + endChar;
                value = StringUtils.replace(
                        value,
                        replacement,
                        beginChar + columnLength + endChar);
            }
            else
            {
                value = value + beginChar + columnLength + endChar;
            }
        }
        return value;
    }

    /**
     * Constructs and returns the foreign key constraint name for the given <code>associationEnd</code>, <code>suffix</code>, <code>sqlNameSeparator</code>
     * and <code>maxLengthProperty</code>.
     *
     * @param associationEnd the association end for which to construct the constraint name.
     * @param suffix the suffix appended to the constraint name (if not limited by length).
     * @param sqlNameSeparator the SQL name separator to use (i.e. '_').
     * @param maxLengthProperty the numeric value stored as a string indicating the max length the constraint may be.
     * @return the constructed foreign key constraint name.
     */
    public static String getForeignKeyConstraintName(EntityAssociationEnd associationEnd, String suffix, String sqlNameSeparator, String maxLengthProperty)
    {
        String constraintName;

        final Object taggedValueObject = associationEnd.findTaggedValue(
                UMLProfile.TAGGEDVALUE_PERSISTENCE_FOREIGN_KEY_CONSTRAINT_NAME);
        if (taggedValueObject == null)
        {
            // we construct our own foreign key constraint name here
            StringBuilder buffer = new StringBuilder();

            final ClassifierFacade type = associationEnd.getOtherEnd().getType();
            if (type instanceof Entity)
            {
                Entity entity = (Entity)type;
                buffer.append(entity.getTableName());
            }
            else
            {
                // should not happen
                buffer.append(type.getName().toUpperCase());
            }

            buffer.append(sqlNameSeparator);
            buffer.append(associationEnd.getColumnName());
            constraintName = buffer.toString();

            // we take into consideration the maximum length allowed
            final short maxLength = (short)(Short.valueOf(maxLengthProperty).shortValue() - suffix.length());
            buffer = new StringBuilder(EntityMetafacadeUtils.ensureMaximumNameLength(constraintName, Short.valueOf(maxLength)));
            buffer.append(suffix);
            constraintName = EntityMetafacadeUtils.getUniqueForeignKeyConstraintName(buffer.toString());
        }
        else
        {
            // use the tagged value
            constraintName = taggedValueObject.toString();
        }
        return constraintName;
    }

    /**
     * An internal static cache for foreign key names (allows us to keep track
     * of which ones have been used).  Its not great that its static, but for now
     * this is the easiest way to enforce this.
     */
    private static Collection<String> foreignKeyConstraintNameCache = new ArrayList<String>();

    /**
     * Retrieves a unique foreign key constraint name given the proposedName.  Compares the proposedName
     * against any foreign key names already stored in an internal collection.
     *
     * @param proposedName the proposed foreign key name.
     * @return the unique foreign key name.
     */
    private static String getUniqueForeignKeyConstraintName(String proposedName)
    {
        final char[] characters = proposedName.toCharArray();
        int numericValue = 0;
        for (int ctr = 0; ctr < characters.length; ctr++)
        {
            numericValue = numericValue + Character.getNumericValue(characters[0]);
        }
        return getUniqueForeignKeyConstraintName(proposedName, new Random(numericValue));
    }

    /**
     * Retrieves a unique foreign key constraint name given the proposedName.  Compares the proposedName
     * against any foreign key names already stored in an internal collection.
     *
     * @param proposedName the proposed foreign key name.
     * @param random the Random number generator to use for enforcing uniqueness.
     * @return the unique foreign key name.
     */
    private static String getUniqueForeignKeyConstraintName(String proposedName, final Random random)
    {
        String name;
        if (foreignKeyConstraintNameCache.contains(proposedName))
        {
            final char[] characters = proposedName.toCharArray();
            int randomInt = random.nextInt(characters.length);
            char randomChar = Character.toUpperCase(characters[randomInt]);
            proposedName = proposedName.substring(0, proposedName.length() - 1) + randomChar;
            name = getUniqueForeignKeyConstraintName(proposedName, random);
        }
        else
        {
            name = proposedName;
            foreignKeyConstraintNameCache.add(name);
        }
        return name;
    }

    /**
     * Clears out the foreign key cache.
     */
    public static void clearForeignKeyConstraintNameCache()
    {
        foreignKeyConstraintNameCache.clear();
    }
}
