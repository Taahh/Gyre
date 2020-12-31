package com.taahyt.gyre.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Config extends YamlConfiguration {
    private final File file;

    /**
     * Parameter to initialize the Config object
     *
     * @param plugin       - Specifies the class extending JavaPlugin
     * @param name         - File name that ends with .yml
     * @param copyDefaults - Determines if you want to copy the resource directly from the resources folder
     */
    public Config(Plugin plugin, String name, boolean copyDefaults) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        this.file = new File(plugin.getDataFolder(), name);
        if (!this.file.exists()) {
            if (copyDefaults) {
                plugin.saveResource(name, false);
            } else {
                try {
                    this.file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        this.load();
        this.save();
    }

    /**
     * Does not copy the resource directly from the resources folder
     *
     * @param plugin - Specifies the class extending JavaPlugin
     * @param name   - File name that ends with .yml
     */
    public Config(Plugin plugin, String name) {
        this(plugin, name, false);
    }

    /**
     * Loads the config file
     */
    public void load() {
        try {
            super.load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the config file
     */
    public void save() {
        try {
            super.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
