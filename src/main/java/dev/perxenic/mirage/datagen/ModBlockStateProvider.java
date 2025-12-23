package dev.perxenic.mirage.datagen;

import dev.perxenic.mirage.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
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
        getVariantBuilder(ModBlocks.SUSPICIOUS_RED_SAND.get()).forAllStates(state -> {
            int dusted = state.getValue(BlockStateProperties.DUSTED);

            String stateName = "suspicious_red_sand_" + dusted;

            return ConfiguredModel.builder().modelFile(models().cubeAll(
                    stateName,
                    mirageLoc("block/" + stateName)
            )).build();
        });
        simpleBlockItem(ModBlocks.SUSPICIOUS_RED_SAND.get(), models().getExistingFile(mirageLoc("suspicious_red_sand_0")));
    }
}
