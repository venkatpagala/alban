// Generated by: hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package org.andromda.timetracker.domain;

import org.andromda.timetracker.vo.TimeAllocationVO;

/**
 * @see TimeAllocation
 */
public class TimeAllocationDaoImpl extends TimeAllocationDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    public void toTimeAllocationVO(final TimeAllocation source, final TimeAllocationVO target)
    {
        // TODO verify behavior of toTimeAllocationVO
        super.toTimeAllocationVO(source, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeAllocationVO toTimeAllocationVO(final TimeAllocation entity)
    {
        // TODO verify behavior of toTimeAllocationVO
        return super.toTimeAllocationVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private TimeAllocation loadTimeAllocationFromTimeAllocationVO(final TimeAllocationVO timeAllocationVO)
    {
        // TODO implement loadTimeAllocationFromTimeAllocationVO
        throw new UnsupportedOperationException("org.andromda.timetracker.domain.loadTimeAllocationFromTimeAllocationVO(TimeAllocationVO) not yet implemented.");

        /*
         * A typical implementation looks like this:
         * TimeAllocation timeAllocation = this.load(timeAllocationVO.getId());
         * if (timeAllocation == null)
         * {
         * timeAllocation = TimeAllocation.Factory.newInstance();
         * }
         * return timeAllocation;
         */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeAllocation timeAllocationVOToEntity(final TimeAllocationVO timeAllocationVO)
    {
        // TODO verify behavior of timeAllocationVOToEntity
        final TimeAllocation entity = this.loadTimeAllocationFromTimeAllocationVO(timeAllocationVO);
        this.timeAllocationVOToEntity(timeAllocationVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void timeAllocationVOToEntity(final TimeAllocationVO source, final TimeAllocation target, final boolean copyIfNull)
    {
        // TODO verify behavior of timeAllocationVOToEntity
        super.timeAllocationVOToEntity(source, target, copyIfNull);
    }
}
