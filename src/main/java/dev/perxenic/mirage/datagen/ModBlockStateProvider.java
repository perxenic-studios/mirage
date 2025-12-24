package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrushableBlock;
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
}
