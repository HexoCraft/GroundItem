package com.github.hexosse.grounditemapi.listeners;

import com.github.hexosse.baseplugin.event.BaseListener;
import com.github.hexosse.grounditemapi.GroundItemPlugin;
import com.github.hexosse.grounditemapi.events.post.CreatedGroundItemEvent;
import com.github.hexosse.grounditemapi.events.post.RemovedGroundItemEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

/**
 * This file is part GroundItemApi
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItemPluginListener extends BaseListener<GroundItemPlugin>
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
