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
		// set how many hits it takes to break the block
		setHardness(1.0f);
		
		// set the CreativeTab to display this block on
		setCreativeTab(SyncCraft.syncCraftTabs);
		
		//Sets the footstep sound for the block. Returns the object for convenience in constructing.
		setStepSound(Block.soundSandFootstep);
		
		//This method will make the hardness of the block equals to -1, and the block is indestructible.
		// setBlockUnbreakable();
		
		//Sets how much light is blocked going through this block. Returns the object for convenience in constructing.
		setLightOpacity(0);
		
		//Sets whether this block type will receive random update ticks
		setTickRandomly(true);
		
		//Sets the the blocks resistance to explosions. Returns the object for convenience in constructing.
		setResistance(5);
		
		
		// this sets the blocks texture and where it is located in the mod
		
		// func_111022_d("synccraft:");
	}
  
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
            this.blockIcon = ir.registerIcon("synccraft:enrichedsand64");
    }
	

	
}

	      
	    
	        
	    

