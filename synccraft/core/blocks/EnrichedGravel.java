package synccraft.core.blocks;


//Sync Craft imports
import static net.minecraftforge.common.ForgeDirection.UP;
import synccraft.core.SyncCraft;
//Forge imports

//MineCraft Imports
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStep;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;





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

public class EnrichedGravel extends Block  
{

	public EnrichedGravel(int id, Material material) {
		super(id, material);
		setUnlocalizedName("enrichedGravel");
		setHardness(1.0f);
		setCreativeTab(SyncCraft.syncCraftTabs);
		setStepSound(Block.soundGravelFootstep);
		// setBlockUnbreakable();
		setLightOpacity(0);
		//setTickRandomly(true);
		setResistance(1);
		setBurnProperties(this.blockID, 0, 0);
		// func_111022_d("synccraft:"); // this is an old block texture method 
	}
	@SideOnly(Side.CLIENT) // Sets the Blocks Texture new as of 9/15/2013
	public void registerIcons(IconRegister ir)
	{
		this.blockIcon = ir.registerIcon("synccraft:enrichedgravel64");
	}
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
         
        
        
    }
	

}






