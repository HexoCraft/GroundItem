package com.github.hexosse.grounditem.configuration;

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
import com.github.hexosse.pluginframework.pluginapi.config.ConfigFile;

import java.io.File;
import java.util.ArrayList;

@ConfigFile.ConfigHeader(comment = {
        "############################################################",
        "# | GroundItem by hexosse                                | #",
        "############################################################"
})
@ConfigFile.ConfigFooter(comment = {
        " ",
        " ",
        "############################################################"
})

public class Config extends ConfigFile<GroundItemPlugin>
{
    /* Plugin */
    @ConfigComment(path = "plugin")
    @ConfigOptions(path = "plugin.useMetrics")
    public boolean useMetrics = (boolean) true;
    @ConfigOptions(path = "plugin.useUpdater")
    public boolean useUpdater = (boolean) true;
    @ConfigOptions(path = "plugin.downloadUpdate")
    public boolean downloadUpdate = (boolean) true;


    @ConfigComment(path = "groundItem")
    @ConfigOptions(path = "groundItem.list")
    public ArrayList<String> groundItemList = new ArrayList<String>();

    /**
     * @param plugin     The plugin that this object belong to.
     * @param dataFolder Folder that contains the config file
     * @param filename   Name of the config file
     */
    public Config(GroundItemPlugin plugin, File dataFolder, String filename) {
        super(plugin, new File(dataFolder, filename), filename);
    }

    /**
     * Reload the plugin config
     */
    public void reloadConfig() {
        load();
    }
}
