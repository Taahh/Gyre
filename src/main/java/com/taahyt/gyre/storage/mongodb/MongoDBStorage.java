package com.taahyt.gyre.storage.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.taahyt.gyre.Gyre;
import com.taahyt.gyre.storage.AbstractStorage;
import com.taahyt.gyre.storage.StorageType;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class MongoDBStorage extends AbstractStorage {

    private final Gyre plugin;
    private Datastore datastore;
    private MongoClient mongoClient;
    private Morphia morphia;

    public MongoDBStorage() {
        this.plugin = Gyre.get();
        this.setEnabled(this.plugin.getMainConfig().getString("options.storage").equalsIgnoreCase("mongodb"));
    }

    @Override
    public StorageType getType() {
        return StorageType.MONGODB;
    }

    public Datastore getDatastore() {
        String host = this.plugin.getMainConfig().getString("options.mongodb.hostname");
        int port = this.plugin.getMainConfig().getInt("options.mongodb.port");
        String username = this.plugin.getMainConfig().getString("options.mongodb.user");
        String password = this.plugin.getMainConfig().getString("options.mongodb.password");
        String database = this.plugin.getMainConfig().getString("options.mongodb.auth-db");

        String connectionString = "mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?authSource=" + database;
        this.mongoClient = new MongoClient(new MongoClientURI(connectionString));
        this.morphia = new Morphia();
        this.datastore = morphia.createDatastore(mongoClient, database);

        this.datastore.ensureIndexes();
        return datastore;
    }
}
