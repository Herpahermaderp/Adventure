package io.github.herpahermaderp.adventure.proxy;

import com.google.common.collect.ImmutableMap;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        
    }

    @Override
    public void init(FMLInitializationEvent e) {
        
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        
    }

    @SuppressWarnings("rawtypes")
    @Override
    public IAnimationStateMachine load(ResourceLocation loc, ImmutableMap params) {
        return null;
    }
}
