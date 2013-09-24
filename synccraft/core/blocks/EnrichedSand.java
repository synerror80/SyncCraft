package synccraft.core.blocks;







//Sync Craft imports
import synccraft.core.SyncCraft;
//Forge imports

//MineCraft Imports
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
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

public class EnrichedSand extends Block  
{

	private static final ForgeDirection UP = null;

	public EnrichedSand(int id, Material material) {
		super(id, material);
		setUnlocalizedName("enrichedSand");
		setHardness(1.0f);
		setCreativeTab(SyncCraft.syncCraftTabs);
		setStepSound(Block.soundSandFootstep);
		// setBlockUnbreakable();
		setLightOpacity(0);
		setTickRandomly(true);
		setResistance(5);
	}
  
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
            this.blockIcon = ir.registerIcon("synccraft:enrichedsand64");
    }
	
}

	      
	    
	        
	    

