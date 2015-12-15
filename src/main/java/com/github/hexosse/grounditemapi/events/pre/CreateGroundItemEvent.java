package com.github.hexosse.grounditemapi.events.pre;

import com.github.hexosse.grounditemapi.events.GroundItemEvent;
import com.github.hexosse.grounditemapi.grounditem.GroundItem;

/**
 * This file is part GroundItemApi
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class CreateGroundItemEvent extends GroundItemEvent
{
    public CreateGroundItemEvent(GroundItem groundItem) {
        super(groundItem);
    }
}
