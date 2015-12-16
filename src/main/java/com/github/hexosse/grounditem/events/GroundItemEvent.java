package com.github.hexosse.grounditem.events;

import com.github.hexosse.grounditem.grounditem.GroundItem;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This file is part GroundItem
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItemEvent extends Event implements Cancellable
{
    private boolean cancel;
    private static final HandlerList handlers = new HandlerList();
    private GroundItem groundItem;

    public GroundItemEvent(GroundItem groundItem) {
        this.groundItem = groundItem;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    @Override
    public boolean isCancelled() { return this.cancel; }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    @Override
    public void setCancelled(boolean cancel) { this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() { return handlers; }

    public static HandlerList getHandlerList() { return handlers; }


    public GroundItem getGroundItem() {
        return groundItem;
    }
}