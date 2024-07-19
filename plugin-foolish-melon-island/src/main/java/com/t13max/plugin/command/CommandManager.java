package com.t13max.plugin.command;

import com.t13max.common.exception.CommonException;
import com.t13max.common.manager.ManagerBase;
import com.t13max.common.plugin.PluginContext;
import com.t13max.plugin.MelonIsland;
import com.t13max.common.util.Log;
import com.t13max.util.PackageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;

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

            //解析yml中配置的命令
            List<Command> commandList = parseCommand();

            //创建命令 注册到服务器
            for (Command command : commandList) {
                MelonIsland.PLUGIN.getServer().getCommandMap().register(command.getLabel(), command);
            }

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new CommonException(e);
        }
    }

    @Override
    protected void onShutdown() {
        //只管注册不管删?
        /*for (IMelonCommand command : this.commandMap.values()) {
            MelonIsland.PLUGIN.getServer().getCommandMap().register()
        }*/
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
            Log.melon.debug("我靠, 这个东东也能发出命令??? 东东={}, label={}", sender.getName(), label);
            return false;
        }
        return melonCommand.onCommand(player, command, args);
    }

    public List<Command> parseCommand() {

        List<Command> commandList = new LinkedList<>();

        Map<String, Map<String, Object>> map = PluginContext.CONFIG.getCommands();

        if (map == null) {
            return commandList;
        }

        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            if (entry.getKey().contains(":")) {
                Log.melon.error("无法加载命令, 参数错误!, key={}", entry.getKey());
                continue;
            }
            Command newCmd = new MelonCommand(entry.getKey(), MelonIsland.PLUGIN);
            Object description = entry.getValue().get("description");
            Object usage = entry.getValue().get("usage");
            Object aliases = entry.getValue().get("aliases");
            Object permission = entry.getValue().get("permission");
            Object permissionMessage = entry.getValue().get("permission-message");

            if (description != null) {
                newCmd.setDescription(description.toString());
            }

            if (usage != null) {
                newCmd.setUsage(usage.toString());
            }

            if (aliases != null) {
                /*List<String> aliasList = new ArrayList<String>();

                if (aliases instanceof List) {
                    for (Object o : (List<?>) aliases) {
                        if (o.toString().contains(":")) {
                            Bukkit.getServer().getLogger().severe("Could not load alias " + o.toString() + " for plugin " + plugin.getName() + ": Illegal Characters");
                            continue;
                        }
                        aliasList.add(o.toString());
                    }
                } else {
                    if (aliases.toString().contains(":")) {
                        Bukkit.getServer().getLogger().severe("Could not load alias " + aliases.toString() + " for plugin " + plugin.getName() + ": Illegal Characters");
                    } else {
                        aliasList.add(aliases.toString());
                    }
                }

                newCmd.setAliases(aliasList);*/
            }

            if (permission != null) {
                newCmd.setPermission(permission.toString());
            }

            if (permissionMessage != null) {
                newCmd.permissionMessage(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(permissionMessage.toString())); // Paper
            }

            commandList.add(newCmd);
        }
        return commandList;
    }
}
