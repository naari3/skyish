package com.naari3.skyish;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

public class NetherLuckPopulator extends LuckPopulator {
    @Override
    public boolean skipChunkRemovingEnvironmentSpecific(World w, Random r, Chunk c) {
        if (c.contains(Material.SPAWNER.createBlockData())) {
            for (var x = 0; x < 16; x++) {
                for (var z = 0; z < 16; z++) {
                    for (var y = 0; y < w.getMaxHeight(); y++) {
                        var b = c.getBlock(x, y, z);
                        if (b.getType() == Material.SPAWNER) {
                            var spawner = ((CreatureSpawner) b.getState());
                            if (spawner.getSpawnedType() == EntityType.BLAZE) {
                                return true;
                            }
                        }

                    }
                }
            }
        }

        return false;
    }
}
