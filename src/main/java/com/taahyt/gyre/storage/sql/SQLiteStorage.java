package com.taahyt.gyre.storage.sql;

import com.taahyt.gyre.Gyre;
import com.taahyt.gyre.storage.AbstractStorage;
import com.taahyt.gyre.storage.StorageType;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteStorage extends AbstractStorage implements ISQLStorage {

    private final Gyre plugin;
    private File databaseFile;

    private Connection connection;

    public SQLiteStorage() {
        this.plugin = Gyre.get();

        this.databaseFile = new File(this.plugin.getDataFolder(), "database.db");
        if (!this.databaseFile.exists()) {
            try {
                this.databaseFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.setEnabled(this.plugin.getMainConfig().getString("options.storage").equalsIgnoreCase("sqlite"));
    }

    @Override
    public StorageType getType() {
        return StorageType.SQLITE;
    }

    @Override
    public Connection getConnection() {

        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + new File(plugin.getDataFolder(), "database.db").getAbsolutePath());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.createTables(this.connection);

        return this.connection;
    }
}
