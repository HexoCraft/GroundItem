package com.github.hexosse.grounditem;

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

import com.github.hexosse.grounditem.events.post.CreatedGroundItemEvent;
import com.github.hexosse.grounditem.events.post.RemovedGroundItemEvent;
import com.github.hexosse.grounditem.events.pre.CreateGroundItemEvent;
import com.github.hexosse.grounditem.events.pre.RemoveGroundItemEvent;
import com.github.hexosse.grounditem.grounditem.GroundItem;
import com.github.hexosse.pluginframework.utilapi.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * This file is part GroundItem
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItemApi
{
    public static List<GroundItem> getGroundItemList()
    {
        return GroundItemPlugin.getInstance().getGroundItemList();
    }

    public static boolean createGroundItem(ItemStack itemStack, Location location)
    {
        // Ground item to create
        GroundItem groundItem = new GroundItem(itemStack, location);

        // Create ground item
        return createGroundItem(groundItem);
    }

    public static boolean createGroundItem(GroundItem groundItem)
    {
        // Check for cancellation
        CreateGroundItemEvent preEvent = new CreateGroundItemEvent(groundItem);
        Bukkit.getPluginManager().callEvent(preEvent);
        if(preEvent.isCancelled()) return false;

        // Create the ground item
        if(!groundItem.create())
            return false;

        // Inform the plugin of ground item creation
        CreatedGroundItemEvent postEvent = new CreatedGroundItemEvent(groundItem);
        Bukkit.getPluginManager().callEvent(postEvent);

        return true;
    }

    public static boolean removeGroundItem(ItemStack itemStack, Location location)
    {
        // Ground item to remove
        GroundItem groundItem = new GroundItem(itemStack, location);

        // Remove
        return removeGroundItem(groundItem);
    }

    public static boolean removeGroundItem(GroundItem groundItem)
    {
        // Check for cancellation
        RemoveGroundItemEvent preEvent = new RemoveGroundItemEvent(groundItem);
        Bukkit.getPluginManager().callEvent(preEvent);
        if(preEvent.isCancelled()) return false;

        // Create the ground item
        if(!groundItem.remove())
            return false;

        // informe the plugin of ground item creation
        RemovedGroundItemEvent postEvent = new RemovedGroundItemEvent(groundItem);
        Bukkit.getPluginManager().callEvent(postEvent);
        return true;
    }

    public static Boolean isGroundItem(Item item)
    {
        return isGroundItem(item.getItemStack());
    }

    public static Boolean isGroundItem(ItemStack itemStack)
    {
        if(!itemStack.hasItemMeta())
            return false;
        if(itemStack.getItemMeta().getDisplayName().equals("#%#GROUND#%#ITEM#%#"))
            return true;
        return false;
    }

    public static  GroundItem findGroundItem(Location location)
    {
        Entity[] entities = location.getChunk().getEntities();
        for(Entity entity : entities)
        {
            if(entity instanceof Item && LocationUtil.equals(entity.getLocation(),location))
                return new GroundItem((Item)entity);
        }
        return null;
    }
}
