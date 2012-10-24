package com.theharofreak.listener;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.theharofreak.commandalert.CommandAlert;


public class PlayerListener implements Listener {
	
	private CommandAlert plugin;
	public PlayerListener (CommandAlert plugin) {
		this.plugin = plugin;
	}
	
	
	/* ChatColors */
	ChatColor gold = ChatColor.GOLD;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor red = ChatColor.RED;
	
	@EventHandler
	public void blockCommands (PlayerCommandPreprocessEvent event) {
		
		/* Getting player and command */
		Player player = event.getPlayer();
		String cmd = event.getMessage();
		
		/* Check if player has permission */
		if(player.hasPermission("commandalert.bypass"))
			return;
		
		/* Get stringlist from config */
		List<String> blockedCommands = plugin.getConfig().getStringList("CommandAlert.BlockedCommands");
		boolean isBlocked = false;
		
		/* Check if config contains this command */
		for(String blockedCommand : blockedCommands) {
			if(cmd.toLowerCase().startsWith(blockedCommand.toLowerCase())) {
				isBlocked = true;
				break;
			}
		}
		
		if(isBlocked == false)
			return;
		
		String msg = plugin.getConfig().getString("CommandAlert.Message");
		msg = ChatColor.translateAlternateColorCodes('&', msg);
		player.sendMessage(plugin.sc + " " + msg);
		
		/* Sending operators a warn message */
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			if(p.hasPermission("commandalert.display")) {
				p.sendMessage(plugin.sc + gold + player.getName() + yellow + " used command " + red + cmd);
			}
		}
		
		/* Checking boolean from config */
		if(plugin.getConfig().getBoolean("CommandAlert.Block") == false)
			return;
		
		event.setCancelled(true);
	}

}
