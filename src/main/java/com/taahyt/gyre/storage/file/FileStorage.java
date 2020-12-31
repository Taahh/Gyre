package com.taahyt.gyre.storage.file;

import com.taahyt.gyre.Gyre;
import com.taahyt.gyre.config.Config;
import com.taahyt.gyre.storage.AbstractStorage;
import com.taahyt.gyre.storage.StorageType;

public class FileStorage extends AbstractStorage {
    private final Config playerConfig;
    private final Config bansConfig;
    private final Config permbansConfig;
    private final Config staffConfig;

    public FileStorage() {
        playerConfig = new Config(Gyre.get(), "players.yml", true);
        bansConfig = new Config(Gyre.get(), "bans.yml", true);
        permbansConfig = new Config(Gyre.get(), "permbans.yml", true);
        staffConfig = new Config(Gyre.get(), "staff.yml", true);

        this.setEnabled(Gyre.get().getMainConfig().getString("options.storage").equalsIgnoreCase("file"));
    }

    @Override
    public StorageType getType() {
        return StorageType.FILE;
    }

    public Config getPlayerConfig() {
        return this.playerConfig;
    }

    public Config getBansConfig() {
        return this.bansConfig;
    }

    public Config getPermbansConfig() {
        return this.permbansConfig;
    }

    public Config getStaffConfig() {
        return this.staffConfig;
    }
}
