package com.github.hexosse.grounditem.utils;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This file is part of GroundItem
 */
public class JsonItemStack {

    private final String type;
    private final short damage;
    private final String name;
    private final List<String> lore;
    private final HashMap<String, Long> enchantments = new HashMap<String, Long>();
    private final long amount;

    public JsonItemStack(final ItemStack itemStack)
    {
        this.type = itemStack.getType().name();
        this.damage = itemStack.getDurability()!=0?Short.valueOf(itemStack.getDurability()):0;
        final ItemMeta meta = itemStack.getItemMeta();
        this.name = meta.getDisplayName();
        this.lore = meta.getLore();
        for(final Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
            this.enchantments.put(entry.getKey().getName(), Long.valueOf(entry.getValue()));
        }
        this.amount = Long.valueOf(itemStack.getAmount());
    }

    public JsonItemStack(final String type, short damage, final String name, final List<String> lore, final HashMap<String, Long> enchantments, final Long amount)
    {
        this.type = type;
        this.damage = damage;
        this.name = name;
        this.lore = lore;
        if(enchantments != null) {
            this.enchantments.putAll(enchantments);
        }
        this.amount = amount == null ? 1L : amount;
    }

    public final String toJson()
    {
        final JSONObject object = new JSONObject();
        object.put("type", type);
        object.put("damage", damage);
        if(name!=null)          object.put("name", name);
        if(lore!=null)          object.put("lore", lore);
        if(enchantments!=null && enchantments.size()>0)  object.put("enchantments", enchantments);
        object.put("amount", amount);
        return object.toJSONString();
    }

    public static final JsonItemStack fromJson(final String jsonItemStack)
    {
        final JSONObject array = (JSONObject) JSONValue.parse(jsonItemStack);
        return new JsonItemStack((String)array.get("type"),
                (short)(long)array.get("damage"),
                (String)array.get("name"),
                (List<String>)array.get("lore"),
                (HashMap<String, Long>)array.get("enchantments"),
                (Long)array.get("amount"));
    }

    public final ItemStack toItemStack()
    {
        final ItemStack itemStack = new ItemStack(type == null ? Material.GRASS : Material.valueOf(type), Ints.checkedCast(amount), Shorts.checkedCast(damage));
        final ItemMeta meta = itemStack.getItemMeta();

        if(name != null)
            meta.setDisplayName(name);

        if(lore != null)
            meta.setLore(lore);

        if(enchantments.size() != 0)
        {
            for(final Map.Entry<String, Long> entry : enchantments.entrySet()) {
                meta.addEnchant(Enchantment.getByName(entry.getKey()), Ints.checkedCast(entry.getValue()), true);
            }
        }

        itemStack.setItemMeta(meta);

        return itemStack;
    }

}


