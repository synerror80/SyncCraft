package synccraft.core.blocks;

import static net.minecraftforge.common.ForgeDirection.UP;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import synccraft.core.SyncCraft;

/** 
 * 
 * Remember you do backups of mod. Only make large changes to none
 * main mod class files and always comment your code !!!!
 * 
 * @author SynERror8o
 *
 */

public class EnrichedDirt extends Block  
{

	public EnrichedDirt(int id, Material material) {
		super(id, Material.ground);
		setHardness(1.0f);
		setCreativeTab(SyncCraft.syncCraftTabs);
		setStepSound(Block.soundStoneFootstep);
		setTickRandomly(true);
		setResistance(10);



	}


	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.blockIcon = ir.registerIcon("synccraft:enricheddirt64");
	}

	public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	{
		int plantID = plant.getPlantID(world, x, y + 1, z);
		EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

		if (plantID == cactus.blockID)
		{
			return true;
		}

		if (plantID == reed.blockID )
		{
			return true;
		}
		if (plantID == reed.blockID )
		{
			return true;
		}
		if (plantID == waterlily.blockID)
		{
			return true;
		}
		if (plantID == waterlily.blockID)
		{
			return true;
		} 
		switch (plantType)
		{
		case Desert: return blockID == SyncCraft.enrichedDirt.blockID;
		case Nether: return blockID == SyncCraft.enrichedDirt.blockID;
		case Crop:   return blockID == SyncCraft.enrichedDirt.blockID;
		case Cave:   return isBlockSolidOnSide(world, x, y, z, UP);
		case Plains: return blockID == SyncCraft.enrichedDirt.blockID || blockID == SyncCraft.enrichedDirt.blockID;
		case Water:  return world.getBlockMaterial(x, y, z) == Material.water && world.getBlockMetadata(x, y, z) == 0;
		case Beach:
			boolean isBeach = (blockID == SyncCraft.enrichedDirt.blockID || blockID == SyncCraft.enrichedDirt.blockID || blockID == SyncCraft.enrichedDirt.blockID);
			boolean hasWater = (world.getBlockMaterial(x - 1, y, z    ) == Material.water ||
					world.getBlockMaterial(x + 1, y, z    ) == Material.water ||
					world.getBlockMaterial(x,     y, z - 1) == Material.water ||
					world.getBlockMaterial(x,     y, z + 1) == Material.water);
			return isBeach && hasWater;
		} 

		return false;
	}

	public boolean isFertile(World world, int x, int y, int z)
	{
		if (blockID == SyncCraft.enrichedDirt.blockID)
		{
			return world.getBlockMetadata(x, y, z) > 0;
		}

		return false;
	}

	public Block setTickRandomly(boolean par1)
	{
		this.needsRandomTick = par1;
		return this;
	}


}








