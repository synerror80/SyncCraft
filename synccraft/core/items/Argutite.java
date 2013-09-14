package synccraft.core.items;




//Sync Craft imports
import java.util.Random;

import synccraft.core.SyncCraft;
//Forge imports


//MineCraft Imports
import net.minecraft.item.Item;

//Java Imports

/** 
 * 
 * Remember you do backups of mod. Only make large changes to none
 * main mod class files and always comment your code !!!!
 * 
 * @author SynERror8o
 *
 */
public class Argutite extends Item {

	public Argutite(int id) {
		super(id);
		setUnlocalizedName("argutite");
		
		// set the CreativeTab to display this block on
		setCreativeTab(SyncCraft.syncCraftTabs);
			
		//	Call to disable repair recipes.
		setNoRepair();	

		//set max damage of an Item
		setMaxDamage(10);
			
		// this sets how many items can stack on each other
		setMaxStackSize(64);

		// Sets bFull3D to True and return the object.
		setFull3D();

		// this sets the blocks texture and where it is located in the mod		
		func_111206_d("synccraft:argutite");
	}

    public int quantityDropped(Random random)
    {
     
        return (5);
    }
	
}
