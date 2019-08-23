package io.github.herpahermaderp.adventure.block;

import javax.annotation.Nonnull;

import io.github.herpahermaderp.adventure.tileentity.TileEntityEyeCage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEyeCage extends Block {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    private static final AxisAlignedBB AABB = new AxisAlignedBB(3.0/16, 0, 3.0/16, 13.0/16, 1, 13.0/16);
    
    public BlockEyeCage(String name, Material mat) {
        super(mat);
        setUnlocalizedName(name);
        setRegistryName(name);
    }
    
    @Override
    public ExtendedBlockState createBlockState() {
        return new ExtendedBlockState(this, new IProperty[] {FACING, Properties.StaticProperty}, new IUnlistedProperty[] {Properties.AnimationProperty});
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float x, float y, float z, int meta, EntityLivingBase player) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, player));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
    
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityEyeCage();
    }
    
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.withProperty(Properties.StaticProperty, true);
    }
    
    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
        if(!world.isRemote) {
            TileEntityEyeCage te = (TileEntityEyeCage) world.getTileEntity(pos);
            //te.doRequest(player.isSneaking());
        }
    }
    
    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return AABB;
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float x, float y, float z) {
        ItemStack stack = player.getHeldItem(hand);
        if(!stack.isEmpty()) {
            TileEntityEyeCage te = (TileEntityEyeCage) world.getTileEntity(pos);
            //te.setRequestTarget(stack);
            return true;
        }
        
        return false;
    }
}
