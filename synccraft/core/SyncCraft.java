package synccraft.core;


import java.io.File;
import java.util.logging.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import synccraft.core.blocks.EnrichedDirt;
import synccraft.core.blocks.EnrichedGravel;
import synccraft.core.blocks.EnrichedSand;
import synccraft.core.blocks.HardenedCobblestone;
import synccraft.core.blocks.HardenedGlass;
import synccraft.core.blocks.JetBlock;
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
import cpw.mods.fml.common.FMLLog;
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
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;

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
	public static enum RenderMode {
		Full, NoDynamic
	};

	// this is setting up the tabs 
	public static CreativeTabs syncCraftTabs = new CreativeTabs("synccraft_syncCraftTab")
	{
		public ItemStack getIconItemStack(){
			return new ItemStack(kinoite, 1,0);
		}

	};
	public static RenderMode render = RenderMode.Full;

	// This is the Ore generation -----------------------------------------
	EventManager oreManager = new EventManager();

	//Ores set here
	public static Block argutiteOre;
	public static Block humiteOre;
	public static Block cobalt;
	public static Block fossilizedWood;
	public static Block gypsumOre;
	public static Block kinoiteOre;
	public static Block rhodiumOre;
	public static Block zirconiumOre;


	//----------------------------------------------------------------------

	// Blocks set here
	public static Block enrichedDirt; 
	public static Block jetBlock; 
	public static Block enrichedGravel;
	public static Block enrichedSand;
	public static Block hardenedCobblestone;
	public static Block naturalGlass;
	public static Block hardenedGlass;

	// Furnace set here
	public static Block syncFurnaceIdle;
	public static Block syncFurnaceBurning;

	// Ingots set here
	public static Item argutiteIngot;
	public static Item darkArgutiteIngot;

	//Items here
	public static Item humite;
	public static Item argutite;
	public static Item syncHammer;
	public static Item jet;
	public static Item kinoite;
	public static Item gypsum;


	// The instance of your mod that Forge uses.
	@Instance("SyncCraft")
	public static SyncCraft instance;

	// this is the Gui Handler for the SyncFurnace. DO NOT REMOVE !!
	private GuiHandler GuiHandler = new GuiHandler(); 


	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="synccraft.core.client.ClientProxy", serverSide="synccraft.core.CommonProxy")
	public static CommonProxy proxy;

	// commonproxy  items here
	public static SyncCraftConfiguration mainConfiguration;
	public static final Logger scLog = Logger.getLogger("Sync Craft");

	/**
	 * 
	 *  This bit of code is all thanks to the buildcraft team.
	 *  	 
	 */

	@EventHandler  
	public void loadConfiguration(FMLPreInitializationEvent evt) {

		scLog.setParent(FMLLog.getLogger()); 
		scLog.info("Starting Sync Craft ");
		scLog.info("Copyright (c) SynError80, 2013");
		scLog.info("No information yet");

		mainConfiguration = new SyncCraftConfiguration(new File(evt.getModConfigurationDirectory(), "synccraft/main.conf"));
		try {
			mainConfiguration.load();

// Items here
			Property synchammerId = SyncCraft.mainConfiguration.getItem("synchammer.id", DefaultProps.SYNCHAMMER_ID);
			synchammerId.comment = "The main tool for Sync Craft";
			syncHammer = (new SyncHammer(synchammerId.getInt(DefaultProps.SYNCHAMMER_ID))).setUnlocalizedName("syncHammer");
			
			Property humiteId = SyncCraft.mainConfiguration.getBlock("humite.id", DefaultProps.HUMITE_ID);
			humite = (new  Humite( humiteId.getInt(DefaultProps.HUMITE_ID))).setUnlocalizedName("humite");

			Property argutiteId = SyncCraft.mainConfiguration.getBlock("argutite.id", DefaultProps.ARGUTITE_ID);
			argutite = (new  Argutite ( argutiteId.getInt(DefaultProps.ARGUTITE_ID))).setUnlocalizedName("argutite");

			Property jetId = SyncCraft.mainConfiguration.getBlock("jet.id", DefaultProps.JET_ID);
			jet = (new  Jet( jetId.getInt(DefaultProps.JET_ID))).setUnlocalizedName("jet");

			Property kinoiteId = SyncCraft.mainConfiguration.getBlock("kinoite.id", DefaultProps.KINOITE_ID);
			kinoite = (new  Kinoite( kinoiteId.getInt(DefaultProps.KINOITE_ID))).setUnlocalizedName("kinoite");

			Property gypsumId = SyncCraft.mainConfiguration.getBlock("gypsum.id", DefaultProps.GYPSUM_ID);
			gypsum = (new  Gypsum( gypsumId.getInt(DefaultProps.GYPSUM_ID))).setUnlocalizedName("gypsum");


// Blocks here
			Property enrichedDirtId = SyncCraft.mainConfiguration.getBlock("enrichedDirt.id", DefaultProps.ENRICHEDDIRT_ID);
			enrichedDirt = (new EnrichedDirt(enrichedDirtId.getInt(DefaultProps.ENRICHEDDIRT_ID),Material.ground)).setUnlocalizedName("enrichedDirt");
			
			Property jetBlockId = SyncCraft.mainConfiguration.getBlock("jetBlock.id", DefaultProps.JETBLOCK_ID);
			jetBlock = (new JetBlock(jetBlockId.getInt(DefaultProps.JETBLOCK_ID),Material.rock)).setUnlocalizedName("jetBlock");
			

			Property enrichedGravelId = SyncCraft.mainConfiguration.getBlock("enrichedGravel.id", DefaultProps.ENRICHEDGRAVEL_ID);
			enrichedGravel = (new EnrichedGravel(enrichedGravelId.getInt(DefaultProps.ENRICHEDDIRT_ID),Material.rock)).setUnlocalizedName("enrichedGravel");

			Property enrichedSandId = SyncCraft.mainConfiguration.getBlock("enrichedSand.id", DefaultProps.ENRICHEDSAND_ID);
			enrichedSand = (new EnrichedSand(enrichedSandId.getInt(DefaultProps.ENRICHEDSAND_ID),Material.sand)).setUnlocalizedName("enrichedSand");

			Property hardenedCobblestonetId = SyncCraft.mainConfiguration.getBlock("hardenedCobblestonet.id", DefaultProps.HARDENEDCOBBLESTONE_ID);
			hardenedCobblestone = (new HardenedCobblestone(hardenedCobblestonetId.getInt(DefaultProps.HARDENEDCOBBLESTONE_ID),Material.rock)).setUnlocalizedName("hardenedCobblestone");

			Property naturalGlassId = SyncCraft.mainConfiguration.getBlock("naturalGlass.id", DefaultProps.NATUARLGLASS_ID);
			naturalGlass = (new NaturalGlass(naturalGlassId.getInt(DefaultProps.HARDENEDCOBBLESTONE_ID),Material.glass, false)).setUnlocalizedName("naturalGlass");

			Property hardenedGlassId = SyncCraft.mainConfiguration.getBlock("hardenedGlass.id", DefaultProps.HARDENEDGLASS_ID);
			hardenedGlass = (new HardenedGlass(hardenedGlassId.getInt(DefaultProps.HARDENEDGLASS_ID),Material.glass, false)).setUnlocalizedName("hardenedGlass");

			
			Property syncFurnaceIdleId = SyncCraft.mainConfiguration.getBlock("syncFurnaceIdle.id", DefaultProps.SYNCFURNACEIDLE_ID);
			syncFurnaceIdleId.comment = "This is the SyncFurnace Idle.";
			syncFurnaceIdle = (new SyncFurnace(syncFurnaceIdleId.getInt(DefaultProps.SYNCFURNACEIDLE_ID), false)).setHardness(3.5F).setUnlocalizedName("Sync Furnace").setCreativeTab(SyncCraft.syncCraftTabs);

			Property syncFurnaceBurningId = SyncCraft.mainConfiguration.getBlock("syncFurnaceBurning.id", DefaultProps.SYNCFURNACEBURNING_ID);
			syncFurnaceBurningId.comment = "This is the SyncFurnace Idle.";
			syncFurnaceBurning = (new SyncFurnace(syncFurnaceBurningId.getInt(DefaultProps.SYNCFURNACEBURNING_ID), true)).setHardness(3.5F).setUnlocalizedName("Sync Furnace").setCreativeTab(SyncCraft.syncCraftTabs);
						
// Ores here
			Property argutiteOreId = SyncCraft.mainConfiguration.getBlock("argutiteOre.id", DefaultProps.ARGUTITEORE_ID);
			argutiteOre = (new ArgutiteOre(argutiteOreId.getInt(DefaultProps.HARDENEDGLASS_ID),Material.rock)).setUnlocalizedName("argutiteOre");

			Property humiteOreId = SyncCraft.mainConfiguration.getBlock("humiteOre.id", DefaultProps.HUMITEORE_ID);
			humiteOre = (new HumiteOre(humiteOreId.getInt(DefaultProps.HUMITEORE_ID),Material.rock)).setUnlocalizedName("humiteOre");

			Property cobaltId = SyncCraft.mainConfiguration.getBlock("cobalt.id", DefaultProps.COBALT_ID);
			cobalt = (new Cobalt(cobaltId.getInt(DefaultProps.COBALT_ID),Material.rock)).setUnlocalizedName("cobalt");

			Property fossilizedWoodId = SyncCraft.mainConfiguration.getBlock("fossilizedWood.id", DefaultProps.FOSSILIZEDWOOD_ID);
			fossilizedWood = (new FossilizedWood(fossilizedWoodId.getInt(DefaultProps.FOSSILIZEDWOOD_ID),Material.rock)).setUnlocalizedName("fossilizedWood");

			Property gypsumOreId = SyncCraft.mainConfiguration.getBlock("gypsumOre.id", DefaultProps.GYPSUMORE_ID);
			gypsumOre = (new GypsumOre(gypsumOreId.getInt(DefaultProps.GYPSUMORE_ID),Material.glass, false)).setUnlocalizedName("gypsumOre");

			Property kinoiteOreId = SyncCraft.mainConfiguration.getBlock("kinoiteOre.id", DefaultProps.KINOITEORE_ID);
			kinoiteOre = (new KinoiteOre(kinoiteOreId.getInt(DefaultProps.KINOITEORE_ID),Material.rock)).setUnlocalizedName("kinoiteOre");

			Property rhodiumOreId = SyncCraft.mainConfiguration.getBlock("rhodiumOre.id", DefaultProps.RHODIUMORE_ID);
			rhodiumOre = (new RhodiumOre(rhodiumOreId.getInt(DefaultProps.RHODIUMORE_ID),Material.rock)).setUnlocalizedName("rhodiumOre");

			Property zirconiumOreId = SyncCraft.mainConfiguration.getBlock("zirconiumOre.id", DefaultProps.ZIRCONIUMORE_ID);
			zirconiumOre = (new  ZirconiumOre( zirconiumOreId.getInt(DefaultProps.ZIRCONIUMORE_ID),Material.rock)).setUnlocalizedName("zirconiumOre");

// Ingots here
			Property argutiteIngotId = SyncCraft.mainConfiguration.getItem("argutiteIngot.id", DefaultProps.ARGUTITEINGOT_ID);
			argutiteIngot = (new  ArgutiteIngot( argutiteIngotId.getInt(DefaultProps.ARGUTITEINGOT_ID))).setUnlocalizedName("argutiteIngot");

			Property darkArgutiteIngotId = SyncCraft.mainConfiguration.getBlock("darkArgutiteIngot.id", DefaultProps.DARKARGUTITEINGOT_ID);
			darkArgutiteIngot = (new  DarkArgutiteIngot( darkArgutiteIngotId.getInt(DefaultProps.DARKARGUTITEINGOT_ID))).setUnlocalizedName("darkArgutiteIngot");

			MinecraftForge.EVENT_BUS.register(this);

		} finally {
			if (mainConfiguration.hasChanged()) {
				mainConfiguration.save();
			}
		}


	}



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

		GameRegistry.registerBlock(enrichedDirt, "enrichedDirt");
		GameRegistry.registerBlock(jetBlock, "jetBlock");
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


// furnace registry here
		GameRegistry.registerBlock(syncFurnaceIdle, "mod_MainClass.syncFurnaceIdle");

//Register Ingots
		GameRegistry.registerItem(argutiteIngot, "argutiteIngot");
		GameRegistry.registerItem(darkArgutiteIngot, "darkArgutiteIngot");

// Furnace Game Registry and Network GUI Handler
		GameRegistry.registerTileEntity(TileEntitySyncFurnace.class, "tileentitysyncfurnace");
		NetworkRegistry.instance().registerGuiHandler(this, GuiHandler);

// Language Registry here		
		LanguageRegistry.addName(gypsum, "Gypsum");
		LanguageRegistry.addName(jetBlock, "Jet Block");
		LanguageRegistry.addName(kinoite, "Kinoite");
		LanguageRegistry.addName(syncFurnaceIdle, "Sync Furnace (WIP)");
		LanguageRegistry.addName(enrichedDirt, "Enriched Dirt (WIP)");
		LanguageRegistry.addName(naturalGlass, "Natural Glass (WIP)");
		LanguageRegistry.addName(hardenedGlass, "Hardened Glass (WIP)");
		LanguageRegistry.addName(hardenedCobblestone, "Hardened Cobblestone");
		LanguageRegistry.addName(enrichedSand, "Enriched Sand (WIP)");
		LanguageRegistry.addName(enrichedGravel, "Enriched Gravel (WIP)");
		LanguageRegistry.addName(argutiteOre, "Argutite Ore (WIP)");
		LanguageRegistry.addName(humiteOre, "Humite Ore (WIP)");
		LanguageRegistry.addName(argutiteIngot, "Argutite Ingot (WIP)");
		LanguageRegistry.addName(darkArgutiteIngot, "Dark Argutite Ingot (WIP)");
		LanguageRegistry.addName(SyncCraft.humite, "Humite (WIP)");
		LanguageRegistry.addName(argutite, "Argutite(WIP)");
		LanguageRegistry.addName(syncHammer, "Sync Hammer (WIP)");
		LanguageRegistry.addName(gypsumOre, "Gypsum Ore");
		LanguageRegistry.addName(cobalt, "Cobalt (WIP)");
		LanguageRegistry.addName(fossilizedWood, "Fossilized Wood");
		LanguageRegistry.addName(kinoiteOre, "Kinoite Ore");
		LanguageRegistry.addName(rhodiumOre, "Rhodium Ore");
		LanguageRegistry.addName(zirconiumOre, "Zirconium Ore");
		LanguageRegistry.addName(jet, "Jet (WIP)");


// This is the Creative Tab Area
		LanguageRegistry.instance().addStringLocalization("itemGroup.synccraft_syncCraftTab", "Sync Craft");



// Harvest level
		MinecraftForge.setBlockHarvestLevel(argutiteOre, "pickaxe", 1);



		/** ItemStack Area
		 *  For ItemStack You can pass in Block/Item - Amount of Item to get - ( amount of items to get ) Damage 
		 */
		ItemStack furnaceStack = new ItemStack(SyncCraft.syncFurnaceIdle);
		ItemStack syncHammerStack = new ItemStack(SyncCraft.syncHammer);
		ItemStack darkArgutiteIngotStack = new ItemStack(SyncCraft.darkArgutiteIngot);
		ItemStack jetBlockStack = new ItemStack(SyncCraft.jetBlock);
		ItemStack coalBlockStack = new ItemStack(Block.coalBlock);
		ItemStack enrichedDirtStack = new ItemStack(SyncCraft.enrichedDirt, 1);

		/**
		 *  Recipe Area
		 */

// This is Shaped crafting	 as output the row1, row2, row3 	
		GameRegistry.addRecipe(enrichedDirtStack, "yxy", "xzx", "yxy",

				'x', Block.dirt, 
				'y', SyncCraft.kinoite,
				'z', Item.bucketWater
				);


// Sync Furnace Recipe
		GameRegistry.addRecipe(furnaceStack, "xyx", "yby", "xyx",

				'x', Block.brick,
				'y', Block.sand,
				'b', SyncCraft.argutiteIngot
				);
		
		GameRegistry.addRecipe(jetBlockStack, "xxx", "xxx", "xxx",

				'x', SyncCraft.jet
				
				);
		
		
		GameRegistry.addRecipe(coalBlockStack, "xxx", "xxx", "xxx",

				'x', SyncCraft.jetBlock
				
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
// EOF
