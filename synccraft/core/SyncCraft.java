package synccraft.core;

// Sync Craft imports

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import synccraft.core.blocks.EnrichedDirtold;
import synccraft.core.blocks.EnrichedGravel;
import synccraft.core.blocks.EnrichedSand;
import synccraft.core.blocks.HardenedCobblestone;
import synccraft.core.blocks.HardenedGlass;
import synccraft.core.ingots.ArgutiteIngot;
import synccraft.core.ingots.DarkArgutiteIngot;
import synccraft.core.items.Argutite;
import synccraft.core.items.Gypsum;
import synccraft.core.items.Humite;
import synccraft.core.items.Jet;
import synccraft.core.items.Kinoite;
import synccraft.core.items.SyncHammer;
import synccraft.core.syncfurnace2d.SyncFurnaceRecipes;
import synccraft.core.syncfurnace2d.SyncPacketHandler;
import synccraft.core.syncfurnace2d.TileEntitySyncFurnace;
import synccraft.core.worldgen.ArgutiteOre;
import synccraft.core.worldgen.Cobalt;
import synccraft.core.worldgen.FossilizedWood;
import synccraft.core.worldgen.GypsumOre;
import synccraft.core.worldgen.HumiteOre;
import synccraft.core.worldgen.KinoiteOre;
import synccraft.core.worldgen.NaturalGlass;
import synccraft.core.worldgen.RhodiumOre;
import synccraft.core.worldgen.ZirconiumOre;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
// MineCraft Imports
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
// Forge imports

// Java Imports


/** 
 * 
 * Remember you do backups of mod. Only make large changes to none
 * main mod class files and always comment your code !!!!
 * 
 * @author SynERror8o
 *
 */

//this is where you set the name and the version number

@Mod(modid="SyncCraft", name="Sync Craft", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels = "Sync Craft", packetHandler = SyncPacketHandler.class)
public class SyncCraft 
{
	// this is setting up the tabs 
	public static CreativeTabs syncCraftTabs = new CreativeTabs("synccraft_syncCraftTab")
	{
		public ItemStack getIconItemStack(){
			return new ItemStack(kinoite, 1,0);
		}

	};

	// This is the Ore generation 
	EventManager oreManager = new EventManager();

	// Blocks set here
	public static Block enrichedDirt = new EnrichedDirtold(4007, Material.ground);
	public static Block enrichedGravel = new EnrichedGravel(4008, Material.rock);
	public static Block enrichedSand = new EnrichedSand(4009, Material.sand);
	public static Block hardenedCobblestone = new HardenedCobblestone(4010, Material.rock);
	public static BlockBreakable naturalGlass = new NaturalGlass(4011, Material.rock, false);
	public static BlockBreakable hardenedGlass = new HardenedGlass(4012, Material.glass, false);

	



	// Furnace set here
	public static final Block syncFurnaceIdle = (new SyncFurnace(4013, false)).setHardness(3.5F).setUnlocalizedName("Sync Furnace").setCreativeTab(SyncCraft.syncCraftTabs);
	public static final Block syncFurnaceBurning = (new SyncFurnace(4014, true)).setHardness(3.5F).setLightValue(0.875F).setUnlocalizedName("Sync Furnace");




	//Ores set here
	public static Block argutiteOre = new ArgutiteOre(4000, Material.rock);
	public static Block humiteOre = new HumiteOre(4001, Material.rock);
	public static Block cobalt = new Cobalt(4015, Material.rock);
	public static Block fossilizedWood = new FossilizedWood(4016, Material.rock);
	public static Block gypsumOre = new GypsumOre(4017, Material.rock, false);
	public static Block kinoiteOre = new KinoiteOre(4018, Material.rock);
	public static Block rhodiumOre = new RhodiumOre(4019, Material.rock);
	public static Block zirconiumOre = new ZirconiumOre(4020, Material.rock);




	// Ingots set here
	public static Item argutiteIngot = new ArgutiteIngot(4002);
	public static Item darkArgutiteIngot = new DarkArgutiteIngot(4010);




	//Items here
	public static Item humite = new Humite(4003);
	public static Item argutite = new Argutite(4005);
	public static Item syncHammer = new SyncHammer(4006);
	public static Item jet = new Jet(4021);
	public static Item kinoite = new Kinoite(4022);
	public static Item gypsum = new Gypsum(4023);
	//public static Item syncCraft101Book = new SyncCraft101Book(4016);

	//public static Item syncCraft101Book = (new SyncCraft101Book(4016)).setUnlocalizedName("book").setCreativeTab(mod_MainClass.syncCraftTabs).func_111206_d("book_normal");


	// The instance of your mod that Forge uses.
	@Instance("SyncCraft")
	public static SyncCraft instance;


	// this is the Gui Handler for the SyncFurnace. DO NOT REMOVE !!
	private GuiHandler GuiHandler = new GuiHandler(); 


	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="synccraft.core.client.ClientProxy", serverSide="synccraft.core.CommonProxy")
	public static CommonProxy proxy;

	// commonproxy  items here


	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// Stub Method
	}




	@EventHandler
	public void load(FMLInitializationEvent event) 
	{
		proxy.registerRenderers();

		// this is the Registry for Ore Generation
		GameRegistry.registerWorldGenerator(oreManager);



		// Register game blocks, items and Entity's here

		//Register Blocks

		// GameRegistry.registerBlock(bonjour, "test_TestBlock");
		GameRegistry.registerBlock(enrichedDirt, "enrichedDirt");
		GameRegistry.registerBlock(naturalGlass, "naturalGlass");
		GameRegistry.registerBlock(hardenedGlass, "hardenedGlass");
		GameRegistry.registerBlock(hardenedCobblestone, "hardenedCobblestone");
		GameRegistry.registerBlock(enrichedSand, "enrichedSand");
		GameRegistry.registerBlock(enrichedGravel, "enrichedGravel");
		GameRegistry.registerBlock(argutiteOre, "argutiteOre");
		GameRegistry.registerBlock(humiteOre, "humuteOre");
		GameRegistry.registerBlock(cobalt, "cobalt");
		GameRegistry.registerBlock(fossilizedWood, "fossilizedWood");
		GameRegistry.registerBlock(kinoiteOre, "kinoiteOre");
		GameRegistry.registerBlock(rhodiumOre, "rhodiumOre");
		GameRegistry.registerBlock(zirconiumOre, "zirconiumOre");
		GameRegistry.registerBlock(gypsumOre, "gypsumOre");

		// Register Items here
		GameRegistry.registerItem(humite, "humite");
		GameRegistry.registerItem(jet, "jet");
		GameRegistry.registerItem(argutite, "argutite");
		GameRegistry.registerItem(syncHammer, "syncHammer");
		GameRegistry.registerItem(kinoite, "kinoite");
		GameRegistry.registerItem(gypsum, "gypsum");


		// GameRegistry.registerTileEntity(SyncFurnace3D.class, "tileentitysyncsurnace3D");
		//	GameRegistry.registerBlock(syncFurnace3DBlock, "syncFurnace3DBlock");


		// furnace registry here
		GameRegistry.registerBlock(syncFurnaceIdle, "mod_MainClass.syncFurnaceIdle");
		// GameRegistry.registerBlock(syncFurnaceBurning, "mod_MainClass.syncFurnaceBurning");

		//Register Ingots
		GameRegistry.registerItem(argutiteIngot, "argutiteIngot");
		GameRegistry.registerItem(darkArgutiteIngot, "darkArgutiteIngot");

		// Furnace Game Registry and Network GUI Handler
		GameRegistry.registerTileEntity(TileEntitySyncFurnace.class, "tileentitysyncfurnace");
		NetworkRegistry.instance().registerGuiHandler(this, GuiHandler);
		



		LanguageRegistry.addName(gypsum, "Gypsum");
		LanguageRegistry.addName(kinoite, "Kinoite");
		LanguageRegistry.addName(syncFurnaceIdle, "Sync Furnace (WIP)");
		// LanguageRegistry.addName(syncFurnaceBurning, "Sync Furnace Burning");
		LanguageRegistry.addName(enrichedDirt, "Enriched Dirt");
		LanguageRegistry.addName(naturalGlass, "Natural Glass ");
		LanguageRegistry.addName(hardenedGlass, "Hardened Glass ");
		LanguageRegistry.addName(hardenedCobblestone, "Hardened Cobblestone");
		LanguageRegistry.addName(enrichedSand, "Enriched Sand");
		LanguageRegistry.addName(enrichedGravel, "Enriched Gravel");
		LanguageRegistry.addName(argutiteOre, "Argutite Ore");
		LanguageRegistry.addName(humiteOre, "Humite Ore");
		LanguageRegistry.addName(argutiteIngot, "Argutite Ingot");
		LanguageRegistry.addName(darkArgutiteIngot, "Dark Argutite Ingot");
		LanguageRegistry.addName(SyncCraft.humite, "Humite");
		LanguageRegistry.addName(argutite, "Argutite");
		LanguageRegistry.addName(syncHammer, "Sync Hammer WIP");
		LanguageRegistry.addName(gypsumOre, "Gypsum Ore");
		LanguageRegistry.addName(cobalt, "Cobalt");
		LanguageRegistry.addName(fossilizedWood, "Fossilized Wood");
		LanguageRegistry.addName(kinoiteOre, "Kinoite Ore");
		LanguageRegistry.addName(rhodiumOre, "Rhodium Ore");
		LanguageRegistry.addName(zirconiumOre, "Zirconium Ore");
		LanguageRegistry.addName(jet, "Jet");

		// This is the Crafting Tab Area

		LanguageRegistry.instance().addStringLocalization("itemGroup.synccraft_syncCraftTab", "Sync Craft");



		// Harvest level

		MinecraftForge.setBlockHarvestLevel(argutiteOre, "pickaxe", 1);



		/** ItemStack Area
		 *  For ItemStack You can pass in Block/Item - Amount of Item to get - ( amount of items to get ) Damage 
		 */
		ItemStack dirtStack = new ItemStack(Block.dirt, 32);
		ItemStack diamondStack = new ItemStack(Item.diamond, 64);
		ItemStack blackWoolStack = new ItemStack(Block.cloth, 10, 15);
		ItemStack whiteWoolStack = new ItemStack(Block.cloth, 10, 0);
		//ItemStack stoneStack = new ItemStack(1, 32, 0);
		ItemStack gravelStack = new ItemStack(Block.gravel);
		ItemStack furnaceStack = new ItemStack(SyncCraft.syncFurnaceIdle);
		ItemStack syncHammerStack = new ItemStack(SyncCraft.syncHammer);
		ItemStack humiteStack = new ItemStack(SyncCraft.humite);
		ItemStack darkArgutiteIngotStack = new ItemStack(SyncCraft.darkArgutiteIngot);


		/**
		 *  Recipe Area
		 */

		// This is shapeless crafting
		GameRegistry.addShapelessRecipe(diamondStack, dirtStack);



		// This is Shaped crafting	 as output the row1, row2, row3 	

		GameRegistry.addRecipe(humiteStack, "xy", "yx",

				'x', Block.brick, 
				'y', SyncCraft.argutite

				);


		// Sync Furnace Recipe

		GameRegistry.addRecipe(furnaceStack, "xyx", "yby", "xyx",

				'x', Block.brick,
				'y', Block.sand,
				'b', SyncCraft.humite
				);


		// this is the smelting area

		//							input item			output 		XP
		// GameRegistry.addSmelting(Block.dirt.blockID, diamondStack, 0.1f);
		// FurnaceRecipes.smelting().addSmelting(Block.cloth.blockID, 15, whiteWoolStack, 1.0f);
		//	FurnaceRecipes.smelting(syncFurnaceIdle.blockID).addSmelting(mod_MainClass.argutiteIngot.itemID, new ItemStack(mod_MainClass.darkArgutiteIngot), 0.25F);



	}



	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// Stub Method
	}

}

