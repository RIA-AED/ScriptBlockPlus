/**
 * ScriptBlockPlus - Allow you to add script to any blocks.
 * Copyright (C) 2021 yuttyann44581
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.yuttyann.scriptblockplus.item.gui;

import com.github.yuttyann.scriptblockplus.file.config.SBConfig;
import com.github.yuttyann.scriptblockplus.utils.ItemUtils;
import com.github.yuttyann.scriptblockplus.utils.StreamUtils.TriConsumer;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * ScriptBlockPlus GUIItem クラス
 * @author yuttyann44581
 */
public final class GUIItem implements Cloneable {                                 

    private static final ItemFlag[] FLAGS = { ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE };

    public static final GUIItem BLACK = new GUIItem(ItemUtils.getGlassPane(15), null).setName("§r");
    public static final Supplier<GUIItem> PAPER = () -> new GUIItem(1, Material.PAPER, "", Arrays.asList(SBConfig.GUI_SYS_RESET.setColor()), null);

    private ItemStack item;
    private TriConsumer<UserWindow, GUIItem, ClickType> clicked;

    private GUIItem() { }

    public GUIItem(@NotNull ItemStack source, @Nullable TriConsumer<UserWindow, GUIItem, ClickType> clicked) {
        this.item = new ItemStack(source);
        this.clicked = clicked;

        var itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.addItemFlags(FLAGS);
        item.setItemMeta(itemMeta);
    }

    public GUIItem(final int amount, @NotNull Material material, @Nullable String name, @Nullable List<String> lore, @Nullable TriConsumer<UserWindow, GUIItem, ClickType> clicked) {
        this(false, amount, material, name, lore, clicked);
    }

    public GUIItem(final boolean enchant, final int amount, @NotNull Material material, @Nullable String name, @Nullable List<String> lore, @Nullable TriConsumer<UserWindow, GUIItem, ClickType> clicked) {
        this.item = new ItemStack(material, amount);
        this.clicked = clicked;

        var itemMeta = item.getItemMeta();
        if (itemMeta == null) {
            return;
        }
        itemMeta.addItemFlags(FLAGS);
        if (enchant) {
            itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
        }
        if (name != null) {
            itemMeta.setDisplayName("§r" + name);
        }
        if (lore != null && !lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        item.setItemMeta(itemMeta);
    }

    public void onClicked(@NotNull UserWindow window, @NotNull ClickType clickType) {
        if (clicked != null) {
            clicked.accept(window, this, clickType);
        }
    }

    public void setClicked(@Nullable TriConsumer<UserWindow, GUIItem, ClickType> clicked) {
        this.clicked = clicked;
    }

    @Nullable
    public TriConsumer<UserWindow, GUIItem, ClickType> getClicked() {
        return clicked;
    }

    @NotNull
    public GUIItem setEnchant(final boolean enchant) {
        var itemMeta = item.getItemMeta();
        if (enchant) {
            itemMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, false);
        } else {
            itemMeta.removeEnchant(Enchantment.LOOT_BONUS_BLOCKS);
        }
        item.setItemMeta(itemMeta);
        return this;
    }

    @NotNull
    public GUIItem setName(@Nullable String name) {
        ItemUtils.setName(item, name);
        return this;
    }

    @NotNull
    public String getName() {
        return ItemUtils.getName(item, "");
    }

    @NotNull
    public GUIItem setLore(@Nullable String lore) {
        return setLore(Arrays.asList(lore));
    }

    @NotNull
    public GUIItem setLore(@Nullable List<String> lore) {
        ItemUtils.setLore(item, lore);
        return this;
    }

    @NotNull
    public List<String> getLore() {
        var itemMeta = item.getItemMeta();
        return itemMeta.hasLore() ? itemMeta.getLore() : Collections.emptyList();
    }

    @NotNull
    public ItemStack toBukkit() {
        return item;
    }

    @Nullable
    public ItemMeta toBukkitMeta() {
        return item.hasItemMeta() ? item.getItemMeta() : null;
    }

    @Override
    @NotNull
    public GUIItem clone() {
        var guiItem = new GUIItem();
        guiItem.item = new ItemStack(item);
        guiItem.clicked = clicked;
        return guiItem;
    }
}