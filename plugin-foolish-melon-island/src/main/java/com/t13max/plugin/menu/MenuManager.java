package com.t13max.plugin.menu;

import com.t13max.common.exception.CommonException;
import com.t13max.common.manager.ManagerBase;
import com.t13max.util.Log;
import com.t13max.util.PackageUtil;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 菜单管理器
 * 主要是各种菜单的实现
 *
 * @author: t13max
 * @since: 11:30 2024/7/17
 */
public class MenuManager extends ManagerBase {

    //菜单集合
    private final Map<Inventory, IMenu> menuMap = new HashMap<>();

    /**
     * 获取当前实例对象
     *
     * @Author t13max
     * @Date 16:44 2024/5/23
     */
    public static MenuManager inst() {
        return ManagerBase.inst(MenuManager.class);
    }

    @Override
    public void init() {

        try {
            Set<Class<?>> classSet = PackageUtil.scan("com.t13max.plugin.command");
            //创建实例
            for (Class<?> clazz : classSet) {
                // 只需要加载CommandExecutor的实现
                if (!IMenu.class.isAssignableFrom(clazz) || Modifier.isAbstract(clazz.getModifiers())) {
                    continue;
                }

                // 创建实例
                Object inst = clazz.getDeclaredConstructor().newInstance();
                IMenu menu = (IMenu) inst;
                //创建这个容器
                menu.createInventory();
                //注册命令
                menuMap.put(menu.getInventory(), menu);
            }

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new CommonException(e);
        }

    }

    /**
     * 监听事件
     *
     * @Author t13max
     * @Date 11:46 2024/7/17
     */
    public void onInventoryClick(InventoryClickEvent event) {
        IMenu menu = this.menuMap.get(event.getInventory());
        if (menu == null) {
            Log.melon.debug("监听到容器点击事件, 容器不是预设菜单容器, 忽略, title={}", event.getView().title());
            return;
        }
        menu.onInventoryClick(event);
    }
}
