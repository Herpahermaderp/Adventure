package io.github.herpahermaderp.adventure.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOldenbloom extends BlockBush {
    public BlockOldenbloom(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return super.getBoundingBox(state, source, pos).offset(state.getOffset(source, pos));
    }
    
    @Override
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if(world.isRemote) {
            double xPos = pos.getX() + (-3 + (4 - (-3)) * rand.nextDouble());
            double yPos = pos.getY() + (2 * rand.nextDouble());
            double zPos = pos.getZ() + (-3 + (4 - (-3)) * rand.nextDouble());
            double xMotion = 0.1D * rand.nextDouble() - 0.05D;
            double yMotion = 0.3D;
            double zMotion = 0.1D * rand.nextDouble() - 0.05D;
        
            world.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, xPos, yPos, zPos, xMotion, yMotion, zMotion);
        }
    }
}
