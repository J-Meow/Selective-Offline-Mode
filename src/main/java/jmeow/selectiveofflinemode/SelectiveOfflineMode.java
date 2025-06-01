package jmeow.selectiveofflinemode;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class SelectiveOfflineMode implements ModInitializer {
	public static final String MOD_ID = "selective-offline-mode";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Selective Offline Mode starting");
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment)
				-> dispatcher.register(CommandManager.literal("allowplayer")
				.requires(source -> source.hasPermissionLevel(2))
				.then(argument("player", StringArgumentType.greedyString())
				.executes(context -> {
					final String playerName = StringArgumentType.getString(context, "player");
					LOGGER.info("Allowing player " + playerName + " to join in the next 60 seconds");
					NameExpiry.addName(playerName);
					context.getSource().sendFeedback(() -> Text.literal("Gave player \"" + playerName + "\" permission to join in the next 60 seconds. They may continue to stay on the server after joining."), true);
					return 1;
				}))));
	}
}