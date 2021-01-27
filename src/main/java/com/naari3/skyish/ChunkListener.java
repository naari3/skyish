package com.naari3.skyish;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import org.bukkit.Bukkit;

public class ChunkListener implements Listener {
    @EventHandler
    public void onWorldInit(WorldInitEvent e) {
        final World world = e.getWorld();

        if (world.getName().equals("world")) {
            Bukkit.getLogger().info("Install populator for world");
            world.getPopulators().add(new NormalLuckPopulator());
        }

        if (world.getName().equals("world_nether")) {
            Bukkit.getLogger().info("Install populator for world_nether");
            world.getPopulators().add(new NetherLuckPopulator());
        }

        if (world.getName().equals("world_the_end")) {
            Bukkit.getLogger().info("Install populator for world_the_end");
            world.getPopulators().add(new EndLuckPopulator());
        }
    }
}
