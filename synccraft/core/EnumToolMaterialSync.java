package synccraft.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public enum EnumToolMaterialSync
{
    WOOD1(5, 59, 2.0F, 0.0F, 15),
    STONE1(6, 131, 4.0F, 1.0F, 5),
    IRON1(7, 250, 6.0F, 2.0F, 14),
    EMERALD1(8, 1561, 8.0F, 3.0F, 10),
    GOLD1(9, 32, 12.0F, 0.0F, 22);

    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    private final int harvestLevel;

    /**
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    private final int maxUses;

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    private final float efficiencyOnProperMaterial;

    /** Damage versus entities. */
    private final float damageVsEntity;

    /** Defines the natural enchantability factor of the material. */
    private final int enchantability;

    //Added by forge for custom Armor materials.
    public Item customCraftingMaterial = null;

    private EnumToolMaterialSync(int par3, int par4, float par5, float par6, int par7)
    {
        this.harvestLevel = par3;
        this.maxUses = par4;
        this.efficiencyOnProperMaterial = par5;
        this.damageVsEntity = par6;
        this.enchantability = par7;
    }

    /**
     * The number of uses this material allows. (wood = 59, stone = 131, iron = 250, diamond = 1561, gold = 32)
     */
    public int getMaxUses()
    {
        return this.maxUses;
    }

    /**
     * The strength of this tool material against blocks which it is effective against.
     */
    public float getEfficiencyOnProperMaterial()
    {
        return this.efficiencyOnProperMaterial;
    }

    /**
     * Damage versus entities.
     */
    public float getDamageVsEntity()
    {
        return this.damageVsEntity;
    }

    /**
     * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 = STONE, 0 = IRON/GOLD)
     */
    public int getHarvestLevel()
    {
        return this.harvestLevel;
    }

    /**
     * Return the natural enchantability factor of the material.
     */
    public int getEnchantability()
    {
        return this.enchantability;
    }

    /**
     * Return the crafting material for this tool material, used to determine the item that can be used to repair a tool
     * with an anvil
     */
    public int getToolCraftingMaterial()
    {
        switch (this)
        {
            case WOOD1:    return Block.planks.blockID;
            case STONE1:   return Block.cobblestone.blockID;
            case GOLD1:    return Item.ingotGold.itemID;
            case IRON1:    return Item.ingotIron.itemID;
            case EMERALD1: return SyncCraft.argutiteIngot.itemID;
            default:      return (customCraftingMaterial == null ? 0 : customCraftingMaterial.itemID);
        }
    }
}
