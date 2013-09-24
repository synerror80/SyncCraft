package synccraft.core.items;



//Sync Craft imports

//Forge imports

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import synccraft.core.SyncCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
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

	private float weaponDamage;

	public SyncHammer(int id) {
		super(id);
		setUnlocalizedName("syncHammer");
		setCreativeTab(SyncCraft.syncCraftTabs);
		setNoRepair();	
		setMaxDamage(10);
		setMaxStackSize(1);
		setFull3D();
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.itemIcon = ir.registerIcon("synccraft:synchammer");
	}



}
