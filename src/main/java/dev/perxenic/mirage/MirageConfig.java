package dev.perxenic.mirage;

import com.google.common.collect.Lists;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.ReloadCommand;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.WorldData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.resource.ResourcePackLoader;

import java.util.Collection;
import java.util.HashMap;

@EventBusSubscriber
public class MirageConfig {
    private static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

    public static final HashMap<String, ModConfigSpec.ConfigValue<?>> specDict = new HashMap<>();
    public static final HashMap<String, Boolean> commonBoolDict = new HashMap<>();
    public static final HashMap<String, Boolean> serverBoolDict = new HashMap<>();
    public static final HashMap<String, Boolean> clientBoolDict = new HashMap<>();

    static void addCommonBooleanConfig(String name, String comment, boolean defaultValue) {
        specDict.put(name, COMMON_BUILDER.comment(comment).define(name, defaultValue));
        commonBoolDict.put(name, defaultValue); // Initialize boolean dict with default values
    }

    static void addServerBooleanConfig(String name, String comment, boolean defaultValue) {
        specDict.put(name, SERVER_BUILDER.comment(comment).define(name, defaultValue));
        serverBoolDict.put(name, defaultValue); // Initialize boolean dict with default values
    }

    static void addClientBooleanConfig(String name, String comment, boolean defaultValue) {
        // No-op on dedicated server
        if (FMLEnvironment.getDist() == Dist.DEDICATED_SERVER) return;

        specDict.put(name, CLIENT_BUILDER.comment(comment).define(name, defaultValue));
        clientBoolDict.put(name, defaultValue); // Initialize boolean dict with default values
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        // Only run on load or reload
        if (event instanceof ModConfigEvent.Unloading) return;

        ModConfig config = event.getConfig();
        if (config.getType() == ModConfig.Type.COMMON) {
            commonBoolDict.forEach((name, defaultValue) -> {
                // If for some reason the booleanDict contains a key that is not supposed to be a boolean, throw an error
                assert specDict.get(name).get() instanceof Boolean : "Boolean dict contains a non-boolean config";

                commonBoolDict.put(name, (Boolean) specDict.get(name).get());
            });
        }
        else if (config.getType() == ModConfig.Type.CLIENT) {
            clientBoolDict.forEach((name, defaultValue) -> {
                // If for some reason the booleanDict contains a key that is not supposed to be a boolean, throw an error
                assert specDict.get(name).get() instanceof Boolean : "Boolean dict contains a non-boolean config";

                clientBoolDict.put(name, (Boolean) specDict.get(name).get());
            });
        }
        else if (config.getType() == ModConfig.Type.SERVER) {
            serverBoolDict.forEach((name, defaultValue) -> {
                // If for some reason the booleanDict contains a key that is not supposed to be a boolean, throw an error
                assert specDict.get(name).get() instanceof Boolean : "Boolean dict contains a non-boolean config";

                serverBoolDict.put(name, (Boolean) specDict.get(name).get());
            });
        }
    }

    // Force server reload to make sure server config is applied to data load conditions
    // Ugly hack, but is required to make sure crafting configs can be applied per-world
    @SubscribeEvent
    static void onServerStart(final ServerStartedEvent event) {
        MinecraftServer server = event.getServer();
        try {
            server.getCommands().getDispatcher().execute("reload", server.createCommandSourceStack());
        } catch (CommandSyntaxException e) {
            throw new RuntimeException("Reload command appears to not exist", e);
        }
    }

    static void register(ModContainer modContainer) {
        // This is common because mods may read from creative tabs
        addCommonBooleanConfig(
                "fadedTerracottaCreative",
                "Whether faded terracotta should be available in the creative inventory (Reload Required)",
                true
        );

        addServerBooleanConfig(
                "armadilloArmorTrimming",
                "Whether armadillo armor can have armor trims applied to it (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "fadedTerracottaSmelting",
                "Whether glazed terracotta should be able to be smelted/blasted into faded terracotta (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "armadilloUnafraidArmor",
                "Whether armadillos are no longer afraid of mobs wearing armadillo chestplates",
                true
        );
        addServerBooleanConfig(
                "sherdCracking",
                "Whether sherds should be able to be smelted into cracked sherds (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "sherdRepairing",
                "Whether blank sherds should be able to be crafted from cracked sherds (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "sherdCrafting",
                "Whether sherds should be able to be crafted from blank sherds (Reload Required)",
                false
        );
        addServerBooleanConfig(
                "blankSherdConstructing",
                "Whether blank sherds should be able to be crafted from basic materials (Reload Required)",
                false
        );
        addServerBooleanConfig(
                "sherdConstructing",
                "Whether any sherd should be able to be crafted from basic materials (Reload Required)",
                false
        );
        addServerBooleanConfig(
                "desertRockSpawning",
                "Whether desert rocks should spawn in deserts (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "sandyStoneGeneration",
                "Whether patches of sandy stone should spawn under the desert (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "sandierStonePatches",
                "Whether patches of sandy stone should sometimes contain sand (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "undergroundDryGrass",
                "Whether patches of sandy stone should sometimes have dry grass growing on them (Reload Required)",
                true
        );
        addServerBooleanConfig(
                "sandyStoneCrafting",
                "Whether sandy stone can be crafted from stone and sand (Reload Required)",
                true
        );

        modContainer.registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
        modContainer.registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
        modContainer.registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }
}
