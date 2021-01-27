package com.naari3.skyish;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

public class NormalLuckPopulator extends LuckPopulator {
    @Override
    public boolean skipChunkRemovingEnvironmentSpecific(World w, Random r, Chunk c) {
        return (c.contains(Material.END_PORTAL_FRAME.createBlockData()));
    }
}
