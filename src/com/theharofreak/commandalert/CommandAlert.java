package com.theharofreak.commandalert;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.theharofreak.listener.PlayerListener;
import com.theharofreak.util.LoadConfigFile;


public class CommandAlert extends JavaPlugin {
	
	//Strings
	public String prefix = "[CommandAlert] ";
	public String sc = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "CommandAlert" + ChatColor.WHITE + "] ";
	
	//Classes
	LoadConfigFile config = new LoadConfigFile(this);
	public PlayerListener playerListener;
	
	@Override
	public void onDisable() {
		//PrintStuff
		System.out.println(prefix + "Plugin disabled.");
	}
	
	@Override
	public void onEnable() {		
		//Config
		config.createConfig();
		
		//Events
		playerListener = new PlayerListener(this);
		registerEvents();
		
		//PrintStuff
		System.out.println(prefix + "Plugin enabled.");
	}
	
	public void registerEvents() {
		getServer().getPluginManager().registerEvents(playerListener, this);
	}

}
