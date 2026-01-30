package dev.perxenic.mirage.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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

    public static final Holder<DecoratedPotPattern> CRACKED = addPotPattern(ModItems.CRACKED_POTTERY_SHERD.getId(), "cracked");
    public static final Holder<DecoratedPotPattern> BLANK = addPotPattern(ModItems.BLANK_POTTERY_SHERD.getId(), "blank");
    public static final Holder<DecoratedPotPattern> HIDE = addPotPattern(ModItems.HIDE_POTTERY_SHERD.getId(), "hide");
    public static final Holder<DecoratedPotPattern> BARREN = addPotPattern(ModItems.BARREN_POTTERY_SHERD.getId(), "barren");

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
