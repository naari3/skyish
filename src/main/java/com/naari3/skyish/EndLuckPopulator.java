package com.naari3.skyish;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

public class EndLuckPopulator extends LuckPopulator {
    @Override
    public void removeEntities(World w, Random r, Chunk c) {
        for (var entity : c.getEntities()) {
            if (entity.getType() != EntityType.ENDER_CRYSTAL) {
                entity.remove();
            }
        }
    }

    @Override
    public void removeBlocks(World w, Random r, Chunk c) {
        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                var maxY = w.getHighestBlockYAt(c.getX() * 16 + x, c.getZ() * 16 + z);
                for (var y = 0; y < maxY + 1; y++) {
                    var block = c.getBlock(x, y, z);
                    if (block.getType() != Material.AIR || block.getType() != Material.IRON_BARS) {
                        block.setType(Material.AIR, false);
                    }
                }
            }
        }
    }
}
