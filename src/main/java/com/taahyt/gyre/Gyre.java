package com.taahyt.gyre;

import com.taahyt.gyre.config.Config;
import com.taahyt.gyre.logger.PLogger;
import com.taahyt.gyre.storage.AbstractStorage;
import com.taahyt.gyre.storage.file.FileStorage;
import com.taahyt.gyre.storage.mongodb.MongoDBStorage;
import com.taahyt.gyre.storage.sql.MariaDBStorage;
import com.taahyt.gyre.storage.sql.SQLiteStorage;
import org.apache.commons.lang.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Gyre extends JavaPlugin {

    private static Gyre plugin;
    private Config config;

    private FileStorage fileStorage;
    private MariaDBStorage mariaDBStorage;
    private SQLiteStorage sqLiteStorage;
    private MongoDBStorage mongoDBStorage;

    @Override
    public void onLoad() {
        plugin = this;

        this.config = new Config(this, "config.yml", true);

        this.fileStorage = new FileStorage();
        this.mariaDBStorage = new MariaDBStorage();
        this.sqLiteStorage = new SQLiteStorage();
        this.mongoDBStorage = new MongoDBStorage();
    }

    @Override
    public void onEnable() {
        PLogger.info("Enabling " + getDescription().getName() + " v" + getDescription().getVersion());
        PLogger.info(String.format("Authors: [%s]", StringUtils.join(getDescription().getAuthors(), ", ")));
    }

    @Override
    public void onDisable() {
        PLogger.info("Disabling " + getDescription().getName() + " v" + getDescription().getVersion());
        PLogger.info(String.format("Authors: [%s]", StringUtils.join(getDescription().getAuthors(), ", ")));
    }


    public Config getMainConfig() {
        return config;
    }

    public static Gyre get() {
        return plugin;
    }

    public AbstractStorage getEnabledStorage() {
        if (this.fileStorage.isEnabled()) return this.fileStorage;
        if (this.mariaDBStorage.isEnabled()) return this.mariaDBStorage;
        if (this.sqLiteStorage.isEnabled()) return this.sqLiteStorage;
        if (this.mongoDBStorage.isEnabled()) return this.mongoDBStorage;
        return null;
    }
}