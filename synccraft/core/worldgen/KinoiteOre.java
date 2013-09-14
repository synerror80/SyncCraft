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


/** 
 * 
 * Remember you do backups of mod. Only make large changes to none
 * main mod class files and always comment your code !!!!
 * 
 * @author SynERror8o
 *
 */

public class KinoiteOre extends Block 
{
	public  KinoiteOre(int id, Material material) 
	{
		super(id, material);
		setUnlocalizedName("kinoiteOre");

		// set how many hits it takes to break the block
		setHardness(1.0f);

		// set the CreativeTab to display this block on
		setCreativeTab(SyncCraft.syncCraftTabs);

		//Sets the footstep sound for the block. Returns the object for convenience in constructing.
		setStepSound(Block.soundStoneFootstep);

		//This method will make the hardness of the block equals to -1, and the block is indestructible.
		// setBlockUnbreakable();

		//Sets how much light is blocked going through this block. Returns the object for convenience in constructing.
		//setLightOpacity(10);

		//Sets whether this block type will receive random update ticks
		// setTickRandomly(true);

		// Sets the amount of light emitted by a block from 0.0f to 1.0f (converts internally to 0-15). Returns the object for convenience in constructing.
		setLightValue(0.15f);

		//Sets the the blocks resistance to explosions. Returns the object for convenience in constructing.
		setResistance(5);

		// this sets the blocks texture and where it is located in the mod

		func_111022_d("synccraft:kinoiteore");
	}
	// this will drop an amount of an item randomly
	public int idDropped(int i, Random random, int fortune)
	{
		return SyncCraft.kinoite.itemID;
	}

	public int quantityDropped(Random random)
	{
		return 2 + random.nextInt(5);
	}



}
