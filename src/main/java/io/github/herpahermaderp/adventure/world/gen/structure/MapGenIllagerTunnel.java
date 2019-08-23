package io.github.herpahermaderp.adventure.world.gen.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenIllagerTunnel extends MapGenStructure {

    private double chance = 0.004D;
    
    @Override
    public String getStructureName() {
        return "Illager Tunnel";
    }

    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        int i = 1000;
        int j = pos.getX() >> 4;
        int k = pos.getZ() >> 4;
        
        for(int l = 0; l <= 1000; ++l) {
            for(int i1 = -1; i1 <= 1; ++i1) {
                boolean flag = i1 == -1 || i1 == 1;
                
                for(int j1 = -1; j1 <= 1; ++j1) {
                    boolean flag1 = j1 == -1 || j1 == 1;
                    
                    if(flag || flag1) {
                        int k1 = j + i1;
                        int l1 = k + j1;
                        this.rand.setSeed((long)(k1 ^ l1) ^ worldIn.getSeed());
                        this.rand.nextInt();
                        
                        if(this.canSpawnStructureAtCoords(k1, l1) && (!findUnexplored || !worldIn.isChunkGeneratedAt(k1, l1))) {
                            return new BlockPos((k1 << 4) + 8, 64, (l1 << 4) + 8);
                        }
                    }
                }
            }
        }
        
        return null;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return this.rand.nextDouble() < this.chance && this.rand.nextInt(80) < Math.max(Math.abs(chunkX), Math.abs(chunkZ));
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        Biome biome = this.world.getBiome(new BlockPos((chunkX << 4) + 8, 64, (chunkZ << 4) + 8));
        return new StructureIllagerTunnelStart();
    }
}
