package com.taahyt.gyre.ranking;

import com.google.gson.Gson;
import org.bukkit.ChatColor;

public class RankObject
{
    private String name;
    private String prefix;
    private String loginMsg;
    private PermissionObject permissionObject;

    public RankObject(String name, String prefix, String loginMsg, PermissionObject permissionObject)
    {
        this.name = name;
        this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
        this.loginMsg = ChatColor.translateAlternateColorCodes('&', loginMsg);
        this.permissionObject = permissionObject;
    }

    public String serialize()
    {
        return new Gson().toJson(this);
    }


}
