package synccraft.core.blocks;

import synccraft.core.SyncCraft;
import synccraft.core.items.Argutite;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

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

public class HardenedCobblestone extends Block 
{
	public  HardenedCobblestone(int id, Material material) 
	{
		super(id, material);
		setUnlocalizedName("hardenedCobblestone");
		setHardness(150.0f);
		setCreativeTab(SyncCraft.syncCraftTabs);
		setStepSound(Block.soundStoneFootstep);
		// setBlockUnbreakable();
		//setLightOpacity(10);
		// setTickRandomly(true);
		//setLightValue(0.0f);
		setResistance(50);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.blockIcon = ir.registerIcon("synccraft:hardenedCobblestone64");
	}

}
