package com.taahyt.gyre.storage.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISQLStorage {
    Connection getConnection();

    default void createTables(Connection connection) {
        try {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS `players` (" +
                    "`uuid` VARCHAR(46) NOT NULL, " +
                    "`name` VARCHAR(18), " +
                    "`login_msg` VARCHAR(70), " +
                    "`prefix` VARCHAR(45), " +
                    "`rank` VARCHAR(20), " +
                    "`ips` VARCHAR(2000), " +
                    "`coins` BIGINT, " +
                    "`vanished` BOOLEAN, " +
                    "PRIMARY KEY (`uuid`));").execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
