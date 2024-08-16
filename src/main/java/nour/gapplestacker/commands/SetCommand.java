package nour.gapplestacker.commands;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.net.command.Command;
import net.minecraft.core.net.command.CommandHandler;
import net.minecraft.core.net.command.CommandSender;
import nour.gapplestacker.GappleStacker;

import java.io.File;

import static nour.gapplestacker.GappleStacker.gapStackSize;

public class SetCommand extends Command {
	public SetCommand() {
		super("gapplestack","gsm");
	}

	public boolean execute(CommandHandler handler, CommandSender sender, String[] args) {
		if (args.length != 1) {
			return false;
		}
		int size = Integer.parseInt(args[0]);
		if (sender.isPlayer()) {
			EntityPlayer p = sender.getPlayer();
			p.sendMessage("§4Golden apples now stack up to " + size + ".");
		} else {
			GappleStacker.LOGGER.info("Golden apples now stack up to {}.", size);
		}
		gapStackSize = size; // THIS MUST GO BEFORE THE CODE IN THE NEXT FEW LINES
		if ((new File(GappleStacker.CFG_FILE_PATH)).delete()) { // Delete the file, or try to, if successful then make a new file with new values
			GappleStacker.getConfig(); // this calls the that makes the new file with new values
		}
		Item.foodAppleGold.setMaxStackSize(size);
		return true;
	}

	public boolean opRequired(String[] args) {
		return true;
	}

	public void sendCommandSyntax(CommandHandler handler, CommandSender sender) {
		sender.sendMessage("§3/gapplestack §4<amount 1-64>");
		sender.sendMessage("§5Set the max stack size for golden apples");
	}
}
