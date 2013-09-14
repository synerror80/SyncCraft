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
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;



//Java Imports
import java.util.Random;


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
		// set how many hits it takes to break the block
		setHardness(1.0f);

		// set the CreativeTab to display this block on
		setCreativeTab(SyncCraft.syncCraftTabs);

		//Sets the footstep sound for the block. Returns the object for convenience in constructing.
		setStepSound(Block.soundGravelFootstep);

		//This method will make the hardness of the block equals to -1, and the block is indestructible.
		// setBlockUnbreakable();

		//Sets how much light is blocked going through this block. Returns the object for convenience in constructing.
		setLightOpacity(0);

		//Sets whether this block type will receive random update ticks
		//setTickRandomly(true);

		//Sets the the blocks resistance to explosions. Returns the object for convenience in constructing.
		setResistance(1);


		setBurnProperties(this.blockID, 0, 0);
		// this sets the blocks texture and where it is located in the mod

		func_111022_d("synccraft:enrichedgravel64");
	}

	 public static void setBurnProperties(int id, int encouragement, int flammability)
	    {
	        blockFireSpreadSpeed[id] = 0;
	        blockFlammability[id] = 0;
	    }
	 
	   /**
	     * Determines if this block should set fire and deal fire damage
	     * to entities coming into contact with it.
	     *
	     * @param world The current world
	     * @param x X Position
	     * @param y Y position
	     * @param z Z position
	     * @return True if the block should deal damage
	     */
	    public boolean isBlockBurning(World world, int x, int y, int z)
	    {
	        return false;
	    }
	 
	    
	    /**
	     * Chance that fire will spread and consume this block.
	     * 300 being a 100% chance, 0, being a 0% chance.
	     *
	     * @param world The current world
	     * @param x The blocks X position
	     * @param y The blocks Y position
	     * @param z The blocks Z position
	     * @param metadata The blocks current metadata
	     * @param face The face that the fire is coming from
	     * @return A number ranging from 0 to 300 relating used to determine if the block will be consumed by fire
	     */
	    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
	    {
	        return blockFlammability[this.blockID];
	    }
	    
	    /**
	     * Called when fire is updating, checks if a block face can catch fire.
	     *
	     *
	     * @param world The current world
	     * @param x The blocks X position
	     * @param y The blocks Y position
	     * @param z The blocks Z position
	     * @param metadata The blocks current metadata
	     * @param face The face that the fire is coming from
	     * @return True if the face can be on fire, false otherwise.
	     */
	    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
	    {
	        return getFlammability(world, x, y, z, metadata, face) > 0;
	    
	    }
	/**
     * Determines if a specified mob type can spawn on this block, returning false will
     * prevent any mob from spawning on the block.
     *
     * @param type The Mob Category Type
     * @param world The current world
     * @param x The X Position
     * @param y The Y Position
     * @param z The Z Position
     * @return True to allow a mob of the specified category to spawn, false to prevent it.
     */
  

	
}






