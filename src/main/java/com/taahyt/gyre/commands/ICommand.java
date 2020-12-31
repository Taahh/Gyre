package com.taahyt.gyre.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ICommand
{
    /**
     * @param sender - Command Sender
     * @param args - Arguments
     * @param senderIsConsole - returns true if sender is console
     */
    void execute(CommandSender sender, String[] args, boolean senderIsConsole);

    /**
     * @param sender - Tab Complete Sender
     * @param args - Returns argument
     * @return
     */
    List<String> onTabComplete(CommandSender sender, String[] args);

}
