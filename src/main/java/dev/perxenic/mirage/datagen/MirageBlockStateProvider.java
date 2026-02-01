package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.registry.MirageBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static dev.perxenic.mirage.Mirage.MIRAGE_ID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class MirageBlockStateProvider extends BlockStateProvider {
    public MirageBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MIRAGE_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        fadedTerracotta(MirageBlocks.FADED_SUN_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_MODERN_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_CROSS_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_CRAWL_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_TARGET_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_POTION_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_FISH_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_FLOWER_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_SPOKE_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_PLANT_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_CREEP_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_GEO_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_FAN_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_BLADE_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_ARROW_TERRACOTTA);
        fadedTerracotta(MirageBlocks.FADED_LEAF_TERRACOTTA);

        brushableBlock(MirageBlocks.SUSPICIOUS_RED_SAND.get(), "suspicious_red_sand");
    }

    public void brushableBlock(Block block, String name) {
        getVariantBuilder(block).forAllStates(state -> {
            int dusted = state.getValue(BlockStateProperties.DUSTED);

            return ConfiguredModel.builder().modelFile(models().cubeAll(
                    name + "_" + dusted,
                    mirageLoc("block/" + name + "_" + dusted)
            )).build();
        });
        simpleBlockItem(block, models().getExistingFile(mirageLoc(name + "_0")));
    }

    public void fadedTerracotta(DeferredBlock<GlazedTerracottaBlock> block) {
        String name = block.getId().getPath();

        getVariantBuilder(block.get()).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            return ConfiguredModel.builder().modelFile(models().singleTexture(
                    name,
                    mcLoc("block/template_glazed_terracotta"),
                    "pattern",
                    mirageLoc("block/" + name)
            )).rotationY((int) facing.toYRot()).build();
        });
        simpleBlockItem(block.get(), models().getExistingFile(mirageLoc(name)));
    }
}
