package dev.perxenic.mirage;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;

@EventBusSubscriber
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ARMADILLO_UNAFRAID_ARMOR = BUILDER
            .comment("Whether armadillos are no longer afraid of mobs wearing armadillo chestplates")
            .define("armadilloUnafraidArmor", true);

    public static final ModConfigSpec.BooleanValue FADED_TERRACOTTA_SMELTING = BUILDER
            .comment("Whether glazed terracotta should be able to be smelted/blasted into faded terracotta (Reload Required)")
            .define("fadedTerracottaSmelting", true);

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
            .define("sherdConstructing", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean armadilloUnafraidArmor;
    public static boolean fadedTerracottaSmelting;
    public static boolean sherdCracking;
    public static boolean sherdRepairing;
    public static boolean sherdCrafting;
    public static boolean blankSherdConstructing;

    public static final HashMap<String, Boolean> configDict = new HashMap<>();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        armadilloUnafraidArmor = ARMADILLO_UNAFRAID_ARMOR.get();
        configDict.put("armadilloUnafraidArmor", armadilloUnafraidArmor);

        fadedTerracottaSmelting = FADED_TERRACOTTA_SMELTING.get();
        configDict.put("fadedTerracottaSmelting", fadedTerracottaSmelting);

        sherdCracking = SHERD_CRACKING.get();
        configDict.put("sherdCracking", sherdCracking);

        sherdRepairing = SHERD_REPAIRING.get();
        configDict.put("sherdRepairing", sherdRepairing);

        sherdCrafting = SHERD_CRAFTING.get();
        configDict.put("sherdCrafting", sherdCrafting);

        blankSherdConstructing = BLANK_SHERD_CONSTRUCTING.get();
        configDict.put("blankSherdConstructing", blankSherdConstructing);
    }
}
