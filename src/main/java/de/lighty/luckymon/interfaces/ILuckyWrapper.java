package de.lighty.luckymon.interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@FunctionalInterface
public interface ILuckyWrapper {
    void run(World world, BlockPos pos);
}

