// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.andromda.timetracker.domain;

import java.util.Collection;

import org.andromda.timetracker.vo.UserDetailsVO;
import org.andromda.timetracker.vo.UserRoleVO;
import org.andromda.timetracker.vo.UserVO;

/**
 * @see User
 */
public class UserDaoImpl extends UserDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    protected User handleGetUserDetails(final String username) throws Exception
    {

        final User user = (User) this.getSession().createQuery("from UserImpl user left join fetch user.roles where user.username = :username").setParameter("username", username).uniqueResult();
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toUserVO(final User source, final UserVO target)
    {
        // TODO verify behavior of toUserVO
        super.toUserVO(source, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVO toUserVO(final User entity)
    {
        // TODO verify behavior of toUserVO
        return super.toUserVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private User loadUserFromUserVO(final UserVO userVO)
    {
        // TODO implement loadUserFromUserVO
        throw new UnsupportedOperationException("org.andromda.timetracker.domain.loadUserFromUserVO(UserVO) not yet implemented.");

        /*
         * A typical implementation looks like this:
         * User user = this.load(userVO.getId());
         * if (user == null)
         * {
         * user = User.Factory.newInstance();
         * }
         * return user;
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User userVOToEntity(final UserVO userVO)
    {
        // TODO verify behavior of userVOToEntity
        final User entity = this.loadUserFromUserVO(userVO);
        this.userVOToEntity(userVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void userVOToEntity(final UserVO source, final User target, final boolean copyIfNull)
    {
        // TODO verify behavior of userVOToEntity
        super.userVOToEntity(source, target, copyIfNull);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toUserDetailsVO(final User source, final UserDetailsVO target)
    {
        super.toUserDetailsVO(source, target);

        // Convert roles
        final Collection<UserRole> srcRoles = source.getRoles();
        final UserRoleVO[] targetRoles = new UserRoleVO[srcRoles.size()];
        int i = 0;
        for (final Object srcRole : srcRoles)
        {
            targetRoles[i] = this.getUserRoleDao().toUserRoleVO((UserRole) srcRole);
            i++;
        }
        target.setRoles(targetRoles);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetailsVO toUserDetailsVO(final User entity)
    {
        // TODO verify behavior of toUserDetailsVO
        return super.toUserDetailsVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private User loadUserFromUserDetailsVO(final UserDetailsVO userDetailsVO)
    {
        // TODO implement loadUserFromUserDetailsVO
        throw new UnsupportedOperationException("org.andromda.timetracker.domain.loadUserFromUserDetailsVO(UserDetailsVO) not yet implemented.");

        /*
         * A typical implementation looks like this:
         * User user = this.load(userDetailsVO.getId());
         * if (user == null)
         * {
         * user = User.Factory.newInstance();
         * }
         * return user;
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User userDetailsVOToEntity(final UserDetailsVO userDetailsVO)
    {
        // TODO verify behavior of userDetailsVOToEntity
        final User entity = this.loadUserFromUserDetailsVO(userDetailsVO);
        this.userDetailsVOToEntity(userDetailsVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void userDetailsVOToEntity(final UserDetailsVO source, final User target, final boolean copyIfNull)
    {
        // TODO verify behavior of userDetailsVOToEntity
        super.userDetailsVOToEntity(source, target, copyIfNull);
    }
}
