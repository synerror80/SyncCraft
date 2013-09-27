package synccraft.core.items;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import synccraft.core.SyncCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;

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
		setPotionEffect(PotionHelper.speckledMelonEffect);
		
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.itemIcon = ir.registerIcon("synccraft:synchammer");
	}



}
