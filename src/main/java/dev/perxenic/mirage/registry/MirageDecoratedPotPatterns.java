package dev.perxenic.mirage.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageDecoratedPotPatterns {
    public static final Map<Identifier, ResourceKey<DecoratedPotPattern>> MIRAGE_ITEM_TO_POT_TEXTURE_MAP = new HashMap<>();

    private static final DeferredRegister<DecoratedPotPattern> PATTERNS =
            DeferredRegister.create(BuiltInRegistries.DECORATED_POT_PATTERN, MIRAGE_ID);

    public static final Holder<DecoratedPotPattern> CRACKED = addPotPattern(MirageItems.CRACKED_POTTERY_SHERD.getId(), "cracked");
    public static final Holder<DecoratedPotPattern> BLANK = addPotPattern(MirageItems.BLANK_POTTERY_SHERD.getId(), "blank");
    public static final Holder<DecoratedPotPattern> HIDE = addPotPattern(MirageItems.HIDE_POTTERY_SHERD.getId(), "hide");
    public static final Holder<DecoratedPotPattern> BARREN = addPotPattern(MirageItems.BARREN_POTTERY_SHERD.getId(), "barren");
    public static final Holder<DecoratedPotPattern> SANCTUARY = addPotPattern(MirageItems.SANCTUARY_POTTERY_SHERD.getId(), "sanctuary");

    public static void register(IEventBus eventBus) {
        PATTERNS.register(eventBus);
    }

    private static Holder<DecoratedPotPattern> addPotPattern(Identifier item, String name) {
        Holder<DecoratedPotPattern> pattern = PATTERNS.register(name,
                () -> new DecoratedPotPattern(mirageLoc(name+"_pottery_pattern"))
        );
        MIRAGE_ITEM_TO_POT_TEXTURE_MAP.put(item, pattern.getKey());
        return pattern;
    }
}
