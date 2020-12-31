package com.taahyt.gyre.listeners;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import com.taahyt.gyre.Gyre;
import com.taahyt.gyre.util.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerMotdListener implements Listener
{
    private final Gyre plugin;

    public ServerMotdListener()
    {
        this.plugin = Gyre.get();
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onNormalPing(PaperServerListPingEvent event)
    {
        String motd = ColorUtils.randomColor(this.plugin.getMainConfig().getString("server.motd"));
        motd = motd.replace("%mcversion%", Bukkit.getBukkitVersion().split("-")[0]);
        event.setMotd(motd);
    }

}
