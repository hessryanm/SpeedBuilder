package org.hessminecraft.noip.speedbuilder;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import net.jrbudda.builder.*;
import net.citizensnpcs.*;
import net.citizensnpcs.api.*;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.Trait;

public final class SpeedBuilder extends JavaPlugin {
	
	private String schematic = "";
	private String options = "";
	private boolean ran = false;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		 
		if(cmd.getName().equalsIgnoreCase("speedbuilder")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
				return true;
			}
			Player player = (Player) sender;
			if (args.length < 1){
				player.sendMessage(ChatColor.RED + "Use /speedbuilder help for command reference");
				return true;
			}
			if (args[0].equalsIgnoreCase("help")){
				player.sendMessage(ChatColor.GOLD + "-------- SpeedBuilder --------");
				player.sendMessage("");
				player.sendMessage(ChatColor.GOLD + "/speedbuilder load [schematic]");
				player.sendMessage(ChatColor.GOLD + "    Choose a schematic to build");
				player.sendMessage(ChatColor.GOLD + "/speedbuilder options (ignoreair) (ignorewater) (excavate) (yoffset:#)");
				player.sendMessage(ChatColor.GOLD + "    Choose builder options to use for build.  If not set before build, will use no options.");
				player.sendMessage(ChatColor.GOLD + "/speedbuilder build");
				player.sendMessage(ChatColor.GOLD + "    Build the loaded schematic with your location as the origin.  DO NOT MOVE after issuing this command.");
				player.sendMessage(ChatColor.GOLD + "/speedbuilder clear");
				player.sendMessage(ChatColor.GOLD + "    Run after build is complete.");
				player.sendMessage(ChatColor.GOLD + "/speedbuilder cancel");
				player.sendMessage(ChatColor.GOLD + "    Stops a running build.");
				return true;
			}
			if (args[0].equalsIgnoreCase("load")){
				if (args.length <= 1){
					player.sendMessage(ChatColor.RED + "You must specify a schematic");
					return true;
				}
				String schem = args[1];
				player.chat("/npc create asdfl1298jdn --trait builder");
				player.chat("/builder load "+schem);
				player.chat("/npc rem");
				schematic = schem;
				return true;
			}
			if (args[0].equalsIgnoreCase("options")){
				String[] opt = Arrays.copyOfRange(args, 1, args.length);
				options = stringify(opt);
				player.sendMessage(ChatColor.GREEN + "Options stored");
				return true;
			}
			if (args[0].equalsIgnoreCase("build")){
				if (schematic.equals("")){
					player.sendMessage(ChatColor.RED + "You must load a schematic first (/speedbuilder load [schematic]).");
					return true;
				}
				player.sendMessage(ChatColor.RED + "Build started.  DO NOT MOVE until told you can do so.");
				
				player.chat("/npc create aaaaaaaa --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");
				
				player.chat("/npc create bbbbbbbb --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");
				
				player.chat("/npc create cccccccc --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");
				
				player.chat("/npc create dddddddd --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");
				
				player.chat("/npc create eeeeeeee --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");
				
				player.chat("/npc create ffffffff --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");
				
				player.chat("/npc create gggggggg --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");
				
				player.chat("/npc create hhhhhhhh --trait builder");
				player.chat("/npc speed 99");
				player.chat("/builder timeout .1");
				player.chat("/builder load "+schematic);
				player.chat("/builder origin me");

				player.chat("/builder build layers:2 "+options);
				
				player.chat("/npc select gggggggg");
				player.chat("/builder build layers:17 "+options);
				
				player.chat("/npc select ffffffff");
				player.chat("/builder build layers:2 reversespiral "+options);
				
				player.chat("/npc select eeeeeeee");
				player.chat("/builder build layers:17 reversespiral "+options);
				
				player.chat("/npc select dddddddd");
				player.chat("/builder build layers:2 reverselinear "+options);
				
				player.chat("/npc select cccccccc");
				player.chat("/builder build layers:17 reverselinear "+options);
				
				player.chat("/npc select bbbbbbbb");
				player.chat("/builder build layers:2 linear "+options);
				
				player.chat("/npc select aaaaaaaa");
				player.chat("/builder build layers:17 linear "+options);
				ran = true;
				
				player.sendMessage(ChatColor.GREEN + "You are now free to move about the cabin.");
				return true;
			}
			if (args[0].equalsIgnoreCase("clear")){
				schematic = "";
				options = "";
				if (ran){
					player.chat("/npc select aaaaaaaa");
					player.chat("/npc rem");
					player.chat("/npc select bbbbbbbb");
					player.chat("/npc rem");
					player.chat("/npc select cccccccc");
					player.chat("/npc rem");
					player.chat("/npc select dddddddd");
					player.chat("/npc rem");
					player.chat("/npc select eeeeeeee");
					player.chat("/npc rem");
					player.chat("/npc select ffffffff");
					player.chat("/npc rem");
					player.chat("/npc select gggggggg");
					player.chat("/npc rem");
					player.chat("/npc select hhhhhhhh");
					player.chat("/npc rem");
				}
				ran = false;
				return true;
			}
			if (args[0].equalsIgnoreCase("cancel")){
				if (!ran) return true;
				player.chat("/npc select aaaaaaaa");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				player.chat("/npc select bbbbbbbb");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				player.chat("/npc select cccccccc");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				player.chat("/npc select dddddddd");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				player.chat("/npc select eeeeeeee");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				player.chat("/npc select ffffffff");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				player.chat("/npc select gggggggg");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				player.chat("/npc select hhhhhhhh");
				player.chat("/builder cancel");
				player.chat("/npc rem");
				ran = false;
				player.sendMessage(ChatColor.GREEN + "Build cancelled.");
				return true;
			}
			if (args[0].equalsIgnoreCase("new")){
				NPC first = spawnNPC("aaaaaaaa");
				first.addTrait(BuilderTrait.class);
				Builder builder = new Builder();
				BuilderTrait trait = builder.getBuilder(first);
				if (trait != null){
					player.sendMessage("We have a builder!");
				}else{
					player.sendMessage("No Dice.");
				}
				
				return true;
			}
		}
		return false;
	}
	
	private String stringify(String[] options){
		String toReturn = "";
		for (int i = 0; i < options.length; i++){
			if (i > 0){
				toReturn += " ";
			}
			toReturn += options[i];
		}
		return toReturn;
	}
	
	private NPC spawnNPC(String name) {
		return CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
	}
}
