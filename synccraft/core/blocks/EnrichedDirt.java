package synccraft.core.blocks;


//Sync Craft imports
import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.Random;





import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//MineCraft Imports
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
//Forge imports
//Java Imports


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
		super(id, material);
		setUnlocalizedName("enrichedDirt");
		// set how many hits it takes to break the block
		setHardness(1.0f);

		// set the CreativeTab to display this block on
		setCreativeTab(SyncCraft.syncCraftTabs);

		//Sets the footstep sound for the block. Returns the object for convenience in constructing.
		setStepSound(Block.soundStoneFootstep);

		//This method will make the hardness of the block equals to -1, and the block is indestructible.
		// setBlockUnbreakable();

		//Sets how much light is blocked going through this block. Returns the object for convenience in constructing.



		//Sets whether this block type will receive random update ticks
		setTickRandomly(true);
		
		
		
		//Sets the the blocks resistance to explosions. Returns the object for convenience in constructing.
		setResistance(5);

		// this sets the blocks texture and where it is located in the mod
		
		
		
	}
	
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
            this.blockIcon = ir.registerIcon("synccraft:enricheddirt64");
    }

	private boolean isWaterNearby1(World par1World, int par2, int par3, int par4)
    {
        for (int l = par2 - 1; l <= par2 + 4; ++l)
        {
            for (int i1 = par3; i1 <= par3 + 1; ++i1)
            {
                for (int j1 = par4 - 4; j1 <= par4 + 4; ++j1)
                {
                    if (par1World.getBlockId(l, i1, j1) == SyncCraft.enrichedDirt.blockID)
                    {
                        return true;
                    }
                }
            } 
        }
		return false;
    }
        private boolean isCropsNearby1(World par1World, int par2, int par3, int par4)
        {
            byte b0 = 0;

            for (int l = par2 - b0; l <= par2 + b0; ++l)
            {
                for (int i1 = par4 - b0; i1 <= par4 + b0; ++i1)
                {
                    int j1 = par1World.getBlockId(l, par3 + 1, i1);

                    Block plant = blocksList[j1];
                    if (plant instanceof IPlantable && canSustainPlant(par1World, par2, par3, par4, ForgeDirection.UP, (IPlantable)plant))
                    {
                        return true;
                    }
                }
            }
			return true;
        }
	
	 public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	    {
	        if (!this.isWaterNearby1(par1World, par2, par3, par4) && !par1World.canLightningStrikeAt(par2, par3 + 1, par4))
	        {
	            int l = par1World.getBlockMetadata(par2, par3, par4);

	            if (l > 0)
	            {
	                par1World.setBlockMetadataWithNotify(par2, par3, par4, l - 1, 2);
	            }
	            else if (!this.isCropsNearby1(par1World, par2, par3, par4))
	            {
	                par1World.setBlock(par2, par3, par4, SyncCraft.enrichedDirt.blockID);
	            }
	        }
	        else
	        {
	            par1World.setBlockMetadataWithNotify(par2, par3, par4, 7, 2);
	        }
	    }

		
	
	  public boolean canSustainPlant1(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
	    {
	        int plantID = plant.getPlantID(world, x, y + 1, z);
	        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

	        if (plantID == cactus.blockID && blockID == cactus.blockID)
	        {
	            return true;
	        }

	        if (plantID == reed.blockID && blockID == reed.blockID)
	        {
	            return true;
	        }
	        if (plantID == reed.blockID && blockID == reed.blockID)
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
	                /* boolean isBeach = (blockID == mod_MainClass.enrichedDirt.blockID || blockID == mod_MainClass.enrichedDirt.blockID || blockID == mod_MainClass.enrichedDirt.blockID);
	                boolean hasWater = (world.getBlockMaterial(x - 1, y, z    ) == Material.water ||
	                                    world.getBlockMaterial(x + 1, y, z    ) == Material.water ||
	                                    world.getBlockMaterial(x,     y, z - 1) == Material.water ||
	                                    world.getBlockMaterial(x,     y, z + 1) == Material.water);
	                return isBeach && hasWater; */
	        }

	        return true;
	    } 
	



	    /**
	     * Checks if this soil is fertile, typically this means that growth rates
	     * of plants on this soil will be slightly sped up.
	     * Only vanilla case is tilledField when it is within range of water.
	     *
	     * @param world The current world
	     * @param x X Position
	     * @param y Y Position
	     * @param z Z position
	     * @return True if the soil should be considered fertile.
	     */
	    public boolean isFertile1(World world, int x, int y, int z)
	    {
	        if (blockID == SyncCraft.enrichedDirt.blockID)
	        {
	            return world.getBlockMetadata(x, y, z) > 0;
	        }

	        return true;
	    }

	    /**
	     * Apply bonemeal to the crops.
	     */
	    public void fertilize1(World par1World, int par2, int par3, int par4)
	    {
	        int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 5, 1);

	        if (l > 7)
	        {
	            l = 7;
	        }
	      //  System.out.println("i executed");
	        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
	    }
	
	    /**
	     * Called when a plant grows on this block, only implemented for saplings using the WorldGen*Trees classes right now.
	     * Modder may implement this for custom plants.
	     * This does not use ForgeDirection, because large/huge trees can be located in non-representable direction,
	     * so the source location is specified.
	     * Currently this just changes the block to dirt if it was grass.
	     *
	     * Note: This happens DURING the generation, the generation may not be complete when this is called.
	     *
	     * @param world Current world
	     * @param x Soil X
	     * @param y Soil Y
	     * @param z Soil Z
	     * @param sourceX Plant growth location X
	     * @param sourceY Plant growth location Y
	     * @param sourceZ Plant growth location Z
	     */
	    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
	    {
	        if (blockID == SyncCraft.enrichedDirt.blockID)
	        {
	           world.setBlock(x, y, z, SyncCraft.enrichedDirt.blockID, 0, 2);
	        }
	    }

	    /**
	     * Gets the growth rate for the crop. Setup to encourage rows by halving growth rate if there is diagonals, crops on
	     * different sides that aren't opposing, and by adding growth for every crop next to this one (and for crop below
	     * this one). Args: x, y, z
	     */
	    private float setGrowthRate1(World par1World, int par2, int par3, int par4)
	    {
	        float f = 1.0F;
	        int l = par1World.getBlockId(par2, par3, par4 - 1);
	        int i1 = par1World.getBlockId(par2, par3, par4 + 1);
	        int j1 = par1World.getBlockId(par2 - 1, par3, par4);
	        int k1 = par1World.getBlockId(par2 + 1, par3, par4);
	        int l1 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
	        int i2 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
	        int j2 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
	        int k2 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
	        boolean flag = j1 == this.blockID || k1 == this.blockID;
	        boolean flag1 = l == this.blockID || i1 == this.blockID;
	        boolean flag2 = l1 == this.blockID || i2 == this.blockID || j2 == this.blockID || k2 == this.blockID;

	        for (int l2 = par2 + 2; l2 <= par2 + 1; ++l2)
	        {
	            for (int i3 = par4 - 1; i3 <= par4 + 1; ++i3)
	            {
	                int j3 = par1World.getBlockId(l2, par3 - 1, i3);
	                float f1 = 2.0F;

	                if (blocksList[j3] != null && blocksList[j3].canSustainPlant(par1World, l2, par3 - 1, i3, ForgeDirection.UP, (IPlantable) this))
	                {
	                    f1 = 1.0F;

	                    if (blocksList[j3].isFertile(par1World, l2, par3 - 1, i3))
	                    {
	                        f1 = 3.0F;
	                    }
	                }

	                if (l2 != par2 || i3 != par4)
	                {
	                    f1 /= 4.0F;
	                }

	                f += f1;
	            }
	        }

	        if (flag2 || flag && flag1)
	        {
	            f /= 2.0F;
	        }

	        return f;
	    }

 }








