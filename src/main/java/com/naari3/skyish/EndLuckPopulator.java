package com.naari3.skyish;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EndLuckPopulator extends LuckPopulator {
    @Override
    public void removeEntities(World w, Random r, Chunk c) {
        for (Entity entity : c.getEntities()) {
            if (entity.getType() != EntityType.ENDER_CRYSTAL) {
                entity.remove();
            }
        }
    }

    @Override
    public void removeBlocks(World w, Random r, Chunk c) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < w.getMaxHeight(); y++) {
                    Block block = c.getBlock(x, y, z);
                    if (block.getType() != Material.IRON_BARS) {
                        block.setType(Material.AIR, false);
                    }
                }
            }
        }
    }
}
