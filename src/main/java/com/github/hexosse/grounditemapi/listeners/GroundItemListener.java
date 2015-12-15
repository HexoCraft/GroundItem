package com.github.hexosse.grounditemapi.listeners;

import com.github.hexosse.baseplugin.event.BaseListener;
import com.github.hexosse.grounditemapi.GroundItemApi;
import com.github.hexosse.grounditemapi.GroundItemPlugin;
import com.github.hexosse.grounditemapi.grounditem.GroundItem;
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

/**
 * This file is part GroundItemPlugin
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItemListener extends BaseListener<GroundItemPlugin>
{
    /**
     * @param plugin The plugin that this listener belongs to.
     */
    public GroundItemListener(GroundItemPlugin plugin) {
        super(plugin);
    }

    /**
     * @param event ItemDespawnEvent
     */
    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled = true)
    public void onItemDespawn(ItemDespawnEvent event)
    {
        event.setCancelled(GroundItemApi.isGroundItem(event.getEntity()));
    }

    /**
     * @param event ItemMergeEvent
     */
    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled = true)
    public void onItemMerge(ItemMergeEvent event)
    {
        event.setCancelled(GroundItemApi.isGroundItem(event.getEntity()));
    }

    /**
     * @param event InventoryPickupItemEvent
     */
    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled = true)
    public void onInventoryPickupItem(InventoryPickupItemEvent event)
    {
        event.setCancelled(GroundItemApi.isGroundItem(event.getItem()));
    }

    /**
     * @param event PlayerPickupItemEvent
     */
    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerPickupItem(PlayerPickupItemEvent event)
    {
        event.setCancelled(GroundItemApi.isGroundItem(event.getItem()));
    }

    /**
     * @param event EntityDeath
     */
    @EventHandler(priority= EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event)
    {
        if(event.getEntity() instanceof Entity)
        {
            Entity entity = (Entity)event.getEntity();

            if(entity instanceof Item)
            {
                Item item = (Item) entity;

                if (GroundItemApi.isGroundItem(item)) {
                    ItemStack itemStack = item.getItemStack();
                    Location location = item.getLocation();
                    GroundItem groundItem = new GroundItem(itemStack, location);
                    GroundItemApi.removeGroundItem(groundItem);
                    GroundItemApi.createGroundItem(groundItem);
                }
            }
        }
    }
}
