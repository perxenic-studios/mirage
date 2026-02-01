package dev.perxenic.mirage.util;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.registries.DeferredBlock;

import static dev.perxenic.mirage.Mirage.mcLoc;

public class MirageBlockStateHelper {
    public static void brushableBlock(BlockStateProvider provider, Block block, ResourceLocation location) {
        provider.getVariantBuilder(block).forAllStates(state -> {
            int dusted = state.getValue(BlockStateProperties.DUSTED);

            return ConfiguredModel.builder().modelFile(provider.models().cubeAll(
                    location.getPath() + "_" + dusted,
                    location.withPrefix("block/").withSuffix("_" + dusted)
            )).build();
        });
        provider.simpleBlockItem(block, provider.models().getExistingFile(location.withSuffix("_0")));
    }

    public static void fadedTerracotta(BlockStateProvider provider, DeferredBlock<GlazedTerracottaBlock> block) {
        ResourceLocation location = block.getId();

        provider.getVariantBuilder(block.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            return ConfiguredModel.builder().modelFile(provider.models().singleTexture(
                    location.getPath(),
                    mcLoc("block/template_glazed_terracotta"),
                    "pattern",
                    location.withPrefix("block/")
            )).rotationY((int) facing.toYRot()).build();
        });
        provider.simpleBlockItem(block.get(), provider.models().getExistingFile(location));
    }
}
