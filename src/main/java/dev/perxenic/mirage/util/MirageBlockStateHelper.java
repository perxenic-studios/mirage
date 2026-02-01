package dev.perxenic.mirage.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.registries.DeferredBlock;

import static dev.perxenic.mirage.Mirage.mcLoc;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageBlockStateHelper {
    public static void brushableBlock(BlockStateProvider provider, Block block, String name) {
        provider.getVariantBuilder(block).forAllStates(state -> {
            int dusted = state.getValue(BlockStateProperties.DUSTED);

            return ConfiguredModel.builder().modelFile(provider.models().cubeAll(
                    name + "_" + dusted,
                    mirageLoc("block/" + name + "_" + dusted)
            )).build();
        });
        provider.simpleBlockItem(block, provider.models().getExistingFile(mirageLoc(name + "_0")));
    }

    public static void fadedTerracotta(BlockStateProvider provider, DeferredBlock<GlazedTerracottaBlock> block) {
        String name = block.getId().getPath();

        provider.getVariantBuilder(block.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            return ConfiguredModel.builder().modelFile(provider.models().singleTexture(
                    name,
                    mcLoc("block/template_glazed_terracotta"),
                    "pattern",
                    mirageLoc("block/" + name)
            )).rotationY((int) facing.toYRot()).build();
        });
        provider.simpleBlockItem(block.get(), provider.models().getExistingFile(mirageLoc(name)));
    }
}
