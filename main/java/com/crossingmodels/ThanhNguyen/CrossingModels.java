package com.crossingmodels.ThanhNguyen;

import java.io.File;

import com.crossingmodels.ThanhNguyen.network.packets.PacketRequestUpdateEnergy;
import com.crossingmodels.ThanhNguyen.network.packets.PacketUpdateEnergy;
import com.crossingmodels.ThanhNguyen.proxy.CommonProxy;
import com.crossingmodels.ThanhNguyen.tabs.TutorialTab;
import com.crossingmodels.ThanhNguyen.util.Reference;
import com.crossingmodels.ThanhNguyen.util.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class CrossingModels 
{
	public static SimpleNetworkWrapper network;
	
	public static File config;
	
	@Instance
	public static CrossingModels instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final CreativeTabs TUTORIALMODTAB = new TutorialTab("tutorialtab");
	
	static
	{
		FluidRegistry.enableUniversalBucket();
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		RegistryHandler.preInitRegistries(event);
		network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
		network.registerMessage(PacketUpdateEnergy.Handler.class, PacketUpdateEnergy.class, 0, Side.CLIENT);
		network.registerMessage(PacketRequestUpdateEnergy.Handler.class, PacketRequestUpdateEnergy.class, 1, Side.SERVER);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		RegistryHandler.initRegistries();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		RegistryHandler.postInitRegistries();
	}
	
	@EventHandler
	public void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
	}
}
}
