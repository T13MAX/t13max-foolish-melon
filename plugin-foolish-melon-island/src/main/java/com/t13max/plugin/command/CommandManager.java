package com.t13max.plugin.command;

import com.t13max.common.exception.CommonException;
import com.t13max.common.manager.ManagerBase;
import com.t13max.plugin.MelonIsland;
import com.t13max.util.Log;
import com.t13max.util.PackageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 命令管理器
 *
 * @Author t13max
 * @Date 11:02 2024/7/17
 */
public class CommandManager extends ManagerBase {

    //命令集合
    private final Map<String, IMelonCommand> commandMap = new HashMap<>();

    /**
     * 获取当前实例对象
     *
     * @Author t13max
     * @Date 16:44 2024/5/23
     */
    public static CommandManager inst() {
        return ManagerBase.inst(CommandManager.class);
    }

    @Override
    public void init() {

        try {
            Set<Class<?>> classSet = PackageUtil.scan("com.t13max.plugin.command");
            //创建实例
            for (Class<?> clazz : classSet) {
                // 只需要加载CommandExecutor的实现
                if (!IMelonCommand.class.isAssignableFrom(clazz) || Modifier.isAbstract(clazz.getModifiers())) {
                    continue;
                }

                // 创建实例
                Object inst = clazz.getDeclaredConstructor().newInstance();
                IMelonCommand command = (IMelonCommand) inst;
                //注册命令
                commandMap.put(command.getLabel(), command);
            }

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new CommonException(e);
        }
    }

    /**
     * 分发命令 执行
     *
     * @Author t13max
     * @Date 11:26 2024/7/17
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        IMelonCommand melonCommand = this.commandMap.get(label);
        if (melonCommand == null) {
            Log.melon.error("命令不存在! label={}", label);
            return false;
        }
        if (!(sender instanceof Player player)) {
            Log.melon.error("我靠, 这个东东也能发出命令??? 东东={}, label={}", sender.getName(), label);
            return false;
        }
        return melonCommand.onCommand(player, command, args);
    }
}
