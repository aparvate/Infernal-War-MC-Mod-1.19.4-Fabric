package net.fabricmc.infernal_war.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.infernal_war.common.item.RegisterItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfernalWar implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("infernalwar");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		RegisterItems.register();
		InfernalWarGroup.modifyEntryEvents();

		LOGGER.info("The Piglins will rise...");
	}
}
