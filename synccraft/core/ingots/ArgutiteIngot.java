package synccraft.core.ingots;




//Sync Craft imports
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import synccraft.core.SyncCraft;
//Forge imports


import net.minecraft.client.renderer.texture.IconRegister;
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
public class ArgutiteIngot extends Item {

	public ArgutiteIngot(int id) {
		super(id);
		setUnlocalizedName("argutiteIngot");
		
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
		// func_111206_d("synccraft:");
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
            this.itemIcon = ir.registerIcon("synccraft:argutiteingot");
    }

}
