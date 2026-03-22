package dev.perxenic.mirage.content;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.item.equipment.trim.TrimPatterns;

import java.util.List;

import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageArmorTrimPatterns {
    public static final List<ResourceKey<TrimPattern>> VANILLA_PATTERNS = List.of(
            TrimPatterns.SENTRY,
            TrimPatterns.DUNE,
            TrimPatterns.COAST,
            TrimPatterns.WILD,
            TrimPatterns.WARD,
            TrimPatterns.EYE,
            TrimPatterns.VEX,
            TrimPatterns.TIDE,
            TrimPatterns.SNOUT,
            TrimPatterns.RIB,
            TrimPatterns.SPIRE,
            TrimPatterns.WAYFINDER,
            TrimPatterns.SHAPER,
            TrimPatterns.SILENCE,
            TrimPatterns.RAISER,
            TrimPatterns.HOST,
            TrimPatterns.FLOW,
            TrimPatterns.BOLT
    );

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        for (ResourceKey<TrimPattern> vanillaPattern : VANILLA_PATTERNS) {
            Identifier newTrimIdentifier = mirageLoc("armadillo_"+vanillaPattern.identifier().getPath());

            TrimPattern armadilloPattern = new TrimPattern(
                    newTrimIdentifier,
                    Component.translatable(Util.makeDescriptionId("trim_pattern", vanillaPattern.identifier())),
                    false
            );

            context.register(toArmadillo(vanillaPattern), armadilloPattern);
        }
    }

    public static Identifier toArmadillo(Identifier identifier) {
        return mirageLoc("armadillo_"+identifier.getPath());
    }

    public static ResourceKey<TrimPattern> toArmadillo(ResourceKey<TrimPattern> key) {
        return ResourceKey.create(key.registryKey(), toArmadillo(key.identifier()));
    }
}
