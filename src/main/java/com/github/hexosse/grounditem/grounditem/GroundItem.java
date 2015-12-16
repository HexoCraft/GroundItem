package com.github.hexosse.grounditem.grounditem;

import com.github.hexosse.grounditem.utils.JsonGroundItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

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
        this.itemStack = new ItemStack(itemStack);
        this.location = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
    }

    /**
     * Constructs a new GroundItem from an existing item
     * @param item item to use as ground iten
     */
    public GroundItem(Item item)
    {
        this.groundItem = item;
        this.itemStack = new ItemStack(item.getItemStack());
        this.location = new Location(item.getLocation().getWorld(), item.getLocation().getX(), item.getLocation().getY(), item.getLocation().getZ());
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

        // Create ground item
        groundItem = location.getWorld().dropItem(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ()), itemStack);
        groundItem.setVelocity(new Vector(0, 0, 0));

        // Name the item so it will be easier to check if it's a ground item
        final ItemMeta meta = groundItem.getItemStack().getItemMeta();
        meta.setDisplayName("#%#GROUND#%#ITEM#%#");
        groundItem.getItemStack().setItemMeta(meta);

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
