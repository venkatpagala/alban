// license-header java merge-point
//
// Attention: generated code (by Metafacade.vsl) - do not modify!
//
package org.andromda.cartridges.seam.metafacades;

import org.andromda.cartridges.jsf.metafacades.JSFEvent;

/**
 * 
 *
 * Metafacade interface to be used by AndroMDA cartridges.
 */
public interface SeamEvent
    extends JSFEvent
{
    /**
     * Indicates the metafacade type (used for metafacade mappings).
     *
     * @return boolean always <code>true</code>
     */
    public boolean isSeamEventMetaType();
}