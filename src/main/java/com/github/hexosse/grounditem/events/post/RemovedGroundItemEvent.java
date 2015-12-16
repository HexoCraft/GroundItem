package com.github.hexosse.grounditem.events.post;

import com.github.hexosse.grounditem.events.GroundItemEvent;
import com.github.hexosse.grounditem.grounditem.GroundItem;

/**
 * This file is part GroundItem
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class RemovedGroundItemEvent extends GroundItemEvent
{
    public RemovedGroundItemEvent(GroundItem groundItem) {
        super(groundItem);
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    @Override
    public boolean isCancelled() { return false; }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    @Override
    public void setCancelled(boolean cancel) {}
}
