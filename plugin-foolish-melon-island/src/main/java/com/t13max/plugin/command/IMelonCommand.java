package com.t13max.plugin.command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * 呆瓜自定义命令必须实现的接口
 *
 * @author: t13max
 * @since: 11:22 2024/7/17
 */
public interface IMelonCommand {

    //获取label 也就是命令
    String getLabel();

    //执行命令
    boolean onCommand(Player player, Command command, String[] args);
}
