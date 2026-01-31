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

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean armadilloUnafraidArmor;
    public static boolean fadedTerracottaSmelting;
    public static boolean sherdCracking;

    public static final HashMap<String, Boolean> configDict = new HashMap<>();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        armadilloUnafraidArmor = ARMADILLO_UNAFRAID_ARMOR.get();
        configDict.put("armadilloUnafraidArmor", armadilloUnafraidArmor);

        fadedTerracottaSmelting = FADED_TERRACOTTA_SMELTING.get();
        configDict.put("fadedTerracottaSmelting", fadedTerracottaSmelting);

        sherdCracking = SHERD_CRACKING.get();
        configDict.put("sherdCracking", sherdCracking);
    }
}
