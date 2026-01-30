package dev.perxenic.mirage.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

import static dev.perxenic.mirage.Mirage.MODID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageDecoratedPotPatterns {
    public static final Map<ResourceLocation, ResourceKey<DecoratedPotPattern>> EDITABLE_ITEM_TO_POT_TEXTURE = new HashMap<>();

    private static final DeferredRegister<DecoratedPotPattern> PATTERNS =
            DeferredRegister.create(BuiltInRegistries.DECORATED_POT_PATTERN, MODID);

    public static final Holder<DecoratedPotPattern> SHATTERED = addPotPattern(ModItems.SHATTERED_POTTERY_SHERD.getId(), "shattered");
    public static final Holder<DecoratedPotPattern> BLANK = addPotPattern(ModItems.BLANK_POTTERY_SHERD.getId(), "blank");
    public static final Holder<DecoratedPotPattern> ARMADILLO = addPotPattern(ModItems.ARMADILLO_POTTERY_SHERD.getId(), "armadillo");
    public static final Holder<DecoratedPotPattern> CACTUS = addPotPattern(ModItems.CACTUS_POTTERY_SHERD.getId(), "cactus");

    public static void register(IEventBus eventBus) {
        PATTERNS.register(eventBus);
    }

    private static Holder<DecoratedPotPattern> addPotPattern(ResourceLocation item, String name) {
        Holder<DecoratedPotPattern> pattern = PATTERNS.register(name,
                () -> new DecoratedPotPattern(mirageLoc(name+"_pottery_pattern"))
        );
        EDITABLE_ITEM_TO_POT_TEXTURE.put(item, pattern.getKey());
        return pattern;
    }
}
