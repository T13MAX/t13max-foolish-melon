package com.t13max.plugin.menu;

import com.t13max.common.consts.Const;
import com.t13max.plugin.consts.MelonTextColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * 主菜单
 *
 * @author: t13max
 * @since: 11:46 2024/7/17
 */
public class MainMenu extends AbstractMenu {

    @Override
    public Inventory createInventory() {
        //主菜单
        inventory = Bukkit.createInventory(null, Const.CHEST_SIZE_27, Component.text("主菜单"));
        // 添加方块
        inventory.setItem(0, new ItemStack(Material.DIAMOND));
        inventory.setItem(1, new ItemStack(Material.GOLD_INGOT));
        inventory.setItem(2, new ItemStack(Material.IRON_INGOT));
        return inventory;
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {

        //父类默认执行
        super.onInventoryClick(event);

        //玩家
        Player player = (Player) event.getWhoClicked();
        //点击的格子
        ItemStack clickedItem = event.getCurrentItem();
        //不为空, 不是空气
        if (clickedItem == null || clickedItem.getType() != Material.AIR) {
            return;
        }
        //点击的道具的类型
        Material itemType = clickedItem.getType();
        //执行特定逻辑
        switch (itemType) {
            case DIAMOND:
                player.sendMessage(Component.text("点击了钻石块", MelonTextColor.RED));
                break;
            case GOLD_INGOT:
                break;
            case IRON_INGOT:
                break;
            default:
        }
    }
}
