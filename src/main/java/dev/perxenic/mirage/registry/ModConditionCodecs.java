package dev.perxenic.mirage.registry;

import com.mojang.serialization.MapCodec;
import dev.perxenic.mirage.Mirage;
import dev.perxenic.mirage.content.conditions.MirageConfigCondition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModConditionCodecs {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, Mirage.MODID);

    public static final Supplier<MapCodec<MirageConfigCondition>> DBV_CONFIG =
            CONDITION_CODECS.register("config", () -> MirageConfigCondition.CODEC);

    public static void register(IEventBus eventBus) {
        CONDITION_CODECS.register(eventBus);
    }
}
