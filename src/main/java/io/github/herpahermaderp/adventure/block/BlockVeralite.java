package io.github.herpahermaderp.adventure.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockVeralite extends Block {
    public BlockVeralite(String name, Material mat) {
        super(mat);
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
