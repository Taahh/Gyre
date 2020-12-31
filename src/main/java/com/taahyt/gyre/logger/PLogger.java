package com.taahyt.gyre.logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class PLogger {

    private static final String PREFIX = ChatColor.GOLD + "[" + ChatColor.RED + "Phoenix" + ChatColor.GOLD + "]";

    /**
     * @param level - Determines the log level of the log
     * @param msg   - The specified message to be logged
     */
    private static void log(LogLevel level, String msg) {
        switch (level) {
            case SUCCESSFUL:
                Bukkit.getConsoleSender().sendMessage(PREFIX + " " + msg);
                break;
            case ERROR:
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "ERROR " + PREFIX + " " + msg);
                break;
            case WARNING:
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "WARNING " + PREFIX + " " + msg);
                break;
        }
    }

    /**
     * @param msg - The specified message to be logged (successfully)
     */
    public static void info(String msg) {
        log(LogLevel.SUCCESSFUL, msg);
    }

    /**
     * @param msg - The specified message to be logged (error)
     */
    public static void error(String msg) {
        log(LogLevel.ERROR, msg);
    }

    /**
     * @param msg - The specified message to be logged (warning)
     */
    public static void warning(String msg) {
        log(LogLevel.WARNING, msg);
    }

}
