package com.taahyt.gyre.storage.sql;

import com.taahyt.gyre.Gyre;
import com.taahyt.gyre.storage.AbstractStorage;
import com.taahyt.gyre.storage.StorageType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBStorage extends AbstractStorage implements ISQLStorage {
    private final Gyre plugin;

    private Connection connection;

    public MariaDBStorage() {
        this.plugin = Gyre.get();
        this.setEnabled(this.plugin.getMainConfig().getString("options.storage").equalsIgnoreCase("mariadb"));
    }

    @Override
    public StorageType getType() {
        return StorageType.MARIADB;
    }

    @Override
    public Connection getConnection() {
        String host = this.plugin.getMainConfig().getString("options.mariadb.hostname");
        int port = this.plugin.getMainConfig().getInt("options.mariadb.port");
        String username = this.plugin.getMainConfig().getString("options.mariadb.user");
        String password = this.plugin.getMainConfig().getString("options.mariadb.password");
        String database = this.plugin.getMainConfig().getString("options.mariadb.db");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mariadb://" + host + ":" + port + "/" + database, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        this.createTables(this.connection);

        return this.connection;
    }
}
