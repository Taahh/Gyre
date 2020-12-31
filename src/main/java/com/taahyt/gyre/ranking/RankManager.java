package com.taahyt.gyre.ranking;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.taahyt.gyre.Gyre;
import com.taahyt.gyre.logger.PLogger;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class RankManager
{
    private final List<RankObject> ranks = Lists.newArrayList();
    private final File rankFolder;

    public RankManager()
    {
        this.rankFolder = new File(Gyre.get().getDataFolder(), "ranks");
        if (!this.rankFolder.exists())
        {
            this.rankFolder.mkdir();
        }
    }

    public void deserialize()
    {
        for (File f : rankFolder.listFiles())
        {
            if (!f.getName().endsWith(".json")) continue;

            try {
                JSONTokener token = new JSONTokener(new FileInputStream(f));
                JSONObject object = new JSONObject(token);
                JSONObject deserializedPermissionObject = object.getJSONObject("permissionObject");
                PermissionObject permissionObject = new Gson().fromJson(deserializedPermissionObject.toString(), PermissionObject.class);
                RankObject rankObject = new RankObject(f.getName().replace(".json", ""), object.getString("prefix"), object.getString("loginMsg"), permissionObject);
                ranks.add(rankObject);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        PLogger.info("Loaded " + ranks.size() + " ranks!");
    }

}
