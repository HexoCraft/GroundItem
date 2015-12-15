package com.github.hexosse.grounditemapi.grounditem;

import com.github.hexosse.grounditemapi.utils.JsonGroundItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Utility;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This file is part GroundItemPlugin
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItem
{
    // the ground item
    private Item groundItem = null;
    // Data used to create the groung item
    private ItemStack itemStack = null;
    private Location location = null;

    /**
     * Constructs a new GroundItem at the given location
     * @param itemStack itemStack to use as ground iten
     * @param location location of the ground item
     */
    public GroundItem(ItemStack itemStack, Location location)
    {
        this.itemStack = itemStack;
        this.location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
    }


    /**
     * Create the ground item
     *
     * @return true if creation succeed
     */
    public boolean create()
    {
        // Check if ground item already exist
        if(groundItem!=null && (!groundItem.isDead() || groundItem.isValid())) return false;
        // Check if location is valid
        if(location==null) return false;
        if(Bukkit.getServer().getWorld(location.getWorld().getName())==null) return false;
        if(!location.getChunk().isLoaded()) return false;
        // Check if Item Stack is valid
        if(itemStack==null) return false;

        // remove existing groung item
        remove();

        // Name the item so it will be easier to check if it's a ground item
        final ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("#%#GROUND#%#ITEM#%#");
        itemStack.setItemMeta(meta);

        // Create ground item
        groundItem = location.getWorld().dropItem(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()), itemStack);
        groundItem.setVelocity(new Vector(0, 0, 0));
        return true;
    }


    /**
     * Remove the ground item
     *
     * @return true if removal succeed
     */
    public boolean remove()
    {
        // Check if ground item exist
        if(groundItem==null) return false;

        // remove ground item
        groundItem.remove();
        groundItem = null;
        return true;
    }

    public Item getItem()
    {
        return groundItem;
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    public Location getLocation()
    {
        return location;
    }

    @Override
    public final int hashCode()
    {
        int hash = 5;

        hash = hash * 17 + this.itemStack.hashCode();
        hash = hash * 17 + this.location.hashCode();

        return hash;
    }

    @Override
    public final String toString()
    {
        final JsonGroundItem json = new JsonGroundItem(this);
        return json.toJson();
    }}
