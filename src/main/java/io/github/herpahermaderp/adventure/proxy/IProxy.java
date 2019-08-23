package io.github.herpahermaderp.adventure.proxy;

import com.google.common.collect.ImmutableMap;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public interface IProxy {
    public void preInit(FMLPreInitializationEvent e);
    public void init(FMLInitializationEvent e);
    public void postInit(FMLPostInitializationEvent e);
    @SuppressWarnings("rawtypes")
    public IAnimationStateMachine load(ResourceLocation loc, ImmutableMap params);
}
