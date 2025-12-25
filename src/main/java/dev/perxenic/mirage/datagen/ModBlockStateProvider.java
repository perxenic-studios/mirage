package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.registry.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static dev.perxenic.mirage.Mirage.MODID;
import static dev.perxenic.mirage.Mirage.mirageLoc;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        fadedTerracotta(ModBlocks.WHITE_FADED_TERRACOTTA.get(), "white_faded_terracotta");
        fadedTerracotta(ModBlocks.LIGHT_GRAY_FADED_TERRACOTTA.get(), "light_gray_faded_terracotta");
        fadedTerracotta(ModBlocks.GRAY_FADED_TERRACOTTA.get(), "gray_faded_terracotta");
        fadedTerracotta(ModBlocks.BLACK_FADED_TERRACOTTA.get(), "black_faded_terracotta");
        fadedTerracotta(ModBlocks.BROWN_FADED_TERRACOTTA.get(), "brown_faded_terracotta");
        fadedTerracotta(ModBlocks.RED_FADED_TERRACOTTA.get(), "red_faded_terracotta");
        fadedTerracotta(ModBlocks.ORANGE_FADED_TERRACOTTA.get(), "orange_faded_terracotta");
        fadedTerracotta(ModBlocks.YELLOW_FADED_TERRACOTTA.get(), "yellow_faded_terracotta");
        fadedTerracotta(ModBlocks.LIME_FADED_TERRACOTTA.get(), "lime_faded_terracotta");
        fadedTerracotta(ModBlocks.GREEN_FADED_TERRACOTTA.get(), "green_faded_terracotta");
        fadedTerracotta(ModBlocks.CYAN_FADED_TERRACOTTA.get(), "cyan_faded_terracotta");
        fadedTerracotta(ModBlocks.LIGHT_BLUE_FADED_TERRACOTTA.get(), "light_blue_faded_terracotta");
        fadedTerracotta(ModBlocks.BLUE_FADED_TERRACOTTA.get(), "blue_faded_terracotta");
        fadedTerracotta(ModBlocks.PURPLE_FADED_TERRACOTTA.get(), "purple_faded_terracotta");
        fadedTerracotta(ModBlocks.MAGENTA_FADED_TERRACOTTA.get(), "magenta_faded_terracotta");
        fadedTerracotta(ModBlocks.PINK_FADED_TERRACOTTA.get(), "pink_faded_terracotta");

        brushableBlock(ModBlocks.SUSPICIOUS_RED_SAND.get(), "suspicious_red_sand");
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

    public void fadedTerracotta(Block block, String name) {
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            return ConfiguredModel.builder().modelFile(models().singleTexture(
                    name,
                    mcLoc("block/template_glazed_terracotta"),
                    "pattern",
                    mirageLoc("block/" + name)
            )).rotationY((int) facing.toYRot()).build();
        });
        simpleBlockItem(block, models().getExistingFile(mirageLoc(name)));
    }
}
