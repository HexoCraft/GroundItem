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
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This file is part GroundItemPlugin
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItemListener extends PluginListener<GroundItemPlugin>
{
    /**
     * @param plugin The plugin that this listener belongs to.
     */
    public GroundItemListener(GroundItemPlugin plugin)
    {
        super(plugin);
    }

    /**
     * @param event ItemDespawnEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onItemDespawn(ItemDespawnEvent event)
    {
		event.setCancelled(GroundItemApi.isGroundItem(event.getEntity()));
	}

    /**
     * @param event ItemMergeEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onItemMerge(ItemMergeEvent event)
    {
		event.setCancelled(GroundItemApi.isGroundItem(event.getEntity()));
    }

    /**
     * @param event InventoryPickupItemEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryPickupItem(InventoryPickupItemEvent event)
    {
        event.setCancelled(GroundItemApi.isGroundItem(event.getItem()));
    }

    /**
     * @param event PlayerPickupItemEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerPickupItem(PlayerPickupItemEvent event)
    {
        event.setCancelled(GroundItemApi.isGroundItem(event.getItem()));
    }

    /**
     * @param event EntityDeath
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event)
    {
        if(event.getEntity() instanceof Entity)
        {
            Entity entity = (Entity) event.getEntity();

            if(entity instanceof Item)
            {
                Item item = (Item) entity;

                if(GroundItemApi.isGroundItem(item))
                {
                    ItemStack itemStack = item.getItemStack();
                    Location location = item.getLocation();
                    final GroundItem groundItem = new GroundItem(itemStack, location);
                    GroundItemApi.removeGroundItem(groundItem);

                    new BukkitRunnable()
                    {
                        @Override
                        public void run()
                        {
							GroundItemApi.createGroundItem(groundItem);
                        }

                    }.runTaskLater(plugin, 10);

                }
            }
        }
    }
}
