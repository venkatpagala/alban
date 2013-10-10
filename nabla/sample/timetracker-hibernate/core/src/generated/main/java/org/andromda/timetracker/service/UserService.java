// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: SpringService.vsl in andromda-spring-cartridge.
//
/**
 * TEMPLATE:    SpringService.vsl in andromda-spring cartridge
 * MODEL CLASS: TimeTracker::TimeTracker::org.andromda.timetracker::service::UserService
 * STEREOTYPE:  Seam
 * STEREOTYPE:  Service
 */
package org.andromda.timetracker.service;

import java.util.List;
import org.andromda.timetracker.domain.User;
import org.andromda.timetracker.vo.UserDetailsVO;
import org.andromda.timetracker.vo.UserVO;

/**
 * TODO: Model Documentation for org.andromda.timetracker.service.UserService
 */
public interface UserService
{
    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.user
     * Getter for user
     * @return Value of user
     */
    public User getUser();

    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.user
     * Setter for user
     * @param userIn New value for user
     */
    public void setUser(User userIn);

    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.userList
     * Getter for userList
     * @return Value of userList
     */
    public List<User> getUserList();

    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.userList
     * Setter for userList
     * @param userListIn New value for userList
     */
    public void setUserList(List<User> userListIn);

    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.getAllUsers
     * @return  UserVO[]
     * @throws UserDoesNotExistException
     */
    public UserVO[] getAllUsers()
        throws UserDoesNotExistException;

    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.getUser
     * @param username TODO: Model Documentation for org.andromda.timetracker.service.UserService.getUser(username)
     * @return  UserVO
     * @throws UserDoesNotExistException
     */
    public UserVO getUser(String username)
        throws UserDoesNotExistException;

    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.registerUser
     * @param userDetailVO TODO: Model Documentation for
org.andromda.timetracker.service.UserService.registerUser(userDetailVO)
     * @return  UserDetailsVO
     * @throws UserDoesNotExistException
     */
    public UserDetailsVO registerUser(UserDetailsVO userDetailVO)
        throws UserDoesNotExistException;

    /**
     * TODO: Model Documentation for org.andromda.timetracker.service.UserService.removeUser
     * @param userVO TODO: Model Documentation for org.andromda.timetracker.service.UserService.removeUser(userVO)
     * @return  void
     * @throws UserDoesNotExistException
     */
    public void removeUser(UserVO userVO)
        throws UserDoesNotExistException;

}