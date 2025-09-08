package de.lighty.luckymon;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Luckymon implements ModInitializer {
	public static final String MOD_ID = "luckymon";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    // Why is Mod
    public static final Block LUCKY_BLOCK = new LuckyBlock(AbstractBlock.Settings.create().nonOpaque().strength(2.0f));

	@Override
	public void onInitialize() {

		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
        LuckyBlockActions.Register();

        Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "lucky_block"), LUCKY_BLOCK);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "lucky_block"), new BlockItem(LUCKY_BLOCK, new Item.Settings()));
	}


}