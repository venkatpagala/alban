// license-header java merge-point
//
// Generated by: SessionBeanImpl.vsl in andromda-ejb3-cartridge.
//
package org.andromda.test.howto18.a;

import java.util.Collection;
import javax.jws.WebService;

/**
 * @see UserServiceBase
 *
 * Remember to manually configure the local business interface this bean implements if originally you only
 * defined the remote business interface.  However, this change is automatically reflected in the ejb-jar.xml.
 *
 * Do not specify the javax.ejb.Stateless annotation
 * Instead, the session bean is defined in the ejb-jar.xml descriptor.
 */
@WebService(endpointInterface = "org.andromda.test.howto18.a.UserServiceWSInterface", serviceName = "UserService")
public class UserServiceBean extends UserServiceBase implements UserServiceRemote
{

    // --------------- Constructors ---------------

    /**
     * Default constructor extending base class default constructor
     */
    public UserServiceBean()
    {
        super();
    }

    // -------- Business Methods Impl --------------

    /**
     * @see UserServiceBase#addUser(User)
     */
    protected void handleAddUser(User user) throws Exception
    {
        // TODO: put your implementation here.
        throw new UnsupportedOperationException("org.andromda.test.howto18.a.UserServiceBean.handleAddUser(User user) Not implemented!");
    }

    /**
     * @see UserServiceBase#getUser(Long)
     */
    protected User handleGetUser(Long id) throws Exception
    {
        // TODO: put your implementation here.
        // Dummy return value, just that the file compiles
        return null;
    }

    /**
     * @see UserServiceBase#getAllUsers()
     */
    protected Collection handleGetAllUsers() throws Exception
    {
        // TODO: put your implementation here.
        // Dummy return value, just that the file compiles
        return null;
    }

    // -------- Lifecycle Callback Implementation --------------

}
