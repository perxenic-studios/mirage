package dev.perxenic.mirage.registry;

import dev.perxenic.mirage.content.RandomLandStructure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;

public class MirageStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(BuiltInRegistries.STRUCTURE_TYPE, MIRAGE_ID);

    public static final DeferredHolder<StructureType<?>,StructureType<RandomLandStructure>> RANDOM_LAND_STRUCTURE = STRUCTURE_TYPES.register("random_land", () -> () -> RandomLandStructure.CODEC);

    public static void register(IEventBus eventBus) {
        STRUCTURE_TYPES.register(eventBus);
    }
}
