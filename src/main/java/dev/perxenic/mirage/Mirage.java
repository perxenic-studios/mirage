package dev.perxenic.mirage;

import dev.perxenic.mirage.registry.ModArmorMaterials;
import dev.perxenic.mirage.registry.ModItems;
import dev.perxenic.mirage.registry.ModStructureTypes;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

@Mod(Mirage.MODID)
public class Mirage {
    public static final String MODID = "mirage";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Mirage(IEventBus modEventBus, ModContainer modContainer) {
        ModArmorMaterials.register(modEventBus);
        ModItems.register(modEventBus);
        ModStructureTypes.register(modEventBus);

        modEventBus.addListener(ModItems::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
