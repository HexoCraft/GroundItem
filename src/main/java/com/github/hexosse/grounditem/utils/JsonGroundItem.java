package com.github.hexosse.grounditem.utils;

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

import com.github.hexosse.grounditem.grounditem.GroundItem;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * This file is part of GroundItem
 */
public class JsonGroundItem {

    private final ItemStack itemStack;
    private final Location location;

    public JsonGroundItem(final GroundItem groundItem)
    {
        this.itemStack = groundItem.getCustomItemStack();
        this.location = groundItem.getLocation();
    }

    public JsonGroundItem(final ItemStack itemStack, final Location location)
    {
        this.itemStack = itemStack;
        this.location = location;
    }

    public final String toJson()
    {
        final JSONObject object = new JSONObject();
        object.put("itemStack", new JsonItemStack(this.itemStack).toJson());
        object.put("location", new JsonLocation(this.location).toJson());
        return object.toJSONString();
    }

    public static final JsonGroundItem fromJson(final String jsonGroundItem)
    {
        final JSONObject array = (JSONObject) JSONValue.parse(jsonGroundItem);

        ItemStack itemStack = JsonItemStack.fromJson((String)array.get("itemStack")).toItemStack();
        Location location = JsonLocation.fromJson((String)array.get("location")).toLocation();

        return new JsonGroundItem(itemStack, location);
    }

    public final GroundItem toGroundItem()
    {
        final GroundItem groundItem = new GroundItem(itemStack, location);
        return groundItem;
    }

}


