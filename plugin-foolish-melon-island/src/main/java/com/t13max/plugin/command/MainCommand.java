package com.t13max.plugin.command;

import com.t13max.plugin.consts.MelonTextColor;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


/**
 * main命令的实现
 *
 * @author: t13max
 * @since: 11:08 2024/7/17
 */
public class MainCommand implements IMelonCommand {

    @Override
    public String getLabel() {
        return "main";
    }

    @Override
    public boolean onCommand(Player player, Command command, String[] strings) {

        player.sendMessage(Component.text("前往主城", MelonTextColor.BLUE));

        //后续把null改为主世界
        player.teleport(new Location(null, 0, 0, 0));
        return true;

    }
}
