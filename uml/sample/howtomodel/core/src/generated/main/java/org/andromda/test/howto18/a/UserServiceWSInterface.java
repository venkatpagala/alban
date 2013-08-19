// license-header java merge-point
/* Autogenerated by AndroMDA WebServiceInterface.vsl in andromda-ejb3-cartridge - do not edit */
package org.andromda.test.howto18.a;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Web service interface for the UserServiceBean session bean.
 *
 */
@WebService(targetNamespace = "http://org.andromda.test.howto18/a")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
//@org.jboss.ws.annotation.PortComponent(contextRoot = "/howtomodel-ws", urlPattern="/services/UserService/*")
public interface UserServiceWSInterface
    extends Remote
{
    @WebMethod(operationName = "AddUser")
    @WebResult(name = "AddUser")
    public void addUser(
        @WebParam(name = "User") User user
    ) throws UserException, RemoteException;

    @WebMethod(operationName = "GetUser")
    public User getUser(
        @WebParam(name = "Id") Long id
    ) throws UserException, RemoteException;

    @WebMethod(operationName = "GetAllUsers")
    public Collection getAllUsers(
) throws UserException, RemoteException;

}