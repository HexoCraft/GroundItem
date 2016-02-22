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

import com.github.hexosse.grounditem.GroundItemApi;
import com.github.hexosse.grounditem.GroundItemPlugin;
import com.github.hexosse.grounditem.grounditem.GroundItem;
import com.github.hexosse.pluginframework.pluginapi.PluginListener;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This file is part GroundItemPlugin
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class ChunckListener extends PluginListener<GroundItemPlugin>
{
    /**
     * @param plugin The plugin that this listener belongs to.
     */
    public ChunckListener(GroundItemPlugin plugin) {
        super(plugin);
    }

    /**
     * @param event ChunkLoadEvent
     */
    @EventHandler(priority= EventPriority.HIGH)
    public void onChunkLoad(ChunkLoadEvent event)
    {
        // Create ground items that has not been created
        // because of chunks not loaded at startup
        for(final GroundItem groundItem : plugin.getGroundItemList())
        {
            new BukkitRunnable() {
                @Override
                public void run() {
                    groundItem.create();
                }

            }.runTaskLater(plugin, 20*2);
        }
    }

    /**
     * @param event ChunkUnloadEvent
     */
    @EventHandler(priority= EventPriority.HIGH)
    public void onChunkLoad(ChunkUnloadEvent event)
    {
        // Remove items as chunck unload
        Entity[] entities = event.getChunk().getEntities();
        for(Entity entity : entities)
        {
            if(entity instanceof Item && GroundItemApi.isGroundItem(((Item)entity).getItemStack()))
                ((Item)entity).remove();
        }
    }
}
