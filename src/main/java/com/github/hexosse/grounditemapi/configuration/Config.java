package com.github.hexosse.grounditemapi.configuration;

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

import com.github.hexosse.baseplugin.config.BaseConfig;
import com.github.hexosse.grounditemapi.GroundItemPlugin;

import java.io.File;
import java.util.ArrayList;

@BaseConfig.ConfigHeader(comment = {
        "############################################################",
        "# | GroundItemApi by hexosse                             | #",
        "############################################################"
})
@BaseConfig.ConfigFooter(comment = {
        " ",
        " ",
        "############################################################"
})

public class Config extends BaseConfig<GroundItemPlugin>
{
    /* Plugin */
    @BaseConfig.ConfigComment(path = "plugin")
    @BaseConfig.ConfigOptions(path = "plugin.useMetrics")
    public boolean useMetrics = (boolean) true;
    @BaseConfig.ConfigOptions(path = "plugin.useUpdater")
    public boolean useUpdater = (boolean) true;
    @BaseConfig.ConfigOptions(path = "plugin.downloadUpdate")
    public boolean downloadUpdate = (boolean) true;


    @BaseConfig.ConfigComment(path = "groundItem")
    @BaseConfig.ConfigOptions(path = "groundItem.list")
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
