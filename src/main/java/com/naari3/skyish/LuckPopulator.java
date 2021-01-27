package com.naari3.skyish;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;

public class LuckPopulator extends BlockPopulator {
    private static final int GEN_LUCK = 100; // Frequency is 1 / GEN_LUCK

    private int recursionDepth = 0; // thread-confined

    @Override
    public void populate(World w, Random r, Chunk c) {
        if (this.recursionDepth >= 1) {
            Bukkit.getLogger().warning("Skipping block population of chunk: recursion depth exceeded threshold");
        }
        ++this.recursionDepth;
        if (w.getSpawnLocation().getChunk() == c) {
            return;
        }
        if (r.nextInt(GEN_LUCK) == 0) {
            return;
        }

        if (c.contains(Material.END_PORTAL_FRAME.createBlockData())) {
            return;
        }

        if (c.contains(Material.SPAWNER.createBlockData())) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < w.getMaxHeight(); y++) {
                        Block b = c.getBlock(x, y, z);
                        if (b.getType() == Material.SPAWNER) {
                            CreatureSpawner spawner = ((CreatureSpawner) b.getState());
                            if (spawner.getSpawnedType() == EntityType.BLAZE) {
                                return;
                            }
                        }

                    }
                }
            }
        }

        // remove all of chunk
        for (Entity entity : c.getEntities()) {
            entity.remove();
        }

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < w.getMaxHeight(); y++) {
                    Block block = c.getBlock(x, y, z);
                    block.setType(Material.AIR, false);
                }
            }
        }

        --this.recursionDepth;
    }
}
