package com.taahyt.gyre.commands;

import com.google.common.collect.ImmutableList;
import com.taahyt.gyre.Gyre;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public abstract class GyreCommand extends Command implements TabExecutor, ICommand
{

    private String[] aliases;
    private String permission;

    /**
     * @param name - Name of the Command (can't be null)
     * @param usage - Usage of the Command (can't be null)
     * @param description - Description of the Command (can't be null)
     * @param permission - Permission of the Command
     * @param aliases - Aliases of the Command
     */
    public GyreCommand(String name, String usage, String description, String permission, String[] aliases)
    {
        super(name);

        setName(name);
        setLabel(name);

        setDescription(description);
        setUsage(usage);
        if (aliases != null && aliases.length > 0)
        {
            this.aliases = aliases;
            setAliases(Arrays.asList(aliases));
        }
        if (permission != null)
        {
            this.permission = permission;
            setPermission(permission);
        }

        getMap().register("", this);
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args)
    {
        onCommand(sender, this, label, args);
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!matches(label))
        {
            return false;
        }

        if (sender instanceof Player)
        {
            if (permission != null)
            {
                if (!sender.hasPermission(permission))
                {
                    sender.sendMessage(ChatColor.RED + "You lack the permission node: " + ChatColor.GOLD + permission + ChatColor.RED + "!");
                    return true;
                }
            }
            execute(sender, args, false);
            return true;
        } else {
            execute(sender, args, true);
            return true;
        }
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args)
    {
        if (!matches(alias))
        {
            return ImmutableList.of();
        }
        if (permission != null && (sender.hasPermission(permission) || sender.isOp()))
        {
            return onTabComplete(sender, args);
        }

        if (permission == null)
        {
            return onTabComplete(sender, args);
        }
        return ImmutableList.of();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args)
    {
        return tabComplete(sender, label, args);
    }

    private boolean matches(String label)
    {
        if (aliases != null && aliases.length > 0)
        {
            for (String alias : aliases)
            {
                if (alias.equalsIgnoreCase(label) || getName().equalsIgnoreCase(label))
                {
                    return true;
                }
            }
        }
        else if (aliases == null || aliases.length < 1)
        {
            return getName().equalsIgnoreCase(label);
        }
        return false;
    }

    public CommandMap getMap()
    {
        return Gyre.get().getServer().getCommandMap();
    }

}
