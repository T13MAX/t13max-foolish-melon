package com.t13max.plugin.menu;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * 菜单抽象父类
 *
 * @author: t13max
 * @since: 11:50 2024/7/17
 */
@Getter
public abstract class AbstractMenu implements IMenu {

    //所创建的容器对象
    protected Inventory inventory;

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
