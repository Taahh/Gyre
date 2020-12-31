package com.taahyt.gyre.util;

import org.bukkit.ChatColor;

import java.util.concurrent.ThreadLocalRandom;

public class ColorUtils
{
    private static final ChatColor[] CHAT_COLOR_POOL = new ChatColor[]{
            ChatColor.RED,
            ChatColor.DARK_RED,
            ChatColor.BLUE,
            ChatColor.DARK_BLUE,
            ChatColor.DARK_AQUA,
            ChatColor.AQUA,
            ChatColor.GREEN,
            ChatColor.DARK_GREEN,
            ChatColor.GOLD,
            ChatColor.YELLOW,
            ChatColor.LIGHT_PURPLE
    };

    public static String randomColor(String msg)
    {
        return msg.replace("&-", CHAT_COLOR_POOL[ThreadLocalRandom.current().nextInt(CHAT_COLOR_POOL.length)].toString());
    }

}
