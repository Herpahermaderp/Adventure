package io.github.herpahermaderp.adventure.client.renderer.tileentity;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

import io.github.herpahermaderp.adventure.tileentity.TileEntityEyeCage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;

public class RenderTileEntityEyeCage extends TileEntitySpecialRenderer<TileEntityEyeCage> {
    private static BlockRendererDispatcher blockRenderer;
    
    @Override
    public void render(@Nonnull TileEntityEyeCage cube, double d0, double d1, double d2, float f, int digProgress, float unused) {
        renderAnimatedModel(cube, d0, d1, d2, f);
    }
    
    private void renderAnimatedModel(TileEntityEyeCage te, double x, double y, double z, float partialTick) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldRenderer = tessellator.getBuffer();
        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        
        if(Minecraft.isAmbientOcclusionEnabled()) {
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
        }
        
        else {
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }
        
        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        
        if(!te.hasCapability(CapabilityAnimation.ANIMATION_CAPABILITY, null)) {
            return;
        }
        
        if(blockRenderer == null) blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
        BlockPos pos = te.getPos();
        IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
        IBlockState state = world.getBlockState(pos);
        if(state.getPropertyKeys().contains(Properties.StaticProperty)) {
            state = state.withProperty(Properties.StaticProperty, false);
        }
        if(state instanceof IExtendedBlockState) {
            IExtendedBlockState exState = (IExtendedBlockState)state;
            if(exState.getUnlistedNames().contains(Properties.AnimationProperty)) {
                float time = Animation.getWorldTime(getWorld(), partialTick);
                Pair<IModelState, Iterable<Event>> pair = te.getCapability(CapabilityAnimation.ANIMATION_CAPABILITY, null).apply(time);
                
                IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(exState.getClean());
                exState = exState.withProperty(Properties.AnimationProperty, pair.getLeft());
                
                worldRenderer.setTranslation(x - pos.getX(), y - pos.getY(), z - pos.getZ());
                
                blockRenderer.getBlockModelRenderer().renderModel(world, model, exState, pos, worldRenderer, false);
            }
        }
        
        worldRenderer.setTranslation(0, 0, 0);
        tessellator.draw();
        RenderHelper.enableStandardItemLighting();
    }
}
