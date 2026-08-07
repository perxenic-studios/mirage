package dev.perxenic.mirage;

import dev.perxenic.mirage.registry.*;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

@Mod(Mirage.MIRAGE_ID)
public class Mirage {
    public static final String MIRAGE_ID = "mirage";
    public static final Logger MIRAGE_LOGGER = LogUtils.getLogger();

    public Mirage(IEventBus modEventBus, ModContainer modContainer) {
        MirageBlocks.register(modEventBus);
        MirageConditionCodecs.register(modEventBus);
        MirageFeatures.register(modEventBus);
        MirageItems.register(modEventBus);
        MirageStructureTypes.register(modEventBus);
        MirageDecoratedPotPatterns.register(modEventBus);

        modEventBus.addListener(MirageBlocks::onBlockEntityTypeAddBlocksEvent);
        modEventBus.addListener(MirageItems::addCreative);

        MirageConfig.register(modContainer);
    }

    public static Identifier mcLoc(String path) {
        return Identifier.withDefaultNamespace(path);
    }

    public static Identifier mirageLoc(String path) {
        return Identifier.fromNamespaceAndPath(MIRAGE_ID, path);
    }
}
