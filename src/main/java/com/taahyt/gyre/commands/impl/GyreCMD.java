package com.taahyt.gyre.commands.impl;

import com.google.common.collect.ImmutableList;
import com.taahyt.gyre.Gyre;
import com.taahyt.gyre.commands.GyreCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GyreCMD extends GyreCommand
{

    public GyreCMD() {
        super("gyre", "/<command>", "The main command for Gyre", null, null);
    }

    @Override
    public void execute(CommandSender sender, String[] args, boolean senderIsConsole)
    {
        sender.sendMessage("§bRunning §e" + Gyre.get().getDescription().getName() + " v" + Gyre.get().getDescription().getVersion());
        sender.sendMessage("§bAuthors: §e[" + StringUtils.join(Gyre.get().getDescription().getAuthors(), ", ") + "]");
        sender.sendMessage("§bDescription: §e" + Gyre.get().getDescription().getDescription());
        sender.sendMessage("§bStorage Mode: §e" + Gyre.get().getEnabledStorage().getType().name());
        sender.sendMessage("§bAPI Version: §e" + Gyre.get().getDescription().getAPIVersion());
        sender.sendMessage("§bGithub Link: §ehttps://github.com/Taahh/Gyre");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return ImmutableList.of();
    }
}
