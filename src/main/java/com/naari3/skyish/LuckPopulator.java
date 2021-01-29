package com.naari3.skyish;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public abstract class LuckPopulator extends BlockPopulator {
    private static final int GEN_LUCK = 100; // Frequency is 1 / GEN_LUCK

    @Override
    public void populate(World w, Random r, Chunk c) {
        if (skipChunkRemoving(w, r, c)) {
            return;
        }

        long start = System.currentTimeMillis();
        removeChunk(w, r, c);
        long end = System.currentTimeMillis();
        System.out.println("Tooks " + (end - start) + "ms");
    }

    /**
     * @param w This string may be use for further computation in overriding classes
     * @param r This string may be use for further computation in overriding classes
     * @param c This string may be use for further computation in overriding classes
     */
    public boolean skipChunkRemoving(World w, Random r, Chunk c) {
        return ((w.getSpawnLocation().getChunk() == c) || (r.nextInt(GEN_LUCK) == 0)
                || skipChunkRemovingEnvironmentSpecific(w, r, c));
    }

    /**
     * @param w This string may be use for further computation in overriding classes
     * @param r This string may be use for further computation in overriding classes
     * @param c This string may be use for further computation in overriding classes
     */
    public boolean skipChunkRemovingEnvironmentSpecific(World w, Random r, Chunk c) {
        return false;
    }

    /**
     * @param w This string may be use for further computation in overriding classes
     * @param r This string may be use for further computation in overriding classes
     * @param c This string may be use for further computation in overriding classes
     */
    public void removeEntities(World w, Random r, Chunk c) {
        for (var entity : c.getEntities()) {
            entity.remove();
        }
    }

    /**
     * @param w This string may be use for further computation in overriding classes
     * @param r This string may be use for further computation in overriding classes
     * @param c This string may be use for further computation in overriding classes
     */
    public void removeBlocks(World w, Random r, Chunk c) {
        for (var x = 0; x < 16; x++) {
            for (var z = 0; z < 16; z++) {
                var maxY = w.getHighestBlockYAt(c.getX() * 16 + x, c.getZ() * 16 + z);
                for (var y = 0; y < maxY + 1; y++) {
                    var block = c.getBlock(x, y, z);
                    if (block.getType() != Material.AIR) {
                        block.setType(Material.AIR, false);
                    }
                }
            }
        }
    }

    /**
     * @param w This string may be use for further computation in overriding classes
     * @param r This string may be use for further computation in overriding classes
     * @param c This string may be use for further computation in overriding classes
     */
    public void removeChunk(World w, Random r, Chunk c) {
        removeEntities(w, r, c);
        removeBlocks(w, r, c);
    }
}
