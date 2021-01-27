package com.naari3.skyish;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.generator.BlockPopulator;

public abstract class LuckPopulator extends BlockPopulator {
    private static final int GEN_LUCK = 100; // Frequency is 1 / GEN_LUCK

    private int recursionDepth = 0; // thread-confined

    @Override
    public void populate(World w, Random r, Chunk c) {
        if (recursionDepth >= 1) {
            Bukkit.getLogger().warning("Skipping block population of chunk: recursion depth exceeded threshold");
        }
        ++recursionDepth;

        if (skipChunkRemoving(w, r, c)) {
            return;
        }

        removeChunk(w, r, c);

        --recursionDepth;
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
        for (Entity entity : c.getEntities()) {
            entity.remove();
        }
    }

    /**
     * @param w This string may be use for further computation in overriding classes
     * @param r This string may be use for further computation in overriding classes
     * @param c This string may be use for further computation in overriding classes
     */
    public void removeBlocks(World w, Random r, Chunk c) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < w.getMaxHeight(); y++) {
                    Block block = c.getBlock(x, y, z);
                    block.setType(Material.AIR, false);
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
