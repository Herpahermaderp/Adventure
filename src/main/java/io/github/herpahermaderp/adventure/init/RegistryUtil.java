package io.github.herpahermaderp.adventure.init;

import io.github.herpahermaderp.adventure.block.BlockOldenbloom;
import io.github.herpahermaderp.adventure.util.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.ID)
public class RegistryUtil {
    public static BlockOldenbloom oldenbloom;
    
    public static Block[] blocks = {
            oldenbloom = new BlockOldenbloom("oldenbloom")
    };
}
