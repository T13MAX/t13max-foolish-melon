package com.t13max.plugin.chunk;

import com.t13max.common.consts.Const;
import org.bukkit.Material;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * 空岛区块生成器
 * 把整个世界掏空 在初始化一个起点
 *
 * @Author: t13max
 * @Since: 22:13 2024/7/15
 */
public class AirChunkGenerator extends ChunkGenerator {

    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        chunkData.setRegion(0, 0, 0, Const.CHUNK_SIDE_LENGTH, Const.CHUNK_SIDE_LENGTH, Const.CHUNK_SIDE_LENGTH, Material.AIR);
    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        //super.generateSurface(worldInfo, random, chunkX, chunkZ, chunkData);
    }

    @Override
    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        //super.generateBedrock(worldInfo, random, chunkX, chunkZ, chunkData);
    }

    @Override
    public void generateCaves(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        //int maxHeight = chunkData.getMaxHeight();
        //int minHeight = chunkData.getMinHeight();
        //chunkData.setRegion(chunkX * Const.CHUNK_SIDE_LENGTH, minHeight, chunkZ * Const.CHUNK_SIDE_LENGTH, (chunkX + 1) * Const.CHUNK_SIDE_LENGTH, maxHeight, (chunkZ + 1) * Const.CHUNK_SIDE_LENGTH, Material.AIR);
    }


}
