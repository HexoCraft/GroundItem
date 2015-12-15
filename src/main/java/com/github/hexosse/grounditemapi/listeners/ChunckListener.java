package com.github.hexosse.grounditemapi.listeners;

import com.github.hexosse.baseplugin.event.BaseListener;
import com.github.hexosse.grounditemapi.GroundItemPlugin;
import com.github.hexosse.grounditemapi.grounditem.GroundItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

/**
 * This file is part GroundItemPlugin
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class ChunckListener extends BaseListener<GroundItemPlugin>
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
        for(GroundItem groundItem : plugin.getGroundItemList())
        {
            if(groundItem.getItem()==null)
                groundItem.create();
        }
    }

    /**
     * @param event ChunkUnloadEvent
     */
    @EventHandler(priority= EventPriority.HIGH)
    public void onChunkLoad(ChunkUnloadEvent event)
    {
        // Remove items as chunck unload
        for(GroundItem groundItem : plugin.getGroundItemList())
        {
            if(groundItem.getItem()!=null && groundItem.getItem().getLocation().getChunk().equals(event.getChunk()))
                groundItem.remove();
        }
    }
}
