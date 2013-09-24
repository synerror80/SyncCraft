package synccraft.core.worldgen;


//Sync Craft imports
import synccraft.core.SyncCraft;

//Forge imports


//MineCraft Imports


import synccraft.core.items.Argutite;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;




//Java Imports
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/** 
 * 
 * Remember you do backups of mod. Only make large changes to none
 * main mod class files and always comment your code !!!!
 * 
 * @author SynERror8o
 *
 */

public class ArgutiteOre extends Block 
{
	public  ArgutiteOre(int id, Material material) 
	{
		super(id, material);
		setUnlocalizedName("argutiteOre");
		setHardness(1.0f);
		setCreativeTab(SyncCraft.syncCraftTabs);
		setStepSound(Block.soundStoneFootstep);
		// setBlockUnbreakable();
		//setLightOpacity(10);
		// setTickRandomly(true);
		setLightValue(0.15f);
		setResistance(5);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
            this.blockIcon = ir.registerIcon("synccraft:argutiteore");
    }
	
	
	// this will drop an amount of an item randomly
	public int idDropped(int i, Random random, int fortune)
	{
		return SyncCraft.argutite.itemID;
	}

	public int quantityDropped(Random random)
	{
		return 2 + random.nextInt(5);
	}



}
