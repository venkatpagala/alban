// license-header java merge-point
// Generated by andromda-jsf cartridge (forms\crud\SearchForm.java.vsl) DO NOT EDIT!
package org.andromda.test.howto16.a.crud;

import java.io.Serializable;
import java.util.Collection;
import org.andromda.test.howto16.a.CarType;

/**
 * This form holds the fields that are used in the search operation of Car
 *
 */
public final class ManageCarSearchForm
    implements Serializable
{
    private String serial;

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Car.serial
     * @return serial
     */
    public String getSerial()
    {
        return this.serial;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Car.serial
     * @param serialIn
     */
    public void setSerial(String serialIn)
    {
        this.serial = serialIn;
    }

    private String name;

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Car.name
     * @return name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Car.name
     * @param nameIn
     */
    public void setName(String nameIn)
    {
        this.name = nameIn;
    }

    private CarType type;

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Car.type
     * @return type
     */
    public CarType getType()
    {
        return this.type;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Car.type
     * @param typeIn
     */
    public void setType(CarType typeIn)
    {
        this.type = typeIn;
    }

    private String owner;

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Person
     * @return owner
     */
    public String getOwner()
    {
        return this.owner;
    }

    /**
     * TODO: Model Documentation for org.andromda.test.howto16.a.Person
     * @param ownerIn
     */
    public void setOwner(String ownerIn)
    {
        this.owner = ownerIn;
    }

    private Collection ownerBackingList;

    /**
     * The current collection of possible values of owner.
     * @return ownerBackingList
     */
    public Collection getOwnerBackingList(){
        return ownerBackingList;
    }

    /**
     * The current collection of possible values of owner.
     * @return ownerBackingListIn
     */
    public void setOwnerBackingList(Collection ownerBackingListIn){
        this.ownerBackingList = ownerBackingListIn;
    }

    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 6816432125743090247L;
}