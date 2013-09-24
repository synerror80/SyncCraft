package synccraft.core.worldgen;


//Sync Craft imports
import synccraft.core.SyncCraft;

//Forge imports


//MineCraft Imports


import synccraft.core.items.Argutite;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
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

public class GypsumOre extends BlockBreakable 
{
	public  GypsumOre(int par1, Material par2Material, boolean par3) 
	{
		super(par1, "synccraft:gypsumore", par2Material, par3);
		setUnlocalizedName("gypsumOre");
		setHardness(1.0f);
		setCreativeTab(SyncCraft.syncCraftTabs);
		setStepSound(Block.soundStoneFootstep);
		// setBlockUnbreakable();
		//setLightOpacity(50);
		// setTickRandomly(true);
		//setLightValue(0.0f);
		setResistance(5);
	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.blockIcon = ir.registerIcon("synccraft:enricheddirt64");
	}


	@SideOnly(Side.CLIENT)

	/**
	 * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
	 */
	public int getRenderBlockPass()
	{
		return 0;
	}

	public boolean isOpaqueCube()
	{
		return true;
	}
	// this will drop an amount of an item randomly
	public int idDropped(int i, Random random, int fortune)
	{
		return SyncCraft.gypsum.itemID;
	}

	public int quantityDropped(Random random)
	{
		return 2 + random.nextInt(5);
	}



}
