package net.cortexmodders.atomtech.blocks;

import java.util.List;

import net.cortexmodders.atomtech.AtomTech;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockATOre extends Block {

    private static Icon[] textures;
    public final static String[] unlocalizedNames = new String[] {
        "Cuprum Ore",
        "Plumbum Ore",
        "Uranium Ore",
        "Lithium Ore",
        "Aluminium Ore"
    };
    
    public BlockATOre(int par1) {
        super(par1, Material.rock);
        setCreativeTab(AtomTech.atomTab);
    }

    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata) {
        return textures[metadata];
    }
    
    @SideOnly(Side.CLIENT)
    @Override 
    public void registerIcons(IconRegister register) {
        textures = new Icon[unlocalizedNames.length];
        for(int i = 0; i < textures.length; i++)
        	textures[i] = register.registerIcon("atomtech:" + unlocalizedNames[i].replace(" ", "").toLowerCase());
    }
    
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < unlocalizedNames.length; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
    
}
