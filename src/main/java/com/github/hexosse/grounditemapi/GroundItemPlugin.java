package com.github.hexosse.grounditemapi;

/*
 * Copyright 2015 Hexosse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.hexosse.baseplugin.BasePlugin;
import com.github.hexosse.githubupdater.GitHubUpdater;
import com.github.hexosse.grounditemapi.configuration.Config;
import com.github.hexosse.grounditemapi.grounditem.GroundItem;
import com.github.hexosse.grounditemapi.listeners.ChunckListener;
import com.github.hexosse.grounditemapi.listeners.GroundItemListener;
import com.github.hexosse.grounditemapi.listeners.GroundItemPluginListener;
import com.github.hexosse.grounditemapi.utils.JsonGroundItem;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This file is part GroundItemPlugin
 *
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class GroundItemPlugin extends BasePlugin
{
    public Config config = null;
    private String repository = "hexosse/GroundItemApi";
    private List<GroundItem> groundItemList = new ArrayList<GroundItem>();


    /**
     * Activation du plugin
     */
    @Override
    public void onEnable()
    {
        /* Chargement de la config */
        config = new Config(this, getDataFolder(), "config.yml");
        config.load();

		/* Enregistrement des listeners */
        Bukkit.getPluginManager().registerEvents(new GroundItemPluginListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChunckListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GroundItemListener(this), this);

        // Create ground items from config file
        for(String jsonGroundItem : config.groundItemList) {
            final GroundItem groundItem = JsonGroundItem.fromJson(jsonGroundItem).toGroundItem();
            groundItemList.add(groundItem);
            groundItem.create();
        }

        /* Updater */
        if(config.useUpdater)
            RunUpdater(config.downloadUpdate);

        /* Metrics */
        if(config.useMetrics)
            RunMetrics();
    }

    /**
     * Désactivation du plugin
     */
    @Override
    public void onDisable()
    {
        super.onDisable();
    }

    public void RunUpdater(final boolean download)
    {
        GitHubUpdater updater = new GitHubUpdater(this, this.repository, this.getFile(), download?GitHubUpdater.UpdateType.DEFAULT:GitHubUpdater.UpdateType.NO_DOWNLOAD, true);
    }

    private void RunMetrics()
    {
        try
        {
            com.github.hexosse.baseplugin.metric.MetricsLite metrics = new com.github.hexosse.baseplugin.metric.MetricsLite(this);
            if(metrics.start())
                pluginLogger.info("Succesfully started Metrics, see http://mcstats.org for more information.");
            else
                pluginLogger.info("Could not start Metrics, see http://mcstats.org for more information.");
        } catch (IOException e)
        {
            // Failed to submit the stats :-(
        }
    }


    /**
     * @return Instance of the plugin
     */
    public static GroundItemPlugin getInstance()
    {
        return (GroundItemPlugin)Bukkit.getPluginManager().getPlugin("GroundItemApi");
    }

    /**
     * @return List of ground item saved to file
     */
    public List<GroundItem> getGroundItemList()
    {
        return groundItemList;
    }
}