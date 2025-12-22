package dev.perxenic.mirage;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ARMADILLO_UNAFRAID_ARMOR = BUILDER
            .comment("Whether armadillos are no longer afraid of mobs wearing armadillo chestplates")
            .define("armadilloUnafraidArmor", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean armadilloUnafraidArmor;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        armadilloUnafraidArmor = ARMADILLO_UNAFRAID_ARMOR.get();
    }
}
