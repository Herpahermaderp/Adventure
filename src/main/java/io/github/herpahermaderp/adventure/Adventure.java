package io.github.herpahermaderp.adventure;

import io.github.herpahermaderp.adventure.init.ModBlocks;
import io.github.herpahermaderp.adventure.proxy.IProxy;
import io.github.herpahermaderp.adventure.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.ID, name = Reference.NAME, version = Reference.VER)
public class Adventure {
    @Instance
    public static Adventure instance;
    
    @SidedProxy(clientSide = Reference.PROXY_LOC + ".ClientProxy", serverSide = Reference.PROXY_LOC + ".ServerProxy")
    public static IProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        ModBlocks.init();
        proxy.preInit(e);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
