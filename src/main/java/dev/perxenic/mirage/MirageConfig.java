package dev.perxenic.mirage;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;

@EventBusSubscriber
public class MirageConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ARMADILLO_UNAFRAID_ARMOR = BUILDER
            .comment("Whether armadillos are no longer afraid of mobs wearing armadillo chestplates")
            .define("armadilloUnafraidArmor", true);

    public static final ModConfigSpec.BooleanValue ARMADILLO_ARMOR_TRIMMING = BUILDER
            .comment("Whether armadillo armor can have armor trims applied to it (Reload Required)")
            .define("armadilloArmorTrimming", true);

    public static final ModConfigSpec.BooleanValue FADED_TERRACOTTA_SMELTING = BUILDER
            .comment("Whether glazed terracotta should be able to be smelted/blasted into faded terracotta (Reload Required)")
            .define("fadedTerracottaSmelting", true);

    public static final ModConfigSpec.BooleanValue FADED_TERRACOTTA_CREATIVE = BUILDER
            .comment("Whether faded terracotta should be available in the creative inventory (Reload Required)")
            .define("fadedTerracottaCreative", true);

    public static final ModConfigSpec.BooleanValue SHERD_CRACKING = BUILDER
            .comment("Whether sherds should be able to be smelted into cracked sherds (Reload Required)")
            .define("sherdCracking", true);

    public static final ModConfigSpec.BooleanValue SHERD_REPAIRING = BUILDER
            .comment("Whether blank sherds should be able to be crafted from cracked sherds (Reload Required)")
            .define("sherdRepairing", true);

    public static final ModConfigSpec.BooleanValue SHERD_CRAFTING = BUILDER
            .comment("Whether sherds should be able to be crafted from blank sherds (Reload Required)")
            .define("sherdCrafting", false);

    public static final ModConfigSpec.BooleanValue BLANK_SHERD_CONSTRUCTING = BUILDER
            .comment("Whether blank sherds should be able to be crafted from basic materials (Reload Required)")
            .define("blankSherdConstructing", false);

    public static final ModConfigSpec.BooleanValue SHERD_CONSTRUCTING = BUILDER
            .comment("Whether any sherd should be able to be crafted from basic materials (Reload Required)")
            .define("sherdConstructing", false);

    public static final ModConfigSpec.BooleanValue DESERT_ROCK_SPAWNING = BUILDER
            .comment("Whether desert rocks should spawn in deserts (Reload Required)")
            .define("desertRockSpawning", true);

    public static final ModConfigSpec.BooleanValue SANDY_STONE_GENERATION = BUILDER
            .comment("Whether patches of sandy stone should spawn under the desert (Reload Required)")
            .define("sandyStoneGeneration", true);

    public static final ModConfigSpec.BooleanValue SANDIER_STONE_PATCHES = BUILDER
            .comment("Whether patches of sandy stone should sometimes contain sand (Reload Required)")
            .define("sandierStonePatches", true);

    public static final ModConfigSpec.BooleanValue UNDERGROUND_DRY_GRASS = BUILDER
            .comment("Whether patches of sandy stone should sometimes have dry grass growing on them (Reload Required)")
            .define("undergroundDryGrass", true);

    public static final ModConfigSpec.BooleanValue SANDY_STONE_CRAFTING = BUILDER
            .comment("Whether sandy stone can be crafted from stone and sand (Reload Required)")
            .define("sandyStoneCrafting", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static final HashMap<String, Boolean> configDict = new HashMap<>();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        configDict.put("armadilloUnafraidArmor", ARMADILLO_UNAFRAID_ARMOR.get());
        configDict.put("armadilloArmorTrimming", ARMADILLO_ARMOR_TRIMMING.get());
        configDict.put("fadedTerracottaSmelting", FADED_TERRACOTTA_SMELTING.get());
        configDict.put("fadedTerracottaCreative", FADED_TERRACOTTA_CREATIVE.get());
        configDict.put("sherdCracking", SHERD_CRACKING.get());
        configDict.put("sherdRepairing", SHERD_REPAIRING.get());
        configDict.put("sherdCrafting", SHERD_CRAFTING.get());
        configDict.put("blankSherdConstructing", BLANK_SHERD_CONSTRUCTING.get());
        configDict.put("sherdConstructing", SHERD_CONSTRUCTING.get());
        configDict.put("desertRockSpawning", DESERT_ROCK_SPAWNING.get());
        configDict.put("sandyStoneGeneration", SANDY_STONE_GENERATION.get());
        configDict.put("sandierStonePatches", SANDIER_STONE_PATCHES.get());
        configDict.put("undergroundDryGrass", UNDERGROUND_DRY_GRASS.get());
        configDict.put("sandyStoneCrafting", SANDY_STONE_CRAFTING.get());
    }
}
