package com.naari3.skyish;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import org.bukkit.Bukkit;

public class ChunkListener implements Listener {
    @EventHandler
    public void onWorldInit(WorldInitEvent e) {
        Bukkit.getLogger().info("WORLD INIT IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        final World world = e.getWorld();

        world.getPopulators().add(new LuckPopulator());
    }
}
