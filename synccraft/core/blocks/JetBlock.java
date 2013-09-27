package synccraft.core.blocks;

import synccraft.core.SyncCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
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

public class JetBlock extends Block 
{
	public  JetBlock(int id, Material material) 
	{
		super(id, material);
		setUnlocalizedName("jetBlock");
		setCreativeTab(SyncCraft.syncCraftTabs);
		setStepSound(Block.soundStoneFootstep);
		setTextureName("coal_block");

	}

	/** @SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.blockIcon = ir.registerIcon("synccraft:hardenedCobblestone64");
	} */

}
