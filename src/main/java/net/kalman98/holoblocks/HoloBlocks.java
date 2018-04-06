package net.kalman98.holoblocks;

import org.apache.logging.log4j.Logger;

import net.kalman98.holoblocks.config.Config;
import net.kalman98.holoblocks.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HoloBlocks.MODID,
	 name = HoloBlocks.NAME,
	 version = HoloBlocks.VERSION,
	 acceptedMinecraftVersions = "[1.12.2]",
	 useMetadata = true,
	 guiFactory = "com.team.kalstuff.config.KalStuffGuiFactory")
public class HoloBlocks
{
	public static final String MODID = "kalstuff";
	public static final String NAME = "KalStuff";
	public static final String VERSION = "0.8.0d";

	@SidedProxy(clientSide = "com.team.kalstuff.proxy.ClientProxy",
			serverSide = "com.team.kalstuff.proxy.ServerProxy")
	public static CommonProxy proxy;

	// The instance of the mod that Forge uses.
	@Mod.Instance
	public static HoloBlocks instance;

	public static Logger logger;

	public static Configuration config;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		logger = e.getModLog();
		Config.initialize(e, e.getSuggestedConfigurationFile());
		config = Config.getConfig();
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		if (config.hasChanged())
		{
			config.save();
		}
		proxy.postInit(e);
	}
}