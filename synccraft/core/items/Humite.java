package synccraft.core.items;




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
public class Humite extends Item {

	public Humite(int id) {
		super(id);
		setUnlocalizedName("humite");
		setCreativeTab(SyncCraft.syncCraftTabs);
		setNoRepair();	
		setMaxDamage(10);
		setMaxStackSize(64);
		setFull3D();
	}

	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
            this.itemIcon = ir.registerIcon("synccraft:humite");
    }
	
}
