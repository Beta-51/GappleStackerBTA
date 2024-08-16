package nour.gapplestacker;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.io.File;


public class GappleStacker implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "gapplestacker";
	public static final String CFG_FILE_PATH = FabricLoader.getInstance().getGameDir().toString() + "/config/"+MOD_ID+".cfg"; // makes things easier.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static TomlConfigHandler CONFIG;
	public static int gapStackSize;

	public static void getConfig() { // creates a new file once the old one is deleted.
		Toml toml = new Toml();
		toml.addEntry("GappleMaxStackSize", "Golden apple max stack size (Default = 4)", gapStackSize);
		CONFIG = new TomlConfigHandler(MOD_ID, toml);
		gapStackSize = CONFIG.getInt("GappleMaxStackSize"); // This is important, it sets the variable to what it needs to be. If this is 0, things go wrong.
	}



    @Override
    public void onInitialize() {
        LOGGER.info("Gapple Stacker initialized.");
    }



	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

		if (!(new File(CFG_FILE_PATH)).exists()) { // checks for the config file, if it exists then just load the one we have, else create a factory new one.
			Toml toml = new Toml();
			toml.addEntry("GappleMaxStackSize", "Golden apple max stack size (Default = 4)", 4);
			CONFIG = new TomlConfigHandler(MOD_ID, toml);
		} else {
			getConfig();
		}
		Item.foodAppleGold.setMaxStackSize(gapStackSize);
	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
