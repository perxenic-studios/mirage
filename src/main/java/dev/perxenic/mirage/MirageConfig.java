package dev.perxenic.mirage;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;

@EventBusSubscriber
public class MirageConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec SPEC;

    public static final HashMap<String, ModConfigSpec.ConfigValue<?>> specDict = new HashMap<>();
    public static final HashMap<String, Boolean> booleanDict = new HashMap<>();

    static void addBooleanConfig(String name, String comment, boolean defaultValue) {
        specDict.put(name, BUILDER.comment(comment).define(name, defaultValue));
        booleanDict.put(name, defaultValue); // Initialize boolean dict with default values
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        booleanDict.forEach((name, defaultValue) -> {
            // If for some reason the booleanDict contains a key that is not supposed to be a boolean, throw an error
            assert specDict.get(name).get() instanceof Boolean : "Boolean dict contains a non-boolean config";

            booleanDict.put(name, (Boolean) specDict.get(name).get());
        });
    }

    static void register(ModContainer modContainer) {
        addBooleanConfig(
                "armadilloUnafraidArmor",
                "Whether armadillos are no longer afraid of mobs wearing armadillo chestplates",
                true
        );
        addBooleanConfig(
                "armadilloArmorTrimming",
                "Whether armadillo armor can have armor trims applied to it (Reload Required)",
                true
        );
        addBooleanConfig(
                "fadedTerracottaSmelting",
                "Whether glazed terracotta should be able to be smelted/blasted into faded terracotta (Reload Required)",
                true
        );
        addBooleanConfig(
                "fadedTerracottaCreative",
                "Whether faded terracotta should be available in the creative inventory (Reload Required)",
                true
        );
        addBooleanConfig(
                "sherdCracking",
                "Whether sherds should be able to be smelted into cracked sherds (Reload Required)",
                true
        );
        addBooleanConfig(
                "sherdRepairing",
                "Whether blank sherds should be able to be crafted from cracked sherds (Reload Required)",
                true
        );
        addBooleanConfig(
                "sherdCrafting",
                "Whether sherds should be able to be crafted from blank sherds (Reload Required)",
                false
        );
        addBooleanConfig(
                "blankSherdConstructing",
                "Whether blank sherds should be able to be crafted from basic materials (Reload Required)",
                false
        );
        addBooleanConfig(
                "sherdConstructing",
                "Whether any sherd should be able to be crafted from basic materials (Reload Required)",
                false
        );
        addBooleanConfig(
                "desertRockSpawning",
                "Whether desert rocks should spawn in deserts (Reload Required)",
                true
        );
        addBooleanConfig(
                "sandyStoneGeneration",
                "Whether patches of sandy stone should spawn under the desert (Reload Required)",
                true
        );
        addBooleanConfig(
                "sandierStonePatches",
                "Whether patches of sandy stone should sometimes contain sand (Reload Required)",
                true
        );
        addBooleanConfig(
                "undergroundDryGrass",
                "Whether patches of sandy stone should sometimes have dry grass growing on them (Reload Required)",
                true
        );
        addBooleanConfig(
                "sandyStoneCrafting",
                "Whether sandy stone can be crafted from stone and sand (Reload Required)",
                true
        );

        SPEC = BUILDER.build();
        modContainer.registerConfig(ModConfig.Type.COMMON, SPEC);
    }
}
