package synccraft.core.blocks;


//Sync Craft imports
import synccraft.core.SyncCraft;

//Java Imports
import java.util.Random;

//MineCraft Imports
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

//Forge imports
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
		 float f = (float)par2 + 1.0F;
         float f1 = (float)par3 + 1.0F + par5Random.nextFloat() * 12.0F / 48.0F;
         float f2 = (float)par4 + 0.5F;
         float f3 = 0.5F;
         float f4 = par5Random.nextFloat() * 0.6F - 0.3F;

        par1World.spawnParticle("reddust", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.4D, 5.3D, 0.0D);
        par1World.spawnParticle("reddust", (double)(f - f3), (double)f1, (double)(f2 + f4), -0.4D, 5.9D, 0.0D);
        par1World.spawnParticle("reddust", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.4D, 5.3D, 0.3D);
        par1World.spawnParticle("reddust", (double)(f - f3), (double)f1, (double)(f2 + f4), -0.4D, 5.9D, -0.3D);

    }
	

}






