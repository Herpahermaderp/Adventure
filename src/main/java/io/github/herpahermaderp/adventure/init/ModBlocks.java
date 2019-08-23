package io.github.herpahermaderp.adventure.init;

import io.github.herpahermaderp.adventure.block.BlockEyeCage;
import io.github.herpahermaderp.adventure.block.BlockOldenbloom;
import io.github.herpahermaderp.adventure.block.BlockVeralite;
import io.github.herpahermaderp.adventure.client.renderer.tileentity.RenderTileEntityEyeCage;
import io.github.herpahermaderp.adventure.tileentity.TileEntityEyeCage;
import io.github.herpahermaderp.adventure.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = Reference.ID)
public class ModBlocks {
    static BlockOldenbloom oldenbloom;
    static Block veralite;
    static Block eyecage;
    
    public static void init() {
        oldenbloom = new BlockOldenbloom("oldenbloom");
        veralite = new BlockVeralite("veralite", Material.ROCK);
        eyecage = new BlockEyeCage("eyecage", Material.ROCK);
    }
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().registerAll(oldenbloom, veralite, eyecage);
    }
    
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> e) {
        e.getRegistry().registerAll(new ItemBlock(oldenbloom).setRegistryName(oldenbloom.getRegistryName()), new ItemBlock(veralite).setRegistryName(veralite.getRegistryName()), new ItemBlock(eyecage).setRegistryName(eyecage.getRegistryName()));
        
        registerTileEntities();
    }
    
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent e) {
        registerRender(Item.getItemFromBlock(oldenbloom));
        registerRender(Item.getItemFromBlock(veralite));
        registerRender(Item.getItemFromBlock(eyecage));
    }
    
    public static void registerTileEntities() {
        System.out.println(eyecage.getRegistryName() + " or " + eyecage.getUnlocalizedName() + " or " + eyecage.getLocalizedName());
        registerTile(TileEntityEyeCage.class, eyecage.getRegistryName());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEyeCage.class, new RenderTileEntityEyeCage());
    }
    
    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    
    public static void registerTile(Class<? extends TileEntity> clazz, ResourceLocation key) {
        GameRegistry.registerTileEntity(clazz, key);
    }
}
