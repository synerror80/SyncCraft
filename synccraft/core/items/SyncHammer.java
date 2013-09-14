package synccraft.core.items;



//Sync Craft imports

//Forge imports

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import synccraft.core.SyncCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
//MineCraft Imports
import net.minecraft.item.Item;
//Java Imports
import net.minecraft.item.ItemStack;

/** 
* 
* Remember you do backups of mod. Only make large changes to none
* main mod class files and always comment your code !!!!
* 
* @author SynERror8o
*
*/


public class SyncHammer extends Item  {
	
		public SyncHammer(int id) {
			super(id);
			setUnlocalizedName("syncHammer");
			
			// set the CreativeTab to display this block on
			setCreativeTab(SyncCraft.syncCraftTabs);
				
			//	Call to disable repair recipes.
			setNoRepair();	

			//set max damage of an Item
			setMaxDamage(10);
				
			// this sets how many items can stack on each other
			setMaxStackSize(1);

			// Sets bFull3D to True and return the object.
			// setFull3D();
			
			
			// this sets the blocks texture and where it is located in the mod		
			func_111206_d("synccraft:synchammer");	
		}
			
	
		
}
