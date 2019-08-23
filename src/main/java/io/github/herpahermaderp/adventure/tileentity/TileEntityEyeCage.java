package io.github.herpahermaderp.adventure.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;

import io.github.herpahermaderp.adventure.Adventure;
import io.github.herpahermaderp.adventure.util.Reference;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class TileEntityEyeCage extends TileEntity {
    private final IAnimationStateMachine asm;
    
    public TileEntityEyeCage() {
        System.out.println("hello");
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            asm = Adventure.proxy.load(new ResourceLocation(Reference.ID, "asms/block/eyecage.json"), ImmutableMap.of());
        }
        
        else {
            asm = null;
        }
    }
    
    public void handleEvents(float time, Iterable<Event> pastEvents) {
        for(Event event : pastEvents) {
           System.out.println("Event: " + event.event() + " " + event.offset() + " " + getPos() + " " + time);
        }
    }
    
    @Override
    public boolean hasFastRenderer() {
        return true;
    }
    
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing side) {
        if(capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
            return true;
        }
        
        return super.hasCapability(capability, side);
    }
    
    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing side) {
        if(capability == CapabilityAnimation.ANIMATION_CAPABILITY) {
            return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
        }
        
        return super.getCapability(capability, side);
    }
}
