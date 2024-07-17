package com.t13max.plugin.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * 菜单接口
 *
 * @author: t13max
 * @since: 11:46 2024/7/17
 */
public interface IMenu {

    Inventory getInventory();

    Inventory createInventory();

    void onInventoryClick(InventoryClickEvent event);
}
