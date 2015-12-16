package com.github.hexosse.grounditem.events.pre;

import com.github.hexosse.grounditem.events.GroundItemEvent;
import com.github.hexosse.grounditem.grounditem.GroundItem;

/**
 * This file is part GroundItemApi
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class RemoveGroundItemEvent extends GroundItemEvent
{
    public RemoveGroundItemEvent(GroundItem groundItem) {
        super(groundItem);
    }
}
