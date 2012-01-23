package org.andromda.timetracker.service;

import org.andromda.timetracker.ServiceLocator;
import org.andromda.timetracker.vo.UserVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 *
 */
public class UserServiceTest
{
    private Log logger = LogFactory.getLog(UserServiceTest.class);

    private UserService userService;

    /**
     * Initialize test suite
     */
    @BeforeSuite
    public void initializeTestSuite()
    {
        // Initialize ServiceLocator
        this.logger.info("Initializing ServiceLocator");
        ServiceLocator locator = ServiceLocator.instance();
        locator.init("testBeanRefFactory.xml", "beanRefFactory");

        // Initialize UserService
        this.logger.info("Initializing UserService");
        this.userService = locator.getUserService();
    }

    /**
     *
     */
    @Test
    public void testGetAllUsers()
    {
        this.logger.info("testGetAllUsers:");

        for (UserVO user : this.userService.getAllUsers())
        {
            this.logger.info(user.getUsername());
        }
    }
}
