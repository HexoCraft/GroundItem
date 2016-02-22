package com.github.hexosse.grounditem.listeners;

/*
 * Copyright 2016 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.github.hexosse.grounditem.GroundItemPlugin;
import com.github.hexosse.grounditem.events.post.CreatedGroundItemEvent;
import com.github.hexosse.grounditem.events.post.RemovedGroundItemEvent;
import com.github.hexosse.pluginframework.pluginapi.PluginListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

/**
 * This file is part GroundItem
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItemPluginListener extends PluginListener<GroundItemPlugin>
{
    /**
     * @param plugin The plugin that this listener belongs to.
     */
    public GroundItemPluginListener(GroundItemPlugin plugin) {
        super(plugin);
    }

    /**
     * @param event CreatedGroundItemEvent
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onCreatedGroundItem(CreatedGroundItemEvent event)
    {
        this.plugin.config.groundItemList.add(event.getGroundItem().toString());
        this.plugin.config.save();
        this.plugin.getGroundItemList().add(event.getGroundItem());
    }

    /**
     * @param event CreatedGroundItemEvent
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onRemovedGroundItem(RemovedGroundItemEvent event)
    {
        this.plugin.config.groundItemList.remove(event.getGroundItem().toString());
        this.plugin.config.save();
        this.plugin.getGroundItemList().remove(event.getGroundItem());
    }
}
