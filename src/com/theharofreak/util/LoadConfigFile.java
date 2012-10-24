package com.theharofreak.util;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.theharofreak.commandalert.CommandAlert;


public class LoadConfigFile {
	
	private CommandAlert plugin;
	public LoadConfigFile(CommandAlert plugin) {
		this.plugin = plugin;
	}
	
	public void createConfig() {
		FileConfiguration config;
		if(new File("plugins/CommandAlert/config.yml").exists()) {
			config = plugin.getConfig();
			System.out.println(plugin.prefix + "Config successfully loaded.");
			
		} else {
			plugin.saveDefaultConfig();
			config = plugin.getConfig();
			System.out.println(plugin.prefix + "No config found. Creating one ...");
			System.out.println(plugin.prefix + "Config successfully loaded.");
		}
	}

}
